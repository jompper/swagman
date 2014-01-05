/*
 * Pacman - Pinky
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.Drawing;

/**
 *
 * @author Joni
 */
public class Pinky extends AbstractMonster implements Drawing {

    public Pinky(int x, int y) {
        super(x, y, Direction.RIGHT, true);
        this.color = new Color(255, 184, 255);
    }

}
