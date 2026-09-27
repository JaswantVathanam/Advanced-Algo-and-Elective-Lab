using System;
class StrMatNaive {
    static void Main() {
        Console.Write("Enter text: "); string t = Console.ReadLine();
        Console.Write("Enter pattern: "); string p = Console.ReadLine();
        int n = t.Length, m = p.Length, count = 0;
        for (int s = 0; s <= n - m; s++) {
            int j = 0;
            while (j < m && t[s + j] == p[j]) j++;
            if (j == m) { Console.WriteLine("Pattern found at index " + s); count++; }
        }
        Console.WriteLine("Total occurrences: " + count);
    }
}
