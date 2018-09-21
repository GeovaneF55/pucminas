#include <stdio.h>
#include <iostream>
#include <string.h>
#include <omp.h>

using namespace std;

const int b[4][3] = {
    {1,2,3},
    {4,5,6},
    {7,8,9},
    {10,11,12}
};  

void loopDiag(){
   for (int i = 4 - 1; i > 0; i--) {
       for (int j = 0, x = i; x <= 3 - 1; j++, x++) {
          cout << b[x][j] << " ";
       }
       cout << endl;
   }

   for (int i = 0; i <= 4 - 1; i++) {
        for (int j = 0, y = i; y <= 3 - 1; j++, y++) {
            cout << b[j][y] << " ";
        }
        cout << endl;
   }
}

int main()
{
    loopDiag();
    return 0;
}