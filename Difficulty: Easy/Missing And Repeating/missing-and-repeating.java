class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        int n = arr.length;

        long sum = 0, sqSum = 0;

        for (int num : arr) {
            sum += num;
            sqSum += 1L * num * num;
        }

        long expectedSum = 1L * n * (n + 1) / 2;
        long expectedSqSum = 1L * n * (n + 1) * (2L * n + 1) / 6;

        // missing - repeating
        long diff = expectedSum - sum;

        // missing² - repeating²
        long sqDiff = expectedSqSum - sqSum;

        // missing + repeating
        long sumXY = sqDiff / diff;

        long missing = (diff + sumXY) / 2;
        long repeating = missing - diff;

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add((int) repeating);
        ans.add((int) missing);

        return ans;
    }
}