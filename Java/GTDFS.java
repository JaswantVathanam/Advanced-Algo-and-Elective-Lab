import java.util.*;
public class GTDFS {
    static void dfs(List<Integer>[] g, int u, boolean[] v) {
        v[u] = true; System.out.print(u + " ");
        for (int w : g[u]) if (!v[w]) dfs(g, w, v);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        List<Integer>[] g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) { int u = sc.nextInt(), v = sc.nextInt(); g[u].add(v); g[v].add(u); }
        System.out.print("Enter starting vertex: "); int s = sc.nextInt();
        System.out.print("DFS: "); dfs(g, s, new boolean[n]); System.out.println();
    }
}
