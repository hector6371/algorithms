package com.hector6371.algorithms.leetcode.dp.twodimensions;

/*
#115
https://leetcode.com/problems/distinct-subsequences/

Given two strings s and t, return the number of distinct subsequences of s which equals t.
A string's subsequence is a new string formed from the original string by deleting some (can be none) of the characters
without disturbing the remaining characters' relative positions. (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from S.
rab(b)(b)it
ra(b)b(b)it
rab(b)(b)it

Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from S.
ba(b)g(b)(a)(g)
ba(bgba)g
b(abgb)ag
(ba)b(gb)ag
(babg)bag

Constraints:
    1 <= s.length, t.length <= 1000
    s and t consist of English letters.
* */

public class DistinctSubsequences {

    Integer [][] memo;

    //TOP-DOWN
    //index i for s, j, for t
    //Recursively, for a String, we could either:
    // a) delete a letter, check next letter (recurse over i++)
    // b) don't delete, check next letter (recurse over i++, j++)
    //Sum the outputs
    public int numDistinct(String s, String t) {
        int i = 0, j = 0;
        memo = new Integer[s.length()][t.length()];
        return recursive(s, t, i, j);
    }

    private int recursive(String s, String t, int i, int j) {
        int solution = 0;
        if (j >= t.length()) {
            // we could delete all the remaining
            solution = 1;
        } else if (i >= s.length()) {
            //we would need more chars from s to fill t, so no solution available here
            solution = 0;
        } else {
            Integer memoizedResult = memo[i][j];
            if (memoizedResult == null) {
                //if character matches, we could delete it or stick with it
                //if it doesn't match, we could only delete it
                if (s.charAt(i) == t.charAt(j)) {
                    solution += recursive(s, t, i + 1, j + 1);
                }
                //delete it, so we should only worry on next the subproblem of the next s char
                solution += recursive(s, t, i + 1, j);
                memo[i][j] = solution;
            } else {
                solution = memoizedResult;
            }
        }
        return solution;
    }
}
