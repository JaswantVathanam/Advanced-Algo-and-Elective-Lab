using System;

int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
int best = arr[0], current = arr[0];

for (int i = 1; i < arr.Length; i++)
{
    current = Math.Max(arr[i], current + arr[i]);
    best = Math.Max(best, current);
}

Console.WriteLine($"Maximum contiguous sum: {best}");
