# Backtracking-3

## Problem1 
N Queens(https://leetcode.com/problems/n-queens/)


Approached Solution: In this solution we will address N-Queens using backtracking. In this algorithm we willplace queens row by row by checking if a position is safe before placing a queen. This ensures that no two queens threaten each other by validating that no queen is in the same column, or diagonally in both directions. If the solution is found where queens are safely placed in all rows, then the current board configuration we will add it to the result. This process we will continue with backtracking, removing queens and trying different positions to explore all valid solutions.

Time Complexity: In this solution time complexity will be O(n!) as each row has n possible columns to try and each recursive call reduces the number of available positions.

Space Complexity: In this solution space complexity will be O(n^2) for the grid and recursion stack used during backtracking.

Approached Code:

class Solution {
    List<List<String>> result;
    boolean[][] grid;
    public List<List<String>> solveNQueens(int n) {
        this.result = new ArrayList<>();
        this.grid = new boolean[n][n];

        backtrack(result, grid, 0);

        return result;
    }

    private void backtrack(List<List<String>> result, boolean[][] grid, int r) {
        // base
        if(r == grid[0].length) {
            List<String> li = new ArrayList<>();

            for(int i = 0; i < grid.length; i++) {
                StringBuilder sb = new StringBuilder();

                for(int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j]) {
                        sb.append('Q');
                    } else {
                        sb.append('.');
                    }
                }

                li.add(sb.toString());
            }

            result.add(li);
            return;
        }

        // logic

        for(int c = 0; c < grid[0].length; c++) {
            if(isSafe(grid, r, c)) {
                // action
                grid[r][c] = true;

                // recurse
                backtrack(result, grid, r+1);

                // backtrack
                grid[r][c] = false;
            }
        }

    }

    private boolean isSafe(boolean[][] grid, int r, int c) {
        for(int i = 0; i < r; i++) {
            if(grid[i][c]) {
                return false;
            }
        }

        int i = r;
        int j = c;

        while(i >= 0 && j < grid[0].length) {
            if(grid[i][j]) {
                return false;
            }

            i--;
            j++;
        }

        i = r;
        j = c;

        while(i >= 0 && j >= 0) {
            if(grid[i][j]) {
                return false;
            }

            i--;
            j--;
        }

        return true;
    }
}



## Problem2
Word Search(https://leetcode.com/problems/word-search/)


Approached Solution: In this solution we will use DFS with backtracking to find a path in the grid that spells out the gien word. We will iterated through each cell in the grid. For each cell we will call backtrack function if the first character matcher the word's initial character. In the backtrack function we will check the base condition, if the currect index is equal to the word length, then the word has been found, so we will return true. Otherwise if the current cell matches the current character in the work it temporarily marks the cell as visited using '#' and then we will recursively explore all four directions using dirs array. If none of these paths complete the word we will backtrack by restoring the cell's original character and the function finally returns false if no path matches the word.

Time Complexity: O(m * n * 4^l) where m*n represents the number of cells in the grid and 4^l represents the recursive calls made for each character in the word which is upto 4 directions for each letter where l is length of the letter.

Space Complexity: O(l) where l is the length of the word due to the recursive call stack.

Approached Code:

class Solution {

    int[][] dirs;
    int m;
    int n;

    public boolean exist(char[][] board, String word) {
        this.dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        this.m = board.length;
        this.n = board[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(backtrack(board, i, j, word, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, int i, int j, String word, int idx) {
        // base

        if(idx == word.length()) {
            return true;
        }

        if(i < 0 || j < 0 || i == m || j == n) {
            return false;
        }

        // logic

        if(board[i][j] == word.charAt(idx)) {
            // action
            board[i][j] = '#';

            // recurse
            for(int[] dir: dirs) {
                int nr = i + dir[0];
                int nc = j + dir[1];

                if(backtrack(board, nr, nc, word, idx+1)) {
                    return true;
                }
            }

            // backtrack
            board[i][j] = word.charAt(idx);
        }

        return false;
    }
}