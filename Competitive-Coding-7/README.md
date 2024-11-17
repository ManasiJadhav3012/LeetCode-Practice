# Competitive-Coding-7

Please submit the interview problems posted in slack channel here. The problems and statements are intentionally not shown here so that students are not able to see them in advance 


## Problem 1
Meeting Rooms 2 (https://leetcode.com/problems/meeting-rooms-ii/description/)

Approached Solution: The solution will solve the problem of finding the minimum number of meeting rooms required by using a greedy approach. First, we will separate the start and end times of the meetings into two arrays (start[] and end[]). Both arrays will be sorted to allow efficient comparison of when meetings start and end. A two-pointer approach can be used: one pointer (startIdx) will iterate through the sorted start times, and the other pointer (endIdx) will track the end times. For each meeting start time, if it occurs before the earliest end time (i.e., a new meeting overlaps with an ongoing meeting), an additional room will be needed. Otherwise, an existing room is freed by moving the endIdx pointer. The number of rooms required will be tracked and returned.

Time Complexity: O(nlogn) as sorting the start and end arrays will be take O(nlogn) time where n is the number of meetings. 

Space Complexity: O(n) as we are using additional arrays - start and end to store the start and end times of the meeting.

Approached Code:

class Solution {
    public int minMeetingRooms(int[][] intervals) {

        if(intervals.length == 0) {
            return 0;
        }

        int n = intervals.length;

        // Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // for(int i = 0; i < n; i++) {
        //     if(!pq.isEmpty() && pq.peek() <= intervals[i][0]) {
        //         pq.poll();
        //     }

        //     pq.add(intervals[i][1]);
        // }

        // return pq.size();




        int[] start = new int[n];
        int[] end = new int[n];

        for(int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0;

        for(int startIdx = 0, endIdx = 0; startIdx < n; startIdx++) {
            if(start[startIdx] < end[endIdx]) {
                rooms++;
            } else {
                endIdx++;
            }
        }

        return rooms;


    }
}



## Problem 2
Kth Smallest Element in Sorted Matrix (https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/)

Approached Solution: The solution will use binary search combined with a counting technique to find the kth smallest element in a sorted 2D matrix. The matrix is sorted both row-wise and column-wise. The binary search will be applied on the value range of the matrix, from the smallest element (matrix[0][0]) to the largest element (matrix[n-1][n-1]). For each middle value (mid), the countLessEqual function will count how many elements in the matrix are less than or equal to mid. If this count equals k, it means mid is the kth smallest element. If the count is less than k, the search range is adjusted to explore larger values, and if the count is greater, it focuses on smaller values.

Time Complexity: O(nlog(max - min)) where max is the largest element and min is the smallest element in the matrix.

Space Complexity: O(1) as this solution uses constant space.

Approached Code:

class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;

        int start = matrix[0][0];
        int end = matrix[n-1][n-1];

        while(start < end) {
            int mid = start + (end - start)/2;

            int[] smallLargePair = {matrix[0][0], matrix[n - 1][n - 1]};

            int count = this.countLessEqual(matrix, mid, smallLargePair);

            if (count == k) return smallLargePair[0];

            if (count < k) start = smallLargePair[1];
            else end = smallLargePair[0];
        }
        return start;
    }

    private int countLessEqual(int[][] matrix, int mid, int[] smallLargePair) {
        int count = 0;
        int n = matrix.length;
        int row = n - 1;
        int col = 0;

        while(row >= 0 && col < n) {
            if(matrix[row][col] > mid) {
                smallLargePair[1] = Math.min(smallLargePair[1], matrix[row][col]);
                row--;
            } else {
                smallLargePair[0] = Math.max(smallLargePair[0], matrix[row][col]);
                count = count + row + 1;
                col++;
            }
        }

        return count;
    }
}


