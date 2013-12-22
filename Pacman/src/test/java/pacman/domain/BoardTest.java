/*
 * Pacman - BoardTest
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
import pacman.level.LevelOne;

/**
 *
 * @author Joni
 */
public class BoardTest {
    
    private Board board;
    
    public BoardTest() {
        this.board = new Board(new LevelOne());
    }
 
    @Before
    public void setUp() {
    }
    
    
    @Test
    public void testPacmanLocationIsCorrect(){
        assertEquals(14, this.board.getPacman().getX());
        assertEquals(26, this.board.getPacman().getY());
    }
    
    @Test
    public void testBoardMovePacmanDirectionRight(){
        board.move();
        assertEquals(15, this.board.getPacman().getX());
        assertEquals(26, this.board.getPacman().getY());
    }
}
