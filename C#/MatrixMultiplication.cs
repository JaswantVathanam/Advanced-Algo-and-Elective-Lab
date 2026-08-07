using System;

int[,] a = { { 1, 2, 3 }, { 4, 5, 6 } };
int[,] b = { { 7, 8 }, { 9, 10 }, { 11, 12 } };
int r1 = 2, c1 = 3, c2 = 2;
int[,] c = new int[r1, c2];

for (int i = 0; i < r1; i++)
    for (int j = 0; j < c2; j++)
        for (int k = 0; k < c1; k++)
            c[i, j] += a[i, k] * b[k, j];

Console.WriteLine("Matrix multiplication result:");
for (int i = 0; i < r1; i++)
{
    for (int j = 0; j < c2; j++) Console.Write(c[i, j] + " ");
    Console.WriteLine();
}
