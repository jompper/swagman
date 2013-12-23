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
import pacman.domain.Eatable;
import pacman.domain.Pacman;
import pacman.level.Level;
import pacman.tile.Drawing;
import pacman.tile.Moving;
import pacman.util.LevelBuilder;

/**
 *
 * @author Joni
 */
public class Board extends JPanel {

    private Pacman pacman;

    private Level level;
    private GameState gameState;
    private int timeout;

    private List<Drawing> drawings;
    private List<Moving> movings;
    private Eatable[][] eatables;
    
    private int highScore;
    private int score;

    public Board(Level level) {
        this.setBackground(Color.BLACK);
        this.drawings = new ArrayList<>();
        this.movings = new ArrayList<>();
        this.gameState = GameState.START;
        this.newGame(level);
        this.highScore = 0;
    }
    
    public void newGame(Level level){
        this.drawings.clear();
        this.movings.clear();
        this.level = level;
        LevelBuilder lb = new LevelBuilder(level);
        this.drawings.addAll(lb.getDrawings());
        this.pacman = lb.getPacman();
        this.movings.add(this.pacman);
        this.eatables = lb.getEatables();
        this.gameState = GameState.START;
        this.score = 0;
        this.timeout = 200;
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
     * if Pacman is in a tile that contains eatable object.
     *
     * TODO: Add true boundary checks.
     *
     * Make interface Eating, make Pacman implement that interface. Then loop
     * all Eating object and check if their current position has something to be
     * eaten. Now Pacman should implement Eating and Eatable interfaces and
     * Ghosts should implement Eating interface with condition Pacman.
     */
    public void move() {
        if (this.gameState != GameState.GAME) {
            return;
        }
        if(this.timeout > 0){
            this.timeout -= 2;
            return;
        }
        int maxW = this.level.getWidth() - 1;
        int maxH = this.level.getHeight() - 1;
        for (Moving m : this.movings) {
            int nextX = m.getNextX(1, m.getDirection());
            int nextY = m.getNextY(1, m.getDirection());
            int nextXCD = m.getNextX(1, m.getChangeDirection());
            int nextYCD = m.getNextY(1, m.getChangeDirection());

            if (m.getDirection() != m.getChangeDirection() && !this.level.isBlocked(nextXCD, nextYCD)) {
                m.setDirection(m.getChangeDirection());
                m.move();
            } else if (!this.level.isBlocked(nextX, nextY)) {
                m.move();
            } else {
                m.moveLocation();
            }

            int mX = m.getX();
            int mY = m.getY();

            if (mX < 0) {
                m.setX(maxW);
            } else if (mX > maxW) {
                m.setX(0);
            }
            if (mY < 0) {
                m.setY(maxH);
            } else if (mY > maxH) {
                m.setY(0);
            }
        }
        Eatable e = this.eatables[this.pacman.getY()][this.pacman.getX()];
        if (e != null) {
            this.score += e.eat();
            if(this.score > this.highScore){
                this.highScore = score;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.gameState) {
            case GAME:
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 16));
                g.drawString("HIGH SCORE", 160, 16);
                g.drawString("" + this.highScore, 160, 32);
                g.drawString("1 UP", 48, 16);
                g.drawString("" + this.score, 48, 32);
                for (Drawing d : this.drawings) {
                    d.draw(g);
                }
                if(this.timeout > 0){
                    g.setColor(Color.YELLOW);
                    g.setFont(new Font("Arial",Font.BOLD, 200));
                    g.drawString(""+(this.timeout/100+1),170,340);
                }
                break;
            case START:
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
                break;
        }

    }
    
    public GameState getGameState(){
        return this.gameState;
    }
}
