# Heaps-1

## Problem1 
Kth largest in Array (https://leetcode.com/problems/kth-largest-element-in-an-array/)

Approached Solution: This problem can be solved by 2 approaches - min-heap solution and max-heap solution. In case of min-heap solution we can use min-heap to keep only the largest k elements. As each element is added if the heap size exceeds k, the smallest element is removed. At the end, the root of the heap is returned being the kth largest element. In case of max-heap solution we will construct max-heap to store all elements. As elements are removed from the heap, the smallest of the largest k elements is tracked to obtain the kth largest element.

Time Complexity: O(nlogk) for the min heap solution as it maintains a heap of size k and O(nlogn) for the max-heap solution.

Space Complexity: O(k) for the min-heap solution for storing k elements and O(n) for the max-heap solution to store all the elements.

Approached Code:

class Solution {
    public int findKthLargest(int[] nums, int k) {

        // Min Heap Solution
        
        // PriorityQueue<Integer> pq = new PriorityQueue<>();

        // for(int i = 0; i < nums.length; i++) {
        //     pq.add(nums[i]);

        //     if(pq.size() > k) {
        //         pq.poll();
        //     }

        //     // System.out.println("Elements in the PriorityQueue:");
        //     // for (Integer element : pq) {
        //     //     System.out.println(element);
        //     // }

        // }

        // return pq.peek();


        // Max Heap Solution

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int result = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);

            if(pq.size() > nums.length - k) {
                result = Math.min(result, pq.poll());
            }
        }

        return result;

    }
}



## Problem2

Merge k Sorted Lists(https://leetcode.com/problems/merge-k-sorted-lists/)

Approached Solution: In this solution we will merge k sorted linked lists into a single sorted linked list using two approaches - a heap-based approach and a pairwise merging approach. In Heap solution, we will use a min-heap (priority queue) to store the smallest nodes from each list. Initially we will add the head of each non-null list to the head and then we will repeatedly remove the smallest node from the heap and append it to the merged list and if the removed node has a next node we will add that next node to the heap. This process will continue until the heap is empty. In case of pairwise merge solution we will sequentially merge each list into a cumulative merged list using the merge helper function. This helper function will merge two sorted lists by comparing node values and appending the smaller node to the merged result.

Time Complexity: O(Nlogk) for the heap solution where N is the total number of nodes across all lists, and k is the number of lists. O(Nlogk) in the optimal divide-and-conquer approach but the code will run in O(Nk) due to sequential merging.

Space Complexity: O(k) for the heap and O(1) for divide and conquer approach. But in case of divide and conquer approach O(k) space will be taken for recursive stack.

Approached Code:

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        
        // Heap Solution

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (ListNode a, ListNode b) -> a.val - b.val);

        for(ListNode li: lists) {
            if(li != null) {
                pq.add(li);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(!pq.isEmpty()) {
            ListNode minHead = pq.poll();
            curr.next = minHead;
            curr = curr.next;

            if(minHead.next != null) {
                pq.add(minHead.next);
            }
        }

        return dummy.next;




        // ListNode merged = new ListNode(Integer.MIN_VALUE);

        // for(ListNode head: lists) {
        //     if(head != null) {
        //         merged = merge(merged, head);
        //     }
        // }

        // return merged.next;

    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(head1 != null && head2 != null) {
            if(head1.val <= head2.val) {
                curr.next = head1;
                head1 = head1.next;
            } else {
                curr.next = head2;
                head2 = head2.next;
            }

            curr = curr.next;
        }

        if(head1 != null) {
            curr.next = head1;
        }

        if(head2 != null) {
            curr.next = head2;
        }

        return dummy.next;
    }
}


