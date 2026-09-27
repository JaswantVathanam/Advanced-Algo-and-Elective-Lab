import java.util.*;
public class SPMFBellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); final int INF = Integer.MAX_VALUE;
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] ed = new int[e][3];
        System.out.println("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) for (int j = 0; j < 3; j++) ed[i][j] = sc.nextInt();
        System.out.print("Enter source vertex: "); int s = sc.nextInt();
        int[] d = new int[n]; Arrays.fill(d, INF); d[s] = 0;
        for (int i = 1; i < n; i++)
            for (int[] x : ed) if (d[x[0]] != INF && d[x[0]] + x[2] < d[x[1]]) d[x[1]] = d[x[0]] + x[2];
        for (int[] x : ed) if (d[x[0]] != INF && d[x[0]] + x[2] < d[x[1]]) { System.out.println("Graph contains a negative weight cycle"); return; }
        System.out.println("Vertex : Distance");
        for (int i = 0; i < n; i++) System.out.println(i + " : " + (d[i] == INF ? "INF" : d[i]));
    }
}
