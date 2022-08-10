package com.hector6371.algorithms.leetcode.dp.twodimensions;

import java.util.Arrays;

/*
#518
https://leetcode.com/problems/coin-change-2/submissions/

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1

Constraints:
    1 <= coins.length <= 300
    1 <= coins[i] <= 5000
    All the values of coins are unique.
    0 <= amount <= 5000
* */
public class CoinChange2 {

    Integer [][] memo;

    //TOP DOWN:
    // starting at index 1, at each iteration, a choice must be done.
    // ¿Should we add one more coin of this coin, or should we move on with the next coin?
    //
    public int change(int amount, int[] coins) {
        int index = 0;
        memo = new Integer[amount + 1][coins.length];
        return recursive(amount, coins, index);
    }
    private int recursive(int remainingAmount, int[] coins, int index) {
        int ways = 0;
        if (index < coins.length) {
            Integer memoizedResult = memo[remainingAmount][index];
            if (memoizedResult != null){
                ways = memoizedResult;
            } else {
                if (remainingAmount == 0) {
                    ways++;
                } else {
                    while (index < coins.length) {

                        if (coins[index] > remainingAmount) {
                            index++;
                        } else if (coins[index] == remainingAmount) {
                            index++;
                            ways++;
                        } else {
                            //add one more of the current coin
                            int substractedAmount = remainingAmount - coins[index];
                            memo[substractedAmount][index] = recursive(substractedAmount, coins, index);
                            ways += memo[substractedAmount][index];
                            //we should move to the next coin
                            index++;
                        }
                    }
                }
            }
        }
        if (index < coins.length) {
            memo[remainingAmount][index] = ways;
        }
        return ways;
    }
}
