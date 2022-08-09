package com.hector6371.algorithms.leetcode.dp.onedimension;

/*
* #70
* https://leetcode.com/problems/climbing-stairs/submissions/
* You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Example 1:
Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:
Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
*
Example 3:
Input: n = 4
Output: ?
Explanation:
1. 1 step + 1 step + 1 step + 1 step
2. 1 step + 1 step + 2 steps
3. 1 step + 2 step + 1 step
4. 2 step + 1 step + 1 step
5. 2 step + 2 step
* */
public class ClimbingStairs {

    public int climbStairs(int n) {
        int ways;
        if (n == 1) {
            ways = 1;
        } else if (n == 2){
            ways = 2;
        } else {
            int[] memo = new int[n];
            memo[0] = 1;
            memo[1] = 2;
            for (int i = 2; i < n; i++) {
                memo[i] = memo[i - 1] + memo[i - 2];
            }

            ways = memo[n - 1];
        }
        return ways;
    }
}
