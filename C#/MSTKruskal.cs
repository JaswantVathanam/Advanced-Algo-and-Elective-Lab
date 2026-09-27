using System;
class MSTKruskal {
    static int[] p;
    static int Find(int x) => p[x] == x ? x : (p[x] = Find(p[x]));
    static void Main() {
        Console.Write("Enter number of vertices: "); int n = int.Parse(Console.ReadLine());
        Console.Write("Enter number of edges: "); int e = int.Parse(Console.ReadLine());
        int[][] ed = new int[e][];
        Console.WriteLine("Enter edges (u v w):");
        for (int i = 0; i < e; i++) ed[i] = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
        Array.Sort(ed, (a, b) => a[2] - b[2]);
        p = new int[n]; for (int i = 0; i < n; i++) p[i] = i;
        int cost = 0;
        Console.WriteLine("MST Edges:");
        foreach (int[] x in ed) {
            int a = Find(x[0]), b = Find(x[1]);
            if (a != b) { p[a] = b; cost += x[2]; Console.WriteLine(x[0] + " - " + x[1] + " : " + x[2]); }
        }
        Console.WriteLine("Total cost of MST: " + cost);
    }
}
