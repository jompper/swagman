/*
 * Pacman - Monster
 * 28.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.domain;

import pacman.algorithm.MoveLogic;

/**
 *
 * @author Joni
 */
public interface Monster {
    public void setAI(MoveLogic ml);
    public void AIMove();
    public boolean isMove();
    public void setJail(boolean jail);
    public boolean inJail();
}
