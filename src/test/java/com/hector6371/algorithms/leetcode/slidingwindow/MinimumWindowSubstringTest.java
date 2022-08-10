package com.hector6371.algorithms.leetcode.slidingwindow;

import com.hector6371.algorithms.leetcode.slidingwindow.MinimumWindowSubstring;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinimumWindowSubstringTest {

    @Test
    void givenFoundable_whenMinWindow_thenReturnString (){
        String s = "ADOBECODEBANC";
        String t = "ABC";
        assertEquals("BANC", new MinimumWindowSubstring().minWindow(s, t));
    }

    @Test
    void givenSmallFoundable_whenMinWindow_thenReturnString (){
        String s = "ab";
        String t = "b";
        assertEquals("b", new MinimumWindowSubstring().minWindow(s, t));
    }

}
