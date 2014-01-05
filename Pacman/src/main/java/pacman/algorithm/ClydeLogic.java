/*
 * Pacman - ClydeLogic
 * 30.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import java.util.ArrayList;
import java.util.List;
import pacman.domain.Clyde;
import pacman.domain.Direction;
import pacman.domain.Pacman;

/**
 *
 * @author Joni
 */
public class ClydeLogic extends AbstractMoveLogic implements MoveLogic {

    private Clyde clyde;
    private Pacman pacman;

    public ClydeLogic(Clyde c, Pacman p, int[][] map) {
        super(map);
        this.clyde = c;
        this.pacman = p;
    }

    @Override
    public void move(boolean chase) {
        if(chase){
            clyde.setChangeDirection(chaseMove());
        }else{
            clyde.setChangeDirection(scatterMove());   
        }
    }

    private Direction chaseMove(){
        int sourceX = clyde.getX();
        int sourceY = clyde.getY();
        int destinationX = pacman.getX();
        int destinationY = pacman.getY();
        if(Math.abs(sourceX-destinationX) + Math.abs(sourceY - destinationY) <=8){
            return scatterMove();
        }
        return findPath(sourceX, sourceY, destinationX, destinationY, clyde.getDirection());
        
    }
    
    private Direction scatterMove() {
        int sourceX = clyde.getX();
        int sourceY = clyde.getY();
        int destinationX = 1;
        int destinationY = map.length - 1;
        return findPath(sourceX, sourceY, destinationX, destinationY, clyde.getDirection());
    }

}
