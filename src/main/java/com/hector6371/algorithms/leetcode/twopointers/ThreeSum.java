package com.hector6371.algorithms.leetcode.twopointers;

import java.util.*;

public class ThreeSum {

    public List<List<Integer>> threeSum (int[] nums) {
        Map<Integer, Set<Integer>> numberToIndexesMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            Set<Integer> indexes = numberToIndexesMap.computeIfAbsent(nums[i], value -> new HashSet<>());
            indexes.add(i);
        }

        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            int firstNumber = nums[i];

            for (int j = i + 1; j < nums.length; j++){
                int secondNumber = nums[j];
                int thirdNumberSumZero = - firstNumber - secondNumber;
                Set<Integer> thirdNumberSumZeroIndex = numberToIndexesMap.get(thirdNumberSumZero);
                if (thirdNumberSumZeroIndex != null && Collections.max(thirdNumberSumZeroIndex) > j){

                    List<Integer> threesome = Arrays.asList(firstNumber, secondNumber, thirdNumberSumZero);
                    results.add(threesome);
                }

            }
        }
        return results;
    }

    public static void main(String[] args) {
        List<List<Integer>> tripletList = new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        for (List<Integer> triplet : tripletList){
            for (Integer number : triplet) {
                System.out.print(number + " ");
            }
            System.out.println();
        }


    }

}
