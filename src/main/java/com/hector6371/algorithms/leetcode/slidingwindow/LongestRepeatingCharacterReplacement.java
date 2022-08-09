package com.hector6371.algorithms.leetcode.slidingwindow;


import java.util.*;

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

    /* Sliding window algorithm
mods = 4
i
KRSCDCSO
j

#1 count # chars in sliding window and save into hashmap. If we do as we proceed, no reread additional
#2 check if sliding window can convert all the non most repeated char into the most repeated char.
#2.a) if they could be transformed, slide right index, and store maximum
#2.b) if they could not be transformed, slide left index
*/
    public int characterReplacement(String word, int k) {
        int leftIndex = 0;
        int rightIndex = 1;

        int maxWindowSize = 1;
        Map<Character, Integer> charsCount = new HashMap<>();
        charsCount.put(word.charAt(leftIndex), 1);

        while (rightIndex < word.length() + 1) {
            int windowSize = checkWindowSize( k, charsCount);
            if (windowSize == -1){
                //invalid, make smaller window
                //decrease excluded char count
                if (leftIndex < word.length() ) {
                    char toExcludeChar = word.charAt(leftIndex);
                    int excludedCharCount = charsCount.get(toExcludeChar);
                    charsCount.put(toExcludeChar, --excludedCharCount);
                    leftIndex++;
                }
            } else {
                //valid, persist value
                if (windowSize > maxWindowSize){
                    maxWindowSize = windowSize;
                }

                //increase char count
                if (rightIndex < word.length() ){
                    char toIncludeChar = word.charAt(rightIndex);
                    int includedCharCount = charsCount.getOrDefault(toIncludeChar, 0);
                    charsCount.put(toIncludeChar, ++includedCharCount);
                }
                //make bigger window
                rightIndex++;
            }
        }
        return maxWindowSize;
    }

    private int checkWindowSize( int k, Map<Character, Integer> charsCount) {
        int maxCount = 0;
        int totalCount = 0;
        for (Integer count: charsCount.values()){
            if (count > maxCount){
                maxCount = count;
            }
            totalCount += count;
        }
        int windowSize = -1;
        if (totalCount - maxCount <= k){
            windowSize = totalCount;
        }
        return windowSize;
    }


}
