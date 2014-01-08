/*
 * Pacman - MainMenu
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import pacman.domain.Drawing;

/**
 *
 * @author Joni
 */
public class MainMenu implements Drawing {

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString("Pac-Man", 20, 100);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawRect(130, 150, 200, 50);
        g.drawString("Start Game", 157, 185);
        g.drawRect(130, 250, 200, 50);
        g.drawString("Restart", 182, 285);
        g.drawRect(130, 350, 200, 50);
        g.drawString("Quit Game", 157, 385);
        g.drawRect(130, 450, 200, 50);
        g.drawString("Hidden Mode", 140, 485);
    }
    
}
