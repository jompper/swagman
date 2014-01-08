/*
 * Pacman - PacDot
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.sprite;

import java.awt.Color;
import java.awt.Graphics;
import pacman.domain.AbstractEatable;
import pacman.domain.Drawing;

/**
 * Main eatables, little dots on the game which pacman eats all the time.
 *
 * Eating should increase players score
 *
 * @author Joni
 */
public class PacDot extends AbstractEatable implements Drawing {

    public PacDot(int x, int y) {
        super(x, y, 10);
    }

    @Override
    public void draw(Graphics g) {
        if (!isEaten()) {
            g.setColor(new Color(255, 184, 174));
            g.fillRect(x * 16 + 6, y * 16 + 6, 4, 4);
        }
    }

}
