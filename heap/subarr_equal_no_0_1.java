/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class subarr_equal_no_0_1 {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int n = scn.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = scn.nextInt();
            System.out.println(equal10(arr, n));
        }
    }

    public static int equal10(int[] arr, int n) {
        int[] pre0 = new int[n];
        int[] pre1 = new int[n];
        if (arr[0] == 1)
            pre1[0] = 1;
        else
            pre0[0] = 1;
        for (int i = 1; i < n; i++) {
            if (arr[i] == 1) {
                pre1[i] = pre1[i - 1] + 1;
                pre0[i] = pre0[i - 1];
            } else {
                pre1[i] = pre1[i - 1];
                pre0[i] = pre0[i - 1] + 1;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            int num = pre0[i] - pre1[i];
            if (map.containsKey(num))
                ans += map.get(num);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return ans;
    }
}