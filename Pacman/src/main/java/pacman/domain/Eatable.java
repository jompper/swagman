/*
 * Pacman - Eatable
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.domain;

/**
 * Interface for object that Pacman can eat
 * @author Joni
 */
public interface Eatable {
    public boolean isEaten();
    public void eat();
}
