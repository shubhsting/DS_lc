import java.util.*;

import java.lang.*;

import java.io.*;

class GFG_ {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        while (t-- > 0) {

            int n = s.nextInt();

            int odd = 0;

            int[][] A = new int[n][n];

            for (int i = 0; i < n; i++) {
                int c = 0;
                for (int j = 0; j < n; j++) {
                    int x = s.nextInt();
                    A[i][j] = x;
                    if (x != 0)
                        c++;
                }
                if (c % 2 != 0)
                    odd++;
            }
            if (odd == 2 || odd == 1 || odd == 0)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}