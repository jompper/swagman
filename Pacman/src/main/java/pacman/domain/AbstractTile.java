/*
 * Pacman - Tile
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

/**
 * Abstract Tile to be extended. Getters for X, Y and constructor
 *
 * @author Joni
 */
public abstract class AbstractTile implements Tile {

    /**
     * Objects current location
     */
    protected int x;
    protected int y;

    
    /**
     * Consturcts AbstractTile with position
     * @param x coordinate
     * @param y coordinate
     */
    public AbstractTile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return current X position
     */
    @Override
    public int getX() {
        return this.x;
    }

    /**
     * @return current Y position
     */
    @Override
    public int getY() {
        return this.y;
    }

}
