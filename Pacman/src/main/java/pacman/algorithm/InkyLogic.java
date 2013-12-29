/*
 * Pacman - InkyLogic
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import pacman.domain.Blinky;
import pacman.domain.Direction;
import pacman.domain.Inky;
import pacman.domain.Pacman;

/**
 *
 * @author Joni
 */
public class InkyLogic  implements MoveLogic {
    private Inky inky;
    private Blinky blinky;
    private Pacman pacman;
    private int[][] map;
    
    public InkyLogic(Inky i, Blinky b, Pacman p, int[][] map){
        this.inky = i;
        this.blinky = b;
        this.pacman = p;
        this.map = map;
    }
    
    @Override
    public void move(){
        int px = this.pacman.getNextX(2, this.pacman.getDirection()) - (this.blinky.getX() - this.pacman.getNextX(2, this.pacman.getDirection()));
        int py = this.pacman.getNextY(2, this.pacman.getDirection()) - (this.blinky.getY() - this.pacman.getNextY(2, this.pacman.getDirection()));
        Astar a = new Astar(this.map, this.inky.getX(), this.inky.getY(), px, py, this.inky.getDirection());
        if(a.getDirection() != null){
            this.inky.setChangeDirection(a.getDirection());
        }
    }
    
}
