/**
 * Nome: Geovane Fonseca de sousa Santos
 **/

#include <stdio.h>
#include <stdlib.h>
#include <omp.h>
#include <time.h>

#define n 20
#define boolean short
#define true 1
#define false 0

int a[n],
    b[n],
    c[2*n];

void intVets(){
	for(int i=0; i<n; i++){
		a[i] = rand()%100;
		b[i] = rand()%100;
	}
}

void selectionSort(int *x) {
	for (int i = 0; i < (n - 1); i++) {
		int indice = i;
		for (int j = (i + 1); j < n; j++){
			if (x[indice] > x[j]){
				indice = j;
			}
		}
		int auxiliar = x[indice];
		x[indice] = x[i];
		x[i] = auxiliar;
	}
}

void merge(){
	int i=0,
	    j=0,
	    z=0;
	boolean fa = false, fb = false;

	while(z<=(2*n) && (fa == false && fb == false)){
		if(a[i]<b[j]){
			c[z++] = a[i++];
		} else{
			c[z++] = b[j++];
		}

		if(i >= n){
			fa = true;
		}
		if(j >= n){
			fb = true;
		}
	}

	if(fa == true){
		while(j < n){
			c[z++] = a[i++]; 
		}
	} else{
		while(i < n){
			c[z++] = b[i++];

		}
	}
}

int main(){
	srand(time(NULL));
	intVets();

	selectionSort(a);

	for(int i=0; i<n; i++){
		printf("\na[%d] = %d", i, a[i]);
	}
	printf("\n");

	selectionSort(b);

	for(int j=0; j<n; j++){
		printf("\nb[%d] = %d", j, b[j]);
	}
	printf("\n");

	merge();

	for(int k=0; k<2*n; k++){
		printf("\nc[%d] = %d", k, c[k]);
	}
	printf("\n");
}
