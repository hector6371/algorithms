package com.hector6371.algorithms.leetcode.dp.twodimensions;


import static java.lang.Math.abs;

/*
#97
https://leetcode.com/problems/interleaving-string/

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
An interleaving of two strings s and t is a configuration where s and t are divided into n and m non-empty substrings respectively, such that:
    s = s1 + s2 + ... + sn
    t = t1 + t2 + ... + tm
    |n - m| <= 1
(Solver's note => This info make me a mess...taking into account the partitions overcomplicated the problem.
It is not neccesary because, when choosing one char, it could be from the previous one string (partitions stay the same),
or from the other one (partition++). But, in the latter case, it would then be the same, picking other char from the latter
string (partitions stay the same) or from the first one (partition--), so partition difference keep balancing between -1
and 1 by themselves)
    The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

Example 1:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Explanation: One way to obtain s3 is:
Split s1 into s1 = "aa" + "bc" + "c", and s2 into s2 = "dbbc" + "a".
Interleaving the two splits, we get "aa" + "dbbc" + "bc" + "a" + "c" = "aadbbcbcac".
Since s3 can be obtained by interleaving s1 and s2, we return true.

Example 2:
Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
Explanation: Notice how it is impossible to interleave s2 with any other string to obtain s3.

Example 3:
Input: s1 = "", s2 = "", s3 = ""
Output: true

Constraints:
    0 <= s1.length, s2.length <= 100
    0 <= s3.length <= 200
    s1, s2, and s3 consist of lowercase English letters.

Follow up: Could you solve it using only O(s2.length) additional memory space?

* */
public class InterleavingString {


    Integer [][]memo;

    //TOP-DOWN solution
    // Recursively, we could either add one letter of s1 and increment the partitions of s1 or
    // add a letter of s2 and increment the partition of s2
    // we could decide this depending on matching on s3. if both options matches, go on with both
    // and return true if any one works.
    public boolean isInterleave(String s1, String s2, String s3) {
        int s1Index = 0;
        int s2Index = 0;
        //int s3Index = s1Index + s2Index;
        Boolean latestFrom1 = null; //not to take in account for first letter
        //keep track of latter added letter so we now if we are partitioning more or not
        memo = new Integer [s1.length()][s2.length()];
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        } else {
            return recursive(s1, s2, s3, s1Index, s2Index, latestFrom1);
        }
    }

    private boolean recursive(String s1, String s2, String s3, int s1Index, int s2Index, Boolean latestFrom1) {
        boolean result;
        if (s1Index == s1.length()){
            result = s2.substring(s2Index).equals(s3.substring(s1Index + s2Index));
        }else if (s2Index == s2.length()){
            result = s1.substring(s1Index).equals(s3.substring(s1Index + s2Index));
        } else {
            Integer memoizedResult = memo[s1Index][s2Index];
            if (memoizedResult != null){
                result = memoizedResult == 1;
            } else {
                char target = s3.charAt(s1Index + s2Index);
                if (s1.charAt(s1Index) == target && s2.charAt(s2Index) == target) {
                    result = recursive(s1, s2, s3, s1Index + 1, s2Index, true)
                            || recursive(s1, s2, s3, s1Index, s2Index + 1, false);
                } else if (s1.charAt(s1Index) == target) {
                    result = recursive(s1, s2, s3, s1Index + 1, s2Index, true);
                } else if (s2.charAt(s2Index) == target) {
                    result = recursive(s1, s2, s3, s1Index, s2Index + 1, false);
                } else { //no matches
                    result = false;
                }
                memo[s1Index][s2Index] = result ? 1 : 0;
            }
        }
        return result;
    }

    //BOTTOM-UP solution
    /*
    * "aabcc"
    * "dbbca"
    * output => aadbbcbcac
    *
    *    | d | b | b | c | a | ""
    * ===|===|===|===|===|===|===
    * a  |   |   |   |   |   |
    * a  |   |   |   |   |   |
    * b  |   | *4|   |   |   |
    * c  |   |   |   |   |   |
    * c  |   |   |   |   | *3|
    * "" |   |   |   |   | *2| *1
    *
    *accumulating the remaining letters...we try to understand the problem
    *1:  "" and "" is true
    *2: combining "" and 'a', we could only get a. Taking the last char from the string, is 'c', so False
    *3: combining 'a' and 'c', whe could get 'ac' or 'ca'. Taking the last two chars from the desired string, are 'ac'. We can get it, True
    *4: this position is at the 3+2 index. The target, at the 5th index has a b..
    *   is there a 'b' at the left? True, so solution might be true if we use that char and the subproblem without this char is true (down cell)
    *   is there a 'b' at the top? True, so solution might be true if we use that char and the subproblem without this char is true (right cell)
    *   If it weren't any b neither at the top nor at the left, it could not be constructed the output, return false.
    *
    * As we go right and down, and we have base cases at the bottom, we could build the array solution bottom up
    * and retrieve the solution at the 0,0
    *
    * */
    public boolean isInterleaveTabulation(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()){
            return false;
        }
        int [][] tabulation = new int [s1.length() + 1][ s2.length() + 1];
        tabulation [s1.length()][s2.length()] = 1; //'' vs ''

        for (int i = s1.length(); i >= 0; i--){
            for (int j = s2.length(); j >= 0; j--) {
                // if not on the bottom, check same char and lower cell subproblem
                if (i < s1.length() && s1.charAt(i) == s3.charAt(i+j) && tabulation[i+1][j] == 1){
                    tabulation[i][j] = 1;
                }
                // if not on the right, check same char and right cell subproblem
                if (j < s2.length() && s2.charAt(j) == s3.charAt(i+j) && tabulation[i][j+1] == 1){
                    tabulation[i][j] = 1;
                }
            }
        }

        return tabulation[0][0] == 1;
    }
}
