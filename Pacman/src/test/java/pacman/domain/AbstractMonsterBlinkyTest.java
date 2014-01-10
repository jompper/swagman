/*
 * Pacman - AbstractMonsterBlinkyTest
 * 11.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.algorithm.BlinkyLogic;
import pacman.sprite.Blinky;
import pacman.sprite.OverlayTile;
import pacman.sprite.Pacman;

/**
 *
 * @author Joni
 */
public class AbstractMonsterBlinkyTest {

    private Blinky blinky;
    private BlinkyLogic bl;
    private Pacman pacman;

    public AbstractMonsterBlinkyTest() {
    }

    @Before
    public void setUp() {
        blinky = new Blinky(0, 0);
        blinky.setChangeDirection(Direction.RIGHT);
        blinky.setDirection(Direction.RIGHT);
        pacman = new Pacman(3, 1, Direction.RIGHT);
        bl = new BlinkyLogic(blinky, pacman, new int[][]{{0, 10, 0, 0}, {0, 0, 10, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}});
        blinky.setAI(bl);
    }

    @Test
    public void testSimpleGentleMoveInJail() {
        blinky.setJail(true);
        blinky.setSpeed(3);
        blinky.locationX = 0;
        blinky.locationY = 0;
        assertFalse(blinky.move());
        checkLocationXY(0, -1);
    }

    @Test
    public void testSimpleGentleMoveLocationInJail() {
        blinky.setJail(true);
        blinky.setSpeed(3);
        blinky.locationX = 0;
        blinky.locationY = 0;
        blinky.moveLocation();
        checkLocationXY(0, -1);
    }
    
    @Test
    public void testGentleMoveDown(){
        blinky.setJail(true);
        blinky.setSpeed(3);
        blinky.locationX = 0;
        blinky.locationY = -7;
        blinky.moveLocation();
        checkLocationXY(0, -7);
        blinky.moveLocation();
        checkLocationXY(0, -6);
    }

    @Test
    public void testTopGentleMoveInJail() {
        blinky.setJail(true);
        blinky.setSpeed(3);
        blinky.locationX = 0;
        blinky.locationY = -7;
        assertFalse(blinky.move());
        checkLocationXY(0, -7);
    }

    @Test
    public void testTopBottomGenteleMoveInJail() {
        testTopGentleMoveInJail();
        blinky.locationY = 7;
        assertFalse(blinky.move());
        checkLocationXY(0, 7);
    }

    @Test
    public void testMoveLocationNotInJail() {
        blinky.setSpeed(1);
        blinky.locationX = 1;
        blinky.locationY = 1;
        blinky.moveLocation();
        checkLocationXY(0, 0);
        assertEquals(Direction.DOWN, blinky.changeDirection);
    }

    private void checkLocationXY(double locationX, double locationY) {
        assertEquals(locationX, blinky.locationX, 0.001);
        assertEquals(locationY, blinky.locationY, 0.001);
    }

    @Test
    public void testPathTiles() {
        blinky.setSpeed(16);
        assertTrue(blinky.move());
        List<Drawing> l = blinky.getPathTiles();
        assertEquals(7, l.size());
        testPathTile(l.get(0), 0, 1, 70);
        testPathTile(l.get(1), 1, 1, 70);
        testPathTile(l.get(2), 0, 2, 40);
        testPathTile(l.get(3), 1, 2, 70);
        testPathTile(l.get(4), 2, 2, 70);
        testPathTile(l.get(5), 3, 2, 70);
        testPathTile(l.get(6), 3, 1, 200);
    }

    private void testPathTile(Drawing d, int x, int y, int alpha) {
        OverlayTile t = (OverlayTile) d;
        assertEquals(x, t.getX());
        assertEquals(y, t.getY());
        assertEquals(alpha, t.getColor().getAlpha());
    }
}
