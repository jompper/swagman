/*
 * Pacman - Board
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import pacman.level.Level;
import pacman.logic.Board;

/**
 *
 * @author Joni
 */
public class Panel extends JPanel {

    private GameState gameState;
    private Board board;
    private MainMenu menu;
    
    public Panel(Level level) {
        this.setBackground(Color.BLACK);
        this.board = new Board(level);
        this.menu = new MainMenu();
        this.gameState = GameState.START;
    }
    
    public void update(){
        switch(this.gameState){
            case GAME:
                board.move();
                break;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.gameState) {
            case GAME:
                board.draw(g);
                break;
            case START:
                menu.draw(g);
                break;
        }
    }
    
    public GameState getGameState(){
        return this.gameState;
    }
    
    public void setGameState(GameState gs){
        this.gameState = gs;
    }
    
    public Board getBoard(){
        return this.board;
    }
    
}
