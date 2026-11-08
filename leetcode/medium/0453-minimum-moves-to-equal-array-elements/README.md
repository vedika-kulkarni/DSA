# Minimum Moves to Equal Array Elements

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

You are given an integer array `nums` of size `n`.

Return the  **minimum**  number of moves required to make all array elements  **equal**.

In one move, you must increment `n - 1` elements of the array by 1.

 

 **Example 1:** 

```
Input: nums = [1,2,3]
Output: 3
Explanation: Only three moves are needed (remember each move increments two elements):
[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]

```

 **Example 2:** 

```
Input: nums = [1,1,1]
Output: 0

```

 

 **Constraints:** 

- n == nums.length
- 1 <= nums.length <= 105
- -109 <= nums[i] <= 109
- The answer is guaranteed to fit in a 32-bit integer.

## Solution

**Language:** Java  
**Runtime:** 2 ms (beats 96.56%)  
**Memory:** 46.5 MB (beats 87.14%)  
**Submitted:** 2026-11-08T07:19:55.500Z  

```java
class Solution {
    public int minMoves(int[] nums) {

        int min = nums[0];

        // Find minimum element
        for (int num : nums) {
            min = Math.min(min, num);
        }

        int moves = 0;

        // Calculate total moves
        for (int num : nums) {
            moves += num - min;
        }

        return moves;
    }
}
```

---

[View on LeetCode](https://leetcode.com/problems/minimum-moves-to-equal-array-elements/)