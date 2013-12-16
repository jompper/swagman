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
        p = new Pacman(10, 10, DIRECTION_UP);
    }

    @Test
    public void testSetUpPosition() {
        assertEquals(10, p.getX());
        assertEquals(10, p.getY());
    }

    @Test
    public void testSetUpDirection() {
        assertEquals(DIRECTION_UP, p.getDirection());
    }

    @Test
    public void testMoveLeft() {
        p.setDirection(DIRECTION_LEFT);
        p.move();
        assertEquals(9, p.getX());
    }

    @Test
    public void testMoveRight() {
        p.setDirection(DIRECTION_RIGHT);
        p.move();
        assertEquals(11, p.getX());
    }

    @Test
    public void testMoveUp() {
        p.setDirection(DIRECTION_UP);
        p.move();
        assertEquals(9, p.getY());
    }

    @Test
    public void testMoveDown() {
        p.setDirection(DIRECTION_DOWN);
        p.move();
        assertEquals(11, p.getY());
    }

}
