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
 *
 * @author Joni
 */
public class Frame  extends Timer implements ActionListener{

    private Board board;
    
    public Frame() {
        super(1000, null);
        this.addActionListener(this);
        setInitialDelay(2000);
        setDelay(50);
    }

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
