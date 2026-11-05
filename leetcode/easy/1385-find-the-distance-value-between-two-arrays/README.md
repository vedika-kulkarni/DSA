# Find the Distance Value Between Two Arrays

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given two integer arrays `arr1` and `arr2`, and the integer `d`,  *return the distance value between the two arrays*.

The distance value is defined as the number of elements `arr1[i]` such that there is not any element `arr2[j]` where `|arr1[i]-arr2[j]| <= d`.

 

 **Example 1:** 

```
Input: arr1 = [4,5,8], arr2 = [10,9,1,8], d = 2
Output: 2
Explanation: 
For arr1[0]=4 we have: 
|4-10|=6 > d=2 
|4-9|=5 > d=2 
|4-1|=3 > d=2 
|4-8|=4 > d=2 
For arr1[1]=5 we have: 
|5-10|=5 > d=2 
|5-9|=4 > d=2 
|5-1|=4 > d=2 
|5-8|=3 > d=2
For arr1[2]=8 we have:
|8-10|=2 <= d=2
|8-9|=1 <= d=2
|8-1|=7 > d=2
|8-8|=0 <= d=2

```

 **Example 2:** 

```
Input: arr1 = [1,4,2,3], arr2 = [-4,-3,6,10,20,30], d = 3
Output: 2

```

 **Example 3:** 

```
Input: arr1 = [2,1,100,3], arr2 = [-5,-2,10,-3,7], d = 6
Output: 1

```

 

 **Constraints:** 

- 1 <= arr1.length, arr2.length <= 500
- -1000 <= arr1[i], arr2[j] <= 1000
- 0 <= d <= 100

## Solution

**Language:** Java  
**Runtime:** 8 ms (beats 24.19%)  
**Memory:** 46.1 MB (beats 61.34%)  
**Submitted:** 2026-11-05T01:59:38.375Z  

```java
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
```

---

[View on LeetCode](https://leetcode.com/problems/find-the-distance-value-between-two-arrays/)