/*
 * Pacman - Monster
 * 8.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.sprite;

import java.awt.Color;
import java.awt.Graphics;
import pacman.domain.AbstractMonster;
import pacman.domain.Direction;
import pacman.domain.Drawing;

/**
 *
 * @author Joni
 */
public abstract class GeneralMonster extends AbstractMonster implements Drawing {

    public GeneralMonster(int x, int y, Direction d, boolean inJail) {
        super(x, y, d, inJail);
        this.color = Color.GREEN;
    }
    
    /**
     * Draw the monster, release the kraken
     * Not symmetric draws as symmetric
     * Just go with it
     * @param g 
     */
    @Override
    public void draw(Graphics g) {
        if (isChase()) {
            g.setColor(this.color);
        } else {
            g.setColor(new Color(0, 0, 255));
        }
        int baseX = x * 16 + (int) locationX;
        int baseY = y * 16 + (int) locationY;
        int[] xPoints = new int[]{
            baseX - 2,
            baseX - 2,
            baseX,
            baseX + 2,
            baseX + 8,
            baseX + 14,
            baseX + 16,
            baseX + 16 + 2,
            baseX + 16 + 2,
            baseX + 14,
            baseX + 11,
            baseX + 8,
            baseX + 7,
            baseX + 4,
            baseX + 1};
        int[] yPoints = new int[]{
            baseY + 16 + 2,
            baseY + 6,
            baseY + 2,
            baseY,
            baseY - 2,
            baseY,
            baseY + 2,
            baseY + 8,
            baseY + 16 + 2,
            baseY + 14,
            baseY + 16 + 2,
            baseY + 14,
            baseY + 15,
            baseY + 16 + 2,
            baseY + 14
        };

        g.fillPolygon(xPoints, yPoints, 15);
        // Draw the eyes, they look spooky without em
        drawEyes(g);
    }

    /**
     * Draw the eyes, hardcore two ellipses and two squares
     * @param g 
     */
    private void drawEyes(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x * 16 + (int) locationX - 1, y * 16 + (int) locationY, 6, 10);
        g.fillOval(x * 16 + (int) locationX + 10, y * 16 + (int) locationY, 6, 10);
        g.setColor(new Color(33, 33, 255));
        g.fillRect(x * 16 + (int) locationX - 1, y * 16 + (int) locationY + 4, 4, 4);
        g.fillRect(x * 16 + (int) locationX + 10, y * 16 + (int) locationY + 4, 4, 4);

    }
}
