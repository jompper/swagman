/*
 * Pacman - AbstractMovingTilePacmanTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import pacman.sprite.Pacman;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class AbstractMovingTilePacmanTest {

    private Pacman pacman;

    @Before
    public void setUp() {
        this.pacman = new Pacman(0, 0, Direction.UP);
        this.pacman.setSpeed(16);
    }

    @Test
    public void testMoveLocationUp() {
        moveLocation(Direction.UP, 1, 0, -1);
    }

    @Test
    public void testMoveLocationDown() {
        moveLocation(Direction.DOWN, 1, 0, 1);
    }

    @Test
    public void testMoveLocationLeft() {
        moveLocation(Direction.LEFT, 1, -1, 0);
    }

    @Test
    public void testMoveLocationRight() {
        moveLocation(Direction.RIGHT, 1, 1, 0);
    }

    private void moveLocation(Direction d, int speed, double locationX, double locationY) {
        pacman.setDirection(d);
        this.pacman.setSpeed(speed);
        pacman.locationX = 0;
        pacman.locationY = 0;
        pacman.move();
        assertEquals(locationX, pacman.locationX, 0.001);
        assertEquals(locationY, pacman.locationY, 0.001);
    }

    @Test
    public void testFakeMoveLocation() {
        moveFakeLocation(1, 1, 1, 0, 0);
        moveFakeLocation(1, -1, -1, 0, 0);
        moveFakeLocation(1, 2, 2, 1, 1);
        moveFakeLocation(1, -2, -2, -1, -1);
        moveFakeLocation(1, 0.5, 0.5, 0, 0);
    }

    private void moveFakeLocation(int speed, double startX, double startY, double endX, double endY) {
        this.pacman.setSpeed(speed);
        pacman.locationX = startX;
        pacman.locationY = startY;
        pacman.moveLocation();
        assertEquals(endX, pacman.locationX, 0.001);
        assertEquals(endY, pacman.locationY, 0.001);
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
    public void testMoveDirectionUp() {
        this.pacman.setDirection(Direction.UP);
        this.pacman.move();
        assertEquals(-1, this.pacman.getY());
        assertEquals(0, this.pacman.getX());
    }

    @Test
    public void testMoveDirectionDown() {
        this.pacman.setDirection(Direction.DOWN);
        this.pacman.move();
        assertEquals(1, this.pacman.getY());
        assertEquals(0, this.pacman.getX());
    }

    @Test
    public void testMoveDirectionLeft() {
        this.pacman.setDirection(Direction.LEFT);
        this.pacman.move();
        assertEquals(0, this.pacman.getY());
        assertEquals(-1, this.pacman.getX());
    }

    @Test
    public void testMoveDirectionRight() {
        this.pacman.setDirection(Direction.RIGHT);
        this.pacman.move();
        assertEquals(0, this.pacman.getY());
        assertEquals(1, this.pacman.getX());
    }
}
