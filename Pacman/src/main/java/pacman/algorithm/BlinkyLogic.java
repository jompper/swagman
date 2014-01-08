/*
 * Pacman - BlinkyLogic
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import java.util.ArrayList;
import java.util.List;
import pacman.sprite.Blinky;
import pacman.domain.Direction;
import pacman.sprite.Pacman;

/**
 *
 * @author Joni
 */
public class BlinkyLogic extends AbstractMoveLogic implements MoveLogic {

    private Blinky blinky;
    private Pacman pacman;

    public BlinkyLogic(Blinky b, Pacman p, int[][] map) {
        super(map);
        this.blinky = b;
        this.pacman = p;
    }

    @Override
    public void move(boolean chase) {
        if (chase) {
            this.blinky.setChangeDirection(chaseMove());
        } else {
            this.blinky.setChangeDirection(scatterMove());
        }
    }

    private Direction chaseMove() {
        int sourceX = blinky.getX();
        int sourceY = blinky.getY();
        int destinationX = pacman.getX();
        int destinationY = pacman.getY();

        return findPath(sourceX, sourceY, destinationX, destinationY, blinky.getDirection());
    }

    private Direction scatterMove() {
        int sourceX = blinky.getX();
        int sourceY = blinky.getY();
        int destinationX = map[0].length - 2;
        int destinationY = 0;

        return findPath(sourceX, sourceY, destinationX, destinationY, blinky.getDirection());
    }

}
