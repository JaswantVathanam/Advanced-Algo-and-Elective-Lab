import java.util.*;
public class ActivitySelection {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of activities: ");
        int n = sc.nextInt();
        int[][] a = new int[n][3];                 // start, finish, id
        System.out.println("Enter the start and finish time of each activity: ");
        for (int i = 0; i < n; i++) { a[i][0] = sc.nextInt();   a[i][1] = sc.nextInt();   a[i][2] = i + 1; }
        Arrays.sort(a, (x, y) -> x[1] - y[1]);
        int count = 0, last = Integer.MIN_VALUE;
        System.out.print("Selected activities: ");
        for (int i = 0; i < n; i++)
            if (a[i][0] >= last) {
                System.out.print(a[i][2] + "(" + a[i][0] + "," + a[i][1] + ") ");
                last = a[i][1];   count++;
            }
        System.out.println("\nMaximum number of activities is " + count);
    }
}
