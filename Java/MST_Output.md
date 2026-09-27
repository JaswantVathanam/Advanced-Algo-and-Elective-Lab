# Experiment 7 – Minimum Spanning Tree (Java)

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

## 1. MSTPrim.java – Prim's Algorithm

### Program

```java
import java.util.*;
public class MSTPrim {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] g = new int[n][n];
        System.out.println("Enter edges (u v w):");
        for (int i = 0; i < e; i++) { int u = sc.nextInt(), v = sc.nextInt(), w = sc.nextInt(); g[u][v] = g[v][u] = w; }
        int[] key = new int[n], par = new int[n]; boolean[] in = new boolean[n];
        Arrays.fill(key, Integer.MAX_VALUE); key[0] = 0; par[0] = -1;
        int cost = 0;
        System.out.println("MST Edges:");
        for (int c = 0; c < n; c++) {
            int u = -1;
            for (int i = 0; i < n; i++) if (!in[i] && (u == -1 || key[i] < key[u])) u = i;
            in[u] = true; cost += key[u];
            if (par[u] != -1) System.out.println(par[u] + " - " + u + " : " + key[u]);
            for (int v = 0; v < n; v++) if (g[u][v] > 0 && !in[v] && g[u][v] < key[v]) { key[v] = g[u][v]; par[v] = u; }
        }
        System.out.println("Total cost of MST: " + cost);
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

## 2. MSTKruskal.java – Kruskal's Algorithm

### Program

```java
import java.util.*;
public class MSTKruskal {
    static int[] p;
    static int find(int x) { return p[x] == x ? x : (p[x] = find(p[x])); }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of vertices: "); int n = sc.nextInt();
        System.out.print("Enter number of edges: "); int e = sc.nextInt();
        int[][] ed = new int[e][3];
        System.out.println("Enter edges (u v w):");
        for (int i = 0; i < e; i++) for (int j = 0; j < 3; j++) ed[i][j] = sc.nextInt();
        Arrays.sort(ed, (a, b) -> a[2] - b[2]);
        p = new int[n]; for (int i = 0; i < n; i++) p[i] = i;
        int cost = 0;
        System.out.println("MST Edges:");
        for (int[] x : ed) {
            int a = find(x[0]), b = find(x[1]);
            if (a != b) { p[a] = b; cost += x[2]; System.out.println(x[0] + " - " + x[1] + " : " + x[2]); }
        }
        System.out.println("Total cost of MST: " + cost);
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
