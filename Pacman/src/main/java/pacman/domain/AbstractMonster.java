/*
 * Pacman - AbstractMonster
 * 2.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import pacman.algorithm.Anode;
import pacman.algorithm.MoveLogic;
import pacman.tile.AbstractMovingTile;
import pacman.tile.Drawing;

/**
 *
 * @author Joni
 */
public abstract class AbstractMonster extends AbstractMovingTile implements Monster {

    private MoveLogic moveLogic;
    private boolean inJail;
    private boolean isMove;
    protected Color color;
    

    public AbstractMonster(int x, int y, Direction d, boolean inJail) {
        super(x, y, d, 1.9);
        this.inJail = inJail;
        this.isMove = true;
        this.color = Color.GREEN;
    }

    @Override
    public boolean move() {
        if (inJail) {
            return false;
        }
        this.isMove = super.move();
        if (this.isMove()) {
            this.AIMove();
        }
        return isMove;
    }

    @Override
    public void moveLocation() {
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
    public void setAI(MoveLogic ml) {
        this.moveLogic = ml;
    }

    @Override
    public void AIMove() {
        this.moveLogic.move(this.isChase());
    }

    @Override
    public boolean isMove() {
        return this.isMove;
    }

    @Override
    public List<Drawing> getPathTiles() {
        List<Drawing> tiles = new ArrayList<>();
        List<Anode> pathTiles = this.moveLogic.getPathTiles();
        int counter = 0;
        int count = pathTiles.size();
        for (Anode n : pathTiles) {
            Color c = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 50);
            if (counter == count - 1) {
                c = new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 200);
            }
            tiles.add(new OverlayTile(n.getX(), n.getY(), c));
            counter++;
        }
        return tiles;
    }
    
}
