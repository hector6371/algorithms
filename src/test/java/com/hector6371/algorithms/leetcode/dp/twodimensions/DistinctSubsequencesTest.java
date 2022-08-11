package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DistinctSubsequencesTest {

    @Test
    void givenSmallMatching_whenNumDistinct_thenReturn3() {
        String s = "rabbbit", t = "rabbit";
        assertEquals(3, new DistinctSubsequences().numDistinct(s,t));
    }

    @Test
    void givenRepeatingPattern_whenNumDistinct_thenReturn5() {
        String s = "babgbag", t = "bag";
        assertEquals(5, new DistinctSubsequences().numDistinct(s,t));
    }
}