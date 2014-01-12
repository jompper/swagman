/*
 * Pacman - ChebyshevHeurestic
 * 12.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.heurestic;

/**
 *
 * @author Joni
 */
public class ChebyshevHeurestic implements Heurestic {

    @Override
    public int getDistance(int sourceX, int sourceY, int destinationX, int destinationY) {
        int xDifference = sourceX - destinationX;
        int yDifference = sourceY - destinationY;
        return Math.max(Math.abs(xDifference), Math.abs(yDifference));
    }

    @Override
    public boolean findMax() {
        return false;
    }

}
