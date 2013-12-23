/*
 * Pacman - MovingTile
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.tile;

import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public interface Moving {
    public void move();
    public void moveLocation();
    public void setX(int x);
    public void setY(int y);
    public int getX();
    public int getY();
    public Direction getDirection();
    public Direction getChangeDirection();
    public void setDirection(Direction d);
    public void setChangeDirection(Direction d);
    public int getNextX(int n, Direction d);
    public int getNextY(int n, Direction d);
}
