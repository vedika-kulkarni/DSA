# Pascal's Triangle II

![Difficulty](https://img.shields.io/badge/Difficulty-Easy-green)

## Problem

Given an integer `rowIndex`, return the `rowIndexth` (**0-indexed**) row of the  **Pascal's triangle**.

In  **Pascal's triangle**, each number is the sum of the two numbers directly above it as shown:

 

Original
Java
 **Example 1:** 

```
Input:int rowIndex = 3;Output: [1,3,3,1]

```

 **Example 2:** 

```
Input:int rowIndex = 0;Output: [1]

```

 **Example 3:** 

```
Input:int rowIndex = 1;Output: [1,1]

```

 

 **Constraints:** 

- 0 <= rowIndex <= 33

 

 **Follow up:**  Could you optimize your algorithm to use only `O(rowIndex)` extra space?

## Solution

**Language:** Java  
**Runtime:** 1 ms (beats 64.07%)  
**Memory:** 42.1 MB (beats 80.88%)  
**Submitted:** 2026-11-12T09:36:28.366Z  

```java

import java.util.*;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();

        list.add(1);

        for (int i = 1; i <= rowIndex; i++) {
            for (int j = list.size() - 1; j >= 1; j--) {
                list.set(j, list.get(j) + list.get(j - 1));
            }

            list.add(1);
        }

        return list;
    }
}

```

---

[View on LeetCode](https://leetcode.com/problems/pascals-triangle-ii/)