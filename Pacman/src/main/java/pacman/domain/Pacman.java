/*
 * Pacman - Pacman
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.AbstractMovingTile;
import pacman.tile.Drawing;

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
    }

    

    @Override
    public void draw(Graphics g) {
        int locationX = (int)this.locationX;
        int locationY = (int)this.locationY;
        g.setColor(Color.YELLOW);
        g.fillOval(x * 16 + locationX - 2, y * 16 + locationY - 2, 20, 20);

        this.mouthPosition++;
        g.setColor(Color.BLACK);
        int[] recX;
        int[] recY;
        if(mouthPosition == 24){
            mouthPosition = 0;
        }
        if(mouthPosition < 6){
            recX = new int[]{x * 16 + locationX, x * 16 + locationX + 8, x * 16 + locationX + 16};
            recY = new int[]{y * 16 + locationY, y * 16 + locationY + 8, y * 16 + locationY + 16};
        }else if(mouthPosition < 12 || mouthPosition > 17){
            recX = new int[]{x * 16 + locationX + 3, x * 16 + locationX + 8, x * 16 + locationX + 13};
            recY = new int[]{y * 16 + locationY + 3, y * 16 + locationY + 8, y * 16 + locationY + 13};
        }else{
            recX = new int[]{x * 16 + locationX + 6, x * 16 + locationX + 8, x * 16 + locationX + 10};
            recY = new int[]{y * 16 + locationY + 6, y * 16 + locationY + 8, y * 16 + locationY + 10};
        }
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
        g.fillPolygon(recX, recY, 3);
    }

}
