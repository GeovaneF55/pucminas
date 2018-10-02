#include <stdio.h>
#include <stdlib.h>

int const SIZE = 100;

int main() {
    int a[SIZE], b[SIZE], c[SIZE];

    for(int i=0; i<SIZE; i++) {
        a[i] = i;
        b[i] = i;
    }

    for (int i=0; i<SIZE; i++) {
        c[i] = a[i] + b[i];
    }

    return 0;
}