/*
 * Pacman - LevelBuilderTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.util;

import org.junit.Test;
import pacman.level.LevelOne;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class LevelBuilderTest {
    
    private LevelBuilder lb;
    
    public LevelBuilderTest() {
        this.lb = new LevelBuilder(new LevelOne());
    }
    
    
    @Test
    public void testBuilderCreatesPacman(){
        assertNotNull(lb.getPacman());
    }
    
    @Test
    public void testBuilderPacmanLocationCorrect(){
        assertEquals(14, lb.getPacman().getX());
        assertEquals(26, lb.getPacman().getY());
    }
   
}
