import java.util.*;
public class MaxContiguousSubsequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        int[] a = new int[n];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < n; i++) a[i] = sc.nextInt();
        int best = a[0], cur = a[0], s = 0, e = 0, t = 0;
        for (int i = 1; i < n; i++) {
            if (cur + a[i] < a[i]) { cur = a[i];  t = i; } else cur += a[i];
            if (cur > best) { best = cur;  s = t;  e = i; }
        }
        System.out.println("Maximum sum is " + best);
        System.out.print("Subsequence is ");
        for (int i = s; i <= e; i++) System.out.print(a[i] + " ");
        System.out.println();
    }
}
