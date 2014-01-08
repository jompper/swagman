/*
 * Pacman - PacDotTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.domain;

import pacman.sprite.PacDot;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class PacDotTest {
    
    private PacDot dot;
    
    @Before
    public void setUp() {
        this.dot = new PacDot(0,0);
    }
    
    @Test
    public void testSetUpPosition(){
        assertEquals(0, this.dot.getX());
        assertEquals(0, this.dot.getY());
    }
    
    @Test
    public void testSetUpIsNotEaten(){
        assertFalse(this.dot.isEaten());
    }
    
    @Test
    public void testEatPacDot(){
        this.dot.eat();
        assertTrue(this.dot.isEaten());
    }
    
    @Test
    public void testEatEatenPacDot(){
        this.dot.eat();
        this.dot.eat();
        assertTrue(this.dot.isEaten());
    }
    
}
