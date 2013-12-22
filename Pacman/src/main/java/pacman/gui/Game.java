/*
 * Pacman - Frame
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import pacman.domain.Board;

/**
 * Main class, that handles that game updates + redraws
 * itself. 
 * @author Joni
 */
public class Game  extends Timer implements ActionListener{

    private Board board;
    /**
     * TODO:
     * Change delay to 17ms after smooth move fix is made
     */
    public Game() {
        super(1000, null);
        this.addActionListener(this);
        setInitialDelay(2000);
        setDelay(50);
    }

    /**
     * Update game every n millisecond
     * 
     * TODO:
     * Start Menu
     * Pause Menu
     * 
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        board.move();
        board.repaint();
    }
    
    public void setBoard(Board b){
        this.board = b;
    }
    
    public Board getBoard(){
        return this.board;
    }
    
}
