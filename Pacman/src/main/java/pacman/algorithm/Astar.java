/*
 * Pacman - Astar
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import java.util.PriorityQueue;
import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public class Astar {

    private Direction d;
    private Direction sd;
    private int[][] map;
    private int width;
    private int height;

    private int sx;
    private int sy;

    public Astar(int[][] map, int sx, int sy, int dx, int dy, Direction sd) {
        if (sx == dx && sy == dy) {
            this.d = sd;
            return;
        }

        
        this.map = map;
        this.height = map.length;
        this.width = map[0].length;
        
        if (dx < 0) {
            dx = 0;
        } else if (dx > width - 1) {
            dx = width - 1;
        }
        if (dy < 0) {
            dy = 0;
        } else if (dy > height - 1) {
            dy = height - 1;
        }

        this.sx = sx;
        this.sy = sy;
        this.sd = sd;

        Anode[][] atable = new Anode[height][width];
        PriorityQueue<Anode> pq = new PriorityQueue();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Anode a = new Anode(x, y, width * height * 2, (Math.abs(x - dx) + Math.abs(y - dy)));
                atable[y][x] = a;
            }
        }
        Anode start = atable[sy][sx];
        start.setStart(0);
        start.setFromDirection(sd);
        start.use();
        //pq.remove(start);
        pq.add(start);
        //System.out.println("SX: " + sx + " SY: " + sy + " DX: "+ dx + " DY: " + dy);
        while (!pq.isEmpty() && !pq.contains(atable[dy][dx])) {
            Anode u = pq.remove();

            int x = u.getX();
            int y = u.getY();
            if (x > 0 && u.getFromDirection() != Direction.RIGHT) {
                this.NodeUpdate(pq, atable[y][x - 1], u, Direction.LEFT);
            }
            if (y > 0 && u.getFromDirection() != Direction.DOWN) {
                this.NodeUpdate(pq, atable[y - 1][x], u, Direction.UP);
            }
            if (x < width - 1 && u.getFromDirection() != Direction.LEFT) {
                this.NodeUpdate(pq, atable[y][x + 1], u, Direction.RIGHT);
            }
            if (y < height - 1 && u.getFromDirection() != Direction.UP) {
                this.NodeUpdate(pq, atable[y + 1][x], u, Direction.DOWN);
            }

        }
        Anode dd = atable[dy][dx];
        int counter = 0;
        while (atable[dd.getFromY()][dd.getFromX()].getStart() > 0 && counter < 100) {
            //System.out.println("x: " + dd.getX() + "/" + dd.getFromX() + ", y: " + dd.getY() + "/" + dd.getFromY() + ", s: " + dd.getStart() + ", e: " + dd.getEnd());
            dd = atable[dd.getFromY()][dd.getFromX()];
            counter++;
        }
        //System.out.println("x: " + dd.getX() + "/" + this.sx + " y: " + dd.getY() + "/" + this.sy + " s: " + dd.getStart());
        //System.exit(0);
        if (dd.getX() < sx) {
            this.d = Direction.LEFT;
        } else if (dd.getX() > sx) {
            this.d = Direction.RIGHT;
        } else if (dd.getY() < sy) {
            this.d = Direction.UP;
        } else if (dd.getY() > sy) {
            this.d = Direction.DOWN;
        }
    }

    private void NodeUpdate(PriorityQueue pq, Anode node, Anode from, Direction nd) {

        if (!node.isUsed()) {
            node.setFrom(from.getX(), from.getY());
            node.setFromDirection(nd);
            node.use();
            //System.out.println("s: " + from.getStart());
            if(this.map[node.getY()][node.getX()]<10){
                node.setStart(from.getStart() + 1);
            }else{
                node.setStart(from.getStart() + this.width * this.height);
            }
            
            //pq.remove(node);
            pq.add(node);
        }
    }

    public Direction getDirection() {
        return this.d;
    }

}
