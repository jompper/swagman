/*
 * Pacman - Inky
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import pacman.tile.Drawing;
import pacman.tile.Moving;

/**
 *
 * @author Joni
 */
public class Inky extends AbstractMonster implements Moving, Drawing, Monster {

    public Inky(int x, int y) {
        super(x, y, Direction.RIGHT, true);
        this.color = new Color(0, 255, 255);
    }

}
