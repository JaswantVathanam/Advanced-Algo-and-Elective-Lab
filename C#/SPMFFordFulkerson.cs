using System;
class SPMFFordFulkerson {
    static int n; static int[,] c;
    static int DFS(int u, int t, int f, bool[] vis) {
        if (u == t) return f;
        vis[u] = true;
        for (int v = 0; v < n; v++)
            if (!vis[v] && c[u, v] > 0) {
                int r = DFS(v, t, Math.Min(f, c[u, v]), vis);
                if (r > 0) { c[u, v] -= r; c[v, u] += r; return r; }
            }
        return 0;
    }
    static void Main() {
        Console.Write("Enter number of vertices: "); n = int.Parse(Console.ReadLine());
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        c = new int[n, n];
        Console.WriteLine("Enter edges (u v capacity):");
        for (int i = 0; i < e; i++) { int[] p = Array.ConvertAll(Console.ReadLine().Split(), int.Parse); c[p[0], p[1]] = p[2]; }
        Console.Write("Enter source and sink: "); int[] st = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
        int flow = 0, f;
        while ((f = DFS(st[0], st[1], int.MaxValue, new bool[n])) > 0) flow += f;
        Console.WriteLine("Maximum flow: " + flow);
    }
}
