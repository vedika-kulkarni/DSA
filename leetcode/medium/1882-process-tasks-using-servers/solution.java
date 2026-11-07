import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        int[] ans = new int[m];

        // Free servers: sort by weight, then index
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                }
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: sort by free time
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        // Initially, all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        long time = 0;

        for (int j = 0; j < m; j++) {

            time = Math.max(time, j);

            // Release all servers that have completed their tasks
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();

                available.offer(new int[]{
                    (int) server[1],
                    (int) server[2]
                });
            }

            // If no server is free, wait for the earliest one
            if (available.isEmpty()) {

                time = busy.peek()[0];

                // Release all servers available at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();

                    available.offer(new int[]{
                        (int) server[1],
                        (int) server[2]
                    });
                }
            }

            // Assign the task to the best available server
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[j] = index;

            // Server becomes free after processing the task
            busy.offer(new long[]{
                time + tasks[j],
                weight,
                index
            });
        }

        return ans;
    }
}