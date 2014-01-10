/*
 * Pacman - LevelOneTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.level;

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
public class LevelOneTest {

    private LevelOne level;

    public LevelOneTest() {
        this.level = new LevelOne();
    }

    @Test
    public void testLevelOneWidthIsCorrect() {
        assertEquals(28, this.level.getWidth());
    }

    @Test
    public void testLevelOneHeightIsCorrect() {
        assertEquals(35, this.level.getHeight());
    }

    @Test
    public void testOutOfMapIsNotBlocked() {
        assertFalse(this.level.isBlocked(-1, 0));
        assertFalse(this.level.isBlocked(0, -1));
        assertFalse(this.level.isBlocked(level.getWidth(), 0));
        assertFalse(this.level.isBlocked(0, level.getHeight()));
    }

    @Test
    public void testWallIsBlocked() {
        assertTrue(this.level.isBlocked(0, 0));
        assertTrue(this.level.isBlocked(level.getWidth() - 1, level.getHeight() - 1));
        assertTrue(this.level.isBlocked(0, 4));
        assertTrue(this.level.isBlocked(1, 3));
    }

    @Test
    public void testPathIsNotBlocked() {
        assertFalse(this.level.isBlocked(13, 14));
        assertFalse(this.level.isBlocked(14, 26));
    }

    @Test
    public void testGetLevelOne() {
        assertEquals(35, this.level.getLevel().length);
        assertEquals(28, this.level.getLevel()[0].length);
    }

}
