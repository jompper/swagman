/*
 * Pacman - Level
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.level;

/**
 * Interface for levels
 * @author Joni
 */
public interface Level {
    /**
     * 
     * @return level int[][] map
     */
    public int[][] getLevel();
    
    /**
     * 
     * @return level width
     */
    public int getWidth();
    
    /**
     * 
     * @return level height
     */
    public int getHeight();
    
    /**
     * Is map location
     * @param x
     * @param y
     * @return blocked
     */
    public boolean isBlocked(int x, int y);
}
