package dp;

public class c_dp {

    // leetcode 312

    // memoised solution

    public int maxCoins_(int[] nums) {
        int[] arr = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        return ball(arr, 0, arr.length - 1, new int[arr.length][arr.length]);

    }

    public int ball(int[] nums, int start, int end, int[][] dp) {

        if (start + 1 == end)
            return dp[start][end] = 0;

        if (dp[start][end] != 0)
            return dp[start][end];
        int min = 0;
        for (int i = start + 1; i < end; i++) {
            int left = ball(nums, start, i, dp);
            int right = ball(nums, i, end, dp);

            min = Math.max(min, left + right + nums[i] * nums[start] * nums[end]);
        }
        dp[start][end] = min;
        return min;
    }

    // dp solution
    public int maxCoins(int[] nums) {
        int[] arr = new int[nums.length + 2];
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = nums[i];
        }
        arr[0] = 1;
        arr[arr.length - 1] = 1;
        int[][] dp = new int[arr.length][arr.length];

        int ans = ball(arr, dp);

        return ans;
    }

    public int ball(int[] nums, int[][] dp) {

        int n = nums.length;

        for (int gap = 2; gap < n; gap++) {
            for (int start = 0, end = start + gap; end < n; start++, end++) {
                // int end=start+gap;
                int min = 0;
                for (int i = start + 1; i < end; i++) {

                    int left = dp[start][i];
                    int right = dp[i][end];
                    min = Math.max(min, right + left + nums[start] * nums[i] * nums[end]);
                }

                dp[start][end] = min;
            }

        }
        return dp[0][nums.length - 1];
    }
}
