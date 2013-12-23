/*
 * Pacman - Pacman
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.AbstractTile;
import pacman.tile.Drawing;
import pacman.tile.Moving;

/**
 * Pacman is games main character and the only object, player can move.
 *
 * @author Joni
 */
public class Pacman extends AbstractTile implements Moving, Drawing {

    private Direction direction;
    private Direction changeDirection;
    private int locationX;
    private int locationY;
    private int speed;
    private int mouthPosition;

    public Pacman(int x, int y, Direction d) {
        super(x, y);
        this.direction = d;
        this.changeDirection = d;
        this.locationX = 0;
        this.locationY = 0;
        this.mouthPosition = 0;
        this.speed = 2;
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
     * Moves Pacman to Pacmans current direction.
     *
     * TODO: Smooth moving, 11 tiles per second. Equals 11 * 16 = 176 pixel per
     * second. Change game speed to 60 FPS Move 3 pixels at a time. Tile
     * position + 9 pixels = Next tile
     *
     */
    @Override
    public void move() {
        switch (this.direction) {
            case UP:
                this.locationY -= this.speed;
                this.locationX = 0;
                if (this.locationY <= -9) {
                    this.locationY += 16;
                    this.y--;
                }
                break;
            case DOWN:
                this.locationY += this.speed;
                this.locationX = 0;
                if (this.locationY >= 9) {
                    this.locationY -= 16;
                    this.y++;
                }
                break;
            case LEFT:
                this.locationX -= this.speed;
                this.locationY = 0;
                if (this.locationX <= -9) {
                    this.locationX += 16;
                    this.x--;
                }
                break;
            case RIGHT:
                this.locationX += this.speed;
                this.locationY = 0;
                if (this.locationX >= 9) {
                    this.locationX -= 16;
                    this.x++;
                }
                break;
        }
    }

    @Override
    public void moveLocation() {
        if (this.locationX <= -this.speed) {
            this.locationX += this.speed;
        } else if (this.locationX >= this.speed) {
            this.locationX -= this.speed;
        } else {
            this.locationX = 0;
        }
        if (this.locationY <= -this.speed) {
            this.locationY += this.speed;
        } else if (this.locationY >= this.speed) {
            this.locationY -= this.speed;
        } else {
            this.locationY = 0;
        }
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
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x * 16 + locationX - 2, y * 16 + locationY - 2, 20, 20);

        this.mouthPosition++;
        g.setColor(Color.BLACK);
        int[] recX;
        int[] recY;
        if(mouthPosition == 24){
            mouthPosition = 0;
        }
        if(mouthPosition < 6){
            recX = new int[]{x * 16 + locationX, x * 16 + locationX + 8, x * 16 + locationX + 16};
            recY = new int[]{y * 16 + locationY, y * 16 + locationY + 8, y * 16 + locationY + 16};
        }else if(mouthPosition < 12 || mouthPosition > 17){
            recX = new int[]{x * 16 + locationX + 3, x * 16 + locationX + 8, x * 16 + locationX + 13};
            recY = new int[]{y * 16 + locationY + 3, y * 16 + locationY + 8, y * 16 + locationY + 13};
        }else{
            recX = new int[]{x * 16 + locationX + 6, x * 16 + locationX + 8, x * 16 + locationX + 10};
            recY = new int[]{y * 16 + locationY + 6, y * 16 + locationY + 8, y * 16 + locationY + 10};
        }
        switch (this.direction) {
            case UP:
                recY = new int[]{y * 16 + locationY - 2, y * 16 + locationY + 8, y * 16 + locationY - 2};
                break;
            case DOWN:
                recY = new int[]{y * 16 + locationY + 18, y * 16 + locationY + 8, y * 16 + locationY + 18};
                break;
            case LEFT:
                recX = new int[]{x * 16 + locationX - 2, x * 16 + locationX + 8, x * 16 + locationX - 2};
                break;
            case RIGHT:
                recX = new int[]{x * 16 + locationX + 18, x * 16 + locationX + 8, x * 16 + locationX + 18};
                break;
        }
        g.fillPolygon(recX, recY, 3);
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
