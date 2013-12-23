/*
 * Pacman - Wall
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.tile;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Joni
 */
public class Wall extends AbstractTile implements Drawing {

    private WallType wallType;

    public Wall(int x, int y, WallType wallType) {
        super(x, y);
        this.wallType = wallType;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        switch (wallType) {
            case LEFT_RIGHT:
                g.drawLine(x * 16, y * 16 + 4, x * 16 + 16, y * 16 + 4);
                g.drawLine(x * 16, y * 16 + 12, x * 16 + 16, y * 16 + 12);
                break;
            case LEFT_UP:
                g.drawLine(x * 16, y * 16 + 4, x * 16 + 3, y * 16 + 4);
                g.drawLine(x * 16 + 4, y * 16 + 4, x * 16 + 4, y * 16);
                g.drawLine(x * 16, y * 16 + 12, x * 16 + 11, y * 16 + 12);
                g.drawLine(x * 16 + 12, y * 16 + 12, x * 16 + 12, y * 16);
                break;
            case LEFT_DOWN:
                g.drawLine(x * 16, y * 16 + 4, x * 16 + 11, y * 16 + 4);
                g.drawLine(x * 16 + 12, y * 16 + 4, x * 16 + 12, y * 16 + 16);
                g.drawLine(x * 16, y * 16 + 12, x * 16 + 4, y * 16 + 12);
                g.drawLine(x * 16 + 4, y * 16 + 12, x * 16 + 4, y * 16 + 16);
                break;
            case RIGHT_UP:
                g.drawLine(x * 16 + 16, y * 16 + 4, x * 16 + 13, y * 16 + 4);
                g.drawLine(x * 16 + 12, y * 16 + 4, x * 16 + 12, y * 16);
                g.drawLine(x * 16 + 16, y * 16 + 12, x * 16 + 5, y * 16 + 12);
                g.drawLine(x * 16 + 4, y * 16 + 12, x * 16 + 4, y * 16);
                break;
            case RIGHT_DOWN:
                g.drawLine(x * 16 + 16, y * 16 + 4, x * 16 + 5, y * 16 + 4);
                g.drawLine(x * 16 + 4, y * 16 + 4, x * 16 + 4, y * 16 + 16);
                g.drawLine(x * 16 + 16, y * 16 + 12, x * 16 + 12, y * 16 + 12);
                g.drawLine(x * 16 + 12, y * 16 + 12, x * 16 + 12, y * 16 + 16);
                break;
            case UP_DOWN:
                g.drawLine(x * 16 + 4, y * 16, x * 16 + 4, y * 16 + 16);
                g.drawLine(x * 16 + 12, y * 16, x * 16 + 12, y * 16 + 16);
                break;
            case GATE_LEFT:
                g.drawLine(x * 16, y * 16 + 4, x * 16, y * 16 + 12);
                g.setColor(Color.RED);
                g.drawLine(x * 16 + 1, y * 16 + 6, x * 16 + 16, y * 16 + 6);
                g.drawLine(x * 16 + 1, y * 16 + 10, x * 16 + 16, y * 16 + 10);
                break;
            case GATE_RIGHT:
                g.drawLine(x * 16 + 16, y * 16 + 4, x * 16 + 16, y * 16 + 12);
                g.setColor(Color.RED);
                g.drawLine(x * 16, y * 16 + 6, x * 16 + 15, y * 16 + 6);
                g.drawLine(x * 16, y * 16 + 10, x * 16 + 15, y * 16 + 10);
                break;
        }
    }

}
