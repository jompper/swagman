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
 *
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

    @Override
    public Direction getDirection(){
        return this.direction;
    }
    
    @Override
    public Direction getChangeDirection(){
        return this.changeDirection;
    }
    
    @Override
    public void setDirection(Direction d) {
        this.direction = d;
    }
    
    @Override
    public void setChangeDirection(Direction d){
        this.changeDirection = d;
    }

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
