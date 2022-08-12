package com.hector6371.algorithms.leetcode.dp.onedimension;

/*
#416
https://leetcode.com/problems/partition-equal-subset-sum/

Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets
 such that the sum of elements in both subsets is equal.

Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.

Constraints:
    1 <= nums.length <= 200
    1 <= nums[i] <= 100
* */
public class PartitionEqualSubsetSum {

    //Sum of both subsets are the same, means half is in one and half is in the other... It means, one subset needs
    // the half of the total
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int value : nums){
            sum += value;
        }
        if (sum % 2 == 1){
            return false;
        } else {
            int target = sum / 2;
            int index = 0;
            return canPartition(nums, target, index);
        }
    }

    // For each value we could include it or not.
    // a) if included, lower the target, and increase the index, and recurse
    // b) if not included, just increase the index and recurse
    private boolean canPartition(int[] nums, int target, int index) {
        if (index == nums.length){
            if (target == 0){
                return true;
            } else {
                return false;
            }
        } else if (target < 0){ // as they are all positive
            return false;
        } else {
            boolean canPartitionIfIncluded = canPartition(nums, target - nums[index], index + 1);
            boolean canPartitionIfNotIncluded = canPartition(nums, target, index + 1);
            return canPartitionIfIncluded || canPartitionIfNotIncluded;
        }
    }
}
