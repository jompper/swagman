/*
 * Swagman - Level1Test
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.map;

import swagman.level.Level1;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import swagman.domain.Pacman;
import swagman.game.Direction;

/**
 *
 * @author Joni
 */
public class Level1Test {

    private Pacman p;
    private Level1 l;

    public Level1Test() {
        l = new Level1(16);
    }

    @Before
    public void setUp() {
        p = new Pacman(14, 23);
    }

    @Test
    public void testMapCantMoveBlocked() {
        p.setDirection(Direction.UP);
        assertFalse(l.getTileMap().canMove(p.getNextX(), p.getNextY()));
    }

    @Test
    public void testCanMoveFree() {
        p.setDirection(Direction.RIGHT);
        assertTrue(l.getTileMap().canMove(p.getNextX(), p.getNextY()));
    }
}
