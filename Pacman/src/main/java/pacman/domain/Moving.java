/*
 * Pacman - MovingTile
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

/**
 * Interface for moving objects
 *
 * @author Joni
 */
public interface Moving {

    /**
     * Should move player
     *
     * @return true on x or y change
     */
    public boolean move();

    /**
     * Center sprite in current tile
     */
    public void moveLocation();

    /**
     * @param x coordinate value
     */
    public void setX(int x);

    /**
     * @param y coordinate value
     */
    public void setY(int y);

    /**
     * @return current x coordinate
     */
    public int getX();

    /**
     * @return current y coordinate
     */
    public int getY();

    /**
     * @return current direction
     */
    public Direction getDirection();

    /**
     * @return direction where wants to got
     */
    public Direction getChangeDirection();

    /**
     * @param d new direction value
     */
    public void setDirection(Direction d);

    /**
     *
     * @param d new direction where would like to go
     */
    public void setChangeDirection(Direction d);

    /**
     * @param n steps in
     * @param d direction
     * @return x coordinate
     */
    public int getNextX(int n, Direction d);

    /**
     * @param n steps in
     * @param d direction
     * @return y coordinate
     */
    public int getNextY(int n, Direction d);

    /**
     * @param n steps in current direction
     * @return x coordinate
     */
    public int getNextX(int n);

    /**
     * @param n steps in current direction
     * @return y coordinate
     */
    public int getNextY(int n);
}
