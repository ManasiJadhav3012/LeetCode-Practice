// Time Complexity : O(n*m) where n is number of rows and m is the number of columns in the image and in the worst case scenario we could visit all the pixels
// Space Complexity : O(n*m) which could be used for recursive stack in DFS approach and for the queue in BFS approach
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : followed the code tutorial


// Your code here along with comments explaining your approach
// In the following approach we will be using DFS. In this firts we have given sr and sc so we will start our algorithm with that pixel and then recursively visit
// all adjacent pixels in all 4 directions - up, down, left, right that share same color. Then we will color these pixels with new color. Then we will continue
// our DFS until we reach all reachable pixels from the starting point with the same color and update all of them. We will have a boundary check to ensure not to 
// go out-of-bounds.

class Solution {
    int n;
    int m;
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        
        if(image == null) {
            return image;
        }

        this.n = image.length;
        this.m = image[0].length;

        int[][] dirs = new int [][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        if(image[sr][sc] == color) {
            return image;
        }

        int startColor = image[sr][sc];

        // BFS Approach

        // Queue<Integer> queue = new LinkedList<>();

        // queue.add(sr);
        // queue.add(sc);

        // image[sr][sc] = color;

        // while(!queue.isEmpty()) {
        //     int cr = queue.poll();
        //     int cc = queue.poll();

        //     for(int[] dir: dirs) {
        //         int nr = cr + dir[0];
        //         int nc = cc + dir[1];

        //         if(nr < n && nr >= 0 && nc < m && nc >= 0 && 
        //         image[nr][nc] == startColor) {
        //             queue.add(nr);
        //             queue.add(nc);
        //             image[nr][nc] = color;
        //         }
        //     }
        // }



        // DFS Approach

        helper(image, sr, sc, startColor, color, dirs);

        return image;

    }

    private void helper(int[][] image, int sr, int sc, int startColor, int color, int[][] dirs) {
        // base

        if(sr == n || sr < 0 || sc == m || sc < 0 || 
        image[sr][sc] != startColor) {
            return;
        }

        // logic

        image[sr][sc] = color;

        for(int[] dir: dirs) {
            int nr = sr + dir[0];
            int nc = sc + dir[1];

            helper(image, nr, nc, startColor, color, dirs);
        }
    }
}



// Time Complexity : O(m*n) where m is number of rows and n is number columns and we are calling dfs on each cell
// Space Complexity : O(m*n) for the dp array and the recursion stack in DFS which results in additional memory usage
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : followed the code tutorial

// Your code here along with comments explaining your approach
// In this approach we are using DFS to update the matrix. We will update cells with value 1 to -1. In the DFS approach we will explore the four possible directions
// (up, down, left, right) to find the nearest 0. A dynammic programming array will store the minimum distance from eacg 1 to the closest 0. We will be 
// calculating distance by recursively checking neighboring cells and taking the minimum value. In the BFS approach we will maintain size variable to check the 
// level wise traversal. Then we will traverse all the nearby cells and update the values accordingly.

class Solution {
    int[][] dirs;
    int[][] dp;
    int m;
    int n;
    public int[][] updateMatrix(int[][] mat) {
        
        if(mat == null || mat.length == 0) {
            return mat;
        }

        this.m = mat.length;
        this.n = mat[0].length;
        dirs = new int[][] {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == 1) {
                    mat[i][j] = -1;
                }
            }
        }

        this.dp = new int[m][n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(mat[i][j] == -1) {
                    dp[i][j] = dfs(mat, i, j);
                }
            }
        }

        return dp;

        // Queue<int []> queue = new LinkedList<>();

        // int n = mat.length;
        // int m = mat[0].length;

        // int[][] dirs = new int [][] {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // for(int i = 0; i < n; i++) {
        //     for(int j = 0; j < m; j++) {
        //         if(mat[i][j] == 1) {
        //             mat[i][j] = -1;
        //         } else {
        //             queue.add(new int[] {i, j});
        //         }
        //     }
        // }

        // int dist = 0;

        // while(!queue.isEmpty()) {
        //     int size = queue.size();

        //     for(int i = 0; i < size; i++) {
        //         int[] curr = queue.poll();

        //         int cr = curr[0];
        //         int cc = curr[1];

        //         for(int[] dir: dirs) {
        //             int nr = cr + dir[0];
        //             int nc = cc + dir[1];

        //             if(nr >= 0 && nc >= 0 && nr < n && nc < m && mat[nr][nc] == -1 ) {
        //                 mat[nr][nc] = dist + 1;
        //                 queue.add(new int[] {nr, nc});
        //             }
        //         }
        //     }

        //     dist++;
        // }

        // return mat;

    }

    private int dfs(int[][] mat, int i, int j) {
        // base
        if(i < m-1 && mat[i+1][j] == 0) {
            return 1;
        }

        if(i > 0 && mat[i-1][j] == 0) {
            return 1;
        }

        if(j < n-1 && mat[i][j+1] == 0) {
            return 1;
        }

        if(j > 0 && mat[i][j-1] == 0) {
            return 1;
        }


        // logic
        int top = 99999;
        int bottom = 99999;
        int left = 99999;
        int right = 99999;

        // top
        if(i > 0 && dp[i-1][j] != 0) {
            top = dp[i-1][j];
        }

        // left
        if(j > 0 && dp[i][j-1] != 0) {
            left = dp[i][j-1];
        }

        // bottom 
        if(i < m-1) {
            if(dp[i+1][j] == 0) {
                dp[i+1][j] = dfs(mat, i+1, j);
            }
            bottom = dp[i+1][j];
        }

        // right
        if(j < n-1) {
            if(dp[i][j+1] == 0) {
                dp[i][j+1] = dfs(mat, i, j+1);
            }
            right = dp[i][j+1];
        }

        return Math.min(right, Math.min(top, Math.min(bottom, left))) + 1;
    }
}


