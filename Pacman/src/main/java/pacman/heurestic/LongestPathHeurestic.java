/*
 * Pacman - LongestPathHeurestic
 * 12.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.heurestic;


/**
 *
 * @author Joni
 */
public class LongestPathHeurestic implements Heurestic {


    @Override
    public int getDistance(int sourceX, int sourceY, int destinationX, int destinationY) {

        int xDifference = -Math.abs(sourceX - destinationX);
        int yDifference = -Math.abs(sourceY - destinationY);
        return xDifference + yDifference;
    }

    @Override
    public boolean findMax() {
        return true;
    }
}
