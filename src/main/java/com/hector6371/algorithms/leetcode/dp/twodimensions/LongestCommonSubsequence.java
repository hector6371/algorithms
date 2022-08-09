package com.hector6371.algorithms.leetcode.dp.twodimensions;


/*
* #1143
* https://leetcode.com/problems/longest-common-subsequence/
* Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.

A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted
* without changing the relative order of the remaining characters.

For example, "ace" is a subsequence of "abcde".
A common subsequence of two strings is a subsequence that is common to both strings.

Example 1:
Input: text1 = "abcde", text2 = "ace"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

Example 2:
Input: text1 = "abc", text2 = "abc"
Output: 3
Explanation: The longest common subsequence is "abc" and its length is 3.

Example 3:
Input: text1 = "abc", text2 = "def"
Output: 0
Explanation: There is no such common subsequence, so the result is 0.
*
* Example 4:
Input: text1 = "abcde", text2 = "axcey"
Output: 3
Explanation: The longest common subsequence is "ace" and its length is 3.

          i                j
 text1 = "abcde", text2 = "axcey"
 if (text1(i) == text2(j)) => +1 length, i+1,j+1
           i                j
 text1 = "abcde", text2 = "axcey"
 max({i+1,j}, {i,j+1})
 *      | a | x | c | e | y | null
 * =====|===|===|===|===|===|=====
 * a    |   |   |   |   |   | 0
 * b    |   |   |   |   |   | 0
 * c    |   |   |   |   |   | 0
 * d    |   |   |   |   |   | 0
 * e    |   |   |   |   |   | 0
 * null | 0 | 0 | 0 | 0 | 0 | 0
* */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "axcey";
        System.out.println(new LongestCommonSubsequence().getLongestCommonSubsequence(text1, text2));
    }

    private int getLongestCommonSubsequence(String text1, String text2) {
        // +1 in length for empty values
        int [][] cache = new int[text1.length() + 1][text2.length() + 1];
        for (int i = 0; i < text1.length() + 1; i++){
            cache[i][text2.length()] = 0;
        }
        for (int j = 0; j < text2.length() + 1; j++){
            cache[text1.length()][j] = 0;
        }
        for (int i = text1.length() - 1; i >= 0; i--){
            for (int j = text2.length() - 1; j >= 0; j--){
                if (text1.charAt(i) == text2.charAt(j)){
                    cache[i][j] = 1 + cache[i+1][j+1];
                } else {
                    cache[i][j] = Math.max(cache[i+1][j], cache[i][j+1]);
                }
            }

        }

        print2DArray(cache);
        return cache[0][0];
    }

    private void print2DArray(int[][] cache) {
        for (int i = 0; i < cache.length; i++){
            for (int j = 0; j < cache[0].length; j++){
                System.out.print(cache[i][j]);
            }
            System.out.println();
        }
    }
}
