# DFS-1



## Problem1 (https://leetcode.com/problems/flood-fill/)

In the following approach we will be using DFS. In this firts we have given sr and sc so we will start our algorithm with that pixel and then recursively visit all adjacent pixels in all 4 directions - up, down, left, right that share same color. Then we will color these pixels with new color. Then we will continue our DFS until we reach all reachable pixels from the starting point with the same color and update all of them. We will have a boundary check to ensure not to go out-of-bounds.



## Problem2 (https://leetcode.com/problems/01-matrix/)

In this approach we are using DFS to update the matrix. We will update cells with value 1 to -1. In the DFS approach we will explore the four possible directions (up, down, left, right) to find the nearest 0. A dynammic programming array will store the minimum distance from eacg 1 to the closest 0. We will be calculating distance by recursively checking neighboring cells and taking the minimum value. In the BFS approach we will maintain size variable to check the level wise traversal. Then we will traverse all the nearby cells and update the values accordingly.


