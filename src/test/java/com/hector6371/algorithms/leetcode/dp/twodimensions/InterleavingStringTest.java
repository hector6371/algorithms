package com.hector6371.algorithms.leetcode.dp.twodimensions;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class InterleavingStringTest {

    @Test
    void givenSmallInterleaving_whenIsInterleave_thenReturnTrue() {
        String s1 = "aabcc", s2 = "dbbca", s3 ="aadbbcbcac";
        assertTrue(new InterleavingString().isInterleaveTabulation(s1, s2, s3) );
    }
    @Test
    void givenSmallNoInterleaving_whenIsInterleave_thenReturnFalse() {
        String s1 = "aabcc", s2 = "dbbca", s3 ="aadbbbaccc";
        assertFalse(new InterleavingString().isInterleaveTabulation(s1, s2, s3) );
    }
    @Test
    void givenEmpty_whenIsInterleave_thenReturnTrue() {
        String s1 = "", s2 = "", s3 ="";
        assertTrue(new InterleavingString().isInterleaveTabulation(s1, s2, s3) );
    }

    @Test
    void givenLong_whenIsInterleave_thenReturnInTime() {
        String s1 = "bbbbbabbbbabaababaaaabbababbaaabbabbaaabaaaaababbbababbbbbabbbbababbabaabababbbaabababababbbaaababaa",
                s2 = "babaaaabbababbbabbbbaabaabbaabbbbaabaaabaababaaaabaaabbaaabaaaabaabaabbbbbbbbbbbabaaabbababbabbabaab",
                s3 ="babbbabbbaaabbababbbbababaabbabaabaaabbbbabbbaaabbbaaaaabbbbaabbaaabababbaaaaaabababbababaababbababbbababbbbaaaabaabbabbaaaaabbabbaaaabbbaabaaabaababaababbaaabbbbbabbbbaabbabaabbbbabaaabbababbabbabbab";
        assertTimeout(Duration.ofMillis(500), () -> new InterleavingString().isInterleaveTabulation(s1, s2, s3) );
    }
    @Test
    void givenResultSmallerThanInputs_whenIsInterleave_thenReturnFalse() {
        String s1 = "aaaa",
                s2 = "aa",
                s3 ="aaa";
        assertFalse(new InterleavingString().isInterleaveTabulation(s1, s2, s3) );
    }
}