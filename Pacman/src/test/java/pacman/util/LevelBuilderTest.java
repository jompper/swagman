/*
 * Pacman - LevelBuilderTest
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.util;

import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;
import pacman.domain.Drawing;
import pacman.domain.Eatable;
import pacman.domain.WallType;
import pacman.level.Level;
import pacman.sprite.Blinky;
import pacman.sprite.Clyde;
import pacman.sprite.Inky;
import pacman.sprite.Pacman;
import pacman.sprite.Pinky;
import pacman.sprite.Wall;

/**
 *
 * @author Joni
 */
public class LevelBuilderTest {

    class TestMap implements Level {

        private int[][] map;

        public TestMap(int[][] map) {
            this.map = map;
        }

        @Override
        public int[][] getLevel() {
            return this.map;
        }

        @Override
        public int getWidth() {
            return this.map[0].length;
        }

        @Override
        public int getHeight() {
            return this.map.length;
        }

        @Override
        public boolean isBlocked(int x, int y) {
            return this.map[y][x] >= 10;
        }
    }

    private final LevelBuilder lb;
    private Level level;
    private int[][] testMapWalls = new int[][]{
        {12, 10, 0, 9, 14},
        {11, 4, 1, 2, 11},
        {13, 10, 10, 8, 15},
        {16, 17, 5, 6, 7}
    };

    public LevelBuilderTest() {
        this.level = new TestMap(testMapWalls);
        this.lb = new LevelBuilder(level);
    }

    @Test
    public void testBuilderCreatesPacman() {
        assertNotNull(lb.getPacman());
    }

    @Test
    public void testBuilderPacmanLocationCorrect() {
        assertEquals(1, lb.getPacman().getX());
        assertEquals(1, lb.getPacman().getY());
    }

    @Test
    public void testBuilderTileCountIsCorret() {
        assertEquals(18, lb.getDrawings().size());
    }

    @Test
    public void testTilesContainPacman() {
        int count = 0;
        for (Drawing d : lb.getDrawings()) {
            if (d instanceof Pacman) {
                count++;
            }
        }
        assertEquals(1, count);
    }

    @Test
    public void testTilesContainTwoEatables() {
        int count = 0;
        for (Drawing d : lb.getDrawings()) {
            if (d instanceof Eatable) {
                count++;
            }
        }
        assertEquals(2, count);
    }

    @Test
    public void testLevelContainsOneOfEachMonster() {
        assertEquals(4, lb.getMonsters().size());
        assertNotNull(lb.getBlinky());
        assertNotNull(lb.getPinky());
        assertNotNull(lb.getInky());
        assertNotNull(lb.getClyde());
        int bc = 0;
        int pc = 0;
        int ic = 0;
        int cc = 0;
        for (Drawing d : lb.getDrawings()) {
            if (d instanceof Blinky) {
                bc++;
            }
            if (d instanceof Pinky) {
                pc++;
            }
            if (d instanceof Inky) {
                ic++;
            }
            if (d instanceof Clyde) {
                cc++;
            }
        }
        assertEquals(1, bc);
        assertEquals(1, pc);
        assertEquals(1, ic);
        assertEquals(1, cc);
    }

    @Test
    public void testWallTypesCountIsCorrect() {
        int[][] map = level.getLevel();
        HashMap<String, Integer> counts = new HashMap<>();
        for (int y = 0; y < level.getHeight(); y++) {
            for (int x = 0; x < level.getWidth(); x++) {
                WallType wt = null;
                switch (map[y][x]) {
                    case 10:
                        wt = WallType.LEFT_RIGHT;
                        break;
                    case 11:
                        wt = WallType.UP_DOWN;
                        break;
                    case 12:
                        wt = WallType.RIGHT_DOWN;
                        break;
                    case 13:
                        wt = WallType.RIGHT_UP;
                        break;
                    case 14:
                        wt = WallType.LEFT_DOWN;
                        break;
                    case 15:
                        wt = WallType.LEFT_UP;
                        break;
                    case 16:
                        wt = WallType.GATE_LEFT;
                        break;
                    case 17:
                        wt = WallType.GATE_RIGHT;
                        break;
                }
                if (wt != null) {
                    int count = 1;
                    if (counts.containsKey(wt.name())) {
                        count += counts.get(wt.name());
                    }
                    counts.put(wt.name(), count);
                }
            }
        }

        for (Drawing d : lb.getDrawings()) {
            if (d instanceof Wall) {
                Wall w = (Wall) d;
                String wt = w.getWallType().name();
                assertTrue(counts.containsKey(wt));
                int c = counts.get(wt);
                c--;
                if (c == 0) {
                    counts.remove(wt);
                } else {
                    counts.put(wt, c);
                }
            }
        }
        assertEquals(0, counts.size());
    }
    
    @Test
    public void testEscapeLocationIsCorrect(){
        for(int y = 0; y < level.getHeight(); y++){
            for(int x = 0; x < level.getWidth(); x++){
                if(testMapWalls[y][x] == 9){
                    assertEquals(x, lb.getEscapeX());
                    assertEquals(y, lb.getEscapeY());
                }
            }
        }
    }
}
