/*
 * Pacman - Pinky
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.Drawing;

/**
 *
 * @author Joni
 */
public class Pinky extends AbstractMonster implements Drawing {

    public Pinky(int x, int y) {
        super(x, y, Direction.RIGHT, true);
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillOval(x * 16 + (int) locationX, y * 16 + (int) locationY, 16, 16);
    }

}
