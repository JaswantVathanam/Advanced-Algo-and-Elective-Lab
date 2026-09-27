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
