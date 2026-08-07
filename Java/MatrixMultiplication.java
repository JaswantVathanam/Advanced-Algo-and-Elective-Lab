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
