/*
 * Swagman - Pacman
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Joni
 */
public class Pacman extends MovingTile implements Drawable {


    public Pacman(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int scale) {
        g.setColor(Color.YELLOW);
        g.fillOval(x * scale, y * scale, scale, scale);
    }

}
