
import java.util.*;
import java.lang.*;
import java.io.*;

class morningassembly {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int tc = scn.nextInt();
        while (tc-- > 0) {
            int n = scn.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = scn.nextInt();
            int tem = longestincreasing(arr);
            System.out.println(n - tem);

        }
    }

    public static int longestincreasing(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int maxi = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i] - 1)) {
                int temp = map.get(arr[i] - 1) + 1;
                map.put(arr[i], temp);
                maxi = Math.max(maxi, temp);
            } else
                map.put(arr[i], 1);
        }
        return maxi;
    }
}