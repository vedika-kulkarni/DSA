# Smallest Unique Subarray

![Difficulty](https://img.shields.io/badge/Difficulty-Hard-red)

## Problem

You are given an integer array `nums`.

Find the  **minimum** length of a subarray that is  **not**   **identical**  to any other  **subarray**  in `nums`.

Return an integer denoting the  **minimum possible length**  of such a  **subarray**.

Two  **subarrays**  are considered identical if they have the same length and the same elements in corresponding positions.

 

Original
Java
 **Example 1:** 

 **Input:**  nums = [3,3,3]

 **Output:**  3

 **Explanation:** 

- Subarrays of length 1: [3] → appears 3 times
- Subarrays of length 2: [3, 3] → appears 2 times
- Subarrays of length 3: [3, 3, 3] → appears once

The subarray `[3, 3, 3]` is unique, so the smallest unique subarray length is 3.

 **Example 2:** 

 **Input:**  nums = [2,1,2,3,3]

 **Output:**  1

 **Explanation:** 

Subarrays of length 1:

- [2] → appears 2 times
- [1] → appears once
- [3] → appears 2 times
The subarray `[1]` is unique, so the smallest unique subarray length is 1.

 **Example 3:** 

 **Input:**  nums = [1,1,2,2,1]

 **Output:**  2

 **Explanation:** 

Subarrays of length 1:

- [1] → appears 3 times
- [2] → appears 2 times

Subarrays of length 2:

- [1, 1] → appears once
- [1, 2] → appears once
- [2, 2] → appears once
- [2, 1] → appears once
There is at least one subarray of length 2 that is unique, so the smallest unique subarray length is 2.

 

 **Constraints:** 

- 1 <= nums.length <= 105
- 1 <= nums[i] <= 105

## Solution

**Language:** Java  
**Runtime:** 418 ms (beats 58.93%)  
**Memory:** 105.3 MB (beats 82.14%)  
**Submitted:** 2026-11-25T04:47:36.698Z  

```java

import java.util.*;

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;

        // Coordinate compression
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Arrays.binarySearch(sorted, nums[i]);
        }

        // Build suffix array
        Integer[] sa = new Integer[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
            rank[i] = a[i];
        }

        for (int k = 1; k < n; k *= 2) {
            final int len = k;
            final int[] r = rank;

            Arrays.sort(sa, (x, y) -> {
                if (r[x] != r[y])
                    return Integer.compare(r[x], r[y]);

                int rx = x + len < n ? r[x + len] : -1;
                int ry = y + len < n ? r[y + len] : -1;

                return Integer.compare(rx, ry);
            });

            int[] nr = new int[n];
            nr[sa[0]] = 0;

            for (int i = 1; i < n; i++) {
                int x = sa[i - 1];
                int y = sa[i];

                boolean diff = r[x] != r[y];

                int rx = x + len < n ? r[x + len] : -1;
                int ry = y + len < n ? r[y + len] : -1;

                if (rx != ry) diff = true;

                nr[y] = nr[x] + (diff ? 1 : 0);
            }

            rank = nr;

            if (rank[sa[n - 1]] == n - 1) break;
            if (k > n / 2) break;
        }

        // Build LCP
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        int[] lcp = new int[n];
        int h = 0;

        for (int i = 0; i < n; i++) {
            int p = pos[i];

            if (p == 0) {
                h = 0;
                continue;
            }

            int j = sa[p - 1];

            while (i + h < n && j + h < n &&
                   a[i + h] == a[j + h]) {
                h++;
            }

            lcp[p] = h;

            if (h > 0) h--;
        }

        // Find minimum unique subarray
        int ans = n;

        for (int i = 0; i < n; i++) {
            int required = 1;

            if (i > 0) {
                required = Math.max(required, lcp[i] + 1);
            }

            if (i + 1 < n) {
                required = Math.max(required, lcp[i + 1] + 1);
            }

            if (sa[i] + required <= n) {
                ans = Math.min(ans, required);
            }
        }

        return ans;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/smallest-unique-subarray/)