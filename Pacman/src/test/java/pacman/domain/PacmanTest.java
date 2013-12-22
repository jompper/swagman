/*
 * Pacman - PacmanTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class PacmanTest {

    private Pacman pacman;

    @Before
    public void setUp() {
        this.pacman = new Pacman(0, 0, Direction.UP);
    }

    @Test
    public void testSetUpDirectionIsUp() {
        assertEquals(Direction.UP, this.pacman.getDirection());
    }

    @Test
    public void testSetUpLocationXY() {
        assertEquals(0, this.pacman.getX());
        assertEquals(0, this.pacman.getY());
    }

    @Test
    public void testSetDirectionUp() {
        this.pacman.setDirection(Direction.DOWN);
        this.pacman.setDirection(Direction.UP);
        assertEquals(Direction.UP, this.pacman.getDirection());
    }

    @Test
    public void testSetDirectionDown() {
        this.pacman.setDirection(Direction.DOWN);
        assertEquals(Direction.DOWN, this.pacman.getDirection());
    }

    @Test
    public void testSetDirectionLeft() {
        this.pacman.setDirection(Direction.LEFT);
        assertEquals(Direction.LEFT, this.pacman.getDirection());
    }

    @Test
    public void testSetDirectionRight() {
        this.pacman.setDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, this.pacman.getDirection());
    }

    @Test
    public void testGetNextXOneDirectionUp() {
        assertEquals(0, this.pacman.getNextX(1, Direction.UP));
    }

    @Test
    public void testGetNextXOneDirectionDown() {
        assertEquals(0, this.pacman.getNextX(1, Direction.DOWN));
    }

    @Test
    public void testGetNextXOneDirectionLeft() {
        assertEquals(-1, this.pacman.getNextX(1, Direction.LEFT));
    }

    @Test
    public void testGetNextXOneDirectionRight() {
        assertEquals(1, this.pacman.getNextX(1, Direction.RIGHT));
    }

    @Test
    public void testGetNextYOneDirectionUp() {
        assertEquals(-1, this.pacman.getNextY(1, Direction.UP));
    }

    @Test
    public void testGetNextYOneDirectionDown() {
        assertEquals(1, this.pacman.getNextY(1, Direction.DOWN));
    }

    @Test
    public void testGetNextYOneDirectionLeft() {
        assertEquals(0, this.pacman.getNextY(1, Direction.LEFT));
    }

    @Test
    public void testGetNextYOneDirectionRight() {
        assertEquals(0, this.pacman.getNextY(1, Direction.RIGHT));
    }
    
    @Test
    public void testMoveDirectionUp(){
        this.pacman.setDirection(Direction.UP);
        this.pacman.move();
        assertEquals(-1, this.pacman.getY());
        assertEquals(0, this.pacman.getX());
    }
    @Test
    public void testMoveDirectionDown(){
        this.pacman.setDirection(Direction.DOWN);
        this.pacman.move();
        assertEquals(1, this.pacman.getY());
        assertEquals(0, this.pacman.getX());
    }
    @Test
    public void testMoveDirectionLeft(){
        this.pacman.setDirection(Direction.LEFT);
        this.pacman.move();
        assertEquals(0, this.pacman.getY());
        assertEquals(-1, this.pacman.getX());
    }
    @Test
    public void testMoveDirectionRight(){
        this.pacman.setDirection(Direction.RIGHT);
        this.pacman.move();
        assertEquals(0, this.pacman.getY());
        assertEquals(1, this.pacman.getX());
    }
}
