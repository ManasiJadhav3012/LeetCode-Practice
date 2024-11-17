# Competetive-Coding-10

Please submit the interview problems posted in slack channel here. The problems and statements are intentionally not shown here so that students are not able to see them in advance 


## Problem 1 - Array - (https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/)

Explain the algorithm in 3 lines: We will iterate through the price array and identify all pairs of consecutive days where buying on the first day and selling on the next day yields a profit. Then we will accumulate the profit from all such transaction. In the result we will store profit achievable by making as many profitable trades as possible using greedy algorithm.

Time Complexity: O(n) where n is number of days as the array is traversed once

Space Complexity: O(1) as we are not using any auxillary space

Approached Code: 

class Solution {
    public int maxProfit(int[] prices) {
        int max = 0;
        int n = prices.length;

        for(int i = 0; i < n-1; i++) {
            if(prices[i] < prices[i+1]) {
                max = max + prices[i+1] - prices[i];
            }
        }

        return max;
    }
}



## Problem 2 - Design - (https://leetcode.com/problems/peeking-iterator/description/)

Explain the algorithm in 3 lines: We will maintain current variable in PeekingIterator class that will store the next element in the iteration allowing peek function to return this element without advancing the iterator. The next method returns the current element and advances the iterator updating the current variable to the next element or null if there are no more elements available. hasNext() method checks if the current variable is not null which indicates that there are more elements available.

Time Complexity: O(1) for all operations as we are doing it in constant time

Space Complexity: O(1) as we are not using any auxillary space

Approached Code: 

// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html

class PeekingIterator implements Iterator<Integer> {

    private Integer current;
    private Iterator<Integer> iterator;

	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
        this.iterator = iterator;
        if(this.iterator.hasNext()) {
            this.current = this.iterator.next();
        }
	    
	}
	
    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return current;
	}
	
	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    Integer old = current;

        if(iterator.hasNext()) {
            current = iterator.next();
        } else {
            current = null;
        }

        return old;
	}
	
	@Override
	public boolean hasNext() {
	    return current != null;
	}
}


