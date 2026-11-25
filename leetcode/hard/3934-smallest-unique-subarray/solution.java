
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
