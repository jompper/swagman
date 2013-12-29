/*
 * Pacman - PinkyLogic
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import pacman.domain.Blinky;
import pacman.domain.Direction;
import pacman.domain.Pacman;
import pacman.domain.Pinky;

/**
 *
 * @author Joni
 */
public class PinkyLogic implements MoveLogic {
    private Pinky pinky;
    private Pacman pacman;
    private int[][] map;
    
    public PinkyLogic(Pinky b, Pacman p, int[][] map){
        this.pinky = b;
        this.pacman = p;
        this.map = map;
    }
    
    @Override
    public void move(){
        Astar a = new Astar(this.map, this.pinky.getX(), this.pinky.getY(), this.pacman.getNextX(2, this.pacman.getDirection()), this.pacman.getNextY(2, this.pacman.getDirection()), this.pinky.getDirection());
        if(a.getDirection() != null){
            this.pinky.setChangeDirection(a.getDirection());
        }
    }
}
