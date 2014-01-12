/*
 * Pacman - Astar
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import pacman.heurestic.Heurestic;
import pacman.datastructure.Stack;
import pacman.datastructure.MinHeap;
import pacman.domain.Direction;

/**
 * Astart, built to find shortest path from point A to point B
 *
 * @author Joni
 */
public class Astar {

    /**
     * Map where the path is being found
     */
    private int[][] map;
    private int width;
    private int height;

    /**
     * Table which keeps record of distances from all coordinates and to all
     * coordinates
     */
    private Anode[][] atable;

    /**
     * All nodes that should be visualized
     */
    private Stack<Anode> tiles;

    /**
     * Source coordinates
     */
    private int sourceX;
    private int sourceY;

    /**
     * Destination coordinates
     */
    private int destinationX;
    private int destinationY;

    /**
     * Direction where the object comes from
     */
    private Direction sourceDirection;

    /**
     * Heurestic
     */
    private Heurestic heurestic;

    /**
     * Initialise Astar
     *
     * @param map
     * @param h heurestic for path finding
     */
    public Astar(int[][] map, Heurestic h) {
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        this.tiles = new Stack<>();
        this.heurestic = h;
    }

    /**
     * Set coodinates
     *
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
    private void fixCoordinatesInWall() {
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
     *
     * @param pq
     * @return is destination found
     */
    private boolean initializePathFind(MinHeap<Anode> pq) {
        atable = buildAtable();
        Anode u = atable[sourceY][sourceX];
        u.setStart(0);
        u.setFromDirection(sourceDirection);
        int x = u.getX();
        int y = u.getY();
        boolean found = false;
        if (this.NodeUpdate(pq, x - 1, y, u, Direction.LEFT)) {
            found = true;
        }
        if (this.NodeUpdate(pq, x + 1, y, u, Direction.RIGHT)) {
            found = true;
        }
        if (this.NodeUpdate(pq, x, y - 1, u, Direction.UP)) {
            found = true;
        }
        if (this.NodeUpdate(pq, x, y + 1, u, Direction.DOWN)) {
            found = true;
        }
        return found;
    }

    /**
     * Find path
     *
     * @param sourceX
     * @param sourceY
     * @param destinationX
     * @param destinationY
     * @param sourceDirection
     * @return in which direction should move for shortest path
     */
    public Direction findPath(int sourceX, int sourceY, int destinationX, int destinationY, Direction sourceDirection) {
        this.tiles = new Stack<>();
        setCoordinates(sourceX, sourceY, destinationX, destinationY, sourceDirection);
        MinHeap<Anode> mh = new MinHeap<>();
        if (!initializePathFind(mh)) {
            findPath(mh);
        }
        Stack<Anode> path = getPath();
        if (path.size() >= 2) {
            path.pop();
            return path.pop().getFromDirection();
        }
        return firstPossibleDirection();
    }

    /**
     * Find nearest coordinate that is not wall Time complexcity O(|map|)
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
                if (distance < min) {
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
     *
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
     * Build table with correct distances from destination Default set all
     * pathLength to Map size * 2
     *
     * @return Anode[][] which contains all map coordinates as nodes
     */
    public final Anode[][] buildAtable() {
        Anode[][] table = new Anode[height][width];
        int max = width * height * 2;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                table[y][x] = new Anode(x, y, max, heurestic.getDistance(x, y, destinationX, destinationY));
            }
        }
        return table;
    }

    /**
     * Find the shortest path from source to destination
     *
     * @param pq
     */
    private void findPath(MinHeap<Anode> pq) {
        while (!pq.isEmpty()) {
            
            Anode u = pq.remove();
            
            int x = u.getX();
            int y = u.getY();
            tiles.push(u);
            if (this.NodeUpdate(pq, x - 1, y, u, Direction.LEFT)) {
                break;
            }
            if (this.NodeUpdate(pq, x + 1, y, u, Direction.RIGHT)) {
                break;
            }
            if (this.NodeUpdate(pq, x, y - 1, u, Direction.UP)) {
                break;
            }
            if (this.NodeUpdate(pq, x, y + 1, u, Direction.DOWN)) {
                break;
            }
        }
        tiles.push(atable[destinationY][destinationX]);
    }

    /**
     * Get path found with findPath returns getPath(mapSize)
     *
     * @return
     */
    public final Stack<Anode> getPath() {
        return getPath(width * height);
    }

    /**
     * Get path found with findPath, path max length = maxTry Best called with
     * |map|. So continues anyway
     *
     * @param maxTry
     * @return path in a stack. Source point first (on top), destination last (on
     * bottom)
     */
    public Stack<Anode> getPath(int maxTry) {
        Stack<Anode> pathStack = new Stack<>();
        atable[sourceY][sourceX].setStart(0);
        Anode lastNode = atable[destinationY][destinationX];
        do {
            lastNode.setInPath(true);
            pathStack.push(lastNode);
            maxTry--;
            lastNode = atable[lastNode.getFromY()][lastNode.getFromX()];
        } while (lastNode.getStart() != 0 && maxTry > 0);
        pathStack.push(lastNode);
        return pathStack;
    }

    /**
     * Return fixed value if coordinate part out of map
     *
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
     * getReverseDirection Up = Down...
     *
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
     * Update distance and add from node, if no errors, add to Heap Build path
     * with setFrom
     *
     * @param pq
     * @param x
     * @param y
     * @param from
     * @param nd
     * @return is destination found
     */
    private boolean NodeUpdate(MinHeap<Anode> pq, int x, int y, Anode from, Direction nd) {
        if (reverseDirection(from.getFromDirection()) == nd) {
            return false;
        }
        if (isOutOfBounds(x, y)) {
            return false;
        }
        if (isWall(x, y)) {
            return false;
        }
        Anode node = this.atable[y][x];
        if (node.isUsed()) {
            return false;
        }
        node.setFrom(from.getX(), from.getY());
        node.setFromDirection(nd);
        node.use();
        // If finding max path use negative values for min heap
        node.setStart(from.getStart() + ((heurestic.findMax()) ? -1 : 1));
        pq.add(node);
        return x == destinationX && y == destinationY;
    }

    /**
     * @return List of tiles that should be visualized
     */
    public Stack<Anode> getTiles() {
        return this.tiles;
    }
    
    public void setHeurestic(Heurestic h) {
        this.heurestic = h;
    }
    
}
