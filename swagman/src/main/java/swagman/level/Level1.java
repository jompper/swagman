/*
 * Swagman - Level1
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.level;

import swagman.domain.Board;
import swagman.domain.Pacman;
import swagman.map.TileMap;

/**
 *
 * @author Joni
 */
public class Level1 extends Board {

    /**
     *
     */
    public Level1(int scale) {
        super(scale);
        char[][] map = new char[][]{
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '#', '.', '.', '#', '.', '#', '.', '.', '.', '#', '.', '#', '#', '.', '#', '.', '.', '.', '#', '.', '#', '.', '.', '#', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '#', '#', '#', '=', '=', '#', '#', '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#'},
            {'.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '.', '.', '.', '.', '.'},
            {'#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '.', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.', '.', '#'},
            {'#', '#', '#', '.', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '.', '#', '#', '#'},
            {'#', '#', '#', '.', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '.', '#', '#', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '#', '#', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#', '#', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '#'},
            {'#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#'},
            {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'}
        };
        TileMap m = new TileMap(map);
        this.setTileMap(m);
        Pacman p = new Pacman(14, 23);
        this.setPacman(p);
        
    }

}