# Experiment 3 — Greedy Algorithms: Knapsack and Activity Selection

**Aim:** To implement an algorithm based on the greedy approach to solve the knapsack problem and the activity selection problem.

**Language:** Java &nbsp;&nbsp; **Compiler:** javac 26.0.1 &nbsp;&nbsp; **Runtime:** Java HotSpot 64-Bit Server VM (26.0.1+8-34)

| # | Program | File | Lines | Design technique |
|---|---------|------|-------|------------------|
| 3.a | Fractional knapsack | `FractionalKnapsack.java` | 24 | Greedy |
| 3.b | 0/1 knapsack | `Knapsack01.java` | 22 | Dynamic programming |
| 3.c | Activity selection | `ActivitySelection.java` | 20 | Greedy |

All three compile without error and every program is under 25 lines.

**Why 3.b is not greedy:** the greedy choice (highest value/weight ratio first) is optimal only when an item may be broken. When items must be taken whole, the same choice can be beaten — run 3.a-1 and run 3.b-2 use identical data and capacity, and the fractional answer 240 is not attainable with whole items, whose true optimum is 220. The 0/1 case is therefore solved by dynamic programming and is included here for contrast.

---

## 3.a Fractional knapsack

**Aim:** To fill a knapsack of capacity W with items of given weight and value, where an item may be broken into a fraction, so that the profit carried is maximum.

**Greedy strategy:** take items in decreasing order of value/weight ratio; break the last item that does not fit completely.

**Algorithm:**

1. Compute ratio = value / weight for every item.
2. Sort the items in decreasing order of ratio.
3. Scan the sorted list. If the whole item fits in the remaining capacity, take it fully; otherwise take the fraction that fits and stop.
4. Accumulate profit as (weight taken) x ratio.

**Complexity:** O(n log n) for the sort, which dominates the O(n) scan; O(n) space

### Program

```java
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
```

The item's original serial number is carried in column 3, so the items can be reported by their input numbers even after sorting.

### Sample run 1 — the standard textbook instance

```
Enter the number of items: 3
Enter the weight and value of each item: 
10 60
20 100
30 120
Enter the capacity of the knapsack: 50
Item 1 taken with fraction 1.00
Item 2 taken with fraction 1.00
Item 3 taken with fraction 0.67
Maximum profit is 240.00

```

### Sample run 2 — capacity equal to the total weight

```
Enter the number of items: 3
Enter the weight and value of each item: 
5 30
10 40
15 45
Enter the capacity of the knapsack: 30
Item 1 taken with fraction 1.00
Item 2 taken with fraction 1.00
Item 3 taken with fraction 1.00
Maximum profit is 115.00

```

### Sample run 3 — capacity smaller than the best item

```
Enter the number of items: 3
Enter the weight and value of each item: 
20 100
10 60
30 120
Enter the capacity of the knapsack: 5
Item 2 taken with fraction 0.50
Maximum profit is 30.00

```

**Verification (run 1):** the ratios are 60/10 = 6, 100/20 = 5, 120/30 = 4, so the order of choice is item 1, item 2, item 3. Items 1 and 2 use 30 units and give 60 + 100 = 160; the remaining 20 units are filled with 20/30 = 0.67 of item 3, worth 20 x 4 = 80. Total = **240.00**, matching the output.

**Verification (run 3):** the ratios are 5, 6, 4, so item 2 is chosen first. Only 5 of its 10 units fit, giving 0.50 of the item and 5 x 6 = **30.00**, after which the capacity is exhausted and the loop stops.

---

## 3.b 0/1 knapsack

**Aim:** To fill a knapsack of capacity W with items of given weight and value, where each item must be taken whole or left behind, so that the profit is maximum.

**Method:** dynamic programming over the table dp[i][c] = the best profit obtainable from the first i items with capacity c.

**Recurrence:**

```
dp[i][c] = dp[i-1][c]                                    if w[i] > c
dp[i][c] = max(dp[i-1][c], dp[i-1][c-w[i]] + v[i])       otherwise
```

The chosen items are recovered by walking backwards from dp[n][W]: whenever dp[i][c] differs from dp[i-1][c], item i was included, so its weight is removed from c.

**Complexity:** O(n·W) time and O(n·W) space (pseudo-polynomial, since W is a value and not an input size)

### Program

```java
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
```

The arrays are indexed from 1 so that row 0 and column 0 stay zero and act as the base case.

### Sample run 1 — four items, capacity 5

```
Enter the number of items: 4
Enter the weight and value of each item: 
2 3
3 4
4 5
5 6
Enter the capacity of the knapsack: 5
Maximum profit is 7
Items selected: 2 1 

```

### Sample run 2 — the same data as fractional run 1

```
Enter the number of items: 3
Enter the weight and value of each item: 
10 60
20 100
30 120
Enter the capacity of the knapsack: 50
Maximum profit is 220
Items selected: 3 2 

```

### Sample run 3 — no item fits

```
Enter the number of items: 2
Enter the weight and value of each item: 
5 40
6 50
Enter the capacity of the knapsack: 4
Maximum profit is 0
Items selected: 

```

**Verification (run 1):** the possible whole-item packings within capacity 5 are {1,2} = 5 units for 3 + 4 = 7, {3} = 4 units for 5, {4} = 5 units for 6, and {1} + nothing else = 3. The best is **7** with items 1 and 2, which is what the traceback prints (in reverse order, 2 then 1).

**Verification (run 2):** taking items 2 and 3 uses 20 + 30 = 50 units exactly and gives 100 + 120 = **220**. Taking items 1 and 3 gives 180, items 1 and 2 give 160. The greedy ratio order would pick items 1 and 2 first and reach only 160, which shows that the greedy choice fails for the 0/1 case.

**Verification (run 3):** both items are heavier than the capacity, so no column of the table ever improves on zero; the profit is **0** and the item list is empty.

---

## 3.c Activity selection

**Aim:** To select the maximum number of activities that can be performed by a single person, given the start and finish time of each activity, so that no two selected activities overlap.

**Greedy strategy:** always choose the activity that finishes earliest among those that are still compatible, since finishing early leaves the largest possible free interval for the remaining activities.

**Algorithm:**

1. Sort the activities in increasing order of finish time.
2. Select the first activity and record its finish time.
3. Scan the rest; select an activity if its start time is not earlier than the last recorded finish time, then update that finish time.

**Complexity:** O(n log n) for the sort, O(n) for the scan; O(n) space

### Program

```java
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
```

Each activity is stored as {start, finish, serial number}, so the sort by finish time still allows the selected activities to be printed by their input numbers. The test `start >= last` treats an activity that begins exactly when the previous one ends as compatible.

### Sample run 1 — six activities

```
Enter the number of activities: 6
Enter the start and finish time of each activity: 
1 2
3 4
0 6
5 7
8 9
5 9
Selected activities: 1(1,2) 2(3,4) 4(5,7) 5(8,9) 
Maximum number of activities is 4

```

### Sample run 2 — the standard eleven-activity instance

```
Enter the number of activities: 11
Enter the start and finish time of each activity: 
1 4
3 5
0 6
5 7
3 9
5 9
6 10
8 11
8 12
2 14
12 16
Selected activities: 1(1,4) 4(5,7) 8(8,11) 11(12,16) 
Maximum number of activities is 4

```

### Sample run 3 — every activity overlaps every other

```
Enter the number of activities: 4
Enter the start and finish time of each activity: 
1 10
2 9
3 8
4 7
Selected activities: 4(4,7) 
Maximum number of activities is 1
```

**Verification (run 1):** sorted by finish time the order is (1,2), (3,4), (0,6), (5,7), (8,9), (5,9). Activity 1 is taken (last = 2); (3,4) starts at 3 >= 2, taken (last = 4); (0,6) starts at 0 < 4, rejected; (5,7) starts at 5 >= 4, taken (last = 7); (8,9) starts at 8 >= 7, taken (last = 9); (5,9) starts at 5 < 9, rejected. Four activities, as printed.

**Verification (run 2):** the selection {(1,4), (5,7), (8,11), (12,16)} is mutually compatible and has size **4**, which is the known optimum for this instance; no five of these activities can be made pairwise non-overlapping.

**Verification (run 3):** the activities are nested inside one another, so at most one can be chosen. The greedy rule picks the one that finishes earliest, activity 4 with (4,7), giving a count of **1**.

---

## Result

The fractional knapsack problem and the activity selection problem were solved by the greedy approach, and the 0/1 knapsack problem by dynamic programming, in Java. All three programs were compiled and executed, and the outputs were verified against hand calculation for every sample run listed above.

---

## Note on the transcripts

Every sample run above is real output from the compiled classes. The programs were driven from input files through a small wrapper that echoes each byte as the program reads it, so the typed values appear interleaved with the prompts exactly as they do when the values are entered at the keyboard.
