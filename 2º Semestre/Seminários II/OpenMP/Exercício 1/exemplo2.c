#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
int main(){
	int n = 4, i;
	int a[] = {1, 2, 3, 4},
	    b[] = {2, 3, 4, 5},
	    c[] = {5, 6, 7, 8};

	#pragma omp parallel private(i)
	{
		for(int i=0; i<n; i++){
			a[i] = b[i] + c[i];
			printf("\na[%d] = %d", i, a[i]);
		}
		printf("\n");
	}
}
