/*
 * Swagman - TileMap
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package swagman.map;

import swagman.domain.Tileable;

/**
 *
 * @author Joni
 */
public interface TileMap {
    public char[][] getMap();
    public char getTile(int x, int y);
    public boolean canMove(int x, int y);
}
