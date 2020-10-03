import java.util.*;

public class buyndsell {
    public static void main(String[] args) {

    }

    // leetcode 121
    // one buy and sell allowed
    public int maxProfit(int[] prices) {
        int ik0 = 0;
        int ik1 = Integer.MIN_VALUE;
        for (int val : prices) {
            ik0 = Math.max(ik0, ik1 + val);
            ik1 = Math.max(ik1, -val);
        }
        return ik0;
    }

    // leetcode 122
    // multiple buy and sell allowed
    public int maxProfit_(int[] prices) {
        int ik0 = 0;
        int ik1 = Integer.MIN_VALUE;
        for (int val : prices) {
            int temp = ik0;
            ik0 = Math.max(ik0, ik1 + val);
            ik1 = Math.max(ik1, temp - val);
        }
        return ik0;
    }

    // leetcode 714
    // multiple transactions with fees
    public int maxProfit(int[] prices, int fee) {

        int ik0 = 0;
        int ik1 = Integer.MIN_VALUE;
        for (int val : prices) {
            int temp = ik0;
            ik0 = Math.max(ik0, ik1 + val);
            ik1 = Math.max(ik1, temp - val - fee);
        }
        return ik0;

    }

    // leetcode 309
    // max profit with cooldown
    public int maxProfit_c(int[] prices) {
        int prevwala = 0;
        int temp = 0;
        int ik0 = 0;
        int ik1 = Integer.MIN_VALUE;
        for (int val : prices) {
            prevwala = temp;
            temp = ik0;
            ik0 = Math.max(ik0, ik1 + val);
            ik1 = Math.max(ik1, prevwala - val);
        }
        return ik0;

    }

    // 2 stocks only leetcode 123
    public int maxProfit_2(int[] prices) {
        int ans = 0;
        int i10 = 0;
        int i11 = Integer.MIN_VALUE;
        int i20 = 0;
        int i21 = Integer.MIN_VALUE;
        int prev1 = 0;
        int prev2 = 0;
        for (int ele : prices) {
            // prev2=i20;
            i20 = Math.max(i20, i21 + ele);
            i21 = Math.max(i21, i10 - ele);
            i10 = Math.max(i10, i11 + ele);
            i11 = Math.max(i11, -ele);
        }
        return i20;
    }

    // leetcode 188 k transactions...good question
    public int maxProfit(int k, int[] prices) {
        if (k > prices.length / 2)
            return maxProfit_infi(prices);
        int[] ik0 = new int[k + 1];
        int[] ik1 = new int[k + 1];
        Arrays.fill(ik0, 0);
        Arrays.fill(ik1, Integer.MIN_VALUE);
        for (int ele : prices) {
            for (int ip = k; ip >= 1; ip--) {
                ik0[ip] = Math.max(ik0[ip], ik1[ip] + ele);
                if (k == 1)
                    ik1[1] = Math.max(ik1[1], -ele);
                else
                    ik1[ip] = Math.max(ik1[ip], ik0[ip - 1] - ele);
            }
        }
        return ik0[k];
    }

    public int maxProfit_infi(int[] prices) {
        int ik0 = 0;
        int ik1 = Integer.MIN_VALUE;
        for (int val : prices) {
            int temp = ik0;
            ik0 = Math.max(ik0, ik1 + val);
            ik1 = Math.max(ik1, temp - val);
        }
        return ik0;
    }

}