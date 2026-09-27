import java.util.*;
public class StrMatRabinKarp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: "); String t = sc.nextLine();
        System.out.print("Enter pattern: "); String p = sc.nextLine();
        int n = t.length(), m = p.length(), d = 256, q = 101, h = 1, ph = 0, th = 0, count = 0;
        for (int i = 0; i < m - 1; i++) h = (h * d) % q;
        for (int i = 0; i < m; i++) { ph = (d * ph + p.charAt(i)) % q; th = (d * th + t.charAt(i)) % q; }
        for (int s = 0; s <= n - m; s++) {
            if (ph == th && t.substring(s, s + m).equals(p)) { System.out.println("Pattern found at index " + s); count++; }
            if (s < n - m) th = ((d * (th - t.charAt(s) * h) + t.charAt(s + m)) % q + q) % q;
        }
        System.out.println("Total occurrences: " + count);
    }
}
