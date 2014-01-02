/*
 * Pacman - InkyLogic
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import pacman.domain.Inky;
import pacman.domain.Pacman;
import pacman.tile.AbstractMovingTile;

/**
 *
 * @author Joni
 */
public class InkyLogic  implements MoveLogic {
    private final Inky inky;
    private final AbstractMovingTile tile;
    private final Pacman pacman;
    private final int[][] map;
    
    public InkyLogic(Inky i, AbstractMovingTile b, Pacman p, int[][] map){
        this.inky = i;
        this.tile = b;
        this.pacman = p;
        this.map = map;
    }
    
    @Override
    public void move(){
        int px = this.pacman.getNextX(2, this.pacman.getDirection()) - (this.tile.getX() - this.pacman.getNextX(2, this.pacman.getDirection()));
        int py = this.pacman.getNextY(2, this.pacman.getDirection()) - (this.tile.getY() - this.pacman.getNextY(2, this.pacman.getDirection()));
        Astar a = new Astar(this.map, this.inky.getX(), this.inky.getY(), px, py, this.inky.getDirection());
        if(a.getDirection() != null){
            this.inky.setChangeDirection(a.getDirection());
        }
    }
    
}
