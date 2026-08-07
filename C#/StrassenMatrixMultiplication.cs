using System;

int[,] a = { { 1, 2 }, { 3, 4 } };
int[,] b = { { 5, 6 }, { 7, 8 } };
int[,] c = Multiply(a, b);

Console.WriteLine("Strassen result:");
for (int i = 0; i < c.GetLength(0); i++)
{
    for (int j = 0; j < c.GetLength(1); j++) Console.Write(c[i, j] + " ");
    Console.WriteLine();
}

static int[,] Add(int[,] a, int[,] b)
{
    int n = a.GetLength(0);
    int[,] r = new int[n, n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            r[i, j] = a[i, j] + b[i, j];
    return r;
}

static int[,] Sub(int[,] a, int[,] b)
{
    int n = a.GetLength(0);
    int[,] r = new int[n, n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            r[i, j] = a[i, j] - b[i, j];
    return r;
}

static int[,] Multiply(int[,] a, int[,] b)
{
    int n = a.GetLength(0);
    if (n == 1) return new int[,] { { a[0, 0] * b[0, 0] } };

    int m = n / 2;
    int[,] a11 = Slice(a, 0, 0, m, m);
    int[,] a12 = Slice(a, 0, m, m, m);
    int[,] a21 = Slice(a, m, 0, m, m);
    int[,] a22 = Slice(a, m, m, m, m);
    int[,] b11 = Slice(b, 0, 0, m, m);
    int[,] b12 = Slice(b, 0, m, m, m);
    int[,] b21 = Slice(b, m, 0, m, m);
    int[,] b22 = Slice(b, m, m, m, m);

    int[,] p1 = Multiply(Add(a11, a22), Add(b11, b22));
    int[,] p2 = Multiply(Add(a21, a22), b11);
    int[,] p3 = Multiply(a11, Sub(b12, b22));
    int[,] p4 = Multiply(a22, Sub(b21, b11));
    int[,] p5 = Multiply(Add(a11, a12), b22);
    int[,] p6 = Multiply(Sub(a21, a11), Add(b11, b12));
    int[,] p7 = Multiply(Sub(a12, a22), Add(b21, b22));

    int[,] c11 = Add(Sub(Add(p1, p4), p5), p7);
    int[,] c12 = Add(p3, p5);
    int[,] c21 = Add(p2, p4);
    int[,] c22 = Add(Sub(Add(p1, p3), p2), p6);
    return Combine(c11, c12, c21, c22);
}

static int[,] Slice(int[,] a, int r, int c, int rows, int cols)
{
    int[,] result = new int[rows, cols];
    for (int i = 0; i < rows; i++)
        for (int j = 0; j < cols; j++)
            result[i, j] = a[r + i, c + j];
    return result;
}

static int[,] Combine(int[,] c11, int[,] c12, int[,] c21, int[,] c22)
{
    int n = c11.GetLength(0) * 2;
    int[,] r = new int[n, n];
    for (int i = 0; i < c11.GetLength(0); i++)
        for (int j = 0; j < c11.GetLength(0); j++)
        {
            r[i, j] = c11[i, j];
            r[i, j + c11.GetLength(0)] = c12[i, j];
            r[i + c11.GetLength(0), j] = c21[i, j];
            r[i + c11.GetLength(0), j + c11.GetLength(0)] = c22[i, j];
        }
    return r;
}
