/*
 * Pacman - BoardTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.logic;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.domain.Direction;
import pacman.domain.Moving;
import pacman.level.LevelTest;

/**
 *
 * @author Joni
 */
public class BoardTest {
    
    private Board board;
    
    public BoardTest() {
        this.board = new Board(new LevelTest());
        this.board.setTimeout(0);
        this.board.getPacman().setSpeed(16);
        this.board.getPacman().setDirection(Direction.UP);
    }
 
    @Before
    public void setUp() {
    }
    
    
    @Test
    public void testPacmanLocationIsCorrect(){
        testLocation(board.getPacman(),5,3);
    }
    
    @Test
    public void testMoveEatsPacDotIncreaseScore(){
        board.move();
        testLocation(board.getPacman(),5,2);
        assertEquals(10, this.board.getScore());
    }
    
    @Test
    public void testMoveTwiceEatTwiceScore(){
        // Should eat pacdot + 10
        board.move();
        // Should eat PowerPellet + 50
        board.move();
        testLocation(board.getPacman(),5,1);
        assertEquals(60, this.board.getScore());
    }
    
    
    
    @Test
    public void testEatAllLevelEnd(){
        board.move();
        board.move();
        board.getPacman().setChangeDirection(Direction.LEFT);
        board.move();
        board.move();
        //Level one should be loaded now
        testLocation(board.getPacman(),14,26);
    }
    
    @Test
    public void testAllEatenRestartsMap(){
        board.checkAllEaten();
    }
    
    private void testLocation(Moving m, int x, int y){
        assertEquals(x, m.getX());
        assertEquals(y, m.getY());
    }
}
