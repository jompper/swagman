/*
 * Swagman - MovingTile
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

/**
 *
 * @author Joni
 */
public abstract class MovingTile extends Tile implements Moveable {

    public static final int DIRECTION_LEFT = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_UP = 2;
    public static final int DIRECTION_DOWN = 3;

    private int direction;

    public MovingTile(int x, int y, int direction) {
        super(x, y);
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Point getNextPoint() {
        if (this.direction == DIRECTION_LEFT) {
            return new Point(x - 1, y);
        } else if (this.direction == DIRECTION_RIGHT) {
            return new Point(x + 1, y);
        } else if (this.direction == DIRECTION_UP) {
            return new Point(x, y - 1);
        } else if (this.direction == DIRECTION_DOWN) {
            return new Point(x, y + 1);
        }
        return null;
    }

    @Override
    public void move() {
        if (this.direction == DIRECTION_LEFT) {
            this.x--;
        } else if (this.direction == DIRECTION_RIGHT) {
            this.x++;
        } else if (this.direction == DIRECTION_UP) {
            this.y--;
        } else if (this.direction == DIRECTION_DOWN) {
            this.y++;
        }
    }

}
