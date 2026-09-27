# Experiment 9 – String Matching Algorithms (Java)

**Aim:** Implement string matching algorithms.

Sample input used for all three programs:

- **Text:** `AABAACAADAABAABA` (length 16)
- **Pattern:** `AABA` (length 4)
- Expected matches at index **0, 9, 12** (0-based)

---

## 1. StrMatNaive.java – Naive String Matching Algorithm

### Program

```java
import java.util.*;
public class StrMatNaive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: "); String t = sc.nextLine();
        System.out.print("Enter pattern: "); String p = sc.nextLine();
        int n = t.length(), m = p.length(), count = 0;
        for (int s = 0; s <= n - m; s++) {
            int j = 0;
            while (j < m && t.charAt(s + j) == p.charAt(j)) j++;
            if (j == m) { System.out.println("Pattern found at index " + s); count++; }
        }
        System.out.println("Total occurrences: " + count);
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

## 2. StrMatRabinKarp.java – Rabin-Karp Algorithm

### Program

```java
import java.util.*;
public class StrMatRabinKarp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: "); String t = sc.nextLine();
        System.out.print("Enter pattern: "); String p = sc.nextLine();
        int n = t.length(), m = p.length(), d = 256, q = 101, h = 1, ph = 0, th = 0, count = 0;
        for (int i = 0; i < m - 1; i++) h = (h * d) % q;
        for (int i = 0; i < m; i++) { ph = (d * ph + p.charAt(i)) % q; th = (d * th + t.charAt(i)) % q; }
        for (int s = 0; s <= n - m; s++) {
            if (ph == th && t.substring(s, s + m).equals(p)) { System.out.println("Pattern found at index " + s); count++; }
            if (s < n - m) th = ((d * (th - t.charAt(s) * h) + t.charAt(s + m)) % q + q) % q;
        }
        System.out.println("Total occurrences: " + count);
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

## 3. StrMatKMP.java – Knuth-Morris-Pratt (KMP) Algorithm

### Program

```java
import java.util.*;
public class StrMatKMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: "); String t = sc.nextLine();
        System.out.print("Enter pattern: "); String p = sc.nextLine();
        int n = t.length(), m = p.length(), count = 0;
        int[] lps = new int[m];
        for (int i = 1, k = 0; i < m; i++) {
            while (k > 0 && p.charAt(i) != p.charAt(k)) k = lps[k - 1];
            if (p.charAt(i) == p.charAt(k)) k++;
            lps[i] = k;
        }
        System.out.println("LPS array: " + Arrays.toString(lps));
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) j = lps[j - 1];
            if (t.charAt(i) == p.charAt(j)) j++;
            if (j == m) { System.out.println("Pattern found at index " + (i - m + 1)); count++; j = lps[j - 1]; }
        }
        System.out.println("Total occurrences: " + count);
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
