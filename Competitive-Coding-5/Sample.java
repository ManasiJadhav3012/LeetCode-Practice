// Time Complexity : O(n) where n is number of nodes in the tree and it will be visited once
// Space Complexity : O(n) for the queue that stores nodes at each level. In the worst case scenario queue can store half of the nodes.
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Followed code tutorial


// Your code here along with comments explaining your approach
// In this solution we will be finding largest value in each row of a binary tree using BFS. We will start by initializing a queue with the root node. For each level
// of the tree we will process all nodes in that level tracking the maximum value. Then we will add left and right children of the current nodes to the queue for 
// the next level. After processing each level we will add the maximum value to the resultant list.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        
        if(root == null) {
            return new ArrayList<>();
        }

        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;

            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();

                max = Math.max(max, curr.val);

                if(curr.left != null) {
                    queue.add(curr.left);
                }

                if(curr.right != null) {
                    queue.add(curr.right);
                }
                
            }

            result.add(max);
        }

        return result;

    }
}


// Time Complexity : O(9^2) = O(81) as board has fixed size 9x9 and algorithm checks each row, column and 3x3grid, separately but still it will be considered as O(1)
// Space Complexity : O(1) as we are only using fixed size boolean arrays of length 9 for each row, column and subgrid
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : Followed code tutorial


// Your code here along with comments explaining your approach
// In this example we will be checking that each row should contain unique numbers, each column should contain unique numbers and each 3x3 grid should contain unique
// numbers. To achieve this goal, in our code we will iterate over the board three time, once for row, once for columns, once for 3x3 grids. We will use a boolean
// array to track whether a number has already appeared or not. If number has already appeared we will immediately return false otherwise at the end we will 
// return true.

class Solution {
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

        for(int i = 0; i < 9; i++) {
            boolean[] b = new boolean[9];

            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    if(b[(int)(board[i][j] - '1')]) {
                        return false;
                    }

                    b[(int)(board[i][j] - '1')] = true;
                }
            }
        }

        for(int j = 0; j < 9; j++) {
            boolean[] b = new boolean[9];

            for(int i = 0; i < 9; i++) {
                if(board[i][j] != '.') {
                    if(b[(int)(board[i][j] - '1')]) {
                        return false;
                    }

                    b[(int)(board[i][j] - '1')] = true;
                }
            }
        }

        for(int block = 0; block < 9; block++) {
            boolean[] b = new boolean[9];

            for(int i = block/3*3; i < block/3*3+3; i++) {
                for(int j = block%3*3; j < block%3*3+3; j++) {
                    if(board[i][j] != '.') {
                        if(b[(int)(board[i][j] - '1')]) {
                            return false;
                        }

                        b[(int)(board[i][j] - '1')] = true;
                    }
                }
            }
        }

        return true;
    }
}