/*
 * Pacman - Wall
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.sprite;

import java.awt.Color;
import java.awt.Graphics;
import pacman.domain.AbstractTile;
import pacman.domain.Drawing;
import pacman.domain.WallType;

/**
 *
 * @author Joni
 */
public class Wall extends AbstractTile implements Drawing {

    private final WallType wallType;

    public Wall(int x, int y, WallType wallType) {
        super(x, y);
        this.wallType = wallType;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(33, 33, 255));
        switch (wallType) {
            case LEFT_RIGHT:
                drawWallLeftRight(g);
                break;
            case LEFT_UP:
                drawWallLeftUp(g);
                break;
            case LEFT_DOWN:
                drawWallLeftDown(g);
                break;
            case RIGHT_UP:
                drawWallRightUp(g);
                break;
            case RIGHT_DOWN:
                drawWallRightDown(g);
                break;
            case UP_DOWN:
                drawWallUpDown(g);
                break;
            case GATE_LEFT:
                drawGateLeft(g);
                break;
            case GATE_RIGHT:
                drawGateRight(g);
                break;
        }
    }

    protected void drawWallLeftRight(Graphics g) {
        g.drawLine(x * 16, y * 16 + 4, x * 16 + 16, y * 16 + 4);
        g.drawLine(x * 16, y * 16 + 12, x * 16 + 16, y * 16 + 12);
    }

    protected void drawWallUpDown(Graphics g) {
        g.drawLine(x * 16 + 4, y * 16, x * 16 + 4, y * 16 + 16);
        g.drawLine(x * 16 + 12, y * 16, x * 16 + 12, y * 16 + 16);
    }

    protected void drawWallLeftUp(Graphics g) {
        g.drawLine(x * 16, y * 16 + 4, x * 16 + 3, y * 16 + 4);
        g.drawLine(x * 16 + 4, y * 16 + 4, x * 16 + 4, y * 16);
        g.drawLine(x * 16, y * 16 + 12, x * 16 + 11, y * 16 + 12);
        g.drawLine(x * 16 + 12, y * 16 + 12, x * 16 + 12, y * 16);
    }

    protected void drawWallLeftDown(Graphics g) {
        g.drawLine(x * 16, y * 16 + 4, x * 16 + 11, y * 16 + 4);
        g.drawLine(x * 16 + 12, y * 16 + 4, x * 16 + 12, y * 16 + 16);
        g.drawLine(x * 16, y * 16 + 12, x * 16 + 4, y * 16 + 12);
        g.drawLine(x * 16 + 4, y * 16 + 12, x * 16 + 4, y * 16 + 16);
    }

    protected void drawWallRightUp(Graphics g) {
        g.drawLine(x * 16 + 16, y * 16 + 4, x * 16 + 13, y * 16 + 4);
        g.drawLine(x * 16 + 12, y * 16 + 4, x * 16 + 12, y * 16);
        g.drawLine(x * 16 + 16, y * 16 + 12, x * 16 + 5, y * 16 + 12);
        g.drawLine(x * 16 + 4, y * 16 + 12, x * 16 + 4, y * 16);
    }

    protected void drawWallRightDown(Graphics g) {
        g.drawLine(x * 16 + 16, y * 16 + 4, x * 16 + 5, y * 16 + 4);
        g.drawLine(x * 16 + 4, y * 16 + 4, x * 16 + 4, y * 16 + 16);
        g.drawLine(x * 16 + 16, y * 16 + 12, x * 16 + 12, y * 16 + 12);
        g.drawLine(x * 16 + 12, y * 16 + 12, x * 16 + 12, y * 16 + 16);
    }

    protected void drawGateLeft(Graphics g) {
        g.drawLine(x * 16, y * 16 + 4, x * 16, y * 16 + 12);
        g.setColor(Color.RED);
        g.drawLine(x * 16 + 1, y * 16 + 6, x * 16 + 16, y * 16 + 6);
        g.drawLine(x * 16 + 1, y * 16 + 10, x * 16 + 16, y * 16 + 10);
    }

    protected void drawGateRight(Graphics g) {
        g.drawLine(x * 16 + 16, y * 16 + 4, x * 16 + 16, y * 16 + 12);
        g.setColor(Color.RED);
        g.drawLine(x * 16, y * 16 + 6, x * 16 + 15, y * 16 + 6);
        g.drawLine(x * 16, y * 16 + 10, x * 16 + 15, y * 16 + 10);
    }
}
