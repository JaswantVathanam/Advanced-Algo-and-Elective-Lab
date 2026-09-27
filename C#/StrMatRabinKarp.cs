using System;
class StrMatRabinKarp {
    static void Main() {
        Console.Write("Enter text: "); string t = Console.ReadLine();
        Console.Write("Enter pattern: "); string p = Console.ReadLine();
        int n = t.Length, m = p.Length, d = 256, q = 101, h = 1, ph = 0, th = 0, count = 0;
        for (int i = 0; i < m - 1; i++) h = (h * d) % q;
        for (int i = 0; i < m; i++) { ph = (d * ph + p[i]) % q; th = (d * th + t[i]) % q; }
        for (int s = 0; s <= n - m; s++) {
            if (ph == th && t.Substring(s, m) == p) { Console.WriteLine("Pattern found at index " + s); count++; }
            if (s < n - m) th = ((d * (th - t[s] * h) + t[s + m]) % q + q) % q;
        }
        Console.WriteLine("Total occurrences: " + count);
    }
}
