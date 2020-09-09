/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class pair_div_k {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int n = scn.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scn.nextInt();
                arr[i] = arr[i] % 4;
                if (arr[i] < 0)
                    arr[i] += 4;
            }
            System.out.println(div(arr, n));
        }
    }

    public static int div(int[] arr, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0 && map.containsKey(4 - arr[i])) {
                count += map.get(4 - arr[i]);
            } else if (arr[i] == 0 && map.containsKey(0))
                count += map.get(arr[i]);
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }
        return count;
    }
}