import java.util.Scanner;
public class StackAmortized {
    static int[] a = new int[2];
    static int top = -1;
    static long cost = 0, pushes = 0;
    static void push(int x) {
        if (top + 1 == a.length) {
            int old = a.length, n = old * 2;
            int[] b = new int[n];
            for (int i = 0; i <= top; i++) b[i] = a[i];
            a = b; cost += old;
        }
        a[++top] = x; cost++; pushes++;
    }
    static int pop() { return a[top--]; }
    static double amortized() { return pushes == 0 ? 0 : (double) cost / pushes; }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of pushes: ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            push(i);
            System.out.println("Pushed: " + i + " -> stack top = " + top);
        }
        System.out.println("Enter no. of pops: ");
        int p = sc.nextInt();
        for (int i = 0; i < p && top >= 0; i++) {
            int x = pop();
            System.out.println("Popped: " + x + " -> stack top = " + top);
        }
        System.out.println("Total cost = " + cost);
        System.out.println("Pushes = " + pushes);
        System.out.printf("Amortized cost = %.2f%n", amortized());
    }
}