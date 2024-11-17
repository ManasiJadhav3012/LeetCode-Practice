# BFS-2

## Problem 1

Binary Tree Right Side View (https://leetcode.com/problems/binary-tree-right-side-view/)

Time Complexity: O(n) where n is number of nodes as each node will be processed once

Space Complexity: O(D) in case of BFS where D will be maximum width of the tree for queue and O(H) in case of DFS where H will be height of the tree for recursive stack.

Explaination: In the case of BFS solution we will traverse the tree level by level adding the elements in the queue and maintain the size at each level. Then, at each level with size we will add the last node's value to the result list. Then, in the depth first search we will give more importance to the right subtree and add the first node that we will encounter at the recursion at each level to the result. If the size of result list is equal to the current level then we will add the node value as it will be the rightmost node at each level.


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
    public List<Integer> rightSideView(TreeNode root) {

        // BFS Solution

        // List<Integer> result = new ArrayList<>();
        // Queue<TreeNode> queue = new LinkedList<>();

        // if(root == null) return result;

        // queue.add(root);

        // while(!queue.isEmpty()) {
        //     int size = queue.size();
            
        //     for(int i = 0; i < size; i++) {
        //         TreeNode curr = queue.poll();

        //         if(i == size-1) {
        //             result.add(curr.val);
        //         }

        //         if(curr.left != null) {
        //             queue.add(curr.left);
        //         }

        //         if(curr.right != null) {
        //             queue.add(curr.right);
        //         }
        //     }
        // }

        // return result;




        // DFS Solution
        List<Integer> result = new ArrayList<>();

        if(root == null) return result;

        dfs(root, 0, result);

        return result;

    }

    private void dfs(TreeNode root, int level, List<Integer> result) {
        //base 
        if(root == null) return;

        //logic
        if(result.size() == level) {
            result.add(root.val);
        }

        dfs(root.right, level+1, result);
        dfs(root.left, level+1, result);
    }
}


## Problem 2

Cousins in binary tree (https://leetcode.com/problems/cousins-in-binary-tree/)


Time Complexity: O(n) where n is number of nodes in the tree.

Space Complexity: O(D) where D is the mximum width of the tree for the queue and in case of DFS O(h) where h is the height of the tree for the recursive stack.

Explaination: In case of DFS solution we will recursively traverse the tree to find the depth and parent of each target node. Then we will check if they have the same depth but different parents. To achieve this we will maintain depth of x and y and parent nodes of x and y. In BFS solution we will do level-order traversal of the tree using queue tracking if x and y are found at the same level and ensuring they don't share the same parent. In both approaches we will maintain parent nodes of x and y to ensure they are not siblings and levels of x and y to ensure they are at same level.


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
    int x_depth;
    int y_depth;
    TreeNode x_parent;
    TreeNode y_parent;

    public boolean isCousins(TreeNode root, int x, int y) {
        
        // DFS Solution

        // if (root == null) return false;

        // dfs(root, null, 0, x, y);

        // return x_parent != y_parent && x_depth == y_depth;


        // BFS Solution

        if(root == null) return false;

        Queue<TreeNode> queue = new LinkedList<>();
        // Queue<TreeNode> parentQueue = new LinkedList<>();

        // TreeNode px = null;
        // TreeNode py = null;

        queue.add(root);
        // parentQueue.add(null);

        while(!queue.isEmpty()) {
            int size = queue.size();
            boolean x_found = false;
            boolean y_found = false;

            for(int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                // TreeNode pCurr = parentQueue.poll();

                if(curr.val == x) {
                    // px = pCurr;
                    x_found = true;
                }

                if(curr.val == y) {
                    // py = pCurr;
                    y_found = true;
                }

                if(curr.left != null && curr.right != null) {
                    if(curr.left.val == x && curr.right.val == y) return false;
                    if(curr.left.val == y && curr.right.val == x) return false;
                }

                if(curr.left != null) {
                    queue.add(curr.left);
                    // parentQueue.add(curr);
                }

                if(curr.right != null) {
                    queue.add(curr.right);
                    // parentQueue.add(curr);
                }
            }

            // if(px != null && py != null) {
            //     return px != py;
            // }

            // if(px != null || py != null) {
            //     return false;
            // }

            if(x_found && y_found) return true;
            if(x_found || y_found) return false;
        }

        return false;

    }

    private void dfs(TreeNode root, TreeNode parent, int level, int x, int y) {
        //base 
        if(root == null) return;

        //logic
        if(root.val == x) {
            x_parent = parent;
            x_depth = level;
        }

        if(root.val == y) {
            y_parent = parent;
            y_depth = level;
        }

        dfs(root.left, root, level+1, x, y);

        if(x_parent == null || y_parent == null) {
            dfs(root.right, root, level+1, x, y);
        }

    }
}