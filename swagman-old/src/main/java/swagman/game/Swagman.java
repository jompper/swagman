/*
 * Swagman - Swagman
 * 17.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package swagman.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import swagman.domain.Board;

/**
 *
 * @author Joni
 */
public class Swagman extends Timer implements ActionListener{

    private Board board;
    
    public Swagman() {
        super(1000, null);
        this.addActionListener(this);
        setInitialDelay(2000);
        setDelay(17);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.move();
        board.repaint();
        //System.out.println("X: " + board.getPacman().getX() + ", Y: " + board.getPacman().getY() + ", D: " + board.getPacman().getDistance());
    }
    
    public void setBoard(Board b){
        this.board = b;
    }
    
    public Board getBoard(){
        return this.board;
    }
    
}