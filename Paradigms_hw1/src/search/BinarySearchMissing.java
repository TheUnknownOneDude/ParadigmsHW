package search;



public class BinarySearchMissing  {
    static int x;


    public static void main(String[] args) {
        int[] a = new int[args.length - 1];

        for (int i = 0; i < a.length; i++)
            a[i] = Integer.parseInt(args[i + 1]);

        x = Integer.parseInt(args[0]);

        IterativeMissingSearch(a);
        //RecursiveMissingSearch(a, -1, a.length);

    }

    // Pre: a != null, a[0] = max(a), a[a.length] = min(a), for all i : 0 < i < a.length a[i] <= a[i - 1]
    // Post: r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0) ||
    // if x not in a (-r - 1) : r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
    // I: a[l] > x >= a[r] && r - 1 > l
    public static void IterativeSearch(int[] a) {
        //a[0] = max(a), a[a.length] = min(a), for all i : 0 < i < a.length a[i] <= a[i - 1]
        int l = -1;
        int r = a.length;
        // a[l] < x <= a[r] && r - 1 > l
        while (r - 1 > l) {
            int m = (l + r) / 2;   // l <= m <= r && m - l == r - m

            if (a[m] > x) r = m; //
                //r = m, m > l => l < r => a[l] < x <= a[r]

            else l = m; //r = m, m > l => l < r => a[l] > x >= a[r]

            // a[l] < x <= a[r], l < r - 1
        }
        // a[l] > x >= a[r], r - 1 == l
        // r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
        System.out.println(r);
    }

    // Pre: a != null, a[0] = max(a), a[a.length] = min(a), for all i [0 < i < a.length] a[i] <= a[i - 1]
    // Post: r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0) ||
    // if x not in a (-r - 1) : r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
    // I: a[l] < x <= a[r] && r - 1 > l
    public static void RecursiveMissingSearch(int[] a,int l,int r) {
        //a[0] = min(a), a[a.length] = max(a), for all i : 0 < i < a.length a[i] >= a[i - 1]

        int m = (l + r) / 2;      // l <= m <= r && m - l == r - m

        if (r - 1 == l) {

            // r : a[r] >= x && r == min(for all r' : a[r'] >= x && r' : min(a[r'] - x && x - a[r'] >= 0)

            if (r < a.length && a[r] == x) System.out.println(r); // exists a[r] == x

            else System.out.println(-r - 1);   // not exists a[r] || a[r] != x

            return;
        }


        if (a[m] >= x) RecursiveMissingSearch(a, l, m); //r = m, m > l => l < r => a[l] < x <= a[r]

        else RecursiveMissingSearch(a, m, r); // x > a[l] && r - 1 > l

    }


    // Pre: a != null, a[0] = min(a), a[a.length] = max(a), for all i [0 < i < a.length] a[i] <= a[i - 1]
    // Post: r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0) ||
    // if x not in a (-r - 1) : r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
    // I: a[l] < x <= a[r] && r - 1 > l
    public static void IterativeMissingSearch(int[] a) {
        //a[0] = min(a), a[a.length] = max(a), for all i : 0 < i < a.length a[i] >= a[i - 1]
        int l = -1;
        int r = a.length;

        while (r - 1 > l) {
            int m = (l + r) / 2;   // l <= m <= r && m - l == r - m

            if (a[m] >= x) r = m;  //r = m, m > l => l < r => a[l] < x <= a[r]

            else l = m; //

            // a[l] < x <= a[r], l < r - 1
        }

        // r : a[r] >= x && r == min(for all r' : a[r'] >= x && r' : min(a[r'] - x && x - a[r'] >= 0)
        // a[l] < x <= a[r], r - 1 == l

        if (r < a.length && a[r] == x) System.out.println(r); // exists a[r] == x

        else System.out.println(-r - 1); // not exists a[r] || a[r] != x
    }
}

