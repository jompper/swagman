/*
 * Pacman - LevelBuilder
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.util;

import java.util.ArrayList;
import java.util.List;
import pacman.domain.AbstractMonster;
import pacman.domain.Blinky;
import pacman.domain.Clyde;
import pacman.domain.Direction;
import pacman.domain.Eatable;
import pacman.domain.Inky;
import pacman.domain.PacDot;
import pacman.domain.Pacman;
import pacman.domain.Pinky;
import pacman.domain.PowerPellet;
import pacman.level.Level;
import pacman.tile.Drawing;
import pacman.tile.Moving;
import pacman.tile.Wall;
import pacman.tile.WallType;

/**
 *
 * @author Joni
 */
public class LevelBuilder {

    private List<Drawing> tiles;
    private Eatable eatables[][];
    private List<Moving> movings;
    private List<AbstractMonster> monsters;

    private Pacman pacman;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;

    private int escapeX;
    private int escapeY;

    /**
     * 0 = Empty, 1 = PacDot, 2 = PowerPellet, 3 = Cherry, 4 = Pacman 10 = Wall
     * Left-Right, 11 = Wall Top-Down, 12 = Wall Right-Down, 13 = Wall Right-up,
     * 14 = Wall Left-Down, 15 = Wall Left-Up, 16 = Gate Left, 17 = Gate Right
     *
     * TODO: Cherry, Ghosts
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

    protected final void buildMap(Level level) {
        int[][] map = level.getLevel();
        for (int r = 0; r < level.getHeight(); r++) {
            for (int s = 0; s < level.getWidth(); s++) {
                switch (map[r][s]) {
                    case 10:
                        tiles.add(new Wall(s, r, WallType.LEFT_RIGHT));
                        break;
                    case 11:
                        tiles.add(new Wall(s, r, WallType.UP_DOWN));
                        break;
                    case 12:
                        tiles.add(new Wall(s, r, WallType.RIGHT_DOWN));
                        break;
                    case 13:
                        tiles.add(new Wall(s, r, WallType.RIGHT_UP));
                        break;
                    case 14:
                        tiles.add(new Wall(s, r, WallType.LEFT_DOWN));
                        break;
                    case 15:
                        tiles.add(new Wall(s, r, WallType.LEFT_UP));
                        break;
                    case 16:
                        tiles.add(new Wall(s, r, WallType.GATE_LEFT));
                        break;
                    case 17:
                        tiles.add(new Wall(s, r, WallType.GATE_RIGHT));
                        break;
                    case 1:
                        PacDot pd = new PacDot(s, r);
                        tiles.add(0,pd);
                        eatables[r][s] = pd;
                        break;
                    case 2:
                        PowerPellet pp = new PowerPellet(s, r);
                        tiles.add(0,pp);
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


    public List<Drawing> getDrawings() {
        return this.tiles;
    }

    public Eatable[][] getEatables() {
        return this.eatables;
    }

    public List<AbstractMonster> getMonsters() {
        return this.monsters;
    }

    public Pacman getPacman() {
        return this.pacman;
    }

    public Blinky getBlinky() {
        return this.blinky;
    }

    public Pinky getPinky() {
        return this.pinky;
    }

    public Inky getInky() {
        return this.inky;
    }

    public Clyde getClyde() {
        return this.clyde;
    }

    public List<Moving> getMovings() {
        return this.movings;
    }

    public int getEscapeX() {
        return this.escapeX;
    }

    public int getEscapeY() {
        return this.escapeY;
    }
}
