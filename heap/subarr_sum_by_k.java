/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int n = scn.nextInt();
            int k = scn.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = scn.nextInt();

            System.out.println(divisible(arr, k, n));
        }
    }

    public static int divisible(int[] arr, int k, int n) {
        int[] prearr = new int[n];
        prearr[0] = arr[0];
        for (int i = 1; i < n; i++)
            prearr[i] = prearr[i - 1] + arr[i];

        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        map.put(0, 1);
        for (int i = 0; i < n; i++) {
            prearr[i] = prearr[i] % k;
            if (prearr[i] < 0)
                prearr[i] += k;

            if (map.containsKey(prearr[i]))
                ans += map.get(prearr[i]);
            map.put(prearr[i], map.getOrDefault(prearr[i], 0) + 1);
        }
        return ans;

    }
}