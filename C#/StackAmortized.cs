using System;
class StackAmortized {
    static int[] a = new int[2];
    static int top = -1;
    static long cost = 0, pushes = 0;
    static void Push(int x) {
        if (top + 1 == a.Length) {
            int old = a.Length, n = old * 2;
            int[] b = new int[n];
            for (int i = 0; i <= top; i++) b[i] = a[i];
            a = b; cost += old;
        }
        a[++top] = x; cost++; pushes++;
    }
    static int Pop() { return a[top--]; }
    static double Amortized() { return pushes == 0 ? 0 : (double)cost / pushes; }
    static void Main() {
        for (int i = 1; i <= 12; i++) Push(i);
        Pop(); Pop(); Pop();
        Console.WriteLine("Total cost = " + cost);
        Console.WriteLine("Pushes = " + pushes);
        Console.WriteLine($"Amortized cost = {Amortized():F2}");
    }
}
