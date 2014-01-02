/*
 * Pacman - Board
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import pacman.algorithm.BlinkyLogic;
import pacman.algorithm.ClydeLogic;
import pacman.algorithm.InkyLogic;
import pacman.algorithm.PinkyLogic;
import pacman.domain.Blinky;
import pacman.domain.Clyde;
import pacman.domain.Direction;
import pacman.domain.Eatable;
import pacman.domain.Inky;
import pacman.domain.Monster;
import pacman.domain.Pacman;
import pacman.domain.Pinky;
import pacman.level.Level;
import pacman.level.LevelOne;
import pacman.tile.AbstractMovingTile;
import pacman.tile.Drawing;
import pacman.tile.Moving;
import pacman.util.LevelBuilder;

/**
 *
 * @author Joni
 */
public class Board extends JPanel {

    private Pacman pacman;
    private Blinky blinky;
    private Pinky pinky;
    private Inky inky;
    private Clyde clyde;

    private Level level;
    private GameState gameState;
    private int timeout;

    private List<Drawing> drawings;
    private List<Moving> movings;
    private Eatable[][] eatables;
    private List<Moving> monsters;

    private int highScore;
    private int score;

    private int escapeX;
    private int escapeY;

    public Board(Level level) {
        this.setBackground(Color.BLACK);
        this.drawings = new ArrayList<>();
        this.movings = new ArrayList<>();
        this.monsters = new ArrayList<>();
        this.gameState = GameState.START;
        this.newGame(level);
        this.highScore = 0;
    }

    public final void newGame(Level level) {
        this.drawings.clear();
        this.movings.clear();
        this.monsters.clear();
        this.level = level;
        LevelBuilder lb = new LevelBuilder(level);
        this.drawings.addAll(lb.getDrawings());
        this.movings.addAll(lb.getMovings());
        this.monsters.addAll(lb.getMonsters());
        this.pacman = lb.getPacman();
        this.eatables = lb.getEatables();
        this.gameState = GameState.START;
        this.score = 0;
        this.timeout = 200;

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

        this.escapeX = lb.getEscapeX();
        this.escapeY = lb.getEscapeY();
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

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
        this.timeout = 300;
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
        checkMonsterIntersectPacman();
        checkMonsterJail();
    }

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

    private boolean canMove(Moving m, Direction d) {
        int nextX = m.getNextX(1, d);
        int nextY = m.getNextY(1, d);
        return (!this.level.isBlocked(nextX, nextY));
    }

    private void checkMonsterIntersectPacman() {
        for (Moving m : this.monsters) {
            if (m.getX() == this.pacman.getX() && m.getY() == this.pacman.getY()) {
                loseGame();
                return;
            }
        }
    }

    private boolean checkTimeout() {
        if (this.gameState != GameState.GAME) {
            return true;
        }
        if (this.timeout > 0) {
            this.timeout -= 2;
        }
        return this.timeout > 0;
    }

    private void checkMonsterJail() {
        if (this.pinky.inJail() && this.score > 300) {
            this.pinky.setX(this.escapeX);
            this.pinky.setY(this.escapeY);
            this.pinky.AIMove();
            this.pinky.setJail(false);
        }
        if (this.inky.inJail() && this.score > 600) {
            this.inky.setX(this.escapeX);
            this.inky.setY(this.escapeY);
            this.inky.AIMove();
            this.inky.setJail(false);
        }
        if (this.clyde.inJail() && this.score > 1200) {
            this.clyde.setX(this.escapeX);
            this.clyde.setY(this.escapeY);
            this.clyde.setJail(false);
            this.clyde.AIMove();
        }
    }

    private void loseGame() {
        if (this.score > this.highScore) {
            this.highScore = this.score;
        }
        newGame(new LevelOne());
        gameState = GameState.GAME;
    }

    private int checkEat(AbstractMovingTile amt) {
        Eatable e = this.eatables[amt.getY()][amt.getX()];
        int score = 0;
        if (e != null) {
            score = e.eat();
        }
        return score;
    }

    private boolean freeFromJail(Monster m, int score, int limit) {
        if (m.inJail() && score > limit) {
            m.setJail(false);
            return true;
        }
        return false;
    }

    private void fixOutOfBounds(Moving m, int maxW, int maxH) {
        m.setX(fixOutOfBounds(m.getX(), maxW));
        m.setY(fixOutOfBounds(m.getY(), maxH));
    }

    private int fixOutOfBounds(int pos, int max) {
        if (pos < 0) {
            return max;
        } else if (pos > max) {
            return 0;
        }
        return pos;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.gameState) {
            case GAME:
                paintGame(g);
                break;
            case START:
                paintMenu(g);
                break;
        }

    }

    private void paintGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("HIGH SCORE", 160, 16);
        g.drawString("" + this.highScore, 160, 32);
        g.drawString("1UP", 48, 16);
        g.drawString("" + this.score, 48, 32);
        for (Drawing d : this.drawings) {
            d.draw(g);
        }
        if (this.timeout > 0) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 200));
            g.drawString("" + (this.timeout / 100 + 1), 170, 340);
        }
    }

    private void paintMenu(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 100));
        g.drawString("Pac-Man", 20, 100);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawRect(130, 150, 200, 50);
        g.drawString("Start Game", 157, 185);
        g.drawRect(130, 250, 200, 50);
        g.drawString("Restart", 182, 285);
        g.drawRect(130, 350, 200, 50);
        g.drawString("Quit Game", 157, 385);
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
