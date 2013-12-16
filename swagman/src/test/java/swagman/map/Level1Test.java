/*
 * Swagman - Level1Test
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package swagman.map;

import org.junit.Before;
import org.junit.Test;
import static swagman.domain.MovingTile.DIRECTION_UP;
import static org.junit.Assert.*;
import static swagman.domain.MovingTile.DIRECTION_RIGHT;
import swagman.domain.Pacman;
import swagman.domain.Point;

/**
 *
 * @author Joni
 */
public class Level1Test {
    
    private Pacman p;
    private Level1 l;
    
    public Level1Test() {
        l = new Level1();
    }
    
    @Before
    public void setUp() {
        p = new Pacman(14, 23, DIRECTION_UP);
    }
    
    @Test
    public void testMapCantMoveBlocked(){
        Point pp = p.getNextPoint();
        if(l.canMove(pp.getX(), pp.getY())){
            p.move();
        }
        assertEquals(14, p.getX());
        assertEquals(23, p.getY());
    }
    
    @Test
    public void testCanMoveFree(){
        p.setDirection(DIRECTION_RIGHT);
        Point pp = p.getNextPoint();
        if(l.canMove(pp.getX(), pp.getY())){
            p.move();
        }
        assertEquals(15, p.getX());
        assertEquals(23, p.getY());
    }
}
