package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterleavingStringTest {

    @Test
    void givenSmallInterleaving_whenIsInterleave_thenReturnTrue() {
        String s1 = "aabcc", s2 = "dbbca", s3 ="aadbbcbcac";
        assertTrue(new InterleavingString().isInterleave(s1, s2, s3) );
    }
    @Test
    void givenSmallNoInterleaving_whenIsInterleave_thenReturnFalse() {
        String s1 = "aabcc", s2 = "dbbca", s3 ="aadbbbaccc";
        assertFalse(new InterleavingString().isInterleave(s1, s2, s3) );
    }
    @Test
    void given_whenIsInterleave_thenReturnTrue() {
        String s1 = "", s2 = "", s3 ="";
        assertTrue(new InterleavingString().isInterleave(s1, s2, s3) );
    }
}