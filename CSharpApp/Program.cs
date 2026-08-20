using System;

class Program {
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

    static int Pop() => a[top--];
    static double Amortized() => pushes == 0 ? 0 : (double)cost / pushes;

    static void Main() {
        Console.WriteLine("Enter no. of pushes: ");
        int n = int.Parse(Console.ReadLine()!);
        for (int i = 1; i <= n; i++) {
            Push(i);
            Console.WriteLine("Pushed: " + i + " -> stack top = " + top);
        }
        Console.WriteLine("Enter no. of pops: ");
        int p = int.Parse(Console.ReadLine()!);
        for (int i = 0; i < p && top >= 0; i++) {
            int x = Pop();
            Console.WriteLine("Popped: " + x + " -> stack top = " + top);
        }
        Console.WriteLine("Total cost = " + cost);
        Console.WriteLine("Pushes = " + pushes);
        Console.WriteLine("Amortized cost = " + Amortized().ToString("F2"));
    }
}
