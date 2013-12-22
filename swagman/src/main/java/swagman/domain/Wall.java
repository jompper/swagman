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

    private final WallType wallType;

    public Wall(int x, int y, WallType wallType) {
        super(x, y);
        this.wallType = wallType;
    }

    private void drawWallLeftRight(Graphics g, int scale) {
        g.drawLine(x * scale, y * scale + scale / 4, x * scale + scale, y * scale + scale / 4);
        g.drawLine(x * scale, y * scale + scale / 4 * 3, x * scale + scale, y * scale + scale / 4 * 3);
    }

    private void drawWallUpDown(Graphics g, int scale) {
        g.drawLine(x * scale + scale / 4, y * scale, x * scale + scale / 4, y * scale + scale);
        g.drawLine(x * scale + scale / 4 * 3, y * scale, x * scale + scale / 4 * 3, y * scale + scale);
    }

    @Override
    public void draw(Graphics g, int scale) {
        g.setColor(Color.BLUE);
        if (wallType == WallType.LEFT_RIGHT) {
            drawWallLeftRight(g, scale);
        } else if(wallType == WallType.UP_DOWN){
            drawWallUpDown(g, scale);
        } else {
            g.drawRect(x * scale, y * scale, scale, scale);
        }

    }

}
