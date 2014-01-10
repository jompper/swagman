/*
 * Pacman - AstarTest
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import pacman.datastructure.Stack;
import static org.junit.Assert.*;
import org.junit.Test;
import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public class AstarTest {

    private int[][] map;
    private Astar astar;

    public AstarTest() {
        this.map = new int[][]{
            {10, 10, 10, 10, 10},
            {10, 0, 0, 0, 10},
            {10, 0, 0, 0, 10},
            {10, 0, 0, 0, 10},
            {10, 10, 10, 10, 10}};
        this.astar = new Astar(this.map);
    }

    @Test
    public void testFindPathSimple() {
        Stack<Anode> p = findPath(1, 1, 1, 2, Direction.LEFT);
        assertEquals(2, p.size());
    }

    @Test
    public void testFindPathAdvanced() {
        Stack<Anode> p = findPath(2, 1, 1, 1, Direction.RIGHT);
        assertEquals(4, p.size());
    }

    @Test
    public void testFindPathToSelfLocation() {
        Stack<Anode> p = findPath(2, 1, 2, 1, Direction.RIGHT);
        assertEquals(5, p.size());
    }

    @Test
    public void testSourceLocationOutsideMap() {
        Stack<Anode> p = findPath(-1, 0, 1, 1, Direction.UP);
    }

    @Test
    public void testDestinationOutsideMap(){
        Stack<Anode> p = findPath(1, 1, -1, 1, Direction.UP);       
        Stack<Anode> p2 = findPath(1, 1, 6, 6, Direction.UP);       
    }
    
    @Test
    public void testDestinationIsWall(){
        Stack<Anode> p = findPath(1, 1, 0, 0, Direction.UP);    
    }
    
    private Stack<Anode> findPath(int sourceX, int sourceY, int destinationX, int destinationY, Direction d) {
        Direction newDirection = astar.findPath(sourceX, sourceY, destinationX, destinationY, d);
        Stack<Anode> path = astar.getPath();
        assertEquals(newDirection, path.get(path.size() - 2).getFromDirection());
        return path;
    }

}
