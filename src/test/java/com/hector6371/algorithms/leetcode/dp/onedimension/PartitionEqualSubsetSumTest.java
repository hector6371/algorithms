package com.hector6371.algorithms.leetcode.dp.onedimension;

import org.junit.jupiter.api.Test;

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
}