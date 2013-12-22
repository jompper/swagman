/*
 * Pacman - PowerPellet
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.AbstractTile;
import pacman.tile.Drawing;

/**
 *
 * @author Joni
 */
public class PowerPellet extends AbstractTile implements Drawing, Eatable {

    private boolean eaten;
    
    public PowerPellet(int x, int y) {
        super(x, y);
        this.eaten = false;
    }

    @Override
    public void draw(Graphics g) {
        if(isEaten())return;
        g.setColor(Color.WHITE);
        g.fillRect(x * 16 + 3, y * 16 + 3, 10, 10);
    }

    @Override
    public boolean isEaten() {
        return this.eaten;
    }

    @Override
    public void eat() {
        if(isEaten())return;
        this.eaten = true;
    }

}
