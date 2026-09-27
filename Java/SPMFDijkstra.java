import java.util.*;
public class SPMFDijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] g = new int[n][n];
        System.out.println("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) g[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        System.out.print("Enter source vertex: "); int s = sc.nextInt();
        int[] d = new int[n]; boolean[] done = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE); d[s] = 0;
        for (int c = 0; c < n; c++) {
            int u = -1;
            for (int i = 0; i < n; i++) if (!done[i] && (u == -1 || d[i] < d[u])) u = i;
            if (d[u] == Integer.MAX_VALUE) break;
            done[u] = true;
            for (int v = 0; v < n; v++) if (g[u][v] > 0 && d[u] + g[u][v] < d[v]) d[v] = d[u] + g[u][v];
        }
        System.out.println("Vertex : Distance");
        for (int i = 0; i < n; i++) System.out.println(i + " : " + (d[i] == Integer.MAX_VALUE ? "INF" : d[i]));
    }
}
