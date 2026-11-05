import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        Arrays.sort(arr2);

        int count = 0;

        for (int num : arr1) {
            int index = Arrays.binarySearch(arr2, num);

            if (index >= 0) {
                continue;
            }

            index = -index - 1;

            boolean valid = true;

            // Check the closest element on the right
            if (index < arr2.length && Math.abs(arr2[index] - num) <= d) {
                valid = false;
            }

            // Check the closest element on the left
            if (index > 0 && Math.abs(arr2[index - 1] - num) <= d) {
                valid = false;
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}