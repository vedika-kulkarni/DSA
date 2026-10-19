
class Solution {
    void pushZerosToEnd(int[] arr) {
        int[] result = new int[arr.length];
        int j = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                result[j] = arr[i];
                j++;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = result[i];
        }
    }
}
