import java.util.*;
public class StrMatNaive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: "); String t = sc.nextLine();
        System.out.print("Enter pattern: "); String p = sc.nextLine();
        int n = t.length(), m = p.length(), count = 0;
        for (int s = 0; s <= n - m; s++) {
            int j = 0;
            while (j < m && t.charAt(s + j) == p.charAt(j)) j++;
            if (j == m) { System.out.println("Pattern found at index " + s); count++; }
        }
        System.out.println("Total occurrences: " + count);
    }
}
