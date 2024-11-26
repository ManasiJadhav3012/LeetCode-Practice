# BFS-4

## Problem1: Minesweeper (https://leetcode.com/problems/minesweeper/)

Approached Solution: The updateBoard function simulates a Minesweeper game update based on a player's click. If the click lands on a mine ('M'), the board is updated to show 'X', and the game ends. Otherwise, the function reveals cells recursively using a BFS approach. The BFS is implemented with a queue to explore adjacent cells iteratively. For each cell, the countMines function checks how many mines are nearby. If no adjacent mines are found, the cell is marked as 'B', and its unvisited neighbors are added to the queue. If adjacent mines are found, the cell is updated with the count of mines. This ensures efficient exploration of only the necessary cells.

Time Complexity: O(n*m) where n and m are the dimensions of the board.

Space Complexity: O(n*m) as in the worst case scenario all elements could be in the queue.

Approached Code:

class Solution {
    public char[][] updateBoard(char[][] board, int[] click) {

        if(board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        int n = board.length;
        int m = board[0].length;

        int[][] dirs = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {-1, -1}, {1, -1}, {-1, 1}};
        Queue<Integer> queue = new LinkedList<>();

        queue.add(click[0]);
        queue.add(click[1]);

        board[click[0]][click[1]] = 'B';

        while(!queue.isEmpty()) {
            int currRow = queue.poll();
            int currCol = queue.poll();

            int count = countMines(board, currRow, currCol, dirs);

            if(count == 0) {
                for(int[] dir: dirs) {
                    int nRow = currRow + dir[0];
                    int nCol = currCol + dir[1];

                    if(nRow >= 0 && nCol >= 0 && nRow < n && nCol < m && board[nRow][nCol] == 'E') {
                        queue.add(nRow);
                        queue.add(nCol);
                        board[nRow][nCol] = 'B';
                    }
                }
            } else {
                board[currRow][currCol] = (char) (count + '0');
            }
        }

        return board;

    }

    private int countMines (char[][] board, int row, int col, int[][] dirs) {
        int result = 0;

        for(int[] dir: dirs) {
            int nRow = row + dir[0];
            int nCol = col + dir[1];

            if(nRow >= 0 && nCol >= 0 && nRow < board.length && nCol < board[0].length && board[nRow][nCol] == 'M') {
                result++;
            }
        }

        return result;
    }
}



## Problem 2 Snakes and ladders (https://leetcode.com/problems/snakes-and-ladders/)

Approached Solution: This solution transforms the given 2D snakes and ladders board into a 1D array for easier traversal. Each cell of the board is mapped to an index in the array, considering the zigzag pattern of rows. Breadth-First Search (BFS) is employed to simulate the dice rolls and explore possible moves level by level. A queue tracks the current positions, starting from the first cell (index 0). For each position, the next six possible moves (representing dice rolls) are calculated. If the destination cell is a ladder or snake, the player is moved to the target cell. A visited marker (-2) is used to avoid revisiting cells. The BFS guarantees finding the shortest path to the last cell (index n^2 - 1).

Time Complexity: O(n^2) where n is the size of the board.

Space Complexity: O(n^2) as we are using queue for traversal and 1D array representation of the board.

Approached Code:

class Solution {
    public int snakesAndLadders(int[][] board) {

        if(board == null || board.length == 0) return 0;

        int n = board.length;
        int[] arr = new int[n*n];

        int row = n - 1;
        int col = 0;

        int idx = 0;
        boolean flag = true;

        while(idx < arr.length) {

            if(board[row][col] == -1) {
                arr[idx] = -1;
            } else {
                arr[idx] = board[row][col] - 1;
            }

            idx++;

            if(flag) {
                col++;

                if(col == n) {
                    flag = false;
                    row--;
                    col--;
                }
            } else {
                col--;

                if(col < 0) {
                    flag = true;
                    row--;
                    col++;
                }
            }
        }

        int moves = 0;
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        arr[0] = -2;

        while(!queue.isEmpty()) {
            int size = queue.size();

            for(int i = 0; i < size; i++) {
                int currIdx = queue.poll();

                if(currIdx == n*n - 1) {
                    return moves;
                }

                for(int k = 1; k <= 6; k++) {
                    int newIdx = currIdx + k;

                    if(newIdx >= n*n) {
                        break;
                    }

                    if(arr[newIdx] != -2) {
                        if(arr[newIdx] == -1) {
                            queue.add(newIdx);
                        } else {
                            queue.add(arr[newIdx]);
                        }

                        arr[newIdx] = -2;
                    }
                }
            }

            moves++;
        }

        return -1;

    }
}


