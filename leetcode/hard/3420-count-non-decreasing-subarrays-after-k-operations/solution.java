
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int log = 1;

        while ((1 << log) <= n) {
            log++;
        }

        // Prefix sum
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Next Greater Element
        int[] nge = new int[n];
        int[] st = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[st[top]] <= nums[i]) {
                top--;
            }

            nge[i] = (top == -1) ? n : st[top];
            st[++top] = i;
        }

        // Binary lifting tables
        int[][] jump = new int[log][n + 1];
        long[][] cost = new long[log][n + 1];

        // Initialize sentinel position
        for (int j = 0; j < log; j++) {
            jump[j][n] = n;
        }

        for (int i = 0; i < n; i++) {
            jump[0][i] = nge[i];

            cost[0][i] =
                (long) nums[i] * (nge[i] - i)
                - (prefix[nge[i]] - prefix[i]);
        }

        // Build binary lifting tables
        for (int j = 1; j < log; j++) {
            for (int i = 0; i < n; i++) {
                int mid = jump[j - 1][i];

                jump[j][i] = jump[j - 1][mid];

                cost[j][i] =
                    cost[j - 1][i] + cost[j - 1][mid];
            }
        }

        long ans = 0;

        // Binary search for each starting position
        for (int l = 0; l < n; l++) {
            int low = l;
            int high = n - 1;
            int best = l - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                long operations = 0;
                int pos = l;

                // Calculate complete segments
                for (int j = log - 1; j >= 0; j--) {
                    if (jump[j][pos] <= mid) {
                        operations += cost[j][pos];
                        pos = jump[j][pos];
                    }
                }

                // Calculate remaining partial segment
                operations += (long) nums[pos] * (mid - pos + 1)
                        - (prefix[mid + 1] - prefix[pos]);

                if (operations <= k) {
                    best = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            ans += best - l + 1;
        }

        return ans;
    }
}
