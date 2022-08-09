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
//    public boolean isMatch (String s, String p) {
//        Queue<MatchingPair> matchingPairs = new LinkedList<>();
//        matchingPairs.add(new MatchingPair(s, p));
//        boolean matches = false;
//
//        while (!matchingPairs.isEmpty() && !matches) {
//
//            MatchingPair matchingPair = matchingPairs.poll();
//            String p = matchingPair.getPattern();
//            String p = matchingPair.getp();
//
//            if (p.length() == 0 && p.length() == 0){
//                matches = true;
//            } else {
//                if (p.length() > 0){
//                    boolean nextCharWildcard = p.length() != 1 && p.charAt(1) == '*';
//                    if (p.length() == 0 ){
//                        if ( nextCharWildcard) {
//                            matchingPairs.add(new MatchingPair(p, p.substring(2)));
//                        }
//                    } else {
//                        boolean currentCharDot = p.charAt(0) == '.';
//                        if (p.charAt(0) == p.charAt(0) || currentCharDot) {
//                            if (nextCharWildcard) {
//                                matchingPairs.add(new MatchingPair(p.substring(1), p));
//                                matchingPairs.add(new MatchingPair(p.substring(1), p.substring(2)));
//                                matchingPairs.add(new MatchingPair(p, p.substring(2)));
//                            } else {
//                                matchingPairs.add(new MatchingPair(p.substring(1), p.substring(1)));
//                            }
//                        } else {
//                            if (nextCharWildcard) {
//                                matchingPairs.add(new MatchingPair(p, p.substring(2)));
//                            } else {
//                                //continues
//                                matches = false;
//                            }
//                        }
//                    }
//                }
//            }
//
//
//        }
//        return matches;
//    }


    public boolean isMatch (String s, String p) {
        int stringIndex = 0;
        int patternIndex = 0;
        return recursive(s, p, stringIndex, patternIndex);
    }

    private static boolean recursive(String s, String p, int stringIndex, int patternIndex) {
        boolean matches = false;

        while (patternIndex < p.length()) {
            boolean nextCharWildcard = patternIndex + 1 < p.length() && p.charAt(patternIndex + 1) == '*';
            if (stringIndex == s.length()){
                if ( nextCharWildcard) {
                    patternIndex += 2;
                } else {
                    return false;
                }
            } else {
                boolean currentCharDot = p.charAt(patternIndex) == '.';
                if (s.charAt(stringIndex) == p.charAt(patternIndex) || currentCharDot) {
                    if (nextCharWildcard) {
                        return recursive (s, p, stringIndex + 1, patternIndex) ||
                                recursive (s, p, stringIndex, patternIndex + 2);
                    } else {
                        stringIndex++;
                        patternIndex++;
                    }
                } else {
                    if (nextCharWildcard) {
                        return recursive (s, p, stringIndex, patternIndex + 2 );
                    } else {
                        //continues
                        return false;
                    }
                }
            }
        }
        if (stringIndex == s.length() && patternIndex == p.length()){
            matches = true;
        }
        return matches;
    }

//    public boolean isMatch (String s, String p) {
        /*
         *BOTTOM-UP Approach: covering all cases...
         *
         * Starting at bottom, where rows and columns have cumulative
         * p\s| a | b |       b      |    b    |    e    |
         * ===|===|===|==============|=========|=========|
         * a* | t | f |              |         |         |
         * .* | t |   | c&(l|u|dul)  |         |         |
         * b  | f |   |              | c & dul |         |
         * e  |   |   |              |         | c & dul |
         *
         * (u)p, (d)own, (l)eft, (r)ight
         * (dul): diagonal up left
         * (c)heck if matching current row|col
         * (t)rue, (f)alse
         *
         * 1. Start at bottom right, check if same letter and it would be true if diagonal up left is true too
         * 2. At b x b, check if same letter and it would be true if diagonal up left is true too
         * 3. At .* x b, as it is a wildcard...
         *   3.1 As character matches, check left (* matches > 1 char), up (* matches 0 chars) and dup ( * matches 1 char). True if any is true
         *   3.2 If char doesn't match, check up (* matches 0 chars)
         * 4. Check the three previous additions
         *   4.1 (left) .* x b
         *   4.2 (up) a* x b
         *   4.3 (dul) a* x b
         *
         * */
//        List<CharPattern> charPatternList = squashPattern(p);
//
//        boolean [][] memo = new boolean[s.length()][charPatternList.size()];
//        fillMemosFirstColAndRow(memo, s, charPatternList);
//
//        return false;
//    }

//    private void fillMemosFirstColAndRow(boolean[][] memo, String s, List<CharPattern> charPatternList) {
//        char firstChar = s.charAt(0);
//        for (int i = 0; i < charPatternList.size(); i++){
//            CharPattern charPattern = charPatternList.get(i);
//            memo[0][i] = charPattern.getCharacter() == firstChar;
//        }
//    }
//
//    private List<CharPattern> squashPattern(String p) {
//        //parse pattern to squash char and wildcard into one position
//        List<CharPattern> charPatternList = new ArrayList<>();
//        char previousChar = p.charAt(0);
//        if (p.length() == 1){
//            charPatternList.add(new CharPattern(previousChar));
//        }
//        for (int i = 1; i < p.length(); i++){
//            CharPattern charPattern = new CharPattern(previousChar);
//            char currentChar = p.charAt(i);
//            if (currentChar == '*'){
//                charPattern.setWildcard(true);
//            }
//            charPatternList.add(charPattern);
//            if (i == p.length() - 1 && currentChar != '*'){
//                CharPattern lastCharPattern = new CharPattern(currentChar);
//                charPatternList.add(lastCharPattern);
//            }
//        }
//        return charPatternList;
//    }
//
//    class CharPattern {
//        char character;
//        boolean isDot(){
//            return character == '.';
//        }
//        boolean wildcard;
//
//        public CharPattern(char character) {
//            this.character = character;
//        }
//
//        public char getCharacter() {
//            return character;
//        }
//
//        public boolean isWildcard() {
//            return wildcard;
//        }
//
//        public void setWildcard(boolean wildcard) {
//            this.wildcard = wildcard;
//        }
//    }

//    class MatchingPair {
//        String pattern;
//        String p;
//
//        public MatchingPair(String p, String pattern ) {
//            this.pattern = pattern;
//            this.p = p;
//        }
//
//        public String getPattern() {
//            return pattern;
//        }
//
//        public void setPattern(String pattern) {
//            this.pattern = pattern;
//        }
//
//        public String getp() {
//            return p;
//        }
//
//        public void setp(String p) {
//            this.p = p;
//        }
//    }

}
