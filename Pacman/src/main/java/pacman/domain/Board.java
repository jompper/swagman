/*
 * Pacman - Board
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
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

    private List<Drawing> drawings;
    private List<Moving> movings;
    private Eatable[][] eatables;

    public Board(Level level) {
        this.setBackground(Color.BLACK);
        this.drawings = new ArrayList<>();
        this.movings = new ArrayList<>();
        this.level = level;
        LevelBuilder lb = new LevelBuilder(level);
        this.drawings.addAll(lb.getDrawings());
        this.pacman = lb.getPacman();
        this.movings.add(this.pacman);
        this.eatables = lb.getEatables();
    }

    /**
     * Add new drawing object d to game
     * @param d
     */
    public void addDrawing(Drawing d) {
        this.drawings.add(d);
    }

    /**
     * Add new moving object m to game
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
    public Level getLevel(){
        return this.level;
    }

    /**
     * Move all moving objects on the game
     * After objects have been moven, checks
     * if Pacman is in a tile that contains
     * eatable object.
     * 
     * TODO: 
     * Add true boundary checks. 
     * 
     * Make interface Eating, make
     * Pacman implement that interface. Then
     * loop all Eating object and check if
     * their current position has something
     * to be eaten. Now Pacman should implement
     * Eating and Eatable interfaces and Ghosts
     * should implement Eating interface with
     * condition Pacman.
     */
    public void move() {
        for (Moving m : this.movings) {
            if(m.getDirection() != m.getChangeDirection() && !this.level.isBlocked(m.getNextX(1, m.getChangeDirection()), m.getNextY(1, m.getChangeDirection()))){
                m.setDirection(m.getChangeDirection());
                m.move();
            } else if(!this.level.isBlocked(m.getNextX(1, m.getDirection()), m.getNextY(1, m.getDirection()))){
                m.move();
            }
        }
        Eatable e = this.eatables[this.pacman.getY()][this.pacman.getX()];
        if(e != null){
            e.eat();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawing d : this.drawings) {
            d.draw(g);
        }
    }
}
