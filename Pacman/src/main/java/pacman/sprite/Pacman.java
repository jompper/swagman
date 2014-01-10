/*
 * Pacman - Pacman
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.sprite;

import java.awt.Color;
import java.awt.Graphics;
import pacman.domain.Direction;
import pacman.domain.AbstractMovingTile;
import pacman.domain.Drawing;

/**
 * Pacman is games main character and the only object, player can move.
 *
 * @author Joni
 */
public class Pacman extends AbstractMovingTile implements Drawing {

    private int mouthPosition;

    public Pacman(int x, int y, Direction d) {
        super(x, y, d, 2);
        this.mouthPosition = 0;
        this.locationX = -7;
    }

    private void moveMouth(){
        if(++mouthPosition >= 24){
            mouthPosition = 0;
        }
    }
    

    /**
     * Draw Pac-Man simple huh ?
     *
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        int locationX = (int) this.locationX;
        int locationY = (int) this.locationY;
        g.setColor(Color.YELLOW);
        g.fillOval(x * 16 + locationX - 2, y * 16 + locationY - 2, 20, 20);
        drawMouth(g, locationX, locationY);
        moveMouth();
    }

    /**
     * Draw mouth for pac-man and animate it
     *
     * Evil draw over hacking, basicly draw black triangle over the yellow
     * pacman oval. Missing alpha channel but don't know how possible anyways
     *
     * @param g
     * @param locationX
     * @param locationY
     */
    private void drawMouth(Graphics g, int locationX, int locationY) {

        // Animate the mouth
        int mouthFix = 3;
        if (mouthPosition < 6) {
            mouthFix = 0;
        } else if (mouthPosition >= 12 && mouthPosition < 18) {
            mouthFix = 6;
        }

        // X positions needed for up and down
        int[] recX = new int[]{x * 16 + locationX + mouthFix, x * 16 + locationX + 8, x * 16 + locationX + 16 - mouthFix};
        
        // Y position needed for left and right
        int[] recY = new int[]{y * 16 + locationY + mouthFix, y * 16 + locationY + 8, y * 16 + locationY + 16 - mouthFix};

        // Fix triangle to current moving direction
        switch (this.direction) {
            case UP:
                recY = new int[]{y * 16 + locationY - 2, y * 16 + locationY + 8, y * 16 + locationY - 2};
                break;
            case DOWN:
                recY = new int[]{y * 16 + locationY + 18, y * 16 + locationY + 8, y * 16 + locationY + 18};
                break;
            case LEFT:
                recX = new int[]{x * 16 + locationX - 2, x * 16 + locationX + 8, x * 16 + locationX - 2};
                break;
            case RIGHT:
                recX = new int[]{x * 16 + locationX + 18, x * 16 + locationX + 8, x * 16 + locationX + 18};
                break;
        }
        g.setColor(Color.BLACK);
        g.fillPolygon(recX, recY, 3);
    }
}
