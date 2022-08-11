package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BurstBallonsTest {

    @Test
    void given3158_whenMaxCoins_thenReturn167() {
        int [] nums = new int[]{3,1,5,8};
        assertEquals(167, new BurstBallons().maxCoins(nums));
    }

    @Test
    void given15_whenMaxCoins_thenReturn10() {
        int [] nums = new int[]{1,5};
        assertEquals(10, new BurstBallons().maxCoins(nums));
    }
}