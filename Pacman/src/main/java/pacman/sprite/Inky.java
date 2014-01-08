/*
 * Pacman - Inky
 * 30.12.2013
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
public class Inky extends GeneralMonster {

    public Inky(int x, int y) {
        super(x, y, Direction.RIGHT, true);
        this.color = new Color(0, 255, 255);
    }

}
