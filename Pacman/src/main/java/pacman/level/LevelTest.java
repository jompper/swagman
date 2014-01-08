/*
 * Pacman - LevelTest
 * 8.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.level;

/**
 *
 * @author Joni
 */
public class LevelTest implements Level{
    
    private final int level[][];
    private final int width;
    private final int height;

    public LevelTest() {

        /**
         * Classic Pacman map
         *
         * Tiles: 0 = Empty, 1 = PacDot, 2 = PowerPellet, 3 = Cherry, 4 = Pacman
         * 5 = Blinky, 6 = Pinky, 7 = Inky, 8 = Clyde, 10 = Wall Left-Right, 11
         * = Wall Top-Down, 12 = Wall Right-Down, 13 = Wall Right-up, 14 = Wall
         * Left-Down, 15 = Wall Left-Up
         */
        level = new int[][]{
            {12, 10, 10, 10, 10, 10, 14},
            {11, 5,  11,  1,  1,  2, 11},
            {11, 16, 17, 10, 14,  1, 11},
            {11,  6,  7,  8, 11,  4, 11},
            {13, 10, 10, 10, 10, 10, 15},
        };

        this.width = level[0].length;
        this.height = level.length;
    }

    @Override
    public int[][] getLevel() {
        return level;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    /**
     *
     * @param x
     * @param y
     * @return true if map position contains wall
     */
    @Override
    public boolean isBlocked(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            return false;
        }
        return this.level[y][x] > 9 && this.level[y][x] < 20;
    }
}
