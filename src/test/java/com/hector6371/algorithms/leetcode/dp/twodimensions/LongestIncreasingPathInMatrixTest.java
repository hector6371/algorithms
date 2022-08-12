package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestIncreasingPathInMatrixTest {

    @Test
    void given3x3Matrix_whenLongestIncreasingPath_thenReturn4() {
        int[][] matrix = new int[][]{{9,9,4},{6,6,8},{2,1,1}};
        assertEquals(4, new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }

    @Test
    void givenOther3x3Matrix_whenLongestIncreasingPath_thenReturn4() {
        int[][] matrix = new int[][]{{3,4,5},{3,2,6},{2,2,1}};
        assertEquals(4, new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }

    @Test
    void given1x1Matrix_whenLongestIncreasingPath_thenReturn4() {
        int[][] matrix = new int[][]{{1}};
        assertEquals(1, new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }
    @Test
    void given1x4Matrix_whenLongestIncreasingPath_thenReturn4() {
        int[][] matrix = new int[][]{{0,1,5,5}};
        assertEquals(3, new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }
    @Test
    void given4x1Matrix_whenLongestIncreasingPath_thenReturn4() {
        int[][] matrix = new int[][]{{0},{1},{5},{5}};
        assertEquals(3, new LongestIncreasingPathInMatrix().longestIncreasingPath(matrix));
    }
}