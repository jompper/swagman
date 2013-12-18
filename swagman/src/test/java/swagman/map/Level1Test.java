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
    }

    @Before
    public void setUp() {

        l = new Level1(16);
        p = new Pacman(14, 23);
        p.setSpeed(100);
        l.getPacman().setSpeed(100);
    }

    @Test
    public void testSetUpHasPacman() {
        assertFalse(l.getPacman() == null);
    }

    @Test
    public void testSetUpHasOneMoveablePacman() {
        assertEquals(1, l.getMoveables().size());
    }

    @Test
    public void testSetUpHasOneDrawablePacman() {
        assertTrue(l.getDrawables().contains(l.getPacman()));
    }
    
    @Test
    public void testSetUpHasWalls(){
        // Has one pacman + 478 walls
        assertEquals(479, l.getDrawables().size());
    }

    @Test
    public void testRemoveMoveablePacman() {
        l.removeMoveable(l.getPacman());
        assertFalse(l.getMoveables().contains(l.getPacman()));
    }

    @Test
    public void testRemoveDrawablePacman() {
        l.removeDrawable(l.getPacman());
        assertFalse(l.getDrawables().contains(l.getPacman()));
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

    @Test
    public void testSetGetPacman() {
        l.setPacman(p);
        assertTrue(p == l.getPacman());
    }

    @Test
    public void testAddOneMoveable() {
        l.addMoveable(p);
        assertEquals(2, l.getMoveables().size());
    }

    @Test
    public void testBoardMoveOne() {
        l.addMoveable(p);
        p.setDirection(Direction.RIGHT);
        l.move();
        assertEquals(15, p.getX());
    }

    @Test
    public void testBoardMovePacmanNewDirection() {
        l.getPacman().setNewDirection(Direction.LEFT);
        l.move();
        assertEquals(13, l.getPacman().getX());
    }
}
