using System;
using System.Linq;

var items = new[]
{
    new { Weight = 10, Value = 60 },
    new { Weight = 20, Value = 100 },
    new { Weight = 30, Value = 120 }
};

double capacity = 50;
var sorted = items.OrderByDescending(x => (double)x.Value / x.Weight).ToList();

double profit = 0;
foreach (var item in sorted)
{
    if (capacity <= 0) break;
    double take = Math.Min(item.Weight, capacity);
    profit += take * ((double)item.Value / item.Weight);
    capacity -= take;
}

Console.WriteLine($"Fractional knapsack profit: {profit:F2}");
