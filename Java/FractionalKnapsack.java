import java.util.*;
public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = sc.nextInt();
        double[][] it = new double[n][4];          // weight, value, ratio, id
        System.out.println("Enter the weight and value of each item: ");
        for (int i = 0; i < n; i++) {
            it[i][0] = sc.nextDouble();   it[i][1] = sc.nextDouble();
            it[i][2] = it[i][1] / it[i][0];   it[i][3] = i + 1;
        }
        System.out.print("Enter the capacity of the knapsack: ");
        double cap = sc.nextDouble();
        Arrays.sort(it, (a, b) -> Double.compare(b[2], a[2]));
        double profit = 0;
        for (int i = 0; i < n && cap > 0; i++) {
            double take = Math.min(it[i][0], cap);
            profit += take * it[i][2];   cap -= take;
            System.out.printf("Item %d taken with fraction %.2f%n", (int) it[i][3], take / it[i][0]);
        }
        System.out.printf("Maximum profit is %.2f%n", profit);
    }
}
