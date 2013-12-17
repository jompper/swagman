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
    
    public TileMap(char[][] tileMap){
        this.tileMap = tileMap;
    }
    
    public char[][] getMap() {
        return this.tileMap;
    }

    public char getTile(int x, int y) {
        return this.tileMap[y][x];
    }

    public boolean canMove(int x, int y) {
        return getTile(x, y) == '.';
    }
}
