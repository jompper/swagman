/*
 * Pacman - AbstractMoveLogic
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import java.util.List;
import pacman.domain.Direction;

/**
 * Abstract move logic implements methods findPath and getPathTiles. Initialises
 * Astar
 *
 * @author Joni
 */
public abstract class AbstractMoveLogic implements MoveLogic {

    protected final int[][] map;
    private final Astar astar;

    /**
     * Initialise Astar with map, and keep map because
     * some objects may need to use it for destination
     * coordinates
     * 
     * @param map 
     */
    public AbstractMoveLogic(int[][] map) {
        this.map = map;
        this.astar = new Astar(map);
    }

    /**
     * Find path from source to destination
     * @param sourceX
     * @param sourceY
     * @param destinationX
     * @param destinationY
     * @param currentDirection
     * @return direction to next tile in path
     */
    public Direction findPath(int sourceX, int sourceY, int destinationX, int destinationY, Direction currentDirection) {
        Direction newDirection = astar.findPath(sourceX, sourceY, destinationX, destinationY, currentDirection);
        return (newDirection == null) ? currentDirection : newDirection;
    }

    /**
     * @return List of nodes that should be visualized
     */
    @Override
    public List<Anode> getPathTiles() {
        return astar.getTiles();
    }
}
