package com.hector6371.algorithms.leetcode.dp.twodimensions;


/*
* #494
* https://leetcode.com/problems/target-sum/
You are given an integer array nums and an integer target.
You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

Example 1:
Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3

Example 2:
Input: nums = [1], target = 1
Output: 1

Constraints:
    1 <= nums.length <= 20
    0 <= nums[i] <= 1000
    0 <= sum(nums[i]) <= 1000
    -1000 <= target <= 1000
*/
public class TargetSum {

    //TOP-DOWN approach
    //We could either put a plus or a minus before each item on recursive way
    //We need to keep track of the remaining so that the remaining sum could evaluate to match the remaining target
    public int findTargetSumWays(int[] nums, int target) {
        int index = 0;
        return recursive(nums, index, target);
    }

    private int recursive(int[] nums, int index, int target) {
        int numSolutions;
        //base case
        if (index >= nums.length){
            //if we pass through the array, no more solutions
            numSolutions = 0;
        } else if (index + 1 == nums.length){
            //if we are in the last index...
            // return if the values matches or opposite
            if (Math.abs(target) == Math.abs(nums[index])){
                if (target == 0){
                    numSolutions = 2; //+0 and -0
                } else {
                    numSolutions = 1;
                }
            } else {
                numSolutions = 0;
            }

        } else {
            //for a plus sign, we need less to reach the target
            numSolutions = recursive(nums, index + 1, target - nums[index]);
            //for a minus sign, we need more to reach the target
            numSolutions += recursive(nums, index + 1, target + nums[index]);
        }
        return numSolutions;
    }
}
