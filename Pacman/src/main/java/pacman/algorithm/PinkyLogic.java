/*
 * Pacman - PinkyLogic
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import pacman.domain.Direction;
import pacman.sprite.Pacman;
import pacman.sprite.Pinky;

/**
 *
 * @author Joni
 */
public class PinkyLogic extends AbstractMoveLogic implements MoveLogic {

    private Pinky pinky;
    private Pacman pacman;

    public PinkyLogic(Pinky b, Pacman p, int[][] map) {
        super(map);
        this.pinky = b;
        this.pacman = p;
    }

    @Override
    public void move(boolean chase) {
        if (chase) {
            this.pinky.setChangeDirection(chaseMove());
        } else {
            this.pinky.setChangeDirection(scatterMove());
        }
    }

    private Direction chaseMove() {
        int sourceX = pinky.getX();
        int sourceY = pinky.getY();
        int destinationX = pacman.getNextX(2);
        int destinationY = pacman.getNextY(2);

        return findPath(sourceX, sourceY, destinationX, destinationY, pinky.getDirection());
    }

    private Direction scatterMove() {
        int sourceX = pinky.getX();
        int sourceY = pinky.getY();
        int destinationX = 1;
        int destinationY = 0;

        return findPath(sourceX, sourceY, destinationX, destinationY, pinky.getDirection());
    }

}
