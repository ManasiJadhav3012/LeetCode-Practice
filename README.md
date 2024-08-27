# Stack-1

## Problem1 Daily Temperatures (https://leetcode.com/problems/daily-temperatures/)

We will traverse the temperatures array while maintaining the stack to store the indices of the days with warmer temperature than current temperature. For each day we will pop the indices from the stack as tong as the current temperature is higher than the temperature at the top index in the stack and calculate the number of days until a warmer temperature. Then we will push the current index onto the stack to resolve in the future when a warmer temperature is found.



## Problem2 Next Greater Element II (https://leetcode.com/problems/next-greater-element-ii/)

We will use stack to keep track of indices whose next greater element that hasn't been found yet by iterating through the array twice to accumulate a circular array. For each element if it is greater than the element at the index on the top of the stack then we will pop the stack and set the result for that index. Then we will push the current index onto the stack if it is within the first pass through the array. At the end we will just return resultant array.