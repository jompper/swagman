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
 * JPanel which handles game and menu drawing to itself
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
        this.gameState = GameState.MENU;
    }
    
    /**
     * Update game
     */
    public void update(){
        switch(this.gameState){
            case GAME:
                board.move();
                break;
        }
    }

    /**
     * Paint game
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (this.gameState) {
            case GAME:
                board.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
        }
    }
    
    /**
     * @return Current game state
     */
    public GameState getGameState(){
        return this.gameState;
    }
    
    /**
     * Set new game state gs
     * @param gs 
     */
    public void setGameState(GameState gs){
        this.gameState = gs;
    }
    
    /**
     * @return Board (Basicly the whole game)
     */
    public Board getBoard(){
        return this.board;
    }
    
}
