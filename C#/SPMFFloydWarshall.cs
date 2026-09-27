using System;
class SPMFFloydWarshall {
    static void Main() {
        const int INF = 99999;
        Console.Write("Enter number of vertices: "); int n = int.Parse(Console.ReadLine());
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        int[,] d = new int[n, n];
        for (int i = 0; i < n; i++) for (int j = 0; j < n; j++) d[i, j] = i == j ? 0 : INF;
        Console.WriteLine("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) { int[] p = Array.ConvertAll(Console.ReadLine().Split(), int.Parse); d[p[0], p[1]] = p[2]; }
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (d[i, k] + d[k, j] < d[i, j]) d[i, j] = d[i, k] + d[k, j];
        Console.WriteLine("All-pairs shortest distances:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) Console.Write((d[i, j] >= INF ? "INF" : d[i, j].ToString()) + "\t");
            Console.WriteLine();
        }
    }
}
