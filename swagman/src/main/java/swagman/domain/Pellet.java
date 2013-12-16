/*
 * Swagman - Pellet
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package swagman.domain;

/**
 *
 * @author Joni
 */
public class Pellet extends Tile implements Eatable {

    public Pellet(int x, int y) {
        super(x, y);
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEaten() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
