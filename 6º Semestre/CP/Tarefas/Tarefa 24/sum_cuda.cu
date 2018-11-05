/*
	Aluno: Geovane Fonseca de Sousa Santos
	Matrícula: 553237
	Matéria: Coputação Paralela
	Tarefa 24: Somatório em CUDA

	Sequencial:

	real	0m0.300s
	user	0m0.068s
	sys	0m0.229s

	Paralelo para multicore em OpenMP:

	real	0m0.148s
	user	0m0.094s
	sys	0m0.408s

	Paralelo para GPU com OpenMP:

	real	0m1.130s
	user	0m0.099s
	sys	0m1.285s

	CUDA:

	real	0m1.608s
	user	0m0.535s
	sys	0m0.960s

	==5588== Profiling application: ./sum_cuda
	==5588== Profiling result:
	Time(%)      Time     Calls       Avg       Min       Max  Name
	 95.54%  468.59ms         1  468.59ms  468.59ms  468.59ms  [CUDA memcpy HtoD]
	  4.39%  21.529ms         1  21.529ms  21.529ms  21.529ms  sum_cuda(double*, double*, int)
	  0.07%  362.22us         1  362.22us  362.22us  362.22us  [CUDA memcpy DtoH]

	==5588== API calls:
	Time(%)      Time     Calls       Avg       Min       Max  Name
	 62.79%  492.67ms         2  246.33ms  25.263ms  467.41ms  cudaMemcpy
	 36.71%  288.05ms         2  144.02ms  32.580us  288.01ms  cudaMalloc
	  0.42%  3.2584ms         2  1.6292ms  29.610us  3.2288ms  cudaFree
	  0.06%  484.80us        90  5.3860us     292ns  206.45us  cuDeviceGetAttribute
	  0.01%  88.564us         1  88.564us  88.564us  88.564us  cuDeviceTotalMem
	  0.01%  67.905us         1  67.905us  67.905us  67.905us  cuDeviceGetName
	  0.01%  52.107us         1  52.107us  52.107us  52.107us  cudaLaunch
	  0.00%  9.7980us         3  3.2660us     371ns  7.6310us  cudaSetupArgument
	  0.00%  2.9470us         2  1.4730us     964ns  1.9830us  cuDeviceGetCount
	  0.00%  1.7250us         1  1.7250us  1.7250us  1.7250us  cudaConfigureCall
	  0.00%  1.1440us         2     572ns     544ns     600ns  cuDeviceGet

	RESULTADOS:

	* O resultado sequencial é considerado o resultado base para fazer os cálculos

	* O resultado paralelo para multicore em OpenMP teve um speedup de (0.300/0.148) = 2,027

	* O resultado paralelo para GPU com OpenMP não obteve speedup em relação ao sequencial devido ao overhead de enviar os dados para a GPU

	* O resultado no CUDA foi pior ainda, também por causa do overhead de enviar os dados para a GPU e por alocá-los
*/

#include <stdio.h>
#include <stdlib.h>

__global__ void sum_cuda(double* a, double *s, int width) {
  int t = threadIdx.x;
  int b = blockIdx.x*blockDim.x;

  __shared__ double o[1024];  

  if(b+t < width)
    o[t] = a[b+t];

  __syncthreads();
  
  int i;
  for(i = blockDim.x/2; i > 0; i /= 2) {
    if(t < i && b+t+i < width)
      o[t] += o[t+i];
    
  	__syncthreads();
  }

  if(t == 0)
    s[blockIdx.x] = o[0];  
} 

int main()
{
  int width = 40000000;
  int size = width * sizeof(double);

  int block_size = 1024;
  int num_blocks = (width-1)/block_size+1;
  int s_size = (num_blocks * sizeof(double));  
 
  double *a = (double*) malloc (size);
  double *s = (double*) malloc (s_size);

  for(int i = 0; i < width; i++)
    a[i] = i;

  double *d_a, *d_s;

  // alocação e cópia dos dados
  cudaMalloc((void **) &d_a, size);
  cudaMemcpy(d_a, a, size, cudaMemcpyHostToDevice);

  cudaMalloc((void **) &d_s, s_size);

  // definição do número de blocos e threads
  dim3 dimGrid(num_blocks,1,1);
  dim3 dimBlock(block_size,1,1);

  // chamada do kernel
  sum_cuda<<<dimGrid,dimBlock>>>(d_a, d_s, width);

  // cópia dos resultados para o host
  cudaMemcpy(s, d_s, s_size, cudaMemcpyDeviceToHost);

  // soma das reduções parciais
  for(int i = 1; i < num_blocks; i++) 
    s[0] += s[i];

  printf("\nSum = %f\n",s[0]);
  
  cudaFree(d_a);
  cudaFree(d_s);
}
