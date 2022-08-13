package com.hector6371.algorithms.leetcode.dp.onedimension;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingSubsequenceTest {

    @Test
    void givenShortSequence_whenLengthOfLIS_thenReturn4() {
        int [] sequence = {10,9,2,5,3,7,101,18};
        assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(sequence));
    }

    @Test
    void givenOtherShortSequence_whenLengthOfLIS_thenReturn4() {
        int [] sequence = {0,1,0,3,2,3};
        assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(sequence));
    }

    @Test
    void givenRepeatingNums_whenLengthOfLIS_then1() {
        int [] sequence = {7,7,7,7,7,7,7};
        assertEquals(1, new LongestIncreasingSubsequence().lengthOfLIS(sequence));
    }
}