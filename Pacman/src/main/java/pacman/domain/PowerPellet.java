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
 * Bigger eatable object in the game
 * When eaten, PowerPellet should change game mode for ? seconds
 * TODO: -||-
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
        g.setColor(new Color(255, 184, 174));
        g.fillOval(x * 16 + 2, y * 16 + 2, 12, 12);
    }

    /**
     * Return True if PowerPellet is eaten, otherwise False
     * 
     * @return eaten status
     */
    @Override
    public boolean isEaten() {
        return this.eaten;
    }

    /**
     * On eat, sets PowerPellet as eaten if not already eaten
     */
    @Override
    public int eat() {
        if(isEaten())return 0;
        this.eaten = true;
        return 50;
    }

}
