package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import java.time.Duration;

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
    @Test
    void givenLongInput_whenMaxCoins_thenReturnInTime() {
        int [] nums = new int[]{7,9,8,0,7,1,3,5,5,2,3};
        assertTimeout(Duration.ofMillis(500), () -> new BurstBallons().maxCoins(nums));
    }
    @Test
    void givenLongerInput_whenMaxCoins_thenReturnInTime() {
        int [] nums = new int[]{8,3,4,3,5,0,5,6,6,2,8,5,6,2,3,8,3,5,1,0,2};
        assertTimeout(Duration.ofMillis(500), () -> new BurstBallons().maxCoins(nums));
    }
}