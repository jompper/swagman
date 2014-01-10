/*
 * Pacman - BoardTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.domain.AbstractMovingTile;
import pacman.domain.Direction;
import pacman.domain.Moving;
import pacman.level.Level;

/**
 *
 * @author Joni
 */
public class BoardTest {

    private Board board;

    public BoardTest() {

    }

    @Before
    public void setUp() {
        this.board = new Board(new LevelTest());
        this.board.setTimeout(0);
        this.board.getPacman().setSpeed(16);
        this.board.getPacman().setDirection(Direction.UP);
        this.board.getBlinky().setSpeed(16);
        this.board.getBlinky().setDirection(Direction.RIGHT);
    }

    @Test
    public void testGetLevelIsLevelTest() {
        LevelTest t = new LevelTest();
        Board b = new Board(t);
        assertEquals(t, b.getLevel());
    }

    @Test
    public void testPacmanLocationIsCorrect() {
        testLocation(board.getPacman(), 5, 3);
    }

    @Test
    public void testMoveEatsPacDotIncreaseScore() {
        board.move();
        testLocation(board.getPacman(), 5, 2);
        assertEquals(10, this.board.getScore());
    }

    @Test
    public void testMoveTwiceEatTwiceScore() {
        board.move();
        board.move();
        testLocation(board.getPacman(), 5, 1);
        assertEquals(20, this.board.getScore());
    }

    @Test
    public void testEatAllLevelEnd() {
        board.toggleDebug();
        board.getPacman().setChangeDirection(Direction.DOWN);
        board.move();
        board.getPacman().setChangeDirection(Direction.UP);
        board.move();
        board.move();
        board.move();
        board.move();
        board.getPacman().setChangeDirection(Direction.DOWN);
        board.move();
        board.getPacman().setChangeDirection(Direction.LEFT);
        board.move();
        board.move();
        //Level one should be loaded now
        testLocation(board.getPacman(), 14, 26);
    }

    @Test
    public void testOnTimeOutDontMove() {
        board.setTimeout(2);
        board.move();
        testPacmanLocationIsCorrect();
    }

    @Test
    public void testTimeOutEndsPacmanMoves() {
        testOnTimeOutDontMove();
        testMoveEatsPacDotIncreaseScore();
    }

    @Test
    public void testMoveOutOfMapComeToTheOtherSide() {
        board.toggleDebug();
        board.getPacman().setChangeDirection(Direction.UP);
        board.move();
        board.move();
        board.move();
        board.move();
        testLocation(board.getPacman(), 5, 4);
        board.getPacman().setChangeDirection(Direction.DOWN);
        board.move();
        testLocation(board.getPacman(), 5, 0);
        board.move();
        board.getPacman().setChangeDirection(Direction.RIGHT);
        board.move();
        board.move();
        testLocation(board.getPacman(), 0, 1);
        board.getPacman().setChangeDirection(Direction.LEFT);
        board.move();
        testLocation(board.getPacman(), 6, 1);
    }

    @Test
    public void testDebugMakesYouGod() {
        board.setDebug(true);
        board.getPacman().setChangeDirection(Direction.LEFT);

        assertTrue(board.isChase());
        for (int i = 0; i < 6; i++) {
            board.move();
        }
        assertFalse(board.isChase());
        for (int i = 0; i < 420; i++) {
            board.move();
        }
        assertTrue(board.isChase());
        testLocation(board.getPacman(), 1, 1);
    }

    @Test
    public void testBlinkyCanEatPacman() {
        board.setDebug(false);
        board.getPacman().setChangeDirection(Direction.LEFT);
        for (int i = 0; i < 3; i++) {
            board.move();
        }
        testLocation(board.getPacman(), 14, 26);
    }

    @Test
    public void testEatAllYouCanFreeAllFromJail() {
        BoardE be = new BoardE(new LevelTest());
        be.setTimeout(0);
        be.getPacman().setSpeed(16);
        be.toggleDebug();
        be.getPacman().setDirection(Direction.UP);
        be.getPacman().setChangeDirection(Direction.LEFT);
        testJail(be, false, true, true, true);
        be.move();
        assertEquals(be.getScore(), 601);
        testJail(be, false, false, true, true);
        be.move();
        testJail(be, false, false, false, true);
        be.move();
        testJail(be, false, false, false, false);

    }

    private void testJail(BoardE be, boolean blinky, boolean pinky, boolean inky, boolean clyde) {
        assertEquals(blinky, be.BlinkyInJail());
        assertEquals(pinky, be.PinkyInJail());
        assertEquals(inky, be.InkyInJail());
        assertEquals(clyde, be.ClydeInJail());
    }

    private void testLocation(Moving m, int x, int y) {
        assertEquals(x, m.getX());
        assertEquals(y, m.getY());
    }

    class BoardE extends Board {

        public BoardE(Level level) {
            super(level);
        }

        @Override
        protected int checkEat(AbstractMovingTile amt) {
            int score = super.checkEat(amt);
            if (score == 10) {
                score = 601;
            }
            return score;
        }

        public boolean BlinkyInJail() {
            return this.blinky.inJail();
        }

        public boolean PinkyInJail() {
            return this.pinky.inJail();
        }

        public boolean InkyInJail() {
            return this.inky.inJail();
        }

        public boolean ClydeInJail() {
            return this.clyde.inJail();
        }
    }

    class LevelTest implements Level {

        private final int level[][];
        private final int width;
        private final int height;

        public LevelTest() {

            /**
             * Classic Pacman map
             *
             * Tiles: 0 = Empty, 1 = PacDot, 2 = PowerPellet, 3 = Cherry, 4 =
             * Pacman 5 = Blinky, 6 = Pinky, 7 = Inky, 8 = Clyde, 10 = Wall
             * Left-Right, 11 = Wall Top-Down, 12 = Wall Right-Down, 13 = Wall
             * Right-up, 14 = Wall Left-Down, 15 = Wall Left-Up
             */
            level = new int[][]{
                {12, 10, 10, 10, 10, 1, 14},
                {0, 5, 9, 2, 1, 1, 0},
                {11, 16, 17, 10, 14, 1, 11},
                {11, 6, 7, 8, 11, 4, 11},
                {13, 10, 10, 10, 10, 1, 15},};

            this.width = level[0].length;
            this.height = level.length;
        }

        @Override
        public int[][] getLevel() {
            return level;
        }

        @Override
        public int getWidth() {
            return this.width;
        }

        @Override
        public int getHeight() {
            return this.height;
        }

        /**
         *
         * @param x
         * @param y
         * @return true if map position contains wall
         */
        @Override
        public boolean isBlocked(int x, int y) {
            if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
                return false;
            }
            return this.level[y][x] > 9 && this.level[y][x] < 20;
        }
    }

}
