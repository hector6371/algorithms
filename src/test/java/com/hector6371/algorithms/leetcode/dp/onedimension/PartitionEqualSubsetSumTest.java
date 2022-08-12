package com.hector6371.algorithms.leetcode.dp.onedimension;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


class PartitionEqualSubsetSumTest {


    @Test
    void givenSmallCompliant_whenCanPartition_thenReturnTrue() {
        int [] nums = {1,5,11,5};
        assertTrue(new PartitionEqualSubsetSum().canPartition(nums));
    }

    @Test
    void givenSmallNonCompliant_whenCanPartition_thenReturnFalse() {
        int [] nums = {1,2,3,5};
        assertFalse(new PartitionEqualSubsetSum().canPartition(nums));
    }
    @Test
    void givenLarge_whenCanPartition_thenReturnInTime() {
        int [] nums = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,99,97};
        assertTimeoutPreemptively(Duration.ofMillis(3000), () -> new PartitionEqualSubsetSum().canPartition(nums));
    }
}