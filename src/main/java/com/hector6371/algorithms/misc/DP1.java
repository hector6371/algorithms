package com.hector6371.algorithms.misc;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DP1 {

    // @Zazo
    // Completad una función `path_exists(a, b, m, n, A)` donde (a, b) es la coordenada de inicio, (m, n) la coordenada
    // de destino, A es un array de 2 dimensiones binario, donde la coordenada (i, j) indica que la casilla está libre
    // si vale 0, y ocupada si vale 1. Devolved True si existe un camino que vaya de la coordenada (a, b) a la
    // coordenada (m, n), y False si no existe. Bonus: devolved el camino más corto si existe en lugar de True.

    public static void main (String [] args ){
        int[][] paths = {
                {0, 0, 0 },
                {0, 0, 0 },
                {0, 0, 0 }
        };
        int origin_x = 0;
        int origin_y = 0;
        int destination_x = 2;
        int destination_y = 2;
        System.out.println(pathExists(origin_x, origin_y, destination_x, destination_y, paths));
    }

    public static class Cell {
        int x;
        int y;

        int distance = Integer.MAX_VALUE;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Cell(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }
    }

    public static boolean pathExists( int origin_x, int origin_y, int destination_x, int destination_y, int [][] paths){
        int [][] visited = new int [paths.length][paths[0].length];
        Arrays.stream(visited).forEach(a -> Arrays.fill(a, 0));

        Queue<Cell> toCheckQueue = new LinkedList<>();
        toCheckQueue.add(new Cell(origin_x, origin_y));

        while (!toCheckQueue.isEmpty()){
            Cell cell = toCheckQueue.poll();
            if (cell.x == destination_x && cell.y == destination_y){
                return true;
            } else {
                addNeighboursIfFree(cell, toCheckQueue, paths, visited);
            }

        }
        return false;
    }

    private static void addNeighboursIfFree(Cell cell, Queue<Cell> toCheckQueue, int[][] paths, int[][] visited) {
        int lastDistance = cell.getDistance();
        if (cell.getX() > 0){
            int toCheckX = cell.getX() - 1;
            int toCheckY = cell.getY();
            if (paths[toCheckX][toCheckY] == 0 && visited[toCheckX][toCheckY] == 0){
                toCheckQueue.add(new Cell(toCheckX, toCheckY, lastDistance + 1));
                visited[toCheckX][toCheckY] = 1;
            }
        }
        if (cell.getY() > 0){
            int toCheckX = cell.getX();
            int toCheckY = cell.getY() - 1;
            if (paths[toCheckX][toCheckY] == 0 && visited[toCheckX][toCheckY] == 0){
                toCheckQueue.add(new Cell(toCheckX, toCheckY));
                visited[toCheckX][toCheckY] = 1;
            }
        }
        if (cell.getX() < paths.length - 1){
            int toCheckX = cell.getX() + 1;
            int toCheckY = cell.getY();
            if (paths[toCheckX][toCheckY] == 0 && visited[toCheckX][toCheckY] == 0){
                toCheckQueue.add(new Cell(toCheckX, toCheckY));
                visited[toCheckX][toCheckY] = 1;
            }
        }
        if (cell.getY() < paths.length - 1){
            int toCheckX = cell.getX();
            int toCheckY = cell.getY() + 1;
            if (paths[toCheckX][toCheckY] == 0 && visited[toCheckX][toCheckY] == 0){
                toCheckQueue.add(new Cell(toCheckX, toCheckY));
                visited[toCheckX][toCheckY] = 1;
            }
        }
    }
}
