/*
 * Pacman - AbstractMoveLogic
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import pacman.datastructure.Stack;
import pacman.domain.Direction;
import pacman.heurestic.DijkstraHeurestic;
import pacman.heurestic.Heurestic;
import pacman.heurestic.ManhattanHeurestic;

/**
 * Abstract move logic implements methods findPath and getPathTiles. Initialises
 * Astar
 *
 * @author Joni
 */
public abstract class AbstractMoveLogic implements MoveLogic {

    protected final int[][] map;
    private final Astar astar;
    private final Astar dijkstra;
    private final Astar manhattan;

    /**
     * Initialise Astar with map, and keep map because
     * some objects may need to use it for destination
     * coordinates
     * 
     * @param map 
     */
    public AbstractMoveLogic(int[][] map) {
        this.map = map;
        this.astar = new Astar(map, new ManhattanHeurestic(), false);
        this.manhattan = new Astar(map, new ManhattanHeurestic(), true);
        this.dijkstra = new Astar(map, new DijkstraHeurestic(), true);
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
        System.out.print("Manhattan: ");
        manhattan.findPath(sourceX, sourceY, destinationX, destinationY, currentDirection);
        System.out.print("Dijkstra: ");
        dijkstra.findPath(sourceX, sourceY, destinationX, destinationY, currentDirection);
        
        
        return (newDirection == null) ? currentDirection : newDirection;
    }

    /**
     * @return List of nodes that should be visualized
     */
    @Override
    public Stack<Anode> getPathTiles() {
        return astar.getTiles();
    }
    /**
     * Set new heuresti
     * @param h for monster AI
     */
    @Override
    public void setHeurestic(Heurestic h){
        this.astar.setHeurestic(h);
    }
}
