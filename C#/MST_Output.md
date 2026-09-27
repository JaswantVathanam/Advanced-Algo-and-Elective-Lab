# Experiment 7 – Minimum Spanning Tree (C#)

**Aim:** Implement an algorithm to construct Minimum Spanning Trees.

Sample graph used for both programs (undirected, weighted, 6 vertices, 9 edges):

| Edge | Weight |
|------|--------|
| 0 - 1 | 4 |
| 0 - 2 | 3 |
| 1 - 2 | 1 |
| 1 - 3 | 2 |
| 2 - 3 | 4 |
| 3 - 4 | 2 |
| 4 - 5 | 6 |
| 3 - 5 | 5 |
| 2 - 4 | 7 |

---

## 1. MSTPrim.cs – Prim's Algorithm

### Program

```csharp
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
```

### Input

```
6
9
0 1 4
0 2 3
1 2 1
1 3 2
2 3 4
3 4 2
4 5 6
3 5 5
2 4 7
```

### Output

```
Enter number of vertices: 6
Enter number of edges: 9
Enter edges (u v w):
0 1 4
0 2 3
1 2 1
1 3 2
2 3 4
3 4 2
4 5 6
3 5 5
2 4 7
MST Edges:
0 - 2 : 3
2 - 1 : 1
1 - 3 : 2
3 - 4 : 2
3 - 5 : 5
Total cost of MST: 13
```

---

## 2. MSTKruskal.cs – Kruskal's Algorithm

### Program

```csharp
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
```

### Input

```
6
9
0 1 4
0 2 3
1 2 1
1 3 2
2 3 4
3 4 2
4 5 6
3 5 5
2 4 7
```

### Output

```
Enter number of vertices: 6
Enter number of edges: 9
Enter edges (u v w):
0 1 4
0 2 3
1 2 1
1 3 2
2 3 4
3 4 2
4 5 6
3 5 5
2 4 7
MST Edges:
1 - 2 : 1
1 - 3 : 2
3 - 4 : 2
0 - 2 : 3
3 - 5 : 5
Total cost of MST: 13
```
