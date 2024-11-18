// Time Complexity : O(n) where n is length of an array as each element will be pushed and popped out of the stack at least once
// Space Complexity : O(n) as we require auxillary space for stack
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : Just followed same approach discussed in class


// Your code here along with comments explaining your approach
// We will traverse the temperatures array while maintaining the stack to store the indices of the days with warmer temperature than current temperature. For each
// day we will pop the indices from the stack as tong as the current temperature is higher than the temperature at the top index in the stack and calculate
// the number of days until a warmer temperature. Then we will push the current index onto the stack to resolve in the future when a warmer temperature is found.

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        
        Stack<Integer> st = new Stack<>();
        int n = temperatures.length;
        int[] result = new int[n];

        for(int i = 0; i < n; i++) {
            
            while(!st.isEmpty() && temperatures[i] > temperatures[st.peek()]) {
                int popped = st.pop();
                result[popped] = i - popped;
            }

            st.push(i);
        }

        return result;

    }
}



// Time Complexity : O(n) where n is length of an array as each element will be pushed and popped out of the stack at least once
// Space Complexity : O(n) as we require auxillary space for stack
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : followed approach discussed in class


// Your code here along with comments explaining your approach
// We will use stack to keep track of indices whose next greater element that hasn't been found yet by iterating through the array twice to accumulate a circular
// array. For each element if it is greater than the element at the index on the top of the stack then we will pop the stack and set the result for that index.
// Then we will push the current index onto the stack if it is within the first pass through the array. At the end we will just return resultant array.

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        
        Stack<Integer> st = new Stack<>();
        int n = nums.length;
        int[] result = new int[n];

        Arrays.fill(result, -1);

        for(int i = 0; i < 2*n; i++) {
            int idx = i%n;

            while(!st.isEmpty() && nums[idx] > nums[st.peek()]) {
                int popped = st.pop();
                result[popped] = nums[idx];
            }

            if(i < n) {
                st.push(idx);
            }
        }

        return result;

    }
}