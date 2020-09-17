public class painterpartition {
    // A->no of painters
    // B->time taken by each painter
    // c->array of tiles to be painted
    public int paint(int A, int B, int[] C) {
        int max_ = Integer.MIN_VALUE;
        long sum = 0;
        for (int ele : C) {
            max_ = Math.max(ele, max_);
            sum += max_;
        }
        long li = max_;
        long ri = sum;
        long ans = 0;
        while (li <= ri) {
            long mid = li + (ri - li) / 2;
            long temp = 0;
            int count = 1;
            for (int i = 0; i < C.length; i++) {
                if (C[i] + temp > mid) {
                    temp = 0;
                    count++;
                }
                temp += C[i];
            }

            if (count > A)
                li = mid + 1;
            else {
                ri = mid - 1;
                ans = mid;
            }
        }
        ans = ans * B;
        ans = ans % 10000003;
        return (int) ans;
    }
}
