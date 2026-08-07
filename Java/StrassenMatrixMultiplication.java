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
