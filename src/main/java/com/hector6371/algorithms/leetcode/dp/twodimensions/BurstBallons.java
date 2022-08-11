package com.hector6371.algorithms.leetcode.dp.twodimensions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
* You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array
* nums. You are asked to burst all the balloons.
If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of
* bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
Return the maximum coins you can collect by bursting the balloons wisely.

Example 1:
Input: nums = [3,1,5,8]
Output: 167
Explanation:
nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167

Example 2:
Input: nums = [1,5]
Output: 10

Constraints:
    n == nums.length
    1 <= n <= 300
    0 <= nums[i] <= 100
* */
public class BurstBallons {

    //TOP-DOWN
    //Recursively, while there are balloons, you could either explode one or move to the next, wrapping the last with the first one
    //
    public int maxCoins(int[] nums) {
        List<Integer> balloonList = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        ;
        return recursive(balloonList);
    }

    private int recursive(List<Integer> balloonList) {
        int value = 0;
        for (int i = 0; i< balloonList.size(); i++){
            //remove i from the nums
            //collect the coins
            List<Integer> remainingBalloonList = new ArrayList<>(balloonList);
            int coinsCollected = collectCoins(remainingBalloonList, i);
            remainingBalloonList.remove(i);

            int recursiveValue = recursive(remainingBalloonList);
            if (coinsCollected + recursiveValue > value){
                value = coinsCollected + recursiveValue;
            }
        }
        return value;
    }

    private int collectCoins(List<Integer> remainingBalloonList, int index) {
        int explodedBalloon = remainingBalloonList.get(index);
        int previousExplodedBalloon = index == 0 ? 1 : remainingBalloonList.get(index - 1);
        int followingExplodedBalloon = index + 1  == remainingBalloonList.size() ? 1 : remainingBalloonList.get(index + 1);

        return explodedBalloon * previousExplodedBalloon * followingExplodedBalloon;
    }
}
