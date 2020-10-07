public class leetcode {
    public static void main(String[] args) {

    }

    // leetcode 45
    public int jump(int[] nums) {
        int max = 0;
        int fullmax = 0;
        int jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            fullmax = Math.max(fullmax, i + nums[i]);
            if (i == max) {
                max = fullmax;
                jump++;
            }
        }
        return jump;
    }

    // https://practice.geeksforgeeks.org/problems/consecutive-1s-not-allowed1912/1
    long countStrings(int n) {
        long[] dp1 = new long[n];
        long[] dp0 = new long[n];
        dp0[0] = 1;
        dp1[0] = 1;
        int num = 1000000000 + 7;
        for (int i = 1; i < n; i++) {
            dp0[i] = (dp0[i - 1] + dp1[i - 1]) % num;
            dp1[i] = dp0[i - 1] % num;
        }

        return (dp1[n - 1] + dp0[n - 1]) % num;
    }

    // catalan numbers
    // method 1
    public static int cn(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int ip = 2; ip <= n; ip++) {
            int i = 0;
            int j = ip - 1;
            int ans = 0;
            while (j >= 0) {
                ans += dp[i] * dp[j];
                i++;
                j--;
            }
            dp[ip] = ans;
        }
        return dp[n];
    }

    // catalon number
    public static int cn_(int n) {
        int ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * (n + i);
            ans = ans / i;
        }
        return ans / (n + 1);
    }

    // paint fence
    public int numWays(int n, int k) {
        int[] dp2same = new int[n + 1];
        int[] dp2diff = new int[n + 1];

        if (n == 1)
            return k;
        dp2same[2] = k;
        dp2diff[2] = k * (k - 1);

        for (int i = 3; i <= n; i++) {
            dp2same[i] = dp2diff[i - 1];
            dp2diff[i] = (k - 1) * (dp2same[i - 1] + dp2diff[i - 1]);

        }

        return dp2diff[n] + dp2same[n];
    }

    // paint house lintcode
    public int minCost(int[][] costs) {

        if (costs.length == 0)
            return 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < costs.length; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);
            costs[i][2] += Math.min(costs[i - 1][1], costs[i - 1][0]);

        }

        return Math.min(costs[costs.length - 1][0], Math.min(costs[costs.length - 1][1], costs[costs.length - 1][2]));
    }

    // leetcode 650
    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i / 2; j >= 1; j--) {
                if (i % j == 0) {
                    dp[i] = Math.min(dp[i], dp[j] + i / j);
                }
            }
        }
        return dp[n];
    }

}