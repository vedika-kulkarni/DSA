
import java.util.Arrays;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, -1_000_000);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            int price = items[i][1];

            // First copy: gives additional free copies.
            for (int b = budget; b >= price; b--) {
                if (dp[b - price] >= 0) {
                    dp[b] = Math.max(
                        dp[b],
                        dp[b - price] + 1 + free[i]
                    );
                }
            }

            // Further copies: only give purchased copies.
            for (int b = price; b <= budget; b++) {
                if (dp[b - price] >= 0) {
                    dp[b] = Math.max(dp[b], dp[b - price] + 1);
                }
            }
        }

        int ans = 0;
        for (int x : dp) {
            ans = Math.max(ans, x);
        }

        return ans;
    }
}
