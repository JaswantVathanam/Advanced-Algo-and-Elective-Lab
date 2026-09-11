using System; using System.Collections.Generic;
class GTDFS {
    static void DFS(List<int>[] g, int u, bool[] v) {
        v[u] = true; Console.Write(u + " ");
        foreach (int w in g[u]) if (!v[w]) DFS(g, w, v);
    }
    static void Main() {
        int n = 6; var g = new List<int>[n];
        for (int i = 0; i < n; i++) g[i] = new List<int>();
        int[,] e = { { 0, 1 }, { 0, 2 }, { 1, 3 }, { 2, 3 }, { 2, 4 }, { 3, 5 }, { 4, 5 } };
        for (int i = 0; i < e.GetLength(0); i++) { int a = e[i, 0], b = e[i, 1]; g[a].Add(b); g[b].Add(a); }
        Console.Write("DFS: "); DFS(g, 0, new bool[n]); Console.WriteLine();
    }
}
