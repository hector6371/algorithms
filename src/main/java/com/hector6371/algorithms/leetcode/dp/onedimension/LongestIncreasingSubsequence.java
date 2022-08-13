package com.hector6371.algorithms.leetcode.dp.onedimension;


import static java.lang.Math.max;

/*
#300
https://leetcode.com/problems/longest-increasing-subsequence/

Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order
of the remaining elements. For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.

Example 2:
Input: nums = [0,1,0,3,2,3]
Output: 4

Example 3:
Input: nums = [7,7,7,7,7,7,7]
Output: 1

Constraints:
    1 <= nums.length <= 2500
    -104 <= nums[i] <= 104
* */
public class LongestIncreasingSubsequence {

    //TOP-DOWN
    //for each element, it could be deleted or not
    // if deleted, move index forward, keep count length
    // if not deleted, move index forward, increase length
    //keep the max decision
    public int lengthOfLIS(int[] nums) {
        int index = 0;
        int length = 0;
        //we start at second value so we don't have to control iabexception (on first case)
        return recursive(nums, index, length, Integer.MIN_VALUE);
    }

    private int recursive(int[] nums, int index, int length, int latestValue) {
        if (index >= nums.length){
            return length;
        }
        int newLength = recursive(nums, index + 1, length, latestValue); //delete and recurse
        if (nums[index] > latestValue){
            newLength = max(newLength, recursive(nums, index + 1, length + 1, nums[index]));
        }
        return newLength;
    }

}
