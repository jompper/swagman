/*
 * Pacman - MoveLogic
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import pacman.datastructure.Stack;
import pacman.heurestic.Heurestic;

/**
 *
 * @author Joni
 */
public interface MoveLogic {
    public void move(boolean chase);
    public Stack<Anode> getPathTiles();
    public void setHeurestic(Heurestic h);
}
