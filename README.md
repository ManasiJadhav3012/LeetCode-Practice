# DP-8

## Problem1 Arithmetic Slices (https://leetcode.com/problems/arithmetic-slices/)

Explain your approach in 3 lines: In this solution we will be using dynammic programming to count the number of arithmetic slices in an array. Arithmetic slice -> subarray of at least three elements where the difference between consecutive elements is constant. In the dp array at dp[i] we will store number of arithmetic slices ending at index i. If the current subarray forms an arithmetic slice then we will increment dp[i] based on dp[i+1]. At the end we will return count which is accumulated by summing up values in the dp array.

Time Complexity: O(n) where n is number of elements in nums array as we traverse array once updating dp array and count in a single pass

Space Complexity: O(n) as this is space required to store dp array

Approached Code:

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        int count = 0;
        int n = nums.length;

        if(n < 3) {
            return 0;
        }

        // for(int i = 0; i < n-2; i++) {
        //     int diff = nums[i+1] - nums[i];
        //     for(int j = i+2; j < n; j++) {
        //         if(nums[j] - nums[j-1] == diff) {
        //             count++;
        //         } else {
        //             break;
        //         }
        //     }
        // }


        int[] dp = new int[n];

        for(int i = n-3; i >= 0; i--) {
            if(nums[i+1] - nums[i] == nums[i+2] - nums[i+1]) {
                dp[i] = dp[i+1] + 1;
            } else {
                dp[i] = 0;
            }

            count = count + dp[i];
        }

        return count;
    }
}



## Problem 2 Triangle (https://leetcode.com/problems/triangle/)

Explain your approach in 3 lines: In this problem we can use bottom-up approach and top-down approach as well. We will be solving this using top down dynamic programming approach with memoization. In the recursive helper function we are computing minimum path sum starting from the element at position (i, j) down to the bottom of the triangle. We will be using memoization to store already computed results to avoid redundant calculations. In the base case we will return when i reaches bottom of the triangle. In the solution we are exploring all possible paths that is 2 at each node and then we are taking minimum between those.

Time Complexity: O(n^2) where n is number of rows in triangle as recursion will be called at least once on each cell.

Space Complexity: O(n^2) as we are using this space for memoization table

Approached Code:

class Solution {
    int[][] memo;
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();
        this.memo = new int[n][n];

        // Bottom-Up Approach

        // for(int i = n-2; i >= 0; i--) {
        //     List<Integer> secondLast = triangle.get(i);
        //     List<Integer> last = triangle.get(i+1);

        //     for(int j = 0; j < secondLast.size(); j++) {
        //         int currMin = Math.min(last.get(j), last.get(j+1));
        //         secondLast.set(j, currMin + secondLast.get(j));
        //     }
        // }

        // return triangle.get(0).get(0);



        // Top-Down Approach

        // for(int i = 1; i < n; i++) {
        //     for(int j = 0; j <= i; j++) {
        //         if(j == 0) {
        //             triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j));
        //         } else if(j == i) {
        //             triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i-1).get(j-1));
        //         } else {
        //             triangle.get(i).set(j, triangle.get(i).get(j) + 
        //             Math.min(triangle.get(i-1).get(j-1), triangle.get(i-1).get(j)));

        //         }
        //     }
        // }

        // int min = Integer.MAX_VALUE;

        // for(int j = 0; j < n; j++) {
        //     min = Math.min(min, triangle.get(n-1).get(j));
        // }

        // return min;



        // Top-Down DP with memo

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                memo[i][j] = -1;
            }
        }

        return helper(triangle, 0, 0);

    }

    private int helper(List<List<Integer>> triangle, int i, int j) {
        // base 
        if(i == triangle.size()) {
            return 0;
        }

        if(memo[i][j] != -1) {
            return memo[i][j];
        }

        // logic
        int case1 = helper(triangle, i+1, j);

        int case2 = helper(triangle, i+1, j+1);

        memo[i][j] = triangle.get(i).get(j) + Math.min(case1, case2);

        return memo[i][j];
    }
}