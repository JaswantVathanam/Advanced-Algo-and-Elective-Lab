import java.util.Scanner;

class StackAmortizedMenu {
    static int[] stack = new int[2], top = {-1};
    static int totalCost = 0, pushCount = 0, popCount = 0, topCount = 0;

    static int push(int value) {
        int cost = 1;
        if (top[0] == stack.length - 1) {
            int[] temp = new int[stack.length * 2];
            for (int i = 0; i <= top[0]; i++) { temp[i] = stack[i]; cost++; }
            stack = temp;
        }
        stack[++top[0]] = value;
        totalCost += cost; pushCount++;
        return cost;
    }

    static int pop() {
        if (top[0] == -1) { System.out.println("Stack is empty"); return 0; }
        top[0]--; popCount++; totalCost++; return 1;
    }

    static int topElement() {
        if (top[0] == -1) { System.out.println("Stack is empty"); return 0; }
        System.out.println("Top element = " + stack[top[0]]); topCount++; totalCost++; return 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of operations: ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("\nOperation " + i); System.out.println("1. Push\n2. Pop\n3. Top");
            System.out.print("Enter choice: "); int c = sc.nextInt();
            int cost = 0;
            switch (c) {
                case 1: System.out.print("Enter value: "); cost = push(sc.nextInt()); System.out.println("Cost of PUSH = " + cost); break;
                case 2: cost = pop(); if (cost > 0) System.out.println("Cost of POP = " + cost); break;
                case 3: cost = topElement(); if (cost > 0) System.out.println("Cost of TOP = " + cost); break;
                default: System.out.println("Invalid choice");
            }
        }
        System.out.println("\nTotal Cost = " + totalCost);
        System.out.printf("Amortized Cost = %.2f%n", (double) totalCost / n);
        sc.close();
    }
}
