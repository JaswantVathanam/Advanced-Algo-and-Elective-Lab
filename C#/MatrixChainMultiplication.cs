using System;

int[] dimensions = { 10, 30, 5, 60 };
int n = dimensions.Length - 1;
int[,] dp = new int[n + 1, n + 1];

for (int length = 2; length <= n; length++)
    for (int i = 1; i <= n - length + 1; i++)
    {
        int j = i + length - 1;
        dp[i, j] = int.MaxValue;
        for (int k = i; k < j; k++)
            dp[i, j] = Math.Min(dp[i, j], dp[i, k] + dp[k + 1, j] + dimensions[i - 1] * dimensions[k] * dimensions[j]);
    }

Console.WriteLine($"Matrix chain cost: {dp[1, n]}");
