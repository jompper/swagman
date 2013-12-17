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

}
