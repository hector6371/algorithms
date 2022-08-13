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
        return recursive(nums, 0, -1, 0);
    }

    private int recursive(int[] nums, int enquiryIndex, int lastChosenIndex, int length ) {
        if (enquiryIndex >= nums.length){
            return length;
        }
        int newLength = recursive(nums, enquiryIndex + 1, lastChosenIndex, length); //delete and recurse
        //if none has been added yet or the new value is bigger than the previously added, recurse
        if (lastChosenIndex == -1 || nums[enquiryIndex] > nums[lastChosenIndex]) {
            newLength = max(newLength, recursive(nums, enquiryIndex + 1, enquiryIndex, length + 1));
        }
        return newLength;
    }

}
