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


    
}