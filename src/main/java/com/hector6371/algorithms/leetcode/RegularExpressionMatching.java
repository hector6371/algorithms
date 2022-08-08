package com.hector6371.algorithms.leetcode;

import java.util.*;

/*
* #10
* https://leetcode.com/problems/regular-expression-matching/
*
*
* Given an input string s and a pattern p, implement regular expression matching with support for '.' and '*' where:
    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4
Input: s = "aaabc", p = "a.*bz"
Output: false
Explanation: ".*" means "zero or more (*) of any character (.)".

Constraints:
    1 <= s.length <= 20
    1 <= p.length <= 30
    s contains only lowercase English letters.
    p contains only lowercase English letters, '.', and '*'.
    It is guaranteed for each appearance of the character '*', there will be a previous valid character to match.
*
* */
public class RegularExpressionMatching {
    public boolean isMatch (String s, String p) {
        //String cleanUpPattern = squashWildcards(p);
        Map<MatchingPair, Boolean > memo = new HashMap<>();

        Queue<MatchingPair> matchingPairs = new LinkedList<>();
        matchingPairs.add(new MatchingPair(s, p));
        boolean matches = false;

        while (!matchingPairs.isEmpty() && !matches) {

            MatchingPair matchingPair = matchingPairs.poll();
            String matchingPattern = matchingPair.getPattern();
            String stringToMatch = matchingPair.getStringToMatch();

            if (matchingPattern.length() == 0 && stringToMatch.length() == 0){
                matches = true;
            } else {
                if (matchingPattern.length() > 0){
                    boolean nextCharWildcard = matchingPattern.length() != 1 && matchingPattern.charAt(1) == '*';
                    if (stringToMatch.length() == 0 ){
                        if ( nextCharWildcard) {
                            matchingPairs.add(new MatchingPair(stringToMatch, matchingPattern.substring(2)));
                        }
                    } else {
                        boolean currentCharDot = matchingPattern.charAt(0) == '.';
                        if (stringToMatch.charAt(0) == matchingPattern.charAt(0) || currentCharDot) {
                            if (nextCharWildcard) {
                                matchingPairs.add(new MatchingPair(stringToMatch.substring(1), matchingPattern));
                                matchingPairs.add(new MatchingPair(stringToMatch.substring(1), matchingPattern.substring(2)));
                                matchingPairs.add(new MatchingPair(stringToMatch, matchingPattern.substring(2)));
                            } else {
                                matchingPairs.add(new MatchingPair(stringToMatch.substring(1), matchingPattern.substring(1)));
                            }
                        } else {
                            if (nextCharWildcard) {
                                matchingPairs.add(new MatchingPair(stringToMatch, matchingPattern.substring(2)));
                            } else {
                                //continues
                                matches = false;
                            }
                        }
                    }
                }
            }


        }
        return matches;
    }

//    private String squashWildcards(String pattern) {
//        StringBuilder stringBuilder = new StringBuilder();
//        Character possibleWildcardChar = null;
//        boolean checkedWildcard = false;
//        for (int i = 0; i < pattern.length(); i++){
//            if (possibleWildcardChar == null){
//                possibleWildcardChar = pattern.charAt(i);
//            } else {
//                if (pattern.charAt(i) == '*'){
//                    checkedWildcard = true;
//                }
//            }
//        }
//        return stringBuilder.toString();
//    }

    class MatchingPair {
        String pattern;
        String stringToMatch;

        public MatchingPair(String stringToMatch, String pattern ) {
            this.pattern = pattern;
            this.stringToMatch = stringToMatch;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public String getStringToMatch() {
            return stringToMatch;
        }

        public void setStringToMatch(String stringToMatch) {
            this.stringToMatch = stringToMatch;
        }
    }

}
