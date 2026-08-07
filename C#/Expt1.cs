using System;
using System.Collections.Generic;
class Program
{
    static void Main()
    {
        Console.Write("Enter number of lists: ");
        int k = int.Parse(Console.ReadLine());
        var lists = new List<List<int>>();
        var pq = new PriorityQueue<(int val, int list, int idx), int>();
        for (int i = 0; i < k; i++)
        {
            Console.Write($"List {i + 1}: ");
            int[] arr = Array.ConvertAll(Console.ReadLine().Split(), int.Parse);
            lists.Add(new List<int>(arr));
            if (arr.Length > 0)
                pq.Enqueue((arr[0], i, 0), arr[0]);
        }
        Console.Write("\nMerged List: ");
        while (pq.Count > 0)
        {
            var (val, list, idx) = pq.Dequeue();
            Console.Write(val + " ");
            if (idx + 1 < lists[list].Count)
            {
                int next = lists[list][idx + 1];
                pq.Enqueue((next, list, idx + 1), next);
            }
        }
    }
}