package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class CoinChange2Test {

    @Test
    void givenSmallPossible_whenCoinChange_thenReturn4() {
        int amount = 5;
        int[] coins = new int[]{1, 2, 5};

        assertEquals(4, new CoinChange2().change(amount, coins));
    }

    @Test
    void givenAmount0_whenCoinChange_thenReturn1() {
        int amount = 0;
        int[] coins = new int[]{7};

        assertEquals(1, new CoinChange2().change(amount, coins));
    }

    @Test
    //@Timeout(1)
    void givenBigAmount_whenCoinChange_thenReturnInTime() {
        int amount = 500;
        int[] coins = new int[]{3,5,7,8,9,10,11};
        assertTimeout(Duration.ofSeconds(2), () -> new CoinChange2().change(amount, coins));
    }
}