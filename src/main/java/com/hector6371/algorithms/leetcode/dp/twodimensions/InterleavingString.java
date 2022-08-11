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
    //public boolean isInterleave(String s1, String s2, String s3) {


    //}
}
