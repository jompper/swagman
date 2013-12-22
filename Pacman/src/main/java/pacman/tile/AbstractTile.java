/*
 * Pacman - Tile
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.tile;

/**
 * Abstract Tile to be extended. Getters for X, Y
 * and constructor
 * 
 * @author Joni
 */
public abstract class AbstractTile implements Tile{

    protected int x;
    protected int y;

    public AbstractTile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

}
