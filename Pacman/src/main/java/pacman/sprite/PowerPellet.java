/*
 * Pacman - PowerPellet
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.sprite;

import java.awt.Color;
import java.awt.Graphics;
import pacman.domain.AbstractEatable;
import pacman.domain.Drawing;

/**
 * Bigger eatable object in the game
 * When eaten, PowerPellet should change game mode for ? seconds
 * 
 * TODO: Implement AbstractEatable
 * @author Joni
 */
public class PowerPellet extends AbstractEatable implements Drawing {

    
    public PowerPellet(int x, int y) {
        super(x, y, 50);
    }

    @Override
    public void draw(Graphics g) {
        if(isEaten())return;
        g.setColor(new Color(255, 184, 174));
        g.fillOval(x * 16 + 2, y * 16 + 2, 12, 12);
    }


}
