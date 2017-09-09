#include <stdio.h>
#include <stdlib.h>
#include <omp.h>

int main(){
	//Fork
	#pragma omp parallel
	{
		printf("Hello World\n");
	}
	//Join
}
