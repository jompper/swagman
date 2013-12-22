/*
 * Pacman - PacDot
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.AbstractTile;
import pacman.tile.Drawing;

/**
 * Main eatables, little dots on the game
 * which pacman eats all the time.
 * 
 * Eating should increase players score
 * 
 * @author Joni
 */
public class PacDot extends AbstractTile implements Drawing, Eatable {

    private boolean eaten;

    public PacDot(int x, int y) {
        super(x, y);
        this.eaten = false;
    }

    @Override
    public void draw(Graphics g) {
        if (!isEaten()) {
            g.setColor(Color.WHITE);
            g.fillRect(x * 16 + 6, y * 16 + 6, 4, 4);
        }
    }

    
    /**
     * Return boolean true if PacDot is already eaten,
     * Otherwise return false
     * 
     * @return eaten status
     */
    @Override
    public boolean isEaten() {
        return this.eaten;
    }

    /**
     * Sets PacDot as eaten, if not eaten.
     * TODO: Increase player score
     */
    @Override
    public void eat() {
        if (isEaten()) {
            return;
        }
        this.eaten = true;
    }

}
