package com.hector6371.algorithms.leetcode.dp.onedimension;

import com.hector6371.algorithms.leetcode.dp.onedimension.ClimbingStairs;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClimbingStairsTest {

    @Test
    void given3_whenClimbStairs_then3 (){
        int n = 3;
        assertEquals(3, new ClimbingStairs().climbStairs(n));
    }
}
