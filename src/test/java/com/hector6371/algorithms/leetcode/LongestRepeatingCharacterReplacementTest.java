package com.hector6371.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LongestRepeatingCharacterReplacementTest {

    @Test
    void givenSimple_whenBridge_thenReturn4 () {
        String word = "AABABBA";
        int k = 1;
        assertEquals(4,  new LongestRepeatingCharacterReplacement().characterReplacement(word, k));
    }

    @Test
    void givenSimple_whenNoChanges_thenReturn2 () {
        String word = "ABAA";
        int k = 0;
        assertEquals(2,  new LongestRepeatingCharacterReplacement().characterReplacement(word, k));
    }

    @Test
    void givenMedium_whenNoChanges_thenReturn6 () {
        String word = "KRSCDCSO";
        int k = 4;
        assertEquals(6,  new LongestRepeatingCharacterReplacement().characterReplacement(word, k));
    }

    @Test
    void givenLong_whenNoChanges_thenReturn7 () {
        String word = "KRSCDCSONAJNHLBMDQGIFCPEKPOHQIHLTDIQGEKLRLCQNBOHNDQGHJPNDQPERNFSSSRDEQLFPCCCARFMDLHADJADAGNNSBNCJQOF";
        int k = 4;
        assertEquals(7,  new LongestRepeatingCharacterReplacement().characterReplacement(word, k));
    }
}
