import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = logs.length;
        int q = queries.length;

        int[][] sortedQueries = new int[q][2];

        for (int i = 0; i < q; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> a[0] - b[0]);

        int[] ans = new int[q];

        Map<Integer, Integer> map = new HashMap<>();

        int left = 0, right = 0;
        int activeServers = 0;

        for (int[] query : sortedQueries) {

            int t = query[0];

            // Add logs with time <= t
            while (right < m && logs[right][1] <= t) {
                int id = logs[right][0];

                map.put(id, map.getOrDefault(id, 0) + 1);

                if (map.get(id) == 1) {
                    activeServers++;
                }

                right++;
            }

            // Remove logs with time < t - x
            while (left < right && logs[left][1] < t - x) {
                int id = logs[left][0];

                map.put(id, map.get(id) - 1);

                if (map.get(id) == 0) {
                    map.remove(id);
                    activeServers--;
                }

                left++;
            }

            ans[query[1]] = n - activeServers;
        }

        return ans;
    }
}