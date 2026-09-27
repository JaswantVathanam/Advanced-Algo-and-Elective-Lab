using System;
class StrMatKMP {
    static void Main() {
        Console.Write("Enter text: "); string t = Console.ReadLine();
        Console.Write("Enter pattern: "); string p = Console.ReadLine();
        int n = t.Length, m = p.Length, count = 0;
        int[] lps = new int[m];
        for (int i = 1, k = 0; i < m; i++) {
            while (k > 0 && p[i] != p[k]) k = lps[k - 1];
            if (p[i] == p[k]) k++;
            lps[i] = k;
        }
        Console.WriteLine("LPS array: [" + string.Join(", ", lps) + "]");
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && t[i] != p[j]) j = lps[j - 1];
            if (t[i] == p[j]) j++;
            if (j == m) { Console.WriteLine("Pattern found at index " + (i - m + 1)); count++; j = lps[j - 1]; }
        }
        Console.WriteLine("Total occurrences: " + count);
    }
}
