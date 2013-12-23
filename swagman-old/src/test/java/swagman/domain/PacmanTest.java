/*
 * Swagman - PacmanTest
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static swagman.domain.MovingTile.*;
import swagman.game.Direction;

/**
 *
 * @author Joni
 */
public class PacmanTest {

    private Pacman p;

    public PacmanTest() {
    }

    @Before
    public void setUp() {
        p = new Pacman(10, 10);
        p.setSpeed(100);
    }

    @Test
    public void testSetUpPosition() {
        assertEquals(10, p.getX());
        assertEquals(10, p.getY());
    }

    @Test
    public void testSetUpDirectionShouldBeRight() {
        assertEquals(Direction.RIGHT, p.getDirection());
    }

    @Test
    public void testMoveLeft() {
        p.setDirection(Direction.LEFT);
        p.move();
        assertEquals(9, p.getX());
    }

    @Test
    public void testMoveRight() {
        p.setDirection(Direction.RIGHT);
        p.move();
        assertEquals(11, p.getX());
    }

    @Test
    public void testMoveUp() {
        p.setDirection(Direction.UP);
        p.move();
        assertEquals(9, p.getY());
    }

    @Test
    public void testMoveDown() {
        p.setDirection(Direction.DOWN);
        p.move();
        assertEquals(11, p.getY());
    }

    @Test
    public void testGetOneXDirectionLeft() {
        assertEquals(9, p.getNextX(1, Direction.LEFT));
    }
    
    @Test
    public void testGetOneXDirectionRight(){
        assertEquals(11, p.getNextX(1, Direction.RIGHT));
    }
    
    @Test
    public void testGetOneYDirectionRight(){
        assertEquals(10, p.getNextY(1, Direction.RIGHT));
    }
    
    @Test
    public void testGetOneYDirectionLeft(){
        assertEquals(10, p.getNextY(1, Direction.LEFT));
    }
    
    @Test
    public void testGetOneYDirectionUp(){
        assertEquals(9, p.getNextY(1, Direction.UP));
    }
    
    @Test
    public void testGetOneYDirectionDown(){
        assertEquals(11, p.getNextY(1, Direction.DOWN));
    }
    
    @Test
    public void testGetOneXDirectionUp(){
        assertEquals(10, p.getNextX(1, Direction.UP));
    }
    
    @Test
    public void testGetOneXDirectionDown(){
        assertEquals(10, p.getNextX(1, Direction.DOWN));
    }

}
