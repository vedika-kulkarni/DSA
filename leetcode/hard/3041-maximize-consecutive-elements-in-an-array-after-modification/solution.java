
import java.util.Arrays;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[1000003];

        int ans = 1;

        for (int x : nums) {
            dp[x + 1] = Math.max(dp[x + 1], dp[x] + 1);
            dp[x] = Math.max(dp[x], dp[x - 1] + 1);

            ans = Math.max(ans, Math.max(dp[x], dp[x + 1]));
        }

        return ans;
    }
}
