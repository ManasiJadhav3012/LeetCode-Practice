# BFS-3

## Problem1 Remove Invalid Parentheses(https://leetcode.com/problems/remove-invalid-parentheses/)

In this approach we are using BFS to explore all possible states by removing one parenthesis at a time to find the valid strig. Once a valid string is found we  are stopping the search for the shorter strings. Optionally we can use DFS to explore all possibilities and track the longest valid string. To check the correctness of string, we are calculating number of opening brackets and number of closing brackets and if the count becomes 0 we are stating that the given  string is valid.



## Problem2 Clone graph (https://leetcode.com/problems/clone-graph/)

In BFS approach we will use a queue to perform leve-order traversal and clone each node as we will encounter it. At the same time we will maintain a map to track visited nodes and their corresponding clones. For each node we will clone its neighbour and link them to the cloned node. In DFS approach, we will use recursive depth-first traversal to clone the graph. We will maintain a map to track visited nodes and their corresponding clones. For each node we will clone its neighbours recursively and link them to the cloned node.


