/*
 * Pacman - Clyde
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.AbstractMovingTile;
import pacman.tile.Drawing;
import pacman.tile.Moving;

/**
 *
 * @author Joni
 */
public class Clyde extends AbstractMovingTile implements Moving, Drawing, Monster {

    private boolean inJail;
    
    public Clyde(int x, int y) {
        super(x, y, Direction.RIGHT);
        this.locationX = 0;
        this.locationY = 0;
        this.speed = 1.9;
        this.inJail = true;
    }
    
    public void setJail(boolean jail){
        this.inJail = jail;
    }
    
    public boolean inJail(){
        return this.inJail;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(x * 16 + (int) locationX, y * 16 + (int) locationY, 16, 16);
    }
    
}
