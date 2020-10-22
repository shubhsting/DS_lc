package dp;

//cut type/given point ko baad mein solve kro phle left right nikaal lo
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

    // lintcode 725 boolean parenthesisation
    public int countParenth(char[] symb, char[] oper) {
        int n = symb.length;
        return cp(symb, oper, 0, symb.length - 1, true, new int[n][n], new int[n][n]);
    }

    public int cp(char[] symb, char[] oper, int start, int end, boolean flag, int[][] dpt, int[][] dpf) {
        if (start == end && flag == true) {
            return dpt[start][end] = (symb[start] == 'T' ? 1 : 0);
        }

        if (start == end && flag == false) {
            return dpf[start][end] = (symb[start] == 'F' ? 1 : 0);
        }

        if (flag == true && dpt[start][end] != 0)
            return dpt[start][end];
        if (flag == false && dpf[start][end] != 0)
            return dpf[start][end];
        int count = 0;
        for (int i = start; i < end; i++) {
            int lefttrue = cp(symb, oper, start, i, true, dpt, dpf);
            int righttrue = cp(symb, oper, i + 1, end, true, dpt, dpf);
            int leftfalse = cp(symb, oper, start, i, false, dpt, dpf);
            int rightfalse = cp(symb, oper, i + 1, end, false, dpt, dpf);

            if (oper[i] == '&') {
                if (flag == true)
                    count += lefttrue * righttrue;
                else
                    count += lefttrue * rightfalse + righttrue * leftfalse + leftfalse * rightfalse;
            }

            else if (oper[i] == '|') {
                if (flag == false)
                    count += leftfalse * rightfalse;
                else
                    count += lefttrue * rightfalse + righttrue * leftfalse + lefttrue * righttrue;
            }

            else {
                if (flag == false)
                    count += lefttrue * righttrue + leftfalse * rightfalse;
                else
                    count += lefttrue * rightfalse + righttrue * leftfalse;
            }
        }
        if (flag == true)
            dpt[start][end] = count;
        if (flag == false)
            dpf[start][end] = count;
        return count;
    }

    // egg drop problem
    public int superEggDrop(int K, int N) {
        if (K == 1)
            return N;
        // return superEggDrop_(K,N,new int[K+1][N+1]);
        return superEggDrop_(K, N);
    }

    // public int superEggDrop_(int K,int N,int[][] dp) {
    // if(K<=1 ||N<=1) return dp[K][N]=N;
    // if(dp[K][N]!=0) return dp[K][N];
    // int ans=Integer.MAX_VALUE;
    // for(int f=1;f<=N;f++){
    // int notbreak=superEggDrop_(K,N-f,dp);
    // int breakh=superEggDrop_(K-1,f-1,dp);
    // int minv=Math.max(notbreak,breakh)+1;
    // ans=Math.min(ans,minv);
    // }
    // return dp[K][N]=ans;
    // }

    public int superEggDrop_(int K, int N) {
        int[][] dp = new int[N + 1][K + 1];
        // for(int moves=0;moves<=N;moves++){
        // dp[moves][0]=0;
        // dp[moves][1]=moves;
        // }

        // for(int eggs=0;eggs<=K;eggs++){
        // dp[0][eggs]=0;
        // dp[1][eggs]=1;
        // }
        for (int moves = 1; moves <= N; moves++) {
            for (int eggs = 1; eggs <= K; eggs++) {
                dp[moves][eggs] = dp[moves - 1][eggs] + dp[moves - 1][eggs - 1] + 1;
                if (dp[moves][eggs] >= N)
                    return moves;
            }
        }
        return -1;
    }

}
