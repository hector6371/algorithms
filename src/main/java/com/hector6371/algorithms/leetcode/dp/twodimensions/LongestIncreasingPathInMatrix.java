package com.hector6371.algorithms.leetcode.dp.twodimensions;

import java.util.*;

import static java.lang.Math.max;

/*
#329
https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

Given an m x n integers matrix, return the length of the longest increasing path in matrix.
From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move
outside the boundary (i.e., wrap-around is not allowed).

Example 1:
Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
Output: 4
Explanation: The longest increasing path is [1, 2, 6, 9].

Example 2:
Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
Output: 4
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.

Example 3:
Input: matrix = [[1]]
Output: 1

Constraints:
    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 200
    0 <= matrix[i][j] <= 231 - 1
* */
public class LongestIncreasingPathInMatrix {

    Map<Cell, List<Cell>> cellToNeighborsMap = new HashMap<>();
    Integer [][] memo;
    //TOP-DOWN approach
    //start at one (test each one),
    // 1) add to visited
    // 2) check neighbors
    //  -if none abort
    //  -if one, proceed
    //  -if several, recurse
    // * we need to keep the visited nodes
    // * we need to keep the current position
    // bfs for each node?
    // no, we don't need to keep track of visited, because visited will always have lower values than current as it is required to increase for each movement
    // we could cache only by current position, because of the previous sentence
    public int longestIncreasingPath(int[][] matrix) {
        memo = new Integer[matrix.length][matrix[0].length];
        int path = 0;
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[0].length; j++) {
                path = max(path, recursive(matrix, i, j));
            }
        }
        return path;
    }

    private int recursive(int[][] matrix, int i, int j) {
        int maxPath = 1;
        Integer memoizedResult = memo[i][j];
        if (memoizedResult == null) {
            List<Cell> neighbors = getNeighbors(matrix, i, j);
            for (Cell cell : neighbors) {
                int path = recursive(matrix, cell.getX(), cell.getY());
                maxPath = max(maxPath, path + 1);
            }
            memo[i][j] = maxPath;
        } else {
            maxPath = memoizedResult;
        }
        return maxPath;
    }

    private List<Cell> getNeighbors(int[][] matrix, int i, int j) {
        Cell currentCell = new Cell(i,j);
        List<Cell> neighbors = cellToNeighborsMap.computeIfAbsent(currentCell, cell -> new ArrayList<>());
        if (neighbors.isEmpty()){
            neighbors = new ArrayList<>();

            int value = matrix[i][j];

            //left
            if (i > 0 && matrix[i-1][j] > value){
                neighbors.add(new Cell(i-1, j));
            }
            //right
            if (i < matrix.length - 1 && matrix[i+1][j] > value){
                neighbors.add(new Cell(i+1, j));
            }
            //up
            if (j > 0 && matrix[i][j-1] > value){
                neighbors.add(new Cell(i, j-1));
            }
            //right
            if (j < matrix[0].length - 1 && matrix[i][j+1] > value){
                neighbors.add(new Cell(i, j+1));
            }
        }

        return neighbors;
    }

    private class Cell {
        int x;
        int y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return x == cell.x && y == cell.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}