# BFS-1
# Problem 1
Binary Tree Level Order Traversal (https://leetcode.com/problems/binary-tree-level-order-traversal/)

Time Complexity: O(n) where n is number of elements in the given array as we will iterate over the array to get that element

Space Complexity: O(1) as we are not using additional auxillary data structure and O(h) recursive stack space complexity where h will be height of the tree

Explaination: Simple approach here is we will maintain level of each node at the recursive call. Then we will apply depth first search on the tree. In the DFS recursive call we will check if the result has an empty list for the level and if it doesn't have it we will create it by checking if the result size is equal to the current level. Then we will get the list of the current level and append an current element in that list. We will complete left and right recursive calls by calling the same dfs function on root.left and root.right.



import com.sun.source.tree.Tree;

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        dfs(root, 0, result);

        return result;
    }

    private void dfs(TreeNode root, int level, List<List<Integer>> result) {
        // base
        if(root == null) return;

        //logic
        if(result.size() == level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(root.val);

        //left-recursive call
        dfs(root.left, level+1, result);

        //right-recursive call
        dfs(root.right, level+1, result);
    }
}

# Problem 2
Course Schedule (https://leetcode.com/problems/course-schedule/)

Time Complexity: O(V+E) where V is number of courses and E is number of prerequisites for that course. 

Space Complexity: O(V+E) where it is adjacency list and indegree array.

Explaination: First we will represent this problem in terms of graph using an adjacency list and an array to keep track of indegrees of each node which will be course. Then we will add all the nodes with zero indegree to the queue which will represent the courses that have no prerequisits. Then we will take each node in the queue and decrement the indegrees of it's children and if the indegrees of child becomes zero we will add it to the queue. If all nodes have been processed we will return true otherwise false.


class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        if(numCourses == 0) return true;
        if(prerequisites == null) return true;

        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] indegrees = new int[numCourses];

        for(int[] prerequisite: prerequisites) {
            int in = prerequisite[0];
            int out = prerequisite[1];

            indegrees[in] = indegrees[in] + 1;

            if(!map.containsKey(out)) {
                map.put(out, new ArrayList<>());
            }
            map.get(out).add(in);
        }

        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        for(int i = 0; i < numCourses; i++) {
            if(indegrees[i] == 0) {
                queue.add(i);
                count = count + 1;
            }
        }

        if(count == numCourses) return true;
        if(count == 0) return false;

        while(!queue.isEmpty()) {
            int parent = queue.poll();
            // System.out.println(parent);

            List<Integer> children = map.get(parent); 

            if(children != null) {
                for(int child: children) {
                    indegrees[child] = indegrees[child] - 1;

                    if(indegrees[child] == 0) {
                        queue.add(child);
                        count = count + 1;

                        if(count == numCourses) return true;
                    }
                }
            }
        }

        return false;

    }
}