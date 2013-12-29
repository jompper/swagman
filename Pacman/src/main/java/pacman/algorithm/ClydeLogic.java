/*
 * Pacman - ClydeLogic
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import pacman.domain.Clyde;
import pacman.domain.Pacman;

/**
 *
 * @author Joni
 */
public class ClydeLogic implements MoveLogic {

    private Clyde clyde;
    private Pacman pacman;
    private int[][] map;

    public ClydeLogic(Clyde c, Pacman p, int[][] map) {
        this.clyde = c;
        this.pacman = p;
        this.map = map;
    }

    @Override
    public void move() {
        int px = this.pacman.getX();
        int py = this.pacman.getY();
        if(Math.abs(this.clyde.getX() - px) + Math.abs(this.clyde.getY() - py) < 8){
            px = 0;
            py = this.map.length - 1;
        }
        Astar a = new Astar(this.map, this.clyde.getX(), this.clyde.getY(), px, py, this.clyde.getDirection());
        if (a.getDirection() != null) {
            this.clyde.setChangeDirection(a.getDirection());
        }
    }

}
