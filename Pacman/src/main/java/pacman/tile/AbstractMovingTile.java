/*
 * Pacman - AbstractMovingTile
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.tile;

import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public class AbstractMovingTile extends AbstractTile implements Moving {

    protected Direction direction;
    protected Direction changeDirection;
    protected double speed;
    protected double locationX;
    protected double locationY;

    public AbstractMovingTile(int x, int y, Direction d, double speed) {
        super(x, y);
        this.direction = d;
        this.changeDirection = d;
        this.locationX = 0;
        this.locationY = 0;
        this.speed = speed;
    }

    public void setSpeed(double speed){
        this.speed = speed;
    }
    
    /**
     * Returns Pacmans current direction
     *
     * @return
     */
    @Override
    public Direction getDirection() {
        return this.direction;
    }

    /**
     * Returns Pacmans change direction. Direction is changed when pacman can
     * move to this direction.
     *
     * @return
     */
    @Override
    public Direction getChangeDirection() {
        return this.changeDirection;
    }

    /**
     * Set Pacmans direction to d Now pacman moves to direction d
     *
     * @param d
     */
    @Override
    public void setDirection(Direction d) {
        this.direction = d;
    }

    /**
     * Set Pacmans change direction to d. Pacmans direction is changed to change
     * direction when pacman can move to this direction.
     *
     * @param d
     */
    @Override
    public void setChangeDirection(Direction d) {
        this.changeDirection = d;
    }

    /**
     * Moves object to current direction.
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
    
    private void moveLocationXY(double locationX, double locationY){
        this.locationX = locationX;
        this.locationY = locationY;
    }
    
    @Override
    public void moveLocation() {
        this.locationX = moveLocation(this.locationX, this.speed);
        this.locationY = moveLocation(this.locationY, this.speed);
    }
    
    private double moveLocation(double location, double speed){
        if (location <= -speed) {
            return location + speed;
        } else if (location >= speed) {
            return location - speed;
        }
        return 0;
    }

    /**
     * Get n next X position to direction d from Pacmans current position. Used
     * to check if next tile is movable and later for AI
     *
     * @param n
     * @param d
     * @return
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
     * Get n next Y position to direction d from Pacmans current position. Used
     * to check if next tile is movalbe and later for AI
     *
     * @param n
     * @param d
     * @return
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

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

}
