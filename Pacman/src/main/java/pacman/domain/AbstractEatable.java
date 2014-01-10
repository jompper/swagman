/*
 * Pacman - AbstractEatable
 * 8.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.domain;

/**
 *
 * @author Joni
 */
public class AbstractEatable  extends AbstractTile implements Eatable {

    /**
     * Is already eaten ?
     */
    private boolean eaten;
    
    /**
     * How much do you score ?
     */
    private final int score;
    
    public AbstractEatable(int x, int y, int score) {
        super(x, y);
        this.eaten = false;
        this.score = score;
    }
    /**
     * Return True if is eaten, otherwise False
     * 
     * @return eaten status
     */
    @Override
    public boolean isEaten() {
        return this.eaten;
    }

    /**
     * On eat, sets as eaten if not already eaten
     * @return score
     */
    @Override
    public int eat() {
        if(isEaten())return 0;
        this.eaten = true;
        return this.score;
    }

    
}
