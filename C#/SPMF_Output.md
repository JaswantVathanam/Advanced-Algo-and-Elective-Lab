# Experiment 8 – Shortest Path and Maximum Flow Algorithms (C#)

**Aim:** Implement Shortest Path and Maximum Flow algorithms.

**Graph A** – directed, weighted (used for Dijkstra and Floyd-Warshall):
`0→1 (10), 0→2 (3), 1→2 (1), 2→1 (4), 1→3 (2), 2→3 (8), 2→4 (2), 3→4 (7), 4→3 (9)`

**Graph B** – directed, with negative weights (used for Bellman-Ford):
`0→1 (6), 0→2 (7), 1→2 (8), 1→3 (5), 1→4 (-4), 2→3 (-3), 2→4 (9), 3→1 (-2), 4→0 (2), 4→3 (7)`

**Flow network** – capacities (used for Ford-Fulkerson), source 0, sink 5:
`0→1 (16), 0→2 (13), 1→2 (10), 2→1 (4), 1→3 (12), 3→2 (9), 2→4 (14), 4→3 (7), 3→5 (20), 4→5 (4)`

---

## 1. SPMFDijkstra.cs – Dijkstra's Algorithm (Single Source Shortest Path)

### Program

```csharp
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
```

### Input

```
5
9
0 1 10
0 2 3
1 2 1
2 1 4
1 3 2
2 3 8
2 4 2
3 4 7
4 3 9
0
```

### Output

```
Enter number of vertices: 5
Enter number of edges: 9
Enter directed edges (u v w):
0 1 10
0 2 3
1 2 1
2 1 4
1 3 2
2 3 8
2 4 2
3 4 7
4 3 9
Enter source vertex: 0
Vertex : Distance
0 : 0
1 : 7
2 : 3
3 : 9
4 : 5
```

---

## 2. SPMFFloydWarshall.cs – Floyd-Warshall Algorithm (All Pairs Shortest Path)

### Program

```csharp
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
```

### Input

```
5
9
0 1 10
0 2 3
1 2 1
2 1 4
1 3 2
2 3 8
2 4 2
3 4 7
4 3 9
```

### Output

```
Enter number of vertices: 5
Enter number of edges: 9
Enter directed edges (u v w):
0 1 10
0 2 3
1 2 1
2 1 4
1 3 2
2 3 8
2 4 2
3 4 7
4 3 9
All-pairs shortest distances:
0	7	3	9	5
INF	0	1	2	3
INF	4	0	6	2
INF	INF	INF	0	7
INF	INF	INF	9	0
```

---

## 3. SPMFBellmanFord.cs – Bellman-Ford Algorithm (Shortest Path with Negative Weights)

### Program

```csharp
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
```

### Input

```
5
10
0 1 6
0 2 7
1 2 8
1 3 5
1 4 -4
2 3 -3
2 4 9
3 1 -2
4 0 2
4 3 7
0
```

### Output

```
Enter number of vertices: 5
Enter number of edges: 10
Enter directed edges (u v w):
0 1 6
0 2 7
1 2 8
1 3 5
1 4 -4
2 3 -3
2 4 9
3 1 -2
4 0 2
4 3 7
Enter source vertex: 0
Vertex : Distance
0 : 0
1 : 2
2 : 7
3 : 4
4 : -2
```

---

## 4. SPMFFordFulkerson.cs – Ford-Fulkerson Algorithm (Maximum Flow)

### Program

```csharp
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
```

### Input

```
6
10
0 1 16
0 2 13
1 2 10
2 1 4
1 3 12
3 2 9
2 4 14
4 3 7
3 5 20
4 5 4
0 5
```

### Output

```
Enter number of vertices: 6
Enter number of edges: 10
Enter edges (u v capacity):
0 1 16
0 2 13
1 2 10
2 1 4
1 3 12
3 2 9
2 4 14
4 3 7
3 5 20
4 5 4
Enter source and sink: 0 5
Maximum flow: 23
```
