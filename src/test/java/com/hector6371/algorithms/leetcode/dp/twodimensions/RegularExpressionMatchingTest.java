package com.hector6371.algorithms.leetcode.dp.twodimensions;

import com.hector6371.algorithms.leetcode.dp.twodimensions.RegularExpressionMatching;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularExpressionMatchingTest {

    @Test
    void givenSameLetters_whenIsMatch_thenReturnTrue() {
        String s = "abcde";
        String p = "abcde";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenDifferentLetters_whenIsMatch_thenReturnFalse() {
        String s = "abcde";
        String p = "abxde";
        assertFalse(new RegularExpressionMatching().isMatch(s, p));
    }


    @Test
    void givenWildcardsMatchingZeroChars_whenIsMatch_thenReturnTrue() {
        String s = "ae";
        String p = "ab*e";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenWildcardsMatchingOneChar_whenIsMatch_thenReturnTrue() {
        String s = "abe";
        String p = "ab*e";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenWildcardsMatchingSeveralChars_whenIsMatch_thenReturnTrue() {
        String s = "abbbe";
        String p = "ab*e";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenWildcardAtTheEndMatchingNoChar_whenIsMatch_thenReturnTrue() {
        String s = "a";
        String p = "ab*";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenWildcardsMatchingSeveralCharsFollowedBySameChar_whenIsMatch_thenReturnTrue() {
        String s = "abbbe";
        String p = "ab*be";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenSameLettersWithDot_whenIsMatch_thenReturnTrue() {
        String s = "abcde";
        String p = "ab.de";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenDifferentLettersWithDot_whenIsMatch_thenReturnFalse() {
        String s = "abcde";
        String p = "abx.e";
        assertFalse(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenDotAndWildcardMatchingZeroChars_whenIsMatch_thenReturnTrue() {
        String s = "ae";
        String p = "a.*e";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenDotAndWildcardMatchingOneChar_whenIsMatch_thenReturnTrue() {
        String s = "abe";
        String p = "a.*e";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenDotAndWildcardMatchingSeveralChars_whenIsMatch_thenReturnTrue() {
        String s = "abbbe";
        String p = "a.*e";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenDotAndWildcardMatchingSeveralCharsFollowedBySameChar_whenIsMatch_thenReturnTrue() {
        String s = "abbbe";
        String p = "a.*be";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }

    @Test
    void givenTonsOfWildcardsMatching_whenIsMatch_thenReturnTrue() {
        String s = "aaaaaaaaaaaaab";
        String p = "a*a*a*a*a*a*a*a*a*a*a*a*b";
        assertTrue(new RegularExpressionMatching().isMatch(s, p));
    }
}