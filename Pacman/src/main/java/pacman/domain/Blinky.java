/*
 * Pacman - Blinky
 * 28.12.2013
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
public class Blinky extends AbstractMovingTile implements Moving, Drawing, Monster {

    public Blinky(int x, int y) {
        super(x, y, Direction.RIGHT);
        this.locationX = 0;
        this.locationY = 0;
        this.speed = 1.9;
    }

    @Override
    public void move(){
        super.move();
        
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x * 16 + (int) locationX, y * 16 + (int) locationY, 16, 16);
    }

}
