# Competitive-Coding-5

Please submit the interview problems posted in slack channel here. The problems and statements are intentionally not shown here so that students are not able to see them in advance 



# Problem 1 - (https://leetcode.com/problems/find-largest-value-in-each-tree-row/)

In this solution we will be finding largest value in each row of a binary tree using BFS. We will start by initializing a queue with the root node. For each level of the tree we will process all nodes in that level tracking the maximum value. Then we will add left and right children of the current nodes to the queue for the next level. After processing each level we will add the maximum value to the resultant list.



# Problem 2 - (https://leetcode.com/problems/valid-sudoku/description/)

In this example we will be checking that each row should contain unique numbers, each column should contain unique numbers and each 3x3 grid should contain unique numbers. To achieve this goal, in our code we will iterate over the board three time, once for row, once for columns, once for 3x3 grids. We will use a boolean array to track whether a number has already appeared or not. If number has already appeared we will immediately return false otherwise at the end we will return true.


