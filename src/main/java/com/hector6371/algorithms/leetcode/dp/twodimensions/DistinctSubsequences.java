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
    // RESULTS:
    // Runtime: 68 ms, faster than 5.12% of Java online submissions for Distinct Subsequences.
    // Memory Usage: 49.7 MB, less than 75.22% of Java online submissions for Distinct Subsequences.
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

    /*
    * BOTTOM-UP
    * s = babgbag", t = "bag"
    * XX| b | a | g | ''
    * ==|===|===|===|===
    * b |   |   |   | 1
    * a |   | *1|   | 1
    * b |   |   |   | 1
    * g | *2|   |   | 1
    * b |   |   |   | 1
    * a |   |   |   | 1
    * g |   |   |   | 1
    * ''| 0 | 0 | 0 | 1
    *
    * If empty and empty, one solution possible, don't do anything
    * If s contains characters but t is empty, there is a solution always, by deleting all the chars from s
    * If s is empty, but t contains character, there is no solution never (as we can't delete from t), return 0
    * *1: as left matches top, there would be the sum of solutions of removing the letter in s (going down one cell)
    *  and not deleting anything and check next char from s and char from t. (going one diagonal down right cell)
    * *2: as left doesn't match top, it would have the same solutions as when deleting that char in s, (going down one cell)
    *
    * As we need to go down and right, for getting the solution, we will start from bottom right to top left
    */

    public int numDistinctBottomUp(String s, String t) {
        int [][] tabulation = new int[s.length() + 1][t.length() + 1];

        //fill last row
        for (int j = 0; j < t.length() + 1; j++){
            tabulation[s.length()][j] = 0;
        }

        //fill last col
        for (int i = 0; i < s.length() + 1; i++){
                tabulation[i][t.length()] = 1;
        }

        //this value overrides the last col initialization... But keep it for verbosity
        tabulation[s.length()][t.length()] = 1;

        for (int i = s.length() - 1; i >= 0; i--){
            for (int j = t.length() - 1; j >= 0; j--) {
                int result = 0;
                result += tabulation[i + 1][j];
                if (s.charAt(i) == t.charAt(j)){
                    result += tabulation[i+1][j+1];
                }
                tabulation[i][j] = result;
            }
        }

        return tabulation[0][0];
    }
}
