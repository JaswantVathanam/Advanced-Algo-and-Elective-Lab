using System;

class MergeSort {
    static void Merge(int[] a, int l, int m, int r) {
        int[] b = new int[r - l + 1]; int i = l, j = m + 1, k = 0;
        while (i <= m && j <= r) b[k++] = a[i] <= a[j] ? a[i++] : a[j++];
        while (i <= m) b[k++] = a[i++];
        while (j <= r) b[k++] = a[j++];
        for (int x = 0; x < k; x++) a[l + x] = b[x];
    }
    static void Sort(int[] a, int l, int r) {
        if (l < r) { int m = l + (r - l) / 2; Sort(a, l, m); Sort(a, m + 1, r); Merge(a, l, m, r); }
    }
    static void Main() {
        Console.Write("Enter size: ");
        int n = int.Parse(Console.ReadLine());
        int[] a = new int[n];
        Console.WriteLine("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) a[i] = int.Parse(Console.ReadLine());
        Console.WriteLine("Original array: " + string.Join(" ", a));
        Sort(a, 0, n - 1);
        Console.WriteLine("Sorted array: " + string.Join(" ", a));
    }
}
