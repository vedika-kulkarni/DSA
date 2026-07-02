import java.util.*;

class Solution {
    static ArrayList<Integer> leaders(int arr[]) {
        ArrayList<Integer> ans = new ArrayList<>();

        int max = arr[arr.length - 1];
        ans.add(max);

        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] >= max) {
                ans.add(arr[i]);
                max = arr[i];
            }
        }

        Collections.reverse(ans);
        return ans;
    }
}