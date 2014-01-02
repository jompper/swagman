/*
 * Pacman - AbstractMonster
 * 2.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import pacman.algorithm.MoveLogic;
import pacman.tile.AbstractMovingTile;
import pacman.tile.Moving;

/**
 *
 * @author Joni
 */
public abstract class AbstractMonster extends AbstractMovingTile implements Moving, Monster {
    
    private MoveLogic moveLogic;
    private boolean inJail;
    private boolean isMove;
    
    public AbstractMonster(int x, int y, Direction d, boolean inJail) {
        super(x, y, d, 1.9);
        this.inJail = inJail;
        this.isMove = true;
    }

    @Override
    public boolean move(){
        this.isMove = super.move();
        if(this.isMove()){
            this.AIMove();
        }
        return isMove;
    }
    
    @Override
    public void moveLocation(){
        super.moveLocation();
        this.AIMove();
    }
    
    @Override
    public void setJail(boolean jail) {
        this.inJail = jail;
    }

    @Override
    public boolean inJail() {
        return this.inJail;
    }
    
    @Override
    public void setAI(MoveLogic ml){
        this.moveLogic = ml;
    }
    
    @Override
    public void AIMove(){
        this.moveLogic.move();
    }
    
    @Override
    public boolean isMove(){
        return this.isMove;
    }
}
