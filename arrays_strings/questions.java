import java.util.*;

public class questions {
    // ===================================================== 21st july
    // 2020===========================================
    // leetcode 925

    public boolean isLongPressedName(String name, String typed) {
        int i = 0;
        int j = 0;
        while (i < name.length() && j < typed.length()) {
            char ch = name.charAt(i);
            char cht = typed.charAt(j);
            if (ch == cht) {
                i++;
                j++;
            } else {
                if (j - 1 >= 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                    j++;
                } else
                    return false;
            }
        }
        if (j == typed.length() && i < name.length())
            return false;
        while (j < typed.length()) {
            if (typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else
                return false;
        }
        return true;
    }

    // https://www.lintcode.com/problem/range-addition/description
    // most most importanrt concept
    public int[] getModifiedArray(int length, int[][] updates) {
        int[] arr = new int[length];
        for (int[] ar : updates) {
            arr[ar[0]] += ar[2];
            if (ar[1] + 1 < length)
                arr[ar[1] + 1] -= ar[2];
        }
        for (int i = 1; i < length; i++) {
            arr[i] += arr[i - 1];
        }
        return arr;
    }

    // very good question
    // https://www.codechef.com/COOK103B/problems/MAXREMOV
    public void maxremove() {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();

        while (tc-- > 0) {
            int n = scn.nextInt();
            int k = scn.nextInt();
            long[] arr = new long[100000];
            int[][] que = new int[n][2];
            int p = 0;
            while (n-- > 0) {
                int a = scn.nextInt();
                int b = scn.nextInt();
                que[p][0] = a;
                que[p][1] = b;
                p++;
                arr[a - 1] += 1;
                if (b < 100000)
                    arr[b] -= 1;
            }
            long[] countofk = new long[100000];
            long[] countofkp1 = new long[100000];
            if (arr[0] == k)
                countofk[0] = 1;
            if (arr[0] == k + 1)
                countofkp1[0] = 1;
            for (int i = 1; i < 100000; i++) {
                arr[i] += arr[i - 1];
                if (arr[i] == k)
                    countofk[i] = countofk[i - 1] + 1;
                else
                    countofk[i] = countofk[i - 1];

                if (arr[i] == k + 1)
                    countofkp1[i] = countofkp1[i - 1] + 1;
                else
                    countofkp1[i] = countofkp1[i - 1];
            }

            long max = Integer.MIN_VALUE;
            for (int[] ar : que) {
                if (ar[0] - 2 >= 0)
                    max = Math.max(max, countofk[countofk.length - 1] - (countofk[ar[1] - 1] - countofk[ar[0] - 2])
                            + countofkp1[ar[1] - 1] - countofkp1[ar[0] - 2]);
                else
                    max = Math.max(max, countofk[countofk.length - 1] - countofk[ar[1] - 1] + countofkp1[ar[1] - 1]);
            }
            System.out.println(max);
        }
    }


    
}