/*
 * Pacman - Monster
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.util.List;
import pacman.algorithm.MoveLogic;

/**
 * Monster interface for no real reason
 * Now why did I create this
 * 
 * Wall baybe someone implements other kind
 * of monster, which I highly doubt
 * 
 * @author Joni
 */
public interface Monster {
    public void setAI(MoveLogic ml);
    public void AIMove();
    public boolean isMove();
    public void setJail(boolean jail);
    public boolean inJail();
    public List<Drawing> getPathTiles();
}
