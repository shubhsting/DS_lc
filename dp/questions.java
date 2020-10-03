
import java.util.*;

public class questions {
    public static void main(String[] args) {

    }

    // =====================LIS===========================
    // september 28
    public int lengthOfLIS(int[] arr) {
        if (arr.length == 0)
            return 0;
        if (arr.length == 1)
            return 1;
        int n = arr.length;
        int[] dp = new int[n];
        int ans = (int) -1e8;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;
    }

    // nlogn wala solution binary search se
    public int lengthOfLIS_(int[] arr) {
        if (arr.length == 0)
            return 0;
        if (arr.length == 1)
            return 1;
        int n = arr.length;
        int[] dp = new int[n];
        int count = 1;
        dp[0] = arr[0];
        for (int i = 0; i < n; i++) {
            int lo = 0;
            int hi = count;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (dp[mid] < arr[i])
                    lo = mid + 1;
                else
                    hi = mid;
            }
            dp[lo] = arr[i];
            if (lo == count)
                count++;
        }
        return count;
    }

    // russian doll envelops
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (int[] a, int[] b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];
        });
        int n = envelopes.length;
        int[] dp = new int[n];
        int ans = (int) -1e8;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                ans = Math.max(ans, dp[i]);
            }
        }
        return ans;

    }

    // minimum no of increasing subsequences
    // we have to find longest increasing subsequence

    // box stacking problem

    public static int maxHeight(int height[], int width[], int length[], int n) {
        int[][] arr = new int[3 * n][3];
        int ip = 0;
        for (int i = 0; i < n; i++) {
            int p1 = height[i];
            int p2 = width[i];
            int p3 = length[i];
            if (p1 > p2) {
                arr[ip][0] = p2;
                arr[ip][1] = p1;
                arr[ip][2] = p3;
                ip++;
            } else {
                arr[ip][0] = p1;
                arr[ip][1] = p2;
                arr[ip][2] = p3;
                ip++;
            }
            if (p2 > p3) {
                arr[ip][0] = p3;
                arr[ip][1] = p2;
                arr[ip][2] = p1;
                ip++;
            } else {
                arr[ip][0] = p2;
                arr[ip][1] = p3;
                arr[ip][2] = p1;
                ip++;
            }
            if (p1 > p3) {

                arr[ip][0] = p3;
                arr[ip][1] = p1;
                arr[ip][2] = p2;
                ip++;
            } else {
                arr[ip][0] = p1;
                arr[ip][1] = p3;
                arr[ip][2] = p2;
                ip++;
            }
        }

        Arrays.sort(arr, (int[] a, int[] b) -> {
            if (a[0] == b[0])
                return b[1] - a[1];
            return a[0] - b[0];

        });
        int[] dp = new int[arr.length];
        int max = 0;
        dp[0] = arr[0][2];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = arr[i][2];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j][1] < arr[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i][2]);
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }


    
}