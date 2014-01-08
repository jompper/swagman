/*
 * Pacman - Blinky
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.sprite;

import java.awt.Color;
import pacman.domain.Direction;

/**
 * Set color extend AbstractMonster
 * Everything is set.
 * @author Joni
 */
public class Blinky extends GeneralMonster {

    public Blinky(int x, int y) {
        super(x, y, Direction.RIGHT, false);
        this.color = new Color(255, 0, 0);
    }

}
