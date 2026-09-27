# Experiment 8 – Shortest Path and Maximum Flow Algorithms (Java)

**Aim:** Implement Shortest Path and Maximum Flow algorithms.

**Graph A** – directed, weighted (used for Dijkstra and Floyd-Warshall):
`0→1 (10), 0→2 (3), 1→2 (1), 2→1 (4), 1→3 (2), 2→3 (8), 2→4 (2), 3→4 (7), 4→3 (9)`

**Graph B** – directed, with negative weights (used for Bellman-Ford):
`0→1 (6), 0→2 (7), 1→2 (8), 1→3 (5), 1→4 (-4), 2→3 (-3), 2→4 (9), 3→1 (-2), 4→0 (2), 4→3 (7)`

**Flow network** – capacities (used for Ford-Fulkerson), source 0, sink 5:
`0→1 (16), 0→2 (13), 1→2 (10), 2→1 (4), 1→3 (12), 3→2 (9), 2→4 (14), 4→3 (7), 3→5 (20), 4→5 (4)`

---

## 1. SPMFDijkstra.java – Dijkstra's Algorithm (Single Source Shortest Path)

### Program

```java
import java.util.*;
public class SPMFDijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] g = new int[n][n];
        System.out.println("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) g[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        System.out.print("Enter source vertex: "); int s = sc.nextInt();
        int[] d = new int[n]; boolean[] done = new boolean[n];
        Arrays.fill(d, Integer.MAX_VALUE); d[s] = 0;
        for (int c = 0; c < n; c++) {
            int u = -1;
            for (int i = 0; i < n; i++) if (!done[i] && (u == -1 || d[i] < d[u])) u = i;
            if (d[u] == Integer.MAX_VALUE) break;
            done[u] = true;
            for (int v = 0; v < n; v++) if (g[u][v] > 0 && d[u] + g[u][v] < d[v]) d[v] = d[u] + g[u][v];
        }
        System.out.println("Vertex : Distance");
        for (int i = 0; i < n; i++) System.out.println(i + " : " + (d[i] == Integer.MAX_VALUE ? "INF" : d[i]));
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

## 2. SPMFFloydWarshall.java – Floyd-Warshall Algorithm (All Pairs Shortest Path)

### Program

```java
import java.util.*;
public class SPMFFloydWarshall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); final int INF = 99999;
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) { Arrays.fill(d[i], INF); d[i][i] = 0; }
        System.out.println("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) d[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (d[i][k] + d[k][j] < d[i][j]) d[i][j] = d[i][k] + d[k][j];
        System.out.println("All-pairs shortest distances:");
        for (int[] r : d) {
            for (int x : r) System.out.print((x >= INF ? "INF" : x) + "\t");
            System.out.println();
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

## 3. SPMFBellmanFord.java – Bellman-Ford Algorithm (Shortest Path with Negative Weights)

### Program

```java
import java.util.*;
public class SPMFBellmanFord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); final int INF = Integer.MAX_VALUE;
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] ed = new int[e][3];
        System.out.println("Enter directed edges (u v w):");
        for (int i = 0; i < e; i++) for (int j = 0; j < 3; j++) ed[i][j] = sc.nextInt();
        System.out.print("Enter source vertex: "); int s = sc.nextInt();
        int[] d = new int[n]; Arrays.fill(d, INF); d[s] = 0;
        for (int i = 1; i < n; i++)
            for (int[] x : ed) if (d[x[0]] != INF && d[x[0]] + x[2] < d[x[1]]) d[x[1]] = d[x[0]] + x[2];
        for (int[] x : ed) if (d[x[0]] != INF && d[x[0]] + x[2] < d[x[1]]) { System.out.println("Graph contains a negative weight cycle"); return; }
        System.out.println("Vertex : Distance");
        for (int i = 0; i < n; i++) System.out.println(i + " : " + (d[i] == INF ? "INF" : d[i]));
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

## 4. SPMFFordFulkerson.java – Ford-Fulkerson Algorithm (Maximum Flow)

### Program

```java
import java.util.*;
public class SPMFFordFulkerson {
    static int n; static int[][] c;
    static int dfs(int u, int t, int f, boolean[] vis) {
        if (u == t) return f;
        vis[u] = true;
        for (int v = 0; v < n; v++)
            if (!vis[v] && c[u][v] > 0) {
                int r = dfs(v, t, Math.min(f, c[u][v]), vis);
                if (r > 0) { c[u][v] -= r; c[v][u] += r; return r; }
            }
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        c = new int[n][n];
        System.out.println("Enter edges (u v capacity):");
        for (int i = 0; i < e; i++) c[sc.nextInt()][sc.nextInt()] = sc.nextInt();
        System.out.print("Enter source and sink: "); int s = sc.nextInt(), t = sc.nextInt();
        int flow = 0, f;
        while ((f = dfs(s, t, Integer.MAX_VALUE, new boolean[n])) > 0) flow += f;
        System.out.println("Maximum flow: " + flow);
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
