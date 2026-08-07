# Experiment 2 — Divide and Conquer / Dynamic Programming

**Language:** Java &nbsp;&nbsp; **Compiler:** javac 26.0.1 &nbsp;&nbsp; **Runtime:** Java HotSpot 64-Bit Server VM (26.0.1+8-34)

| # | Program | File | Lines |
|---|---------|------|-------|
| 2.a | Matrix multiplication | `MatrixMultiplication.java` | 20 |
| 2.b | Chain matrix multiplication | `MatrixChainMultiplication.java` | 19 |
| 2.c | Strassen's matrix multiplication | `StrassenMatrixMultiplication.java` | 29 |
| 2.d | Maximum value contiguous subsequence | `MaxContiguousSubsequence.java` | 20 |

All four compile without error and every program is under 30 lines.

---

## 2.a Matrix multiplication

**Aim:** To multiply two matrices A (r1 x c1) and B (r2 x c2), given c1 = r2, using the standard triple-loop method.
**Complexity:** O(r1 · c1 · c2)

### Program

```java
import java.util.*;
public class MatrixMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter rows and columns of matrix A: ");
        int r1 = sc.nextInt(), c1 = sc.nextInt();
        System.out.print("Enter rows and columns of matrix B: ");
        int r2 = sc.nextInt(), c2 = sc.nextInt();
        if (c1 != r2) { System.out.println("Not possible: columns of A must equal rows of B"); return; }
        int[][] a = new int[r1][c1], b = new int[r2][c2], c = new int[r1][c2];
        System.out.println("Enter elements of matrix A: ");
        for (int i = 0; i < r1; i++) for (int j = 0; j < c1; j++) a[i][j] = sc.nextInt();
        System.out.println("Enter elements of matrix B: ");
        for (int i = 0; i < r2; i++) for (int j = 0; j < c2; j++) b[i][j] = sc.nextInt();
        for (int i = 0; i < r1; i++) for (int j = 0; j < c2; j++)
            for (int k = 0; k < c1; k++) c[i][j] += a[i][k] * b[k][j];
        System.out.println("Resultant matrix: ");
        for (int[] row : c) { for (int v : row) System.out.print(v + " "); System.out.println(); }
    }
}
```

### Sample run 1 — 2x3 multiplied by 3x2

```
Enter rows and columns of matrix A: 2 3
Enter rows and columns of matrix B: 3 2
Enter elements of matrix A: 
1 2 3
4 5 6
Enter elements of matrix B: 
7 8
9 10
11 12
Resultant matrix: 
58 64 
139 154 

```

### Sample run 2 — invalid order

```
Enter rows and columns of matrix A: 2 3
Enter rows and columns of matrix B: 2 2
Not possible: columns of A must equal rows of B

```

**Verification:** row 1 of the product = [1·7 + 2·9 + 3·11, 1·8 + 2·10 + 3·12] = [58, 64]; row 2 = [4·7 + 5·9 + 6·11, 4·8 + 5·10 + 6·12] = [139, 154]. Matches the output.

---

## 2.b Chain matrix multiplication

**Aim:** To find the minimum number of scalar multiplications needed to multiply a chain of n matrices, where matrix i has order p[i-1] x p[i], using dynamic programming.
**Complexity:** O(n^3) time, O(n^2) space

### Program

```java
import java.util.*;
public class MatrixChainMultiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of matrices: ");
        int n = sc.nextInt();
        int[] p = new int[n + 1];
        System.out.println("Enter the " + (n + 1) + " dimensions: ");
        for (int i = 0; i <= n; i++) p[i] = sc.nextInt();
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++)
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;   dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++)
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + p[i-1] * p[k] * p[j]);
            }
        System.out.println("Minimum number of multiplications is " + dp[1][n]);
    }
}
```

### Sample run 1 — 4 matrices: 5x4, 4x6, 6x2, 2x7

```
Enter the number of matrices: 4
Enter the 5 dimensions: 
5 4 6 2 7
Minimum number of multiplications is 158

```

### Sample run 2 — 3 matrices: 10x100, 100x5, 5x50

```
Enter the number of matrices: 3
Enter the 4 dimensions: 
10 100 5 50
Minimum number of multiplications is 7500

```

**Verification (run 2):** ((A1·A2)·A3) costs 10·100·5 + 10·5·50 = 5000 + 2500 = **7500**, while (A1·(A2·A3)) costs 100·5·50 + 10·100·50 = 25000 + 50000 = 75000. The DP correctly reports the minimum, 7500.

---

## 2.c Strassen's matrix multiplication

**Aim:** To multiply two square matrices using Strassen's divide-and-conquer method, which needs only 7 recursive multiplications per level instead of 8.
**Complexity:** O(n^2.81) versus O(n^3) for the ordinary method

The seven products and the four result quadrants:

```
P1 = A11 (B12 - B22)          C11 = P5 + P4 - P2 + P6
P2 = (A11 + A12) B22          C12 = P1 + P2
P3 = (A21 + A22) B11          C21 = P3 + P4
P4 = A22 (B21 - B11)          C22 = P5 + P1 - P3 - P7
P5 = (A11 + A22)(B11 + B22)
P6 = (A12 - A22)(B21 + B22)
P7 = (A11 - A21)(B11 + B12)
```

A matrix whose order is not a power of 2 is padded with zero rows and columns up to the next power of 2; the padding does not affect the top-left n x n block that is printed.

### Program

```java
import java.util.*;
public class StrassenMatrixMultiplication {
    static int[][] f(int[][] x, int a, int b, int[][] y, int c, int d, int s, int n) {  // block(x,a,b) + s*block(y,c,d)
        int[][] r = new int[n][n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) r[i][j] = x[a+i][b+j] + s * y[c+i][d+j];   return r;
    }
    static int[][] mul(int[][] a, int[][] b) {
        int n = a.length, m = n / 2;
        if (n == 1) return new int[][]{{a[0][0] * b[0][0]}};
        int[][] p1 = mul(f(a,0,0,a,0,0,0,m), f(b,0,m,b,m,m,-1,m)), p2 = mul(f(a,0,0,a,0,m,1,m), f(b,m,m,b,m,m,0,m));
        int[][] p3 = mul(f(a,m,0,a,m,m,1,m), f(b,0,0,b,0,0,0,m)),  p4 = mul(f(a,m,m,a,0,0,0,m), f(b,m,0,b,0,0,-1,m));
        int[][] p5 = mul(f(a,0,0,a,m,m,1,m), f(b,0,0,b,m,m,1,m)),  p6 = mul(f(a,0,m,a,m,m,-1,m), f(b,m,0,b,m,m,1,m));
        int[][] p7 = mul(f(a,0,0,a,m,0,-1,m), f(b,0,0,b,0,m,1,m)); int[][] c = new int[n][n];
        for (int i = 0; i < m; i++) for (int j = 0; j < m; j++) {
            c[i][j] = p5[i][j] + p4[i][j] - p2[i][j] + p6[i][j];   c[i][j+m] = p1[i][j] + p2[i][j];
            c[i+m][j] = p3[i][j] + p4[i][j];   c[i+m][j+m] = p5[i][j] + p1[i][j] - p3[i][j] - p7[i][j];
        }
        return c;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);   System.out.print("Enter the order of the square matrices: ");
        int n = sc.nextInt(), s = 1;   while (s < n) s *= 2;    // pad up to a power of 2
        int[][] a = new int[s][s], b = new int[s][s];
        System.out.println("Enter elements of matrix A and then matrix B: ");
        for (int[][] t : new int[][][]{a, b}) for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) t[i][j] = sc.nextInt();
        int[][] c = mul(a, b);   System.out.println("Resultant matrix: ");
        for (int i = 0; i < n; i++) { for (int j = 0; j < n; j++) System.out.print(c[i][j] + " "); System.out.println(); }
    }
}
```

### Sample run 1 — order 2

```
Enter the order of the square matrices: 2
Enter elements of matrix A and then matrix B: 
1 2
3 4
5 6
7 8
Resultant matrix: 
19 22 
43 50 

```

### Sample run 2 — order 4

```
Enter the order of the square matrices: 4
Enter elements of matrix A and then matrix B: 
1 2 3 4
5 6 7 8
9 1 2 3
4 5 6 7
1 0 2 1
0 1 1 2
2 1 0 1
1 2 1 0
Resultant matrix: 
11 13 8 8 
27 29 24 24 
16 9 22 13 
23 25 20 20 

```

### Sample run 3 — order 3 (padded internally to 4)

```
Enter the order of the square matrices: 3
Enter elements of matrix A and then matrix B: 
1 2 3
4 5 6
7 8 9
9 8 7
6 5 4
3 2 1
Resultant matrix: 
30 24 18 
84 69 54 
138 114 90 

```

**Verification (run 1):** [[1,2],[3,4]] x [[5,6],[7,8]] = [[19,22],[43,50]] by ordinary multiplication, which is what Strassen's method returns.

---

## 2.d Maximum value contiguous subsequence

**Aim:** To find the contiguous subsequence with the largest sum (Kadane's algorithm), and to print the subsequence itself.
**Complexity:** O(n) time, O(1) extra space

### Program

```java
import java.util.*;
public class MaxContiguousSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int best = a[0], cur = a[0], s = 0, e = 0, t = 0;
        for (int i = 1; i < n; i++) {
            if (cur + a[i] < a[i]) { cur = a[i];  t = i; } else cur += a[i];
            if (cur > best) { best = cur;  s = t;  e = i; }
        }
        System.out.println("Maximum sum is " + best);
        System.out.print("Subsequence is ");
        for (int i = s; i <= e; i++) System.out.print(a[i] + " ");
        System.out.println();
    }
}
```

### Sample run 1 — mixed signs

```
Enter the number of elements: 9
Enter the elements: 
-2 1 -3 4 -1 2 1 -5 4
Maximum sum is 6
Subsequence is 4 -1 2 1 

```

### Sample run 2 — all elements negative

```
Enter the number of elements: 3
Enter the elements: 
-5 -2 -8
Maximum sum is -2
Subsequence is -2 

```

**Verification (run 1):** the subsequence 4, -1, 2, 1 sums to 6; no other contiguous block of the input reaches a higher total. In run 2 every element is negative, so the answer is the single largest element, -2, rather than an empty sum of 0.

---

## Note on the transcripts

Every sample run above is real output from the compiled classes. The programs were driven from input files through a small wrapper that echoes each byte as the program reads it, so the typed values appear interleaved with the prompts exactly as they do when the values are entered at the keyboard.
