/*
 * Swagman - SwagmanTest
 * 18.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import swagman.game.Swagman;
import swagman.level.Level1;

/**
 *
 * @author Joni
 */
public class SwagmanTest {

    private Swagman swagger;

    @Before
    public void setUp() {
        swagger = new Swagman();
    }
    
    @Test
    public void testSetBoard(){
        Level1 level = new Level1(16);
        swagger.setBoard(level);
        assertEquals(level, swagger.getBoard());
    }
}
