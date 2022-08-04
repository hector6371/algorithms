package com.hector6371.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordBreakTest {
    @Test
    void givenNonComplementaryWords_whenWordBreak_thenReturnFalse() {
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        assertFalse(new WordBreak().isWordBreakable(s, wordDict));
    }

    @Test
    void givenComplementaryWords_whenWordBreak_thenReturnTrue() {
        String s = "applepenapple";
        List<String> wordDict = Arrays.asList("apple","pen");
        assertTrue(new WordBreak().isWordBreakable(s, wordDict));
    }

    @Test
    void givenComplementaryAndSimilarWords_whenWordBreak_thenReturnTrue() {
        String s = "cars";
        List<String> wordDict = Arrays.asList("car","ca","rs");
        assertTrue(new WordBreak().isWordBreakable(s, wordDict));
    }

    @Test
    void givenLargeAndNonComplementaryWords_whenWordBreak_thenReturnFalse() {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa");
        assertFalse(new WordBreak().isWordBreakable(s, wordDict));
    }

    @Test
    void givenLargeAndComplementaryWords_whenWordBreak_thenReturnTrue() {
        String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        List<String> wordDict = Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa", "b");
        assertTrue(new WordBreak().isWordBreakable(s, wordDict));
    }
}
