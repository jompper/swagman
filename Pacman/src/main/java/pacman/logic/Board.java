 /*
 * Pacman - Board
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.logic;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import pacman.algorithm.BlinkyLogic;
import pacman.algorithm.ClydeLogic;
import pacman.algorithm.InkyLogic;
import pacman.algorithm.PinkyLogic;
import pacman.domain.AbstractMonster;
import pacman.sprite.Blinky;
import pacman.sprite.Clyde;
import pacman.domain.Direction;
import pacman.domain.Eatable;
import pacman.sprite.Inky;
import pacman.sprite.Pacman;
import pacman.sprite.Pinky;
import pacman.level.Level;
import pacman.level.LevelOne;
import pacman.domain.AbstractMovingTile;
import pacman.domain.Drawing;
import pacman.domain.Moving;
import pacman.util.LevelBuilder;

/**
 *
 * @author Joni
 */
public class Board implements Drawing{
    
    private Pacman pacman;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;

    private Level level;
    private int timeout;
    private int scatter;
    private boolean showPaths;
    private boolean debug;

    private List<Drawing> drawings;
    private List<Moving> movings;
    private Eatable[][] eatables;
    private List<AbstractMonster> monsters;

    private int highScore;
    private int score;

    private int escapeX;
    private int escapeY;

    /**
     * Initialise board with Level ready to play
     * @param level
     */
    public Board(Level level) {
        this.drawings = new ArrayList<>();
        this.movings = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.highScore = 0;
        this.score = 0;
        this.showPaths = true;
        this.newGame(level);
    }

    /**
     * Start new game with Level
     * Set timeout to x seconds
     * @param level
     */
    public final void newGame(Level level) {
        this.drawings.clear();
        this.movings.clear();
        this.monsters.clear();
        this.level = level;
        buildLevel(level);
        this.timeout = 200;
    }

    /**
     * Extract all objects from level
     * using LevelBuilder
     * @param level 
     */
    private void buildLevel(Level level){     
        LevelBuilder lb = new LevelBuilder(level);
        this.drawings.addAll(lb.getDrawings());
        this.movings.addAll(lb.getMovings());
        this.monsters.addAll(lb.getMonsters());
        this.pacman = lb.getPacman();
        this.eatables = lb.getEatables();

        this.escapeX = lb.getEscapeX();
        this.escapeY = lb.getEscapeY();  
        createMonsters(lb);
    }
    
     
    /**
     * Set Ai to monsters from LevelBuilder
     * @param lb 
     */
    private void createMonsters(LevelBuilder lb){
        this.blinky = lb.getBlinky();
        BlinkyLogic bl = new BlinkyLogic(this.blinky, this.pacman, level.getLevel());
        this.blinky.setAI(bl);

        this.pinky = lb.getPinky();
        PinkyLogic pl = new PinkyLogic(this.pinky, this.pacman, level.getLevel());
        this.pinky.setAI(pl);

        this.inky = lb.getInky();
        InkyLogic il = new InkyLogic(this.inky, this.blinky, this.pacman, level.getLevel());
        this.inky.setAI(il);

        this.clyde = lb.getClyde();
        ClydeLogic cl = new ClydeLogic(this.clyde, this.pacman, level.getLevel());
        this.clyde.setAI(cl);
    }
    
    /**
     * Add new drawing object d to game
     *
     * @param d
     */
    public void addDrawing(Drawing d) {
        this.drawings.add(d);
    }

    /**
     * Add new moving object m to game
     *
     * @param m
     */
    public void addMoving(Moving m) {
        this.movings.add(m);
    }

    /**
     * @return pacman
     */
    public Pacman getPacman() {
        return this.pacman;
    }

    /**
     * @return level map
     */
    public Level getLevel() {
        return this.level;
    }

    /**
     * Move all moving objects on the game After objects have been moven, checks
     * if Pacman is in a tile that contains eatable object. And if monsters
     * intersect with Pacmans location
     */
    public void move() {
        if (this.checkTimeout()) {
            return;
        }
        for (Moving m : this.movings) {
            move(m);
        }
        this.score += checkEat(this.pacman);
        if (!debug && isChase()) {
            checkMonsterIntersectPacman();
        }
        checkMonsterJail();
        if (checkAllEaten()) {
            newGame();
        }
        if (!isChase()) {
            this.scatter--;
            if (scatter == 0) {
                deactivateScatter();
            }
        }
    }

    /**
     * Move Moving object
     * Change direction to new if able to move to that Direction. 
     * If new tile ain't wall move, else center sprite to current
     * position. 
     * 
     * Always check if out of map and return to otherside of map
     * if this happens. Called to prevent overflow errors
     * @param m 
     */
    private void move(Moving m) {
        if (canMove(m, m.getChangeDirection())) {
            m.setDirection(m.getChangeDirection());
        }
        if (canMove(m, m.getDirection())) {
            m.move();
        } else {
            m.moveLocation();
        }
        fixOutOfBounds(m, this.level.getWidth() - 1, this.level.getHeight() - 1);

    }

    /**
     * 
     * @param m
     * @param d
     * @return true if next tile ain't wall else false
     */
    private boolean canMove(Moving m, Direction d) {
        int nextX = m.getNextX(1, d);
        int nextY = m.getNextY(1, d);
        return (!this.level.isBlocked(nextX, nextY));
    }

    /**
     *
     * @return true if chase mode, else false
     */
    public boolean isChase() {
        return scatter == 0;
    }

    /**
     * Check if any of monsters is in same position as
     * Pacman, if so, loseGame
     */
    private void checkMonsterIntersectPacman() {
        for (Moving m : this.monsters) {
            if (m.getX() == this.pacman.getX() && m.getY() == this.pacman.getY()) {
                if (isChase()) {
                    loseGame();
                    return;
                }
            }
        }
    }

    
    /**
     * if timeout left decrease
     * @return true if timeout left
     */
    private boolean checkTimeout() {
        if (this.timeout > 0) {
            this.timeout -= 2;
        }
        return this.timeout > 0;
    }

    /**
     * Check if monsters are in jail and should be freed
     * 
     * Force set new XY location to escape coordinates
     */
    private void checkMonsterJail() {
        if (this.pinky.inJail() && this.score > 600) {
            this.pinky.setX(this.escapeX);
            this.pinky.setY(this.escapeY);
            this.pinky.AIMove();
            this.pinky.setJail(false);
        }
        if (this.inky.inJail() && this.score > 1200) {
            this.inky.setX(this.escapeX);
            this.inky.setY(this.escapeY);
            this.inky.AIMove();
            this.inky.setJail(false);
        }
        if (this.clyde.inJail() && this.score > 1800) {
            this.clyde.setX(this.escapeX);
            this.clyde.setY(this.escapeY);
            this.clyde.setJail(false);
            this.clyde.AIMove();
        }
    }

    /**
     * Start new game with level one
     */
    private void newGame() {
        newGame(new LevelOne());
    }

    /**
     * Game Lost
     * 
     * Reset score, set new highscore if higher than current
     * Start new game with level one
     */
    public void loseGame() {
        if (this.score > this.highScore) {
            this.highScore = this.score;
        }
        this.score = 0;
        newGame();
    }

    /**
     * Check if AbstractMoving tile is position
     * where it could eat something, if so eat it
     * and return score it values.
     * 
     * If score is 50 activate scatter mode
     * 
     * @param amt
     * @return score of eaten object
     */
    private int checkEat(AbstractMovingTile amt) {
        Eatable e = this.eatables[amt.getY()][amt.getX()];
        int score = 0;
        if (e != null) {
            score = e.eat();
            if (score == 50) {
                activateScatter();
            }
        }
        return score;
    }

    /**
     * Deactivate scatter mode, set all monsters to chase
     * mode again. 
     */
    private void deactivateScatter() {
        this.scatter = 0;
        for (AbstractMovingTile m : this.monsters) {
            m.setChase(true);
        }
    }

    /**
     * Activate scatter mode, set all monster to scatter
     * mode. 
     * 
     * 7 seconds = 60fps * 7 = 420 frames
     */
    private void activateScatter() {
        this.scatter = 420;
        for (AbstractMovingTile m : this.monsters) {
            m.setChase(false);
        }
    }

    /**
     * Checks if all eatables in map have been eaten already
     * @return true if everything eaten = map complete
     */
    public boolean checkAllEaten() {
        for (Eatable[] eatable : this.eatables) {
            for (int x = 0; x < this.eatables[0].length; x++) {
                if (eatable[x] != null && !eatable[x].isEaten()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Check if moving object is out of map, and fix it
     * @param m
     * @param maxW
     * @param maxH 
     */
    private void fixOutOfBounds(Moving m, int maxW, int maxH) {
        m.setX(fixOutOfBounds(m.getX(), maxW));
        m.setY(fixOutOfBounds(m.getY(), maxH));
    }

    /**
     * Check if position is out of map
     * 
     * 
     * @param pos
     * @param max
     * @return max value if position < 0 else return 0
     */
    private int fixOutOfBounds(int pos, int max) {
        if (pos < 0) {
            return max;
        } else if (pos > max) {
            return 0;
        }
        return pos;
    }

    /**
     * Draws the timeout number
     * @param g
     * @param number 
     */
    private void drawTimeOutNumber(Graphics g, int number) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 200));
        g.drawString("" + number, 170, 340);
    }


    /**
     *
     * @param timeout
     */
    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    /**
     * Toggle path showing, Hidden mode in menu
     * CTRL hotkey
     */
    public void toggleShowPaths() {
        this.showPaths = !this.showPaths;
    }

    /**
     * Toggle God mode, ALT hotkey
     */
    public void toggleDebug() {
        this.debug = !this.debug;
    }

    
    /**
     * @return current score
     */
    public int getScore(){
        return this.score;
    }    
    
    
    /**
     * Draw everything :)
     * Ain't nobody got time to explain
     * @param g
     */
    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("HIGH SCORE", 160, 16);
        g.drawString("" + this.highScore, 160, 32);
        g.drawString("1UP", 48, 16);
        g.drawString("" + this.score, 48, 32);
        if (this.showPaths) {
            for (AbstractMonster m : this.monsters) {
                if (m.inJail()) {
                    continue;
                }
                for (Drawing t : m.getPathTiles()) {
                    t.draw(g);
                }
            }
        }
        for (Drawing d : this.drawings) {
            d.draw(g);
        }
        if (checkTimeout()) {
            drawTimeOutNumber(g, (this.timeout / 100 + 1));
        }
    }
}
