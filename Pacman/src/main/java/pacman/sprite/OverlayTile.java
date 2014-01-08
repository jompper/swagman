/*
 * Pacman - OverlayTile
 * 3.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.sprite;

import java.awt.Color;
import java.awt.Graphics;
import pacman.domain.AbstractTile;
import pacman.domain.Drawing;

/**
 * Overlay tile for pathfinding
 * 
 * Color should have nice alpha channel
 * 
 * @author Joni
 */
public class OverlayTile  extends AbstractTile implements Drawing {
    private Color color;

    public OverlayTile(int x, int y, Color c){
        super(x,y);
        this.color = c;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x*16, y*16, 16, 16);
    }
}
