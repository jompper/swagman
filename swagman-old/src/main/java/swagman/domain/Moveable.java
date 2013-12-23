/*
 * Swagman - Moveable
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import swagman.game.Direction;

/**
 *
 * @author Joni
 */
public interface Moveable {

    public Direction getNewDirection();
    
    public void setDirection(Direction d);
    
    public int getNextX();

    public int getNextX(int n);

    public int getNextX(int n, Direction d);

    public int getNextY();

    public int getNextY(int n);

    public int getNextY(int n, Direction d);

    public void move();
}
