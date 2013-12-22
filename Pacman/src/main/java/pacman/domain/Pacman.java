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
 * Pacman is games main character and the only object,
 * player can move.
 * @author Joni
 */
public class Pacman extends AbstractTile implements Moving, Drawing {

    private Direction direction;
    private Direction changeDirection;

    public Pacman(int x, int y, Direction d) {
        super(x, y);
        this.direction = d;
        this.changeDirection = d;
    }

    /**
     * Returns Pacmans current direction
     * 
     * @return 
     */
        @Override
    public Direction getDirection(){
        return this.direction;
    }
    
    /**
     * Returns Pacmans change direction. Direction
     * is changed when pacman can move to this
     * direction.
     * 
     * @return 
     */
    @Override
    public Direction getChangeDirection(){
        return this.changeDirection;
    }
    
    
    /**
     * Set Pacmans direction to d
     * Now pacman moves to direction d
     * @param d 
     */
    @Override
    public void setDirection(Direction d) {
        this.direction = d;
    }
    
    /**
     * Set Pacmans change direction to d.
     * Pacmans direction is changed to change
     * direction when pacman can move to this
     * direction.
     * 
     * @param d 
     */
    @Override
    public void setChangeDirection(Direction d){
        this.changeDirection = d;
    }

    
    /**
     * Moves Pacman to Pacmans current direction.
     * 
     * TODO: Smooth moving, 11 tiles per second.
     *       Equals 11 * 16 = 176 pixel per second.
     *       Change game speed to 60 FPS
     *       Move 3 pixels at a time. 
     *       Tile position + 9 pixels = Next tile
     * 
     */
    @Override
    public void move() {
        switch (this.direction) {
            case UP:
                this.y--;
                break;
            case DOWN:
                this.y++;
                break;
            case LEFT:
                this.x--;
                break;
            case RIGHT:
                this.x++;
                break;
        }
    }

    /**
     * Get n next X position to direction d from
     * Pacmans current position. Used to check if 
     * next tile is movable and later for AI
     * 
     * @param n
     * @param d
     * @return 
     */
    @Override
    public int getNextX(int n, Direction d){
        switch(d){
            case LEFT:
                return this.x - n;
            case RIGHT:
                return this.x + n;
        }
        return this.x;
    }
    
    /**
     * Get n next Y position to direction d from
     * Pacmans current position. Used to check if
     * next tile is movalbe and later for AI
     * 
     * @param n
     * @param d
     * @return 
     */
    @Override
    public int getNextY(int n, Direction d){
        switch(d){
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
        g.fillOval(x * 16, y * 16, 16, 16);
    }

}
