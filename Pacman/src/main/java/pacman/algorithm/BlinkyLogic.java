/*
 * Pacman - BlinkyLogic
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import pacman.domain.Blinky;
import pacman.domain.Pacman;

/**
 *
 * @author Joni
 */
public class BlinkyLogic implements MoveLogic {

    private Blinky blinky;
    private Pacman pacman;
    private int[][] map;

    public BlinkyLogic(Blinky b, Pacman p, int[][] map) {
        this.blinky = b;
        this.pacman = p;
        this.map = map;
    }

    @Override
    public void move() {
        //System.out.println("X: " + this.blinky.getX() + "/" + this.blinky.getNextX(1, this.blinky.getDirection()) + " Y: " + this.blinky.getY() + "/" + this.blinky.getNextY(1, this.blinky.getDirection()));
        Astar a = new Astar(this.map, this.blinky.getX(), this.blinky.getY(), this.pacman.getX(), this.pacman.getY(), this.blinky.getDirection());
        if (a.getDirection() != null) {
            this.blinky.setChangeDirection(a.getDirection());
        }
    }

}
