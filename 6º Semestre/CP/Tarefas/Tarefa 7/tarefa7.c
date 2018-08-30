/*
 * Aluno: Geovane Fonseca de Sousa Santos
 * Matrícula: 553237
 * Matéria: Coputação Paralela
 * Tarefa 07: Crivo de Eratóstenes Paralelo
 *
 *	* Local: 
 *		- Sequencial:
 *		2.34		user
 *		0.03		system
 *		0:02.38		elapsed
 *		99%		CPU
 *
 *	- Paralelo:
 *		5.89		user
 *		0.02		system
 *		0:01.52		elapsed
 *		387%		CPU
 *
 *	- Speedup: (2.38/1.52) = 1,565789474
 *
 *	* PUC:
 *	- Sequencial:
 *		real	0m4.035s
 *		user	0m3.957s
 *		sys	0m0.072s
 *
 *	- Paralelo:
 *		real	0m2.486s
 *		user	0m9.544s
 *		sys	0m0.084s
 *
 *	- Speedup: (4,035/2,486) = 1,6230893
 */

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
#include <omp.h>

int sieveOfEratosthenes(int n)
{
	// Create a boolean array "prime[0..n]" and initialize
	// all entries it as true. A value in prime[i] will
	// finally be false if i is Not a prime, else true.
	int primes = 0; 
	bool *prime = (bool*) malloc((n+1)*sizeof(bool));
	int sqrt_n = sqrt(n);

	memset(prime, true,(n+1)*sizeof(bool));

	// Uso do schedule para dividir as interações do loop de forma a primeira thread a aparecer será a primeira a ser realizada
	#pragma omp parallel for schedule(dynamic)
	for (int p=2; p <= sqrt_n; p++)
	{
		// If prime[p] is not changed, then it is a prime
		if (prime[p] == true)
		{
			// Update all multiples of p
			for (int i=p*2; i<=n; i += p)
				prime[i] = false;
		}
	}

	// Uso do reduction para tornar privado a variável primes e somar suas instâncias no fim de cada thread
	// count prime numbers
	#pragma omp parallel for reduction(+:primes)
	for (int p=2; p<=n; p++)
		if (prime[p])
			primes++;

	return(primes);
}

int main()
{
	int n = 100000000;
	printf("%d\n",sieveOfEratosthenes(n));
	return 0;
} 
