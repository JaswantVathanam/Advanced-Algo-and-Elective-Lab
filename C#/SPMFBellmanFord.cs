using System;
class SPMFBellmanFord {
    static void Main() {
        const int INF = int.MaxValue;
        Console.Write("Enter number of vertices: "); int n = int.Parse(Console.ReadLine());
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        int[][] ed = new int[e][];
        Console.WriteLine("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) ed[i] = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
        Console.Write("Enter source vertex: "); int s = int.Parse(Console.ReadLine());
        int[] d = new int[n]; Array.Fill(d, INF); d[s] = 0;
        for (int i = 1; i < n; i++)
            foreach (int[] x in ed) if (d[x[0]] != INF && d[x[0]] + x[2] < d[x[1]]) d[x[1]] = d[x[0]] + x[2];
        foreach (int[] x in ed) if (d[x[0]] != INF && d[x[0]] + x[2] < d[x[1]]) { Console.WriteLine("Graph contains a negative weight cycle"); return; }
        Console.WriteLine("Vertex : Distance");
        for (int i = 0; i < n; i++) Console.WriteLine(i + " : " + (d[i] == INF ? "INF" : d[i].ToString()));
    }
}
