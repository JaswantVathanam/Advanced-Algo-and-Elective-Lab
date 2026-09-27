# Graph Traversal – DFS and BFS (Java)

Sample graph used for both programs (undirected, 6 vertices, 7 edges):

```
    0
   / \
  1   2
 / \ /
3   4
 \ /
  5
```

---

## 1. GTDFS.java – Depth First Search

### Input

```
6
7
0 1
0 2
1 3
1 4
2 4
3 5
4 5
0
```

### Output

```
Enter number of vertices: 6
Enter number of edges: 7
Enter edges (u v):
0 1
0 2
1 3
1 4
2 4
3 5
4 5
Enter starting vertex: 0
DFS: 0 1 3 5 4 2
```

---

## 2. GTBFS.java – Breadth First Search

### Input

```
6
7
0 1
0 2
1 3
1 4
2 4
3 5
4 5
0
```

### Output

```
Enter number of vertices: 6
Enter number of edges: 7
Enter edges (u v):
0 1
0 2
1 3
1 4
2 4
3 5
4 5
Enter starting vertex: 0
BFS: 0 1 2 3 4 5
```
