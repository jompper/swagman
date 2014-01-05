/*
 * Pacman - MoveLogic
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import java.util.List;

/**
 *
 * @author Joni
 */
public interface MoveLogic {
    public void move(boolean chase);
    public List<Anode> getPathTiles();
}
