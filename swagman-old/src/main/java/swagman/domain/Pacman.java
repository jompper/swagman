/*
 * Swagman - Pacman
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import java.awt.Color;
import java.awt.Graphics;
import swagman.game.Direction;

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
        int distance = this.getDistance() / (100 / scale);
        if (this.getDirection() == Direction.LEFT) {
            g.fillOval(x * scale - distance, y * scale, scale, scale);
        } else if (this.getDirection() == Direction.RIGHT) {
            g.fillOval(x * scale + distance, y * scale, scale, scale);
        } else if (this.getDirection() == Direction.UP) {
            g.fillOval(x * scale, y * scale - distance, scale, scale);
        } else if (this.getDirection() == Direction.DOWN) {
            g.fillOval(x * scale, y * scale + distance, scale, scale);
        }
    }

}
