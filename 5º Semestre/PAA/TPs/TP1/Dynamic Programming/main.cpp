// SPOJ - MENU
#include <iostream>
using namespace std;

int main ()
{
    cout.setf(ios::fixed,ios::floatfield);
    cout.precision(1);
    int k,n,m;
    int cost[51];
    int benefit[51];
    double dp[22][51][101];
    int remember1[22][51][101];
    int remember2[22][51][101];
    while (true)
    {
        cin >> k >> n >> m;
        if (k == 0 && n == 0 && m == 0) break;
        for (int i = 1; i <= n; i++)
            cin >> cost[i] >> benefit[i];

        // Initialization
        for (int i = 0; i <= k; i++)
            for (int j = 0; j <= n; j++)
                for (int z = 0; z <= m; z++)
                {
                    if (i == 0)
                        dp[i][j][z] = 0;
                    else
                        dp[i][j][z] = -1;
                    remember1[i][j][z] = 0;
                    remember2[i][j][z] = 0;
                }

        // Dynamic Programming
        for (int i = 1; i <= k; i++)
            for (int j = 1; j <= n; j++)
                for (int z = 0; z <= m; z++)
                {
                    for (int h = 1; h <= i; h++)
                    {
                        if (z >= h*cost[j])
                        {
                        for (int x = 1; x <= n; x++)
                            if (x != j && dp[i-h][x][z-h*cost[j]] != -1)
                            {
                                double extraBenefit = benefit[j];
                                if (h >= 2){
                                  extraBenefit = 1.5 * benefit[j];
				}
                                if (dp[i-h][x][z-h*cost[j]] + extraBenefit > dp[i][j][z] || (dp[i-h][x][z-h*cost[j]] + extraBenefit == dp[i][j][z] && (remember1[i][j][z] == 0 || remember2[i][j][z] * cost[remember1[i][j][z]] > h*cost[k])))
                                {
                                    dp[i][j][z] = dp[i-h][x][z-h*cost[j]] + extraBenefit;
                                    remember1[i][j][z] = x;
                                    remember2[i][j][z] = h;
                                }
                            }
                        }
                    }
                }

        // Print out results
        int tempIndex = 1;
        for (int j = 2; j <= n; j++)
            if (dp[k][tempIndex][m] < dp[k][j][m])
                tempIndex = j;

        if (dp[k][tempIndex][m] == -1)
            cout << "0.0\n";
        else
        {
            cout << dp[k][tempIndex][m] << "\n";
            // Find out the menu
            int tempK = k;
            int tempM = m;
            int tg1, tg2;
            while (tempK > 0)
            {
              tg1 = remember1[tempK][tempIndex][tempM];
              tg2 = remember2[tempK][tempIndex][tempM];
              for (int i = 0 ; i < tg2 ; i++)
              {
                  cout << tempIndex;
                  if (i != tg2 - 1)
                     cout << " ";
              }
              tempK = tempK - tg2;
              tempM = tempM - tg2 * cost[tempIndex];
              tempIndex = tg1;
              if (tempK > 0)
                 cout << " ";
            }
        }
        cout << "\n";
    }
}
