package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BestTimeToBuyAnSellStockWithCooldownTest {

    @Test
    void given12302_whenMaxProfit_thenReturn3() {
        int [] prices = new int[]{1,2,3,0,2};
        assertEquals(3, new BestTimeToBuyAnSellStockWithCooldown().maxProfit(prices));
    }

    @Test
    void givenSingle_whenMaxProfit_thenReturn0() {
        int [] prices = new int[]{1};
        assertEquals(0, new BestTimeToBuyAnSellStockWithCooldown().maxProfit(prices));
    }
}