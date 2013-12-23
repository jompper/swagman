/*
 * Pacman - MouseListener
 * 23.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import pacman.level.LevelOne;

/**
 *
 * @author Joni
 */
public class MiceListener implements MouseListener {

    private Board board;

    public MiceListener(Board b) {
        this.board = b;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        switch (this.board.getGameState()) {
            case START:
                if (mX >= 137 && mX <= 337) {
                    if (mY >= 180 && mY <= 230) {
                        this.board.setGameState(GameState.GAME);
                    } else if (mY >= 280 && mY <= 330) {
                        this.board.newGame(new LevelOne());
                        this.board.setGameState(GameState.GAME);
                    } else if(mY >= 380 && mY <= 430){
                        System.exit(0);
                    }
                }
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
