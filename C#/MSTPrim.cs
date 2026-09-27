using System;
class MSTPrim {
    static void Main() {
        Console.Write("Enter number of vertices: "); int n = int.Parse(Console.ReadLine());
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        int[,] g = new int[n, n];
        Console.WriteLine("Enter edges (u v w):");
        for (int i = 0; i < e; i++) { int[] p = Array.ConvertAll(Console.ReadLine().Split(), int.Parse); g[p[0], p[1]] = g[p[1], p[0]] = p[2]; }
        int[] key = new int[n], par = new int[n]; bool[] inT = new bool[n];
        Array.Fill(key, int.MaxValue); key[0] = 0; par[0] = -1;
        int cost = 0;
        Console.WriteLine("MST Edges:");
        for (int c = 0; c < n; c++) {
            int u = -1;
            for (int i = 0; i < n; i++) if (!inT[i] && (u == -1 || key[i] < key[u])) u = i;
            inT[u] = true; cost += key[u];
            if (par[u] != -1) Console.WriteLine(par[u] + " - " + u + " : " + key[u]);
            for (int v = 0; v < n; v++) if (g[u, v] > 0 && !inT[v] && g[u, v] < key[v]) { key[v] = g[u, v]; par[v] = u; }
        }
        Console.WriteLine("Total cost of MST: " + cost);
    }
}
