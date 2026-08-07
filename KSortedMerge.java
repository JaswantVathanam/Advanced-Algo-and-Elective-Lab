import java.util.*;
public class KSortedMerge
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of lists: ");
        int k = Integer.parseInt(sc.nextLine().trim());
        int[][] lists = new int[k][];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < k; i++)
        {
            System.out.print("List " + (i + 1) + ": ");
            lists[i] = Arrays.stream(sc.nextLine().trim().split("\\s+")).mapToInt(Integer::parseInt).toArray();
            if (lists[i].length > 0)
                pq.add(new int[]{lists[i][0], i, 0});
        }
        System.out.print("\nMerged List: ");
        while (!pq.isEmpty())
        {
            int[] n = pq.poll();
            System.out.print(n[0] + " ");
            if (n[2] + 1 < lists[n[1]].length)
                pq.add(new int[]{lists[n[1]][n[2] + 1], n[1], n[2] + 1});
        }
    }
}