import java.util.Arrays;

class Solution {
    public int getSecondLargest(int[] arr) {
        Arrays.sort(arr);

        int largest = arr[arr.length - 1];

        // Find the first element smaller than the largest
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] != largest) {
                return arr[i];
            }
        }

        return -1; // No second largest element
    }
}