# Experiment 9 – String Matching Algorithms (C#)

**Aim:** Implement string matching algorithms.

Sample input used for all three programs:

- **Text:** `AABAACAADAABAABA` (length 16)
- **Pattern:** `AABA` (length 4)
- Expected matches at index **0, 9, 12** (0-based)

---

## 1. StrMatNaive.cs – Naive String Matching Algorithm

### Program

```csharp
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
```

### Input

```
AABAACAADAABAABA
AABA
```

### Output

```
Enter text: AABAACAADAABAABA
Enter pattern: AABA
Pattern found at index 0
Pattern found at index 9
Pattern found at index 12
Total occurrences: 3
```

---

## 2. StrMatRabinKarp.cs – Rabin-Karp Algorithm

### Program

```csharp
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
```

### Input

```
AABAACAADAABAABA
AABA
```

### Output

```
Enter text: AABAACAADAABAABA
Enter pattern: AABA
Pattern found at index 0
Pattern found at index 9
Pattern found at index 12
Total occurrences: 3
```

---

## 3. StrMatKMP.cs – Knuth-Morris-Pratt (KMP) Algorithm

### Program

```csharp
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
```

### Input

```
AABAACAADAABAABA
AABA
```

### Output

```
Enter text: AABAACAADAABAABA
Enter pattern: AABA
LPS array: [0, 1, 0, 1]
Pattern found at index 0
Pattern found at index 9
Pattern found at index 12
Total occurrences: 3
```
