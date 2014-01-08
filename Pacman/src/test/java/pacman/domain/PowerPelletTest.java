/*
 * Pacman - PowerPelletTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.domain;

import pacman.sprite.PowerPellet;
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
public class PowerPelletTest {
    
    private PowerPellet pellet;
    
    @Before
    public void setUp() {
        this.pellet = new PowerPellet(0,0);
    }
    
    @Test
    public void testSetUpLocationIsCorrect(){
        assertEquals(0, this.pellet.getX());
        assertEquals(0, this.pellet.getY());
    }
    
    @Test
    public void testSetUpIsEatenFalse(){
        assertFalse(this.pellet.isEaten());
    }
    
    @Test
    public void testEatPellet(){
        this.pellet.eat();
        assertTrue(this.pellet.isEaten());
    }
    
    @Test
    public void testEatEatenPellet(){
        this.pellet.eat();
        this.pellet.eat();
        assertTrue(this.pellet.isEaten());
    }
}
