package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class UniquePathsTest {

    @Test
    void givenMiddleBoard_whenUniquePaths_thenReturn28() {
        int rows = 3, cols = 7;
        assertEquals(28, new UniquePaths().uniquePaths(rows, cols));
    }

    @Test
    void givenSmallBoard_whenUniquePaths_thenReturn3() {
        int rows = 3, cols = 2;
        assertEquals(3, new UniquePaths().uniquePaths(rows, cols));
    }
    @Test
    void givenBigBoard_whenUniquePaths_thenReturnFast() {
        int rows = 23, cols = 12;
        assertTimeout(Duration.ofSeconds(1), () -> new UniquePaths().uniquePaths(rows, cols));
    }
}