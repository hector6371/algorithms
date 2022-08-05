package com.hector6371.algorithms.leetcode;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
*
* #424
* https://leetcode.com/problems/longest-repeating-character-replacement/
* You are given a string s and an integer k. You can choose any character of the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.



Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.



Constraints:

    1 <= s.length <= 105
    s consists of only uppercase English letters.
    0 <= k <= s.length

*/
public class LongestRepeatingCharacterReplacement {

    public static char [] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    Map<String, Integer> memo = new HashMap<>();

    public int characterReplacement(String s, int k) {
        int index = 0;
        return recursive(s, index, k);
    }

    private int recursive(String s, int index, int changesLeft) {
        if (index >= s.length()){
            return calculateRepeatingCharsWOReplacement(s);
        }
        int maxChain = 0;
        if (changesLeft > 0) {
            System.out.println("Iterating with string " + s + " over alphabet with changesLeft: " + changesLeft + " and index: " + index  );
            for (char c : ALPHABET) {
                char[] dimensionalCharArray = s.toCharArray();
                dimensionalCharArray[index] = c;
                String dimensionalString = String.valueOf(dimensionalCharArray);

                int maxDimensionalChain = recursive(dimensionalString, index + 1, changesLeft - 1);
                if (maxDimensionalChain > maxChain) {
                    maxChain = maxDimensionalChain;
                }
            }
        }
        int untouchedStringChain = recursive (s, index + 1, changesLeft);
        if(untouchedStringChain > maxChain){
            maxChain = untouchedStringChain;
        }
        return maxChain;
    }

    private int calculateRepeatingCharsWOReplacement(String s) {
        Optional<Integer> memoizedRepeatingCharsOptional = Optional.ofNullable(memo.get(s));
        if (memoizedRepeatingCharsOptional.isPresent()){
            return memoizedRepeatingCharsOptional.get();
        }
        int max = 0;
        int tmpMax = 0;
        Character latestChar = null;
        for (char c : s.toCharArray()){
            if (latestChar == null){
                tmpMax = 1;
                max = tmpMax;
            } else if (latestChar == c){
                tmpMax++;
                if (tmpMax > max){
                    max = tmpMax;
                }
            } else {
                tmpMax = 1;
            }
            latestChar = c;
        }
        memo.put(s, max);
        return max;
    }
}
