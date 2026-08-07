using System;

var weights = new[] { 2, 3, 4, 5 };
var values = new[] { 3, 4, 5, 6 };
int capacity = 5;
int[,] dp = new int[weights.Length + 1, capacity + 1];

for (int i = 1; i <= weights.Length; i++)
    for (int c = 0; c <= capacity; c++)
        dp[i, c] = weights[i - 1] <= c
            ? Math.Max(dp[i - 1, c], dp[i - 1, c - weights[i - 1]] + values[i - 1])
            : dp[i - 1, c];

Console.WriteLine($"Knapsack 01 profit: {dp[weights.Length, capacity]}");
