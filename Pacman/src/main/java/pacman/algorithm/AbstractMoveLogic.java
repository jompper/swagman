/*
 * Pacman - AbstractMoveLogic
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import java.util.ArrayList;
import java.util.List;
import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public abstract class AbstractMoveLogic implements MoveLogic{
    
    protected final int[][] map;
    private List<Anode> pathTiles;
    
    public AbstractMoveLogic(int[][] map){     
        this.map = map;
        this.pathTiles = new ArrayList<>();
    }
    
    public Direction findPath(int sourceX, int sourceY, int destinationX, int destinationY, Direction currentDirection){
        Astar astar = new Astar(this.map, sourceX, sourceY, destinationX, destinationY, currentDirection);
        this.pathTiles = astar.getTiles();
        return (astar.getDirection()==null)?currentDirection:astar.getDirection();
    }
    
    @Override
    public List<Anode> getPathTiles(){
        return this.pathTiles;
    }
}
