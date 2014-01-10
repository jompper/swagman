/*
 * Pacman - AbstractMovingTile
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

/**
 * Abstact Moving object All moving objects could extend this class
 *
 * @author Joni
 */
public class AbstractMovingTile extends AbstractTile implements Moving {

    /**
     * Objects current direction
     */
    protected Direction direction;
    
    /**
     * Direction where would like to move whenpossible
     */
    protected Direction changeDirection;
    
    /**
     * Speed, how many pixels per move does object move
     */
    protected double speed;
    
    /**
     * Fake location, for smooth moving and drawing
     */
    protected double locationX;
    protected double locationY;
    
    /**
     * Is object in chase mode
     */
    private boolean isChase;

    /**
     * Construct AbstractMovingTile
     * @param x coordinate
     * @param y coordinate
     * @param d current direction
     * @param speed current speed
     */
    public AbstractMovingTile(int x, int y, Direction d, double speed) {
        super(x, y);
        this.direction = d;
        this.changeDirection = d;
        this.locationX = 0;
        this.locationY = 0;
        this.speed = speed;
        this.isChase = true;
    }

    /**
     * Set new speed 2 is basic value for pacman
     *
     * @param speed of an object
     */
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    /**
     * Returns Pacmans current direction
     *
     * @return current direction
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns Pacmans change direction. Direction is changed when pacman can
     * move to this direction.
     *
     * @return Direction where should go when possible
     */
    @Override
    public Direction getChangeDirection() {
        return this.changeDirection;
    }

    /**
     * Set Pacmans direction to d Now pacman moves to direction d
     *
     * @param d new Direction
     */
    @Override
    public void setDirection(Direction d) {
        this.direction = d;
    }

    /**
     * Set Pacmans change direction to d. Pacmans direction is changed to change
     * direction when pacman can move to this direction.
     *
     * @param d new direction where should turn when ever possible
     */
    @Override
    public void setChangeDirection(Direction d) {
        this.changeDirection = d;
    }

    /**
     * Moves object to current direction.
     *
     * @return true if tile really moves
     */
    @Override
    public boolean move() {
        switch (this.direction) {
            case UP:
                moveLocationXY(0, this.locationY - this.speed);
                if (this.locationY <= -9) {
                    this.locationY += 16;
                    this.y--;
                    return true;
                }
                break;
            case DOWN:
                moveLocationXY(0, this.locationY + this.speed);
                if (this.locationY >= 9) {
                    this.locationY -= 16;
                    this.y++;
                    return true;
                }
                break;
            case LEFT:
                moveLocationXY(this.locationX - this.speed, 0);
                if (this.locationX <= -9) {
                    this.locationX += 16;
                    this.x--;
                    return true;
                }
                break;
            case RIGHT:
                moveLocationXY(this.locationX + this.speed, 0);
                if (this.locationX >= 9) {
                    this.locationX -= 16;
                    this.x++;
                    return true;
                }
                break;
        }
        return false;
    }

    /**
     * Set new fake location XY
     *
     * @param locationX
     * @param locationY
     */
    private void moveLocationXY(double locationX, double locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }

    /**
     * Center fake XY if unable to move to fix sprite location
     */
    @Override
    public void moveLocation() {
        this.locationX = moveLocation(this.locationX, this.speed);
        this.locationY = moveLocation(this.locationY, this.speed);
    }

    /**
     * Return new distance to origo with speed
     *
     * @param location value
     * @param speed value
     * @return basicly distance from origo - speed
     */
    private double moveLocation(double location, double speed) {
        if (location <= -speed) {
            return location + speed;
        } else if (location >= speed) {
            return location - speed;
        }
        return 0;
    }

    /**
     * Get n next X position value to current direction
     * @param n steps in current direction
     * @return x coordinate
     */
    @Override
    public int getNextX(int n) {
        return getNextX(n, this.direction);
    }

    /**
     * Get n next X position to direction d from Pacmans current position. Used
     * to check if next tile is movable and for AI
     *
     * @param n steps in
     * @param d direction
     * @return x coordinate
     */
    @Override
    public int getNextX(int n, Direction d) {
        switch (d) {
            case LEFT:
                return this.x - n;
            case RIGHT:
                return this.x + n;
        }
        return this.x;
    }

    
    /**
     * Get n next Y position value to current direction
     * @param n steps in current direction
     * @return y coordinate
     */
    @Override
    public int getNextY(int n) {
        return getNextY(n, this.direction);
    }

    /**
     * Get n next Y position to direction d from Pacmans current position. Used
     * to check if next tile is movalbe and for AI
     *
     * @param n steps in
     * @param d direction
     * @return y coordinate
     */
    @Override
    public int getNextY(int n, Direction d) {
        switch (d) {
            case UP:
                return this.y - n;
            case DOWN:
                return this.y + n;
        }
        return this.y;
    }

    
    /**
     * Force set new Y to object
     * @param x coordinate
     */
    @Override
    public void setX(int x) {
        this.x = x;
    }

    
    /**
     * Force set new Y to object
     * @param y coordinate
     */
    @Override
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Set object to chase or scatter mode
     * @param chase status
     */
    public void setChase(boolean chase) {
        this.isChase = chase;
    }

    /**
     * @return current mode for object 
     */
    public boolean isChase() {
        return this.isChase;
    }
}
