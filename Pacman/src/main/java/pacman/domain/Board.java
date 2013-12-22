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

    public void addDrawing(Drawing d) {
        this.drawings.add(d);
    }

    public void addMoving(Moving m) {
        this.movings.add(m);
    }

    public Pacman getPacman() {
        return this.pacman;
    }
    
    public Level getLevel(){
        return this.level;
    }

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
