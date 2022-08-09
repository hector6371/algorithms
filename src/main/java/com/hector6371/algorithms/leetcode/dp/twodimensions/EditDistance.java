package com.hector6371.algorithms.leetcode.dp.twodimensions;

public class EditDistance {

// #72
// https://leetcode.com/problems/edit-distance/
// Write a function that computes the minimum number of operations needed to transform one string into another.
//The operations you can use are insert a character, remove a character or replace a character.
//
//For example:
//Input:   str1 = "cat", str2 = "cut"
//Output:  1
//We can convert str1 into str2 by replacing 'a' with 'u'.
//
//Input:   str1 = "sunday", str2 = "saturday"
//Output:  3
//We need to convert "un" to "atur"; Replace 'n' with 'r', insert 't', insert 'a'
//
//Input:  str1 = "abcde", str2 = "bcdef"
//Output: 2
//We remove the first 'a' and add a 'd'
//
//
//Input:  str1 = "abababa",  = "aaaa"
//Output: 3
//
//Input:  str1 = "abc", str2 = ""
//Output: 3
//
//
//Input:  str1 = "abc", str2 = "def"
//Output: 3
//
//str1 = "abcdef" str2="def"
//
//
//Input:  str1 = "defabc", str2 = "defxby"
//Output: 3
//"""

    public static void main(String[] args) {
        String origin = "defabc";
        String destination = "defxby";
        System.out.println("Distance is: " + new EditDistance().stringDistance(origin, destination));
    }

    private int stringDistance(String origin, String destination) {
        //cache holds memoization in 2d. The +1 in size is for the empty strings
        int [][] cache = new int[origin.length() + 1][ destination.length() + 1];


        // #1 ob: from null to null, there is no ops, distance is 0
        // #2 ob: from null to word and the inverse, the num ops is all inserts or all deletes, so distance is the length of word
        // That so, we can draw the initial matrix (each letter accumulates the previous ones, i.e b means bc, and a means abc (for row) or adc (for column):
        //     - | a | b | c | null
        //   ====|===|===|===|======
        //     a |   |   |   | 3 ( from adc -> null, three remove ops)
        //     d |   |   |   | 2 (from dc -> null, two remove ops)
        //     c |   |   |   | 1 (from c -> null, one remove op)
        //  null | 3 | 2 | 1 | 0

        // if i is the index for the first word and j is the index of the second word
        // #3 ob: if origin[i] == destination[j], the letter is at the correct, space, do nothing with it, 0 ops
        // So, if we start at the first cell, a-a, as they have the same value, it will not require more operations than
        // the d-b cell. So we should check that cell. As the d-b cell have not the same value, we should brute force
        // the three operation options (insert, delete, replace). And pick the one whe makes less operations in the future...
        // So, for filling the upper cells, we need to resolve the bottom ones: bottom-up dp.
        //
        // Ob #4. What is the cost of each operation? It is one plus the operations to be done afterwards. Converting dc -> bc by
        //  -inserting: add one to the cost, then we would have to solve the problem of bdc -> bc. As the first char is the same,
        // we would need to solve dc -> c. That is, our pointers should move from i, j to i, j+1. (i doesn't move because we have entered a
        // letter just before i, so i-1+1 = i
        //  -deleting: add one to the cost, then we would have to solve the problem of c -> bc. That is, our pointers
        //  should move from i, j to i+1, j
        //  -replacing: add one to the cost, then we would have to solve the problem of bc -> bc. As the first char is the same,
        // (because we forced so), we would have to solve the problem c -> c. That is, moving the pointers to the next pair of letters,
        // from i, j to i+1, j+1
        //
        // After checking all the three possibilities we stick to the min one to match the problem


        //fill the last row and column corresponding to the empty strings (Ob #2):
        for (int j = 0; j < destination.length() + 1; j++){
            cache [origin.length()][j] = destination.length() - j;
        }
        for (int i = 0; i < origin.length() + 1; i++){
            cache [i][destination.length()] = origin.length() - i;
        }

        //fill it bottom down (Excluded last row and last column which are already filled)
        for (int i = origin.length() - 1; i >= 0; i--){
            for (int j = destination.length() - 1; j >= 0; j--){
                // Ob #3:
                if (origin.charAt(i) == destination.charAt(j)) {
                    cache[i][j] = cache[i+1][j+1];
                } else {
                    cache[i][j] = 1 + Math.min(cache[i+1][j+1], Math.min(cache[i][j+1], cache[i+1][j]));
                }
            }
        }
        printArray (cache);

        return cache[0][0];
    }

    private void printArray(int[][] cache) {
        for (int i = 0; i < cache.length; i++){
            for (int j = 0; j < cache[0].length; j++){
                System.out.print(cache[i][j]);
            }
            System.out.println();
        }
    }


}
