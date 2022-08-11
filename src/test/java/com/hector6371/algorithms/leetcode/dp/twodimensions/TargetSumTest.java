package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TargetSumTest {

    @Test
    void givengrainedInput_whenFindTargetSumWays_thenReturn5() {
        int [] nums = new int []{1,1,1,1,1};
        int target = 3;
        assertEquals(5, new TargetSum().findTargetSumWays(nums, target));
    }

    @Test
    void givenLastZero_whenFindTargetSumWays_thenReturn2() {
        int [] nums = new int []{1,0};
        int target = 1;
        assertEquals(2, new TargetSum().findTargetSumWays(nums, target));
    }
}