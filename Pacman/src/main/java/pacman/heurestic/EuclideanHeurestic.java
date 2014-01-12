/*
 * Pacman - EuclideanHeurestic
 * 12.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.heurestic;

/**
 *
 * @author Joni
 */
public class EuclideanHeurestic implements Heurestic {

    @Override
    public int getDistance(int sourceX, int sourceY, int destinationX, int destinationY) {
        int xDifference = Math.abs(sourceX - destinationX);
        int yDifference = Math.abs(sourceY - destinationY);
        double pow = xDifference * 2 + yDifference * 2;
        return (int)Math.sqrt(pow);
    }

    @Override
    public boolean findMax() {
        return false;
    }
}
