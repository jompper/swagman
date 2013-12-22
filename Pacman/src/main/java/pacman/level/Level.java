/*
 * Pacman - Level
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.level;

/**
 *
 * @author Joni
 */
public interface Level {
    public int[][] getLevel();
    public int getWidth();
    public int getHeight();
    public boolean isBlocked(int x, int y);
}
