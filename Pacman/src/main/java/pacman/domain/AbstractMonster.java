/*
 * Pacman - AbstractMonster
 * 2.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import pacman.sprite.OverlayTile;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import pacman.algorithm.Anode;
import pacman.algorithm.MoveLogic;

/**
 * Abstract Monster basicly the whole Monster class
 * except for colors.
 * 
 * @author Joni
 */
public abstract class AbstractMonster extends AbstractMovingTile implements Monster {

    private MoveLogic moveLogic;
    private boolean inJail;
    private boolean isMove;
    protected Color color;
    private boolean gentleUp;

    /**
     * Construct AbstractMonster
     * 
     * You need to set AI to monster else NullPointerException
     * or something else stupid. Just set it okay ? Also most 
     * monsters can be in jail. Ok all should be able to if eaten
     * but that still needs to be implemented. 
     * 
     * TODO: Implement monster eating and find path to escape coordinates
     * 
     * @param x
     * @param y
     * @param d
     * @param inJail 
     */
    public AbstractMonster(int x, int y, Direction d, boolean inJail) {
        super(x, y, d, 1.9);
        this.inJail = inJail;
        this.isMove = true;
        this.gentleUp = true;
        this.locationX = -7;
    }

    /**
     * If in jail just don't do stuff,
     * else move to current direction.
     * 
     * If true move = x or y position changed
     * call AI to find new direction
     * 
     * @return if real move
     * 
     * TODO: Implement gentle up-down movement for jail time
     */
    @Override
    public boolean move() {
        if (inJail) {
            moveGentleUpDown();
            return false;
        }
        this.isMove = super.move();
        if (this.isMove()) {
            this.AIMove();
        }
        return isMove;
    }

    /**
     * Gentle Up Down movement
     * Very smooth 5/5
     */ 
    public void moveGentleUpDown(){
        if(gentleUp){
            this.locationY -= this.speed / 3;
        }else{
            this.locationY += this.speed / 3;
        }
        if(this.locationY >= 8){
            this.locationY = 7;
            this.gentleUp = true;
        }else if(locationY <= -8){
            this.locationY = -7;
            this.gentleUp = false;
        }
    }
    
    /**
     * Center monster and find new direction,
     * shouldn't happen at least not much
     */
    @Override
    public void moveLocation() {
        if (inJail) {
            moveGentleUpDown();
            return;
        }
        super.moveLocation();
        this.AIMove();
    }

    /**
     * Set monster to jail or not to jail
     * @param jail 
     */
    @Override
    public void setJail(boolean jail) {
        this.inJail = jail;
    }

    /**
     * 
     * @return is monster in jail 
     */
    @Override
    public boolean inJail() {
        return this.inJail;
    }

    /**
     * Set AI for monster MoveLogic
     * @param ml 
     */
    @Override
    public void setAI(MoveLogic ml) {
        this.moveLogic = ml;
    }

    /**
     * Call AI to set new Direction
     */
    @Override
    public void AIMove() {
        this.moveLogic.move(this.isChase());
    }

    /**
     * @return Should AI find new direction
     */
    @Override
    public boolean isMove() {
        return this.isMove;
    }

    /**
     * Build Astar path finding overlay for map
     * @return Overlay tiles
     */
    @Override
    public List<Drawing> getPathTiles() {
        List<Drawing> tiles = new ArrayList<>();
        List<Anode> pathTiles = this.moveLogic.getPathTiles();
        int counter = 0;
        int count = pathTiles.size();
        for (Anode n : pathTiles) {
            int distance = n.getStart() * 3;
            if(distance > 40){
                distance = 40;
            }
            Color c = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 90 - distance);
            if (counter == count - 1) {
                c = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 200);
            }
            tiles.add(new OverlayTile(n.getX(), n.getY(), c));
            counter++;
        }
        return tiles;
    }
}
