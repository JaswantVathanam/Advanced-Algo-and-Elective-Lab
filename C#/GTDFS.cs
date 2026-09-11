using System; using System.Collections.Generic;
class GTDFS {
    static void DFS(List<int>[] g, int u, bool[] v) {
        v[u] = true; Console.Write(u + " ");
        foreach (int w in g[u]) if (!v[w]) DFS(g, w, v);
    }
    static void Main() {
        Console.Write("Enter number of vertices: "); int n = int.Parse(Console.ReadLine());
        var g = new List<int>[n]; for (int i = 0; i < n; i++) g[i] = new List<int>();
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        Console.WriteLine("Enter edges (u v):");
        for (int i = 0; i < e; i++) { string[] p = Console.ReadLine().Split(); int u = int.Parse(p[0]), v = int.Parse(p[1]); g[u].Add(v); g[v].Add(u); }
        Console.Write("Enter starting vertex: "); int s = int.Parse(Console.ReadLine());
        Console.Write("DFS: "); DFS(g, s, new bool[n]); Console.WriteLine();
    }
}
