package search;



public class BinarySearch  {
    static int x;


    public static void main(String[] args) {
        int[] a = new int[args.length - 1];

        for (int i = 0; i < a.length; i++)
            a[i] = Integer.parseInt(args[i + 1]);

        x = Integer.parseInt(args[0]);
        //IterativeSearch(a);
        RecursiveSearch(a, -1, a.length);
    }

    // Pre: a != null, a[0] = max(a), a[a.length] = min(a), for all i : 0 < i < a.length a[i] <= a[i - 1]
    // Post: r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
    // I: a[l] > x >= a[r] && r - 1 > l
    public static void IterativeSearch(int[] a) {
        //a[0] = max(a), a[a.length] = min(a), for all i : 0 < i < a.length a[i] <= a[i - 1]
        int l = -1;
        int r = a.length;
        // a[l] < x <= a[r] && r - 1 > l
        while (r - 1 > l) {
            int m = (l + r) / 2;   // l <= m <= r && m - l == r - m

            if (a[m] > x) l = m; //
                //l = m, m < r => l < r => a[l] > x >= a[r]

            else r = m; //r = m, m > l => l < r => a[l] > x >= a[r]

            // a[l] > x >= a[r], l < r - 1
        }
        // a[l] > x >= a[r], r - 1 == l
        // r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
        System.out.println(r);
    }

    // Pre: a != null, a[0] = max(a), a[a.length] = min(a), for all i [0 < i < a.length] a[i] <= a[i - 1]
    // Post: r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
    public static void RecursiveSearch(int[] a,int l,int r) {
        //a[0] = max(a), a[a.length] = min(a), for all i : 0 < i < a.length a[i] <= a[i - 1]

        int m = (l + r) / 2;      // l <= m <= r && m - l == r - m

        if (r - 1 == l) {
            System.out.println(r);
            // r : a[r] <= x && r == min(for all r' : a[r'] <= x && r' : min(a[r'] - x && x - a[r'] >= 0)
            return;
        }


        if (a[m] > x) RecursiveSearch(a, m, r); // a[l] > x >= a[r] && r - 1 > l

        else RecursiveSearch(a, l, m); // x < a[l] && r - 1 > l

    }

}

