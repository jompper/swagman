/*
 * Swagman - MovingTile
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import swagman.game.Direction;

/**
 *
 * @author Joni
 */
public abstract class MovingTile extends Tile implements Moveable {

    private Direction direction;
    private Direction newDirection;

    public MovingTile(int x, int y) {
        this(x, y, Direction.RIGHT);
    }

    public MovingTile(int x, int y, Direction d) {
        super(x, y);
        this.direction = d;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public Direction getNewDirection() {
        return this.newDirection;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }
    
    public void setNewDirection(Direction direction){
        this.newDirection = direction;
    }
    
    @Override
    public int getNextX() {
        return getNextX(1);
    }

    @Override
    public int getNextX(int n) {
        return getNextX(n, this.direction);
    }

    @Override
    public int getNextX(int n, Direction d){
        if (d == Direction.LEFT) {
            return this.x - n;
        } else if (d == Direction.RIGHT) {
            return this.x + n;
        }
        return this.x;
    }
    
    @Override
    public int getNextY() {
        return getNextY(1);
    }

    @Override
    public int getNextY(int n) {
        return getNextY(n, this.direction);
    }
    
    @Override
    public int getNextY(int n, Direction d){
        if (d == Direction.UP) {
            return this.y - n;
        } else if (d == Direction.DOWN) {
            return this.y + n;
        }
        return this.y;
    }

    @Override
    public void move() {
        if (this.direction == Direction.LEFT) {
            this.x--;
        } else if (this.direction == Direction.RIGHT) {
            this.x++;
        } else if (this.direction == Direction.UP) {
            this.y--;
        } else if (this.direction == Direction.DOWN) {
            this.y++;
        }
    }

}
