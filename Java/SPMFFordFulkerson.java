import java.util.*;
public class SPMFFordFulkerson {
    static int n; static int[][] c;
    static int dfs(int u, int t, int f, boolean[] vis) {
        if (u == t) return f;
        vis[u] = true;
        for (int v = 0; v < n; v++)
            if (!vis[v] && c[u][v] > 0) {
                int r = dfs(v, t, Math.min(f, c[u][v]), vis);
                if (r > 0) { c[u][v] -= r; c[v][u] += r; return r; }
            }
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        c = new int[n][n];
        System.out.println("Enter edges (u v capacity):");
        for (int i = 0; i < e; i++) c[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        System.out.print("Enter source and sink: "); int s = sc.nextInt(), t = sc.nextInt();
        int flow = 0, f;
        while ((f = dfs(s, t, Integer.MAX_VALUE, new boolean[n])) > 0) flow += f;
        System.out.println("Maximum flow: " + flow);
    }
}
