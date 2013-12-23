/*
 * Swagman - TileMap
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package swagman.map;


/**
 *
 * @author Joni
 */
public class TileMap {
    
    private final char[][] tileMap;
    private int width;
    private int height;
    
    
    public TileMap(char[][] tileMap){
        this.tileMap = tileMap;
        this.height = tileMap.length;
        this.width = tileMap[0].length;
    }
    
    public char[][] getMap() {
        return this.tileMap;
    }

    public char getTile(int x, int y) {
         if(x < 0 || y < 0 || y >= height || x >= width){
            return '#';
        }
        return this.tileMap[y][x];
    }

    public boolean canMove(int x, int y) {
         if(x < 0 || y < 0 || y >= height || x >= width){
            return false;
        }
        return getTile(x, y) == '.';
    }
}
