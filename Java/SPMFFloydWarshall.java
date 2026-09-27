import java.util.*;
public class SPMFFloydWarshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); final int INF = 99999;
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) { Arrays.fill(d[i], INF); d[i][i] = 0; }
        System.out.println("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) d[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (d[i][k] + d[k][j] < d[i][j]) d[i][j] = d[i][k] + d[k][j];
        System.out.println("All-pairs shortest distances:");
        for (int[] r : d) {
            for (int x : r) System.out.print((x >= INF ? "INF" : x) + "\t");
            System.out.println();
        }
    }
}
