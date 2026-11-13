# Count Non-Decreasing Subarrays After K Operations

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an array `nums` of `n` integers and an integer `k`.

For each subarray of `nums`, you can apply  **up to**  `k` operations on it. In each operation, you increment any element of the subarray by 1.

 **Note**  that each subarray is considered independently, meaning changes made to one subarray do not persist to another.

Return the number of subarrays that you can make  **non-decreasing**  ​​​​​after performing at most `k` operations.

An array is said to be  **non-decreasing**  if each element is greater than or equal to its previous element, if it exists.

 

Original
Java
 **Example 1:** 

 **Input:**  nums = [6,3,1,2,4,4], k = 7

 **Output:**  17

 **Explanation:** 

Out of all 21 possible subarrays of `nums`, only the subarrays `[6, 3, 1]`, `[6, 3, 1, 2]`, `[6, 3, 1, 2, 4]` and `[6, 3, 1, 2, 4, 4]` cannot be made non-decreasing after applying up to k = 7 operations. Thus, the number of non-decreasing subarrays is `21 - 4 = 17`.

 **Example 2:** 

 **Input:**  nums = [6,3,1,3,6], k = 4

 **Output:**  12

 **Explanation:** 

The subarray `[3, 1, 3, 6]` along with all subarrays of `nums` with three or fewer elements, except `[6, 3, 1]`, can be made non-decreasing after `k` operations. There are 5 subarrays of a single element, 4 subarrays of two elements, and 2 subarrays of three elements except `[6, 3, 1]`, so there are `1 + 5 + 4 + 2 = 12` subarrays that can be made non-decreasing.

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 109
- 1 <= k <= 109

## Solution

**Language:** Java  
**Runtime:** 519 ms (beats 7.14%)  
**Memory:** 211.3 MB (beats 7.14%)  
**Submitted:** 2026-11-13T04:21:01.503Z  

```java

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

```

---

[View on LeetCode](https://leetcode.com/problems/count-non-decreasing-subarrays-after-k-operations/)