/*
 * Pacman - Monster
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.util.List;
import pacman.algorithm.MoveLogic;

/**
 * Monster interface for no real reason Now why did I create this
 *
 * Wall baybe someone implements other kind of monster, which I highly doubt
 *
 * @author Joni
 */
public interface Monster {

    /**
     *
     * @param ml Move Logic for monster
     */
    public void setAI(MoveLogic ml);

    /**
     * Get new direction from move logic
     */
    public void AIMove();

    /**
     * @return should Monster find new direction with move logic with next move
     */
    public boolean isMove();

    /**
     * @param jail new jail status
     */
    public void setJail(boolean jail);

    /**
     * @return is in jail
     */
    public boolean inJail();

    /**
     * @return Tiles to visualize path finding
     */
    public List<Drawing> getPathTiles();
}
