using System;
class SPMFDijkstra {
    static void Main() {
        Console.Write("Enter number of vertices: "); int n = int.Parse(Console.ReadLine());
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        int[,] g = new int[n, n];
        Console.WriteLine("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) { int[] p = Array.ConvertAll(Console.ReadLine().Split(), int.Parse); g[p[0], p[1]] = p[2]; }
        Console.Write("Enter source vertex: "); int s = int.Parse(Console.ReadLine());
        int[] d = new int[n]; bool[] done = new bool[n];
        Array.Fill(d, int.MaxValue); d[s] = 0;
        for (int c = 0; c < n; c++) {
            int u = -1;
            for (int i = 0; i < n; i++) if (!done[i] && (u == -1 || d[i] < d[u])) u = i;
            if (d[u] == int.MaxValue) break;
            done[u] = true;
            for (int v = 0; v < n; v++) if (g[u, v] > 0 && d[u] + g[u, v] < d[v]) d[v] = d[u] + g[u, v];
        }
        Console.WriteLine("Vertex : Distance");
        for (int i = 0; i < n; i++) Console.WriteLine(i + " : " + (d[i] == int.MaxValue ? "INF" : d[i].ToString()));
    }
}
