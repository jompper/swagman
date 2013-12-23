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

    private int speed;
    private int distance;

    public MovingTile(int x, int y) {
        this(x, y, Direction.RIGHT, 21);
    }

    public MovingTile(int x, int y, Direction d, int speed) {
        super(x, y);
        this.direction = d;
        this.speed = speed;
        this.distance = 0;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Direction getNewDirection() {
        return this.newDirection;
    }

    public void setDirection(Direction direction) {
        if (this.direction != direction) {
            if (this.direction == Direction.LEFT && direction == Direction.RIGHT || this.direction == Direction.RIGHT && direction == Direction.LEFT || this.direction == Direction.UP && direction == Direction.DOWN || this.direction == Direction.DOWN && direction == Direction.UP) {
                this.distance = 0;
            } else {
                this.distance *= -1;
            }
        }
        this.direction = direction;
    }

    public void setNewDirection(Direction direction) {
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
    public int getNextX(int n, Direction d) {
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
    public int getNextY(int n, Direction d) {
        if (d == Direction.UP) {
            return this.y - n;
        } else if (d == Direction.DOWN) {
            return this.y + n;
        }
        return this.y;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void move() {
        this.distance += speed;
        if (this.distance >= 80) {
            this.distance = 0 - this.distance + 80 + speed;
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

}
