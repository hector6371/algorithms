package com.hector6371.algorithms.leetcode.dp.twodimensions;


/*
* #62
* https://leetcode.com/problems/unique-paths/

There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
The test cases are generated so that the answer will be less than or equal to 2 * 109.


Example 1:
Input: m = 3, n = 7
Output: 28

Example 2:
Input: m = 3, n = 2
Output: 3
Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Down -> Down
2. Down -> Down -> Right
3. Down -> Right -> Down

Constraints:
    1 <= m, n <= 100

* */
public class UniquePaths {

    int [][] memo;
    public int uniquePaths(int rows, int cols) {
        memo = new int[rows + 1][cols + 1];

        return recursive(rows, cols);
    }

    private int recursive(int rows, int cols) {
        int ways = 0;

        int memoizedResult = memo[rows][cols];
        if (memoizedResult != 0){
            ways = memoizedResult;
        } else {
            if (rows > 1) {
                ways += recursive(rows - 1, cols);
            }
            if (cols > 1) {
                ways += recursive(rows, cols - 1);
            }
            if (rows == 1 && cols == 1) {
                ways++;
            }
            memo[rows][cols] = ways;
        }
        return ways;
    }

}
