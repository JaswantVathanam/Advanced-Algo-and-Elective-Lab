import java.util.*;
public class StrMatKMP {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: "); String t = sc.nextLine();
        System.out.print("Enter pattern: "); String p = sc.nextLine();
        int n = t.length(), m = p.length(), count = 0;
        int[] lps = new int[m];
        for (int i = 1, k = 0; i < m; i++) {
            while (k > 0 && p.charAt(i) != p.charAt(k)) k = lps[k - 1];
            if (p.charAt(i) == p.charAt(k)) k++;
            lps[i] = k;
        }
        System.out.println("LPS array: " + Arrays.toString(lps));
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && t.charAt(i) != p.charAt(j)) j = lps[j - 1];
            if (t.charAt(i) == p.charAt(j)) j++;
            if (j == m) { System.out.println("Pattern found at index " + (i - m + 1)); count++; j = lps[j - 1]; }
        }
        System.out.println("Total occurrences: " + count);
    }
}
