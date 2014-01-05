/*
 * Pacman - InkyLogic
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import java.util.ArrayList;
import java.util.List;
import pacman.domain.Direction;
import pacman.domain.Inky;
import pacman.domain.Pacman;
import pacman.tile.AbstractMovingTile;

/**
 *
 * @author Joni
 */
public class InkyLogic extends AbstractMoveLogic implements MoveLogic {

    private final Inky inky;
    private final AbstractMovingTile tile;
    private final Pacman pacman;

    public InkyLogic(Inky i, AbstractMovingTile b, Pacman p, int[][] map) {
        super(map);
        this.inky = i;
        this.tile = b;
        this.pacman = p;
    }

    @Override
    public void move(boolean chase) {
        if (chase) {
            this.inky.setChangeDirection(chaseMove());
        } else {
            this.inky.setChangeDirection(scatterMove());
        }
    }

    public Direction chaseMove() {
        int sourceX = inky.getX();
        int sourceY = inky.getY();
        int destinationX = pacman.getNextX(2) - (tile.getX() - pacman.getNextX(2));
        int destinationY = pacman.getNextY(2) - (tile.getY() - pacman.getNextY(2));

        return findPath(sourceX, sourceY, destinationX, destinationY, inky.getDirection());
    }

     public Direction scatterMove() {
        int sourceX = inky.getX();
        int sourceY = inky.getY();
        int destinationX = map[0].length - 2;
        int destinationY = map.length;

        return findPath(sourceX, sourceY, destinationX, destinationY, inky.getDirection());
    }
    
}
