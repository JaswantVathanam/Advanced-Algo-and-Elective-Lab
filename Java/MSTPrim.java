import java.util.*;
public class MSTPrim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] g = new int[n][n];
        System.out.println("Enter edges (u v w):");
        for (int i = 0; i < e; i++) { int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt(); g[u][v] = g[v][u] = w; }
        int[] key = new int[n], par = new int[n]; boolean[] in = new boolean[n];
        Arrays.fill(key, Integer.MAX_VALUE); key[0] = 0; par[0] = -1;
        int cost = 0;
        System.out.println("MST Edges:");
        for (int c = 0; c < n; c++) {
            int u = -1;
            for (int i = 0; i < n; i++) if (!in[i] && (u == -1 || key[i] < key[u])) u = i;
            in[u] = true; cost += key[u];
            if (par[u] != -1) System.out.println(par[u] + " - " + u + " : " + key[u]);
            for (int v = 0; v < n; v++) if (g[u][v] > 0 && !in[v] && g[u][v] < key[v]) { key[v] = g[u][v]; par[v] = u; }
        }
        System.out.println("Total cost of MST: " + cost);
    }
}
