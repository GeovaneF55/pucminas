/*

Alunos: Geovane Fonseca de Sousa Santos
  Luigi Soares Sorrentino
	
Matéria: Coputação Paralela
Tarefa 17: Maior Sequência Comum de DNA

SEQUENCIAL:

    Length of Longest Common Substring is 14

    real	0m3.770s
    user	0m3.333s
    sys	0m0.436s

PARALELO:

    real	0m1.770s
    user	0m5.576s
    sys	0m0.667s

    Performance:

    6297,752653      task-clock (msec)         #    3,563 CPUs utilized          
               162      context-switches          #    0,026 K/sec                  
                 2      cpu-migrations            #    0,000 K/sec                  
           434.885      page-faults               #    0,069 M/sec                  
    18.164.045.690      cycles                    #    2,884 GHz                      (49,89%)
    23.323.284.792      instructions              #    1,28  insn per cycle           (62,37%)
     3.525.899.725      branches                  #  559,866 M/sec                    (62,53%)
       107.941.085      branch-misses             #    3,06% of all branches          (62,63%)
    10.653.342.578      L1-dcache-loads           # 1691,610 M/sec                    (62,60%)
        89.129.813      L1-dcache-load-misses     #    0,84% of all L1-dcache hits    (62,55%)
         2.218.391      LLC-loads                 #    0,352 M/sec                    (49,94%)
           768.502      LLC-load-misses           #   34,64% of all LL-cache hits     (49,87%)

    Speedup: (tempo Seq./ tempo Par.) = (3.770/1.770) = 2.1299

PARALELO (percorrendo a matriz pela diagonal):

    real	0m2.710s
    user	0m9.800s
    sys	0m0.768s

    Performance:

    10623,372886      task-clock (msec)         #    3,895 CPUs utilized          
               263      context-switches          #    0,025 K/sec                  
                 0      cpu-migrations            #    0,000 K/sec                  
           431.153      page-faults               #    0,041 M/sec                  
    30.624.172.337      cycles                    #    2,883 GHz                      (49,88%)
    25.502.399.698      instructions              #    0,83  insn per cycle           (62,39%)
     3.950.670.924      branches                  #  371,885 M/sec                    (62,42%)
       110.725.895      branch-misses             #    2,80% of all branches          (62,48%)
    12.410.567.935      L1-dcache-loads           # 1168,232 M/sec                    (62,59%)
       802.334.762      L1-dcache-load-misses     #    6,46% of all L1-dcache hits    (62,63%)
       330.154.321      LLC-loads                 #   31,078 M/sec                    (50,02%)
         2.872.820      LLC-load-misses           #    0,87% of all LL-cache hits     (49,97%)

    Speedup: (tempo Seq./ tempo Par.) = (3.770/2.710) = 1.3911

ANÁLISE:

    É possível perceber que existe uma quantidade muito considerável de carregamentos da cache usando o método paralelo
    percorrendo a matriz em diagonal em comparação com percorrendo a matriz de forma normal.

    Comparação:
    
        330.154.321     LLC-loads - PARALELO (percorrendo a matriz pela diagonal)
        2.218.391       LLC-loads - PARALELO

    Também é possível perceber que na cache-L1 onde não há tanta diferença nos carregamentos, a quantidade de misses é bem
    maior no método paralelo percorrendo a matriz em diagonal em comparação com percorrendo a matriz de forma normal.

    Comparação:

        802.334.762      L1-dcache-load-misses - PARALELO (percorrendo a matriz pela diagonal)
        89.129.813       L1-dcache-load-misses - PARALELO

CONCLUSÃO:

    Mesmo com a melhora no uso das threads, ao percorrer a matriz em diagonal há uma perda considerável de tempo devido
    a localidade espacial não estar sendo bem explorada, fazendo com que seja preciso acesso aos dados em níveis mais 
    altos de memória algo mais constante.

*/

/* Dynamic Programming solution to find length of the 
   longest common substring 
   Adapted from http://www.geeksforgeeks.org/longest-common-substring/
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <omp.h>

// Read input files
char* readFile(char* filename, int* size)
{
   char* buffer = NULL;
   *size = 0;

   /* Open your_file in read-only mode */
   FILE *fp = fopen(filename, "r");

   /* Get the buffer size */
   fseek(fp, 0, SEEK_END); /* Go to end of file */
   *size = ftell(fp); /* How many bytes did we pass ? */

   /* Set position of stream to the beginning */
   rewind(fp);

   /* Allocate the buffer (no need to initialize it with calloc) */
   buffer = malloc((*size + 1) * sizeof(*buffer)); /* size + 1 byte for the \0 */

   /* Read the file into the buffer */
   int err = fread(buffer, *size, 1, fp); /* Read 1 chunk of size bytes from fp into buffer */

   /* NULL-terminate the buffer */
   buffer[*size] = '\0';

   /* Print it ! */
   //   printf("%s\n", buffer);

   return(buffer);
}   

// A utility function to find maximum of two integers
int max(int a, int b)
{   return (a > b)? a : b; }

// A utility function to find minimum of two integers
int min(int a, int b)
{   return (a < b)? a : b; }

// A utility function to find minimum of three integers
int min3(int a, int b, int c)
{   return min(min(a, b), c); }

/* Returns length of longest common substring of X[0..m-1] 
   and Y[0..n-1] */
int LCSubStrDiag(char *x, char *y, int m, int n)
{
  // Create a table to store lengths of longest common suffixes of
  // substrings.   Notethat LCSuff[i][j] contains length of longest
  // common suffix of X[0..i-1] and Y[0..j-1]. The first row and
  // first column entries have no logical meaning, they are used only
  // for simplicity of program
  int **LCSuff = (int**) malloc((m+1) * sizeof(int*));
  for(int i =0; i < m+1; i++)
    LCSuff[i] = (int*) malloc((n+1) * sizeof(int));

  int result = 0;  // To store length of the longest common substring

  /* Following steps build LCSuff[m+1][n+1] in bottom up fashion. */
  #pragma omp parallel for schedule(dynamic, 100) reduction(max:result)
  for(int i=0; i<=m; i++){
    int linha = i;
    int coluna = 0;

    while(linha<=m && coluna<=n){
        if (linha == 0 || coluna == 0)
          LCSuff[linha][coluna] = 0;
        else if (x[linha-1] == y[coluna-1]) {
          LCSuff[linha][coluna] = LCSuff[linha-1][coluna-1] + 1;
          result = max(result, LCSuff[linha][coluna]);
        }
        else LCSuff[linha][coluna] = 0;
        linha++;
        coluna++;
    }
  }

  #pragma omp parallel for schedule(dynamic, 100) reduction(max:result)
  for(int j=0; j<=n; j++){
    int linha = 0;
    int coluna = j;

    while(linha<=m && coluna<=n){
        if (linha == 0 || coluna == 0)
          LCSuff[linha][coluna] = 0;
        else if (x[linha-1] == y[coluna-1]) {
          LCSuff[linha][coluna] = LCSuff[linha-1][coluna-1] + 1;
          result = max(result, LCSuff[linha][coluna]);
        }
        else LCSuff[linha][coluna] = 0;
        linha++;
        coluna++;
    }
  }

  return result;
}

/* Returns length of longest common substring of X[0..m-1] 
   and Y[0..n-1] */
int LCSubStr(char *x, char *y, int m, int n)
{
  // Create a table to store lengths of longest common suffixes of
  // substrings.   Notethat LCSuff[i][j] contains length of longest
  // common suffix of X[0..i-1] and Y[0..j-1]. The first row and
  // first column entries have no logical meaning, they are used only
  // for simplicity of program
  int **LCSuff = (int**) malloc((m+1) * sizeof(int*));
  for(int i =0; i < m+1; i++)
    LCSuff[i] = (int*) malloc((n+1) * sizeof(int));

  int result = 0;  // To store length of the longest common substring

  /* Following steps build LCSuff[m+1][n+1] in bottom up fashion. */
  #pragma omp parallel for schedule(dynamic, 100) reduction(max:result)
  for (int i=0; i<=m; i++) {
    for (int j=0; j<=n; j++) {
      if (i == 0 || j == 0)
        LCSuff[i][j] = 0;
      else if (x[i-1] == y[j-1]) {
        LCSuff[i][j] = LCSuff[i-1][j-1] + 1;
        result = max(result, LCSuff[i][j]);
      }
      else LCSuff[i][j] = 0;
    }
  }

  return result;
}

/* Driver program to test above function */
int main()
{
  int m, n;
  char* x = readFile("seqA.txt",&m);
  char* y = readFile("seqB.txt",&n);
  //printf("\nLength of Longest Common Substring is %d\n",LCSubStrDiag(x, y, m, n));
  printf("\nLength of Longest Common Substring is %d\n",LCSubStr(x, y, m, n));
  return 0;
}
