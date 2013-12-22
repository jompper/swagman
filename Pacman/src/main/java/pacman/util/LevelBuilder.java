/*
 * Pacman - LevelBuilder
 * 21.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.util;

import java.util.ArrayList;
import java.util.List;
import pacman.domain.Direction;
import pacman.domain.Eatable;
import pacman.domain.PacDot;
import pacman.domain.Pacman;
import pacman.domain.PowerPellet;
import pacman.level.Level;
import pacman.tile.Drawing;
import pacman.tile.Wall;
import pacman.tile.WallType;

/**
 *
 * @author Joni
 */
public class LevelBuilder {

    private List<Drawing> tiles;
    private Eatable eatables[][];

    private Pacman pacman;

    /**
     * Tiles: 1 = Wall Left_Right 2 = Wall Up_Down 3 = Wall Right_Down 4 = Wall
     * Right_Up 5 = Wall Left_Down 6 = Wall Left_Up 7 = Gate 8 = PacDot 9 =
     * PowerPellet 10 = Cherry 11 = Pacman 40-49 = Teleports
     * 
     * TODO: Teleports, Cherry, Ghosts
     *
     * @param level
     */
    public LevelBuilder(Level level) {
        this.tiles = new ArrayList<>();
        this.eatables = new Eatable[level.getHeight()][level.getWidth()];
        int[][] map = level.getLevel();
        for (int r = 0; r < level.getHeight(); r++) {
            for (int s = 0; s < level.getWidth(); s++) {
                switch (map[r][s]) {
                    case 1:
                        tiles.add(new Wall(s, r, WallType.LEFT_RIGHT));
                        break;
                    case 2:
                        tiles.add(new Wall(s, r, WallType.UP_DOWN));
                        break;
                    case 3:
                        tiles.add(new Wall(s, r, WallType.RIGHT_DOWN));
                        break;
                    case 4:
                        tiles.add(new Wall(s, r, WallType.RIGHT_UP));
                        break;
                    case 5:
                        tiles.add(new Wall(s, r, WallType.LEFT_DOWN));
                        break;
                    case 6:
                        tiles.add(new Wall(s, r, WallType.LEFT_UP));
                        break;
                    case 7:
                        break;
                    case 8:
                        PacDot pd = new PacDot(s, r);
                        tiles.add(pd);
                        eatables[r][s] = pd;
                        break;
                    case 9:
                        PowerPellet pp = new PowerPellet(s, r);
                        tiles.add(pp);
                        eatables[r][s] = pp;
                        break;
                    case 10:
                        break;
                    case 11:
                        this.pacman = new Pacman(s, r, Direction.RIGHT);
                        tiles.add(this.pacman);
                        map[r][s] = 0;
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

    public Pacman getPacman() {
        return this.pacman;
    }
}
