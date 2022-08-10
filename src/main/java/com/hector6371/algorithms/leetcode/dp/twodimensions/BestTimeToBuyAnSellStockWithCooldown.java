package com.hector6371.algorithms.leetcode.dp.twodimensions;


import static java.lang.Math.max;

/*
*
* #309
* https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
*
You are given an array prices where prices[i] is the price of a given stock on the ith day.
Find the maximum profit you can achieve. You may complete as many transactions as you like
(i.e., buy one and sell one share of the stock multiple times) with the following restrictions:

After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

Example 1:
Input: prices = [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]

Example 2:
Input: prices = [1]
Output: 0

Constraints:
    1 <= prices.length <= 5000
    0 <= prices[i] <= 1000
* */
public class BestTimeToBuyAnSellStockWithCooldown {

    //TOP DOWN solution
    // If you don't have anything, you could buy or cooldown.
    // If you have something, you could sell or cooldown
    // If you have sold the previous day, cooldown is mandatory
    // needless to say, if you sell for less than you bought, they were two non-profit operations.
    //
    public int maxProfit(int[] prices) {
        int profit = 0;
        int index = 0;
        boolean justSold = false;
        boolean haveProduct = false;

        return recursive(prices, index, profit, haveProduct, justSold);
    }

    public int recursive (int[] prices, int index, int profit, boolean haveProduct, boolean justSold){
        if (index >= prices.length){
            return profit;
        } else {
            if (justSold) {
                //cooldown mandatory
                return recursive(prices, index + 1, profit, false, false);
            }
            if (haveProduct) {
                //if we have product, we can sell or cooldown
                int profitSelling = recursive(prices, index + 1, profit + prices[index], false, true);
                int profitCoolingDown = recursive(prices, index + 1, profit, true, false);
                return max(profitSelling, profitCoolingDown);
            } else {
                //if we have no product, we could just buy or cooldown
                int profitBuying = recursive(prices, index + 1, profit - prices[index], true, false);
                int profitCoolingDown = recursive(prices, index + 1, profit, false, false);
                return max(profitBuying, profitCoolingDown);
            }
        }

    }
}
