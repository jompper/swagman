/*
 * Pacman - OverlayTile
 * 3.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.Drawing;

/**
 *
 * @author Joni
 */
public class OverlayTile implements Drawing {
    private int x;
    private int y;
    private Color color;

    public OverlayTile(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.color = c;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x*16, y*16, 16, 16);
    }
}
