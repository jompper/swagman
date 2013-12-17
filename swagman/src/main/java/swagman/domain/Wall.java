/*
 * Swagman - Wall
 * 17.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Joni
 */
public class Wall extends Tile implements Drawable {

    public Wall(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics g, int scale) {
        g.setColor(Color.BLUE);
        g.drawRect(x * scale, y * scale, scale, scale);
    }

}
