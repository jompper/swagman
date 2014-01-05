/*
 * Pacman - Blinky
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.Drawing;
import pacman.tile.Moving;

/**
 *
 * @author Joni
 */
public class Blinky extends AbstractMonster implements Moving, Drawing, Monster {

    public Blinky(int x, int y) {
        super(x, y, Direction.RIGHT, false);
        this.color = new Color(255, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        if (isChase()) {
            g.setColor(this.color);
        } else {
            g.setColor(new Color(0, 0, 255));
        }
        g.fillOval(x * 16 + (int) locationX, y * 16 + (int) locationY, 16, 16);
    }

}
