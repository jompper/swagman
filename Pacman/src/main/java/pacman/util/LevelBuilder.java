/*
 * Pacman - LevelBuilder
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.util;

import java.util.ArrayList;
import java.util.List;
import pacman.domain.AbstractMonster;
import pacman.sprite.Blinky;
import pacman.sprite.Clyde;
import pacman.domain.Direction;
import pacman.domain.Eatable;
import pacman.sprite.Inky;
import pacman.sprite.PacDot;
import pacman.sprite.Pacman;
import pacman.sprite.Pinky;
import pacman.sprite.PowerPellet;
import pacman.level.Level;
import pacman.domain.Drawing;
import pacman.domain.Moving;
import pacman.sprite.Wall;
import pacman.domain.WallType;

/**
 *
 * @author Joni
 */
public class LevelBuilder {

    /**
     * Everythings that needs to be drawn
     */
    private List<Drawing> tiles;
    /**
     * Everything that should be eatable
     */
    protected Eatable eatables[][];
    /**
     * Everything that should be moving
     */
    private List<Moving> movings;
    /**
     * Everything that should be scary
     */
    private List<AbstractMonster> monsters;

    
    /**
     * Required elements
     */
    private Pacman pacman;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;

    /**
     * Jailbreak location coordinates
     */
    private int escapeX;
    private int escapeY;

    /**
     * 0 = Empty, 1 = PacDot, 2 = PowerPellet, 3 = Cherry, 4 = Pacman 10 = Wall
     * Left-Right, 11 = Wall Top-Down, 12 = Wall Right-Down, 13 = Wall Right-up,
     * 14 = Wall Left-Down, 15 = Wall Left-Up, 16 = Gate Left, 17 = Gate Right
     *
     * TODO: Cherry
     *
     * @param level
     */
    public LevelBuilder(Level level) {
        this.tiles = new ArrayList<>();
        this.eatables = new Eatable[level.getHeight()][level.getWidth()];
        this.movings = new ArrayList<>();
        this.monsters = new ArrayList<>();
        buildMap(level);

    }

    /**
     * Make objects from map
     *
     * @param level
     */
    protected final void buildMap(Level level) {
        int[][] map = level.getLevel();
        for (int r = 0; r < level.getHeight(); r++) {
            for (int s = 0; s < level.getWidth(); s++) {
                switch (map[r][s]) {
                    case 10:
                        addWall(s, r, WallType.LEFT_RIGHT);
                        break;
                    case 11:
                        addWall(s, r, WallType.UP_DOWN);
                        break;
                    case 12:
                        addWall(s, r, WallType.RIGHT_DOWN);
                        break;
                    case 13:
                        addWall(s, r, WallType.RIGHT_UP);
                        break;
                    case 14:
                        addWall(s, r, WallType.LEFT_DOWN);
                        break;
                    case 15:
                        addWall(s, r, WallType.LEFT_UP);
                        break;
                    case 16:
                        addWall(s, r, WallType.GATE_LEFT);
                        break;
                    case 17:
                        addWall(s, r, WallType.GATE_RIGHT);
                        break;
                    case 1:
                        PacDot pd = new PacDot(s, r);
                        tiles.add(0, pd);
                        eatables[r][s] = pd;
                        break;
                    case 2:
                        PowerPellet pp = new PowerPellet(s, r);
                        tiles.add(0, pp);
                        eatables[r][s] = pp;
                        break;
                    case 3:
                        break;
                    case 4:
                        this.pacman = new Pacman(s, r, Direction.RIGHT);
                        tiles.add(this.pacman);
                        movings.add(this.pacman);
                        map[r][s] = 0;
                        break;
                    case 5:
                        this.blinky = new Blinky(s, r);
                        monsters.add(this.blinky);
                        tiles.add(this.blinky);
                        movings.add(this.blinky);
                        map[r][s] = 0;
                        break;
                    case 6:
                        this.pinky = new Pinky(s, r);
                        monsters.add(this.pinky);
                        tiles.add(this.pinky);
                        movings.add(this.pinky);
                        map[r][s] = 20;
                        break;
                    case 7:
                        this.inky = new Inky(s, r);
                        monsters.add(this.inky);
                        tiles.add(this.inky);
                        movings.add(this.inky);
                        map[r][s] = 20;
                        break;
                    case 8:
                        this.clyde = new Clyde(s, r);
                        monsters.add(this.clyde);
                        tiles.add(this.clyde);
                        movings.add(this.clyde);
                        map[r][s] = 20;
                        break;
                    case 9:
                        this.escapeX = s;
                        this.escapeY = r;
                        break;
                }
            }
        }
    }

    /**
     * Add wall to location of map
     * @param x
     * @param y
     * @param wt 
     */
    private void addWall(int x, int y, WallType wt) {
        tiles.add(new Wall(x, y, wt));
    }

    /**
     * @return All objects that should be drawn
     */
    public List<Drawing> getDrawings() {
        return this.tiles;
    }

    /**
     * @return eatables as table for fast access with coorinates
     */
    public Eatable[][] getEatables() {
        return this.eatables;
    }

    /**
     * @return all monsters in list
     */
    public List<AbstractMonster> getMonsters() {
        return this.monsters;
    }

    /**
     * @return Pacman
     */
    public Pacman getPacman() {
        return this.pacman;
    }

    /**
     * @return Blinky monster
     */
    public Blinky getBlinky() {
        return this.blinky;
    }

    /**
     * @return Pinky monster
     */
    public Pinky getPinky() {
        return this.pinky;
    }

    /**
     * @return Inky monster
     */
    public Inky getInky() {
        return this.inky;
    }

    /**
     * @return Clyde monster
     */
    public Clyde getClyde() {
        return this.clyde;
    }

    /**
     * @return All moving objects
     */
    public List<Moving> getMovings() {
        return this.movings;
    }

    /**
     *
     * @return Escape X coordinate
     */
    public int getEscapeX() {
        return this.escapeX;
    }

    /**
     * @return Escape Y coordinate
     */
    public int getEscapeY() {
        return this.escapeY;
    }
}
