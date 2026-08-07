import java.util.*;
public class Knapsack01 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();
        int[] w = new int[n + 1], v = new int[n + 1];
        System.out.println("Enter the weight and value of each item: ");
        for (int i = 1; i <= n; i++) { w[i] = sc.nextInt();   v[i] = sc.nextInt(); }
        System.out.print("Enter the capacity of the knapsack: ");
        int cap = sc.nextInt();
        int[][] dp = new int[n + 1][cap + 1];
        for (int i = 1; i <= n; i++)
            for (int c = 0; c <= cap; c++)
                dp[i][c] = (w[i] <= c) ? Math.max(dp[i-1][c], dp[i-1][c-w[i]] + v[i]) : dp[i-1][c];
        System.out.println("Maximum profit is " + dp[n][cap]);
        System.out.print("Items selected: ");
        for (int i = n, c = cap; i > 0; i--)
            if (dp[i][c] != dp[i-1][c]) { System.out.print(i + " ");   c -= w[i]; }
        System.out.println();
    }
}
