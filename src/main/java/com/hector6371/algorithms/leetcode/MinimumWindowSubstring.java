package com.hector6371.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
* # 76
* https://leetcode.com/problems/minimum-window-substring/submissions/
* Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every
* character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.
A substring is a contiguous sequence of characters within the string.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.

Constraints:
    m == s.length
    n == t.length
    1 <= m, n <= 10^5
    s and t consist of uppercase and lowercase English letters.

Follow up: Could you find an algorithm that runs in O(m + n) time?
* */
public class MinimumWindowSubstring {

    public String minWindow (String s, String t) {
        boolean windowFound = false;

        int leftIndex = 0;
        int rightIndex = 1;

        int minWindowLeftIndex = Integer.MIN_VALUE;
        int minWindowRightIndex = Integer.MAX_VALUE;

        Map<Character, Integer> characterCountMap = new HashMap<>();
        Map<Character, Integer> remainingCharacterCountMap =  new HashMap<>();
        for (char c: t.toCharArray()){
            characterCountMap.merge(c, 1, Integer::sum);
            remainingCharacterCountMap.merge(c, 1, Integer::sum);
        }

        //as right starts in 1, checks first character and removes from requirements if matches
        char firstChar = s.charAt(0);
        if (characterCountMap.containsKey(firstChar)) {
            remainingCharacterCountMap.merge(firstChar, 1, (previousValue, newValue) -> previousValue - newValue);
        }


        while (rightIndex < s.length() + 1){
            if (validWindow (remainingCharacterCountMap)){
                if (!windowFound || rightIndex - leftIndex < minWindowRightIndex - minWindowLeftIndex) {
                    //persist
                    minWindowRightIndex = rightIndex;
                    minWindowLeftIndex = leftIndex;
                    windowFound = true;
                }
                //change count
                char toRemoveChar = s.charAt(leftIndex);
                if (characterCountMap.containsKey(toRemoveChar)){
                    remainingCharacterCountMap.merge(toRemoveChar, 1, Integer::sum);
                }
                leftIndex++;
            } else {
                //changeCount
                if (rightIndex < s.length() ) {
                    char toRemoveChar = s.charAt(rightIndex);
                    if (characterCountMap.containsKey(toRemoveChar)) {
                        remainingCharacterCountMap.merge(toRemoveChar, 1, (previousValue, newValue) -> previousValue - newValue);
                    }
                }
                rightIndex++;
            }

        }

        return windowFound ? s.substring(minWindowLeftIndex, minWindowRightIndex) : "";
    }

    private boolean validWindow(Map<Character, Integer> remainingCharacterCountMap) {
        boolean valid = true;
        for( Integer count : remainingCharacterCountMap.values()){
            if (count > 0) {
                valid = false;
            }
        }
        return valid;
    }
}
