/*
 * Pacman - Heurestic
 * 12.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.heurestic;

/**
 *
 * @author Joni
 */
public interface Heurestic {
    public int getDistance(int sourceX, int sourceY, int destinationX, int destinationY);
    public boolean findMax();
}
