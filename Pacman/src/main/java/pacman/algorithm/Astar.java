/*
 * Pacman - Astar
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import pacman.domain.Direction;

/**
 * Astart, built to find shortest path from point A to point B
 * @author Joni
 */
public class Astar {

    private Direction newDirection;
    private Direction sourceDirection;
    private int[][] map;
    private int width;
    private int height;

    private Anode[][] atable;

    private List<Anode> tiles;

    private int sourceX;
    private int sourceY;
    private int destinationX;
    private int destinationY;

    /**
     * Find shortest path from a to b
     * @param map
     * @param stx
     * @param sty
     * @param dtx
     * @param dty
     * @param sd 
     */
    
    public Astar(int[][] map, int stx, int sty, int dtx, int dty, Direction sd) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        this.tiles = new ArrayList<>();
        setCoordinates(stx, sty, dtx, dty, sd);
        findPath();
    }

    /**
     * Set coodinates
     * @param stx
     * @param sty
     * @param dtx
     * @param dty
     * @param sd 
     */
    
    private void setCoordinates(int stx, int sty, int dtx, int dty, Direction sd) {
        this.sourceX = checkCollision(stx, 0, width - 1);
        this.sourceY = checkCollision(sty, 0, height - 1);
        this.destinationX = checkCollision(dtx, 0, width - 1);
        this.destinationY = checkCollision(dty, 0, height - 1);
        this.sourceDirection = sd;

        fixCoordinatesInWall();
    }

    /**
     * fix coordinates if in wall
     */
    private void fixCoordinatesInWall(){
        if (isWall(sourceX, sourceY)) {
            int[] p = findNearestNotWall(sourceX, sourceY, 0, width - 1, 0, height - 1);
            sourceX = p[0];
            sourceY = p[1];
        }
        if (isWall(destinationX, destinationY)) {
            int[] p = findNearestNotWall(destinationX, destinationY, 0, width - 1, 0, height - 1);
            destinationX = p[0];
            destinationY = p[1];
        }
    }
    
    /**
     * Initialize, build atable, handle source node
     * @param pq 
     */
    
    private void initializePathFind(PriorityQueue<Anode> pq) {
        atable = buildAtable();
        Anode u = atable[sourceY][sourceX];
        u.setStart(0);
        u.setFromDirection(sourceDirection);
        int x = u.getX();
        int y = u.getY();
        this.NodeUpdate(pq, x - 1, y, u, Direction.LEFT);
        this.NodeUpdate(pq, x + 1, y, u, Direction.RIGHT);
        this.NodeUpdate(pq, x, y - 1, u, Direction.UP);
        this.NodeUpdate(pq, x, y + 1, u, Direction.DOWN);
    }

    /**
     * Find path, call's other needed functions
     */
    private void findPath() {
        PriorityQueue<Anode> pq = new PriorityQueue();
        initializePathFind(pq);
        findPath(pq);
        Stack<Anode> path = getPath();
        if (path.size() >= 2) {
            path.pop();
            this.newDirection = path.pop().getFromDirection();
        } else {
            this.newDirection = firstPossibleDirection();
        }
    }

    /**
     * Find nearest coordinate that is not wall
     * Time complexcity O(|map|)
     * 
     * @param x
     * @param y
     * @param minX
     * @param maxX
     * @param minY
     * @param maxY
     * @return 
     */
    
    private int[] findNearestNotWall(int x, int y, int minX, int maxX, int minY, int maxY) {
        int[] p = new int[]{x, y};
        int min = Integer.MAX_VALUE;
        for (int dy = minY; dy < maxY; dy++) {
            for (int dx = minX; dx < maxX; dx++) {
                if (isWall(dx, dy)) {
                    continue;
                }
                int distance = Math.abs(x - dx) + Math.abs(y - dy);
                if(distance < min){
                    p[0] = dx;
                    p[1] = dy;
                    min = distance;
                }
            }
        }
        return p;
    }

    /**
     * If no path is found, return some direction can move to
     * @return 
     */
    
    private Direction firstPossibleDirection() {
        Direction r = reverseDirection(sourceDirection);
        for (Direction d : Direction.values()) {
            if (r != d) {
                int x = sourceX;
                int y = sourceY;
                switch (d) {
                    case UP:
                        y--;
                        break;
                    case DOWN:
                        y++;
                        break;
                    case LEFT:
                        x--;
                        break;
                    case RIGHT:
                        x++;
                        break;
                }
                if (!isWall(x, y)) {
                    return d;
                }
            }
        }
        return sourceDirection;
    }

    /**
     * Build table with correct distances from destination
     * Default set all pathLength to Map size * 2
     * @return Anode[][] which contains all map coordinates as nodes 
     */
    
    public final Anode[][] buildAtable() {
        Anode[][] table = new Anode[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Anode a = new Anode(x, y, width * height * 2, (Math.abs(x - destinationX) + Math.abs(y - destinationY)));
                table[y][x] = a;
            }
        }
        return table;
    }

    /**
     * Find the shortest path from source to destination
     * @param pq 
     */
    
    private void findPath(PriorityQueue<Anode> pq) {
        while (!pq.isEmpty() && !pq.contains(atable[destinationY][destinationX])) {

            Anode u = pq.remove();

            int x = u.getX();
            int y = u.getY();
            tiles.add(u);
            this.NodeUpdate(pq, x - 1, y, u, Direction.LEFT);
            this.NodeUpdate(pq, x + 1, y, u, Direction.RIGHT);
            this.NodeUpdate(pq, x, y - 1, u, Direction.UP);
            this.NodeUpdate(pq, x, y + 1, u, Direction.DOWN);
        }
        tiles.add(atable[destinationY][destinationX]);
    }

    /**
     * Get path found with findPath
     * returns getPath(mapSize) 
     * @return 
     */
    
    public final Stack<Anode> getPath() {
        return getPath(width * height);
    }

    /**
     * Get path found with findPath, path max length = maxTry
     * Best called with |map|. So continues anyway
     * @param maxTry
     * @return path in stack. Source point first (on top), 
     * destination last (on bottom)
     */
    
    public Stack<Anode> getPath(int maxTry) {
        Stack<Anode> pathStack = new Stack<>();
        atable[sourceY][sourceX].setStart(0);
        Anode lastNode = atable[destinationY][destinationX];
        pathStack.add(lastNode);
        if (sourceX == destinationX && sourceY == destinationY) {
            lastNode = atable[lastNode.getFromY()][lastNode.getFromX()];
            pathStack.add(lastNode);
        }
        while (lastNode.getStart() > 0 && maxTry > 0) {
            lastNode = atable[lastNode.getFromY()][lastNode.getFromX()];
            pathStack.add(lastNode);
            maxTry--;
        }
        return pathStack;
    }

    /**
     * Return fixed value if coordinate part out of map
     * @param xy
     * @param min
     * @param max
     * @return 
     */
    
    private int checkCollision(int xy, int min, int max) {
        if (xy < min) {
            return min;
        } else if (xy > max) {
            return max;
        }
        return xy;
    }

    /**
     * 
     * @param x
     * @param y
     * @return true coordinate is out of map, else false
     */
    private boolean isOutOfBounds(int x, int y) {
        return x < 0 || y < 0 || x > this.width - 1 || y > this.height - 1;
    }

    
    /**
     * 
     * @param x
     * @param y
     * @return true if map location is wall
     */
    private boolean isWall(int x, int y) {
        if (isOutOfBounds(x, y)) {
            return true;
        }
        return this.map[y][x] >= 10;
    }

    
    /**
     * getReverseDirection
     * Up = Down...
     * @param d
     * @return reverse direction
     */
    private Direction reverseDirection(Direction d) {
        switch (d) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
        }
        return null;
    }

    /**
     * Update node, if no errors, add to Heap
     * Build path with setFrom
     * 
     * @param pq
     * @param x
     * @param y
     * @param from
     * @param nd 
     */
    private void NodeUpdate(PriorityQueue pq, int x, int y, Anode from, Direction nd) {
        if (reverseDirection(from.getFromDirection()) == nd) {
            return;
        }
        if (isOutOfBounds(x, y)) {
            return;
        }
        if (isWall(x, y)) {
            return;
        }
        Anode node = this.atable[y][x];
        if (node.isUsed()) {
            return;
        }
        node.setFrom(from.getX(), from.getY());
        node.setFromDirection(nd);
        node.use();
        if (!isWall(node.getX(), node.getY())) {
            node.setStart(from.getStart() + 1);
        } else {
            node.setStart(from.getStart() + this.width * this.height);
        }
        pq.add(node);

    }

    public Direction getDirection() {
        return this.newDirection;
    }

    public List<Anode> getTiles() {
        return this.tiles;
    }

}
