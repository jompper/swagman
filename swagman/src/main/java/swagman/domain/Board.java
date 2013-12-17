/*
 * Swagman - Board
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.domain;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import swagman.map.TileMap;

/**
 *
 * @author Joni
 */
public abstract class Board extends JPanel {

    private Pacman pacman;
    private TileMap tileMap;

    private int width;
    private int height;
    private int scale;

    private ArrayList<Drawable> drawables;
    private ArrayList<Moveable> moveables;

    public Board(int scale) {
        this.drawables = new ArrayList<>();
        this.moveables = new ArrayList<>();
        this.scale = scale;
        setBackground(Color.BLACK);
    }

    public void setPacman(Pacman pacman) {
        this.pacman = pacman;
        this.drawables.add(pacman);
        this.moveables.add(pacman);
    }

    public void setTileMap(TileMap tileMap) {
        this.tileMap = tileMap;
        char[][] tm = tileMap.getMap();
        this.height = tm.length;
        this.width = tm[0].length;
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(tm[y][x] == '#'){
                    this.addDrawable(new Wall(x, y));
                }
            }
        }
    }

    public void addDrawable(Drawable d) {
        this.drawables.add(d);
    }

    public void addMoveable(Moveable m) {
        this.moveables.add(m);
    }

    public void removeDrawable(Drawable d) {
        this.drawables.remove(d);
    }

    public void removeMoveable(Moveable m) {
        this.moveables.remove(m);
    }

    public void move() {
        for (Moveable m : this.moveables) {
            if (this.tileMap.canMove(m.getNextX(1, m.getNewDirection()), m.getNextY(1, m.getNewDirection()))){
                m.setDirection(m.getNewDirection());
                m.move();
            }else if (this.tileMap.canMove(m.getNextX(), m.getNextY())) {
                m.move();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Drawable d : this.drawables) {
            d.draw(g, this.scale);
        }
    }

    public Pacman getPacman() {
        return this.pacman;
    }

    public TileMap getTileMap() {
        return this.tileMap;
    }
}
