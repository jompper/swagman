/*
 * Pacman - MouseListener
 * 23.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import static pacman.gui.GameState.MENU;
import pacman.logic.Board;

/**
 * Mice Listener for game. Only used in menu for now at least.
 *
 * @author Joni
 */
public class MiceListener implements MouseListener {

    private Panel panel;
    private Board board;

    public MiceListener(Panel p) {
        this.panel = p;
        this.board = p.getBoard();
    }

    /**
     * If mouse was clicked check if buttons were pressed and do the actions.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
        switch (panel.getGameState()) {
            case MENU:
                if (mX >= 137 && mX <= 337) {
                    if (mY >= 180 && mY <= 230) {
                        panel.setGameState(GameState.GAME);
                    } else if (mY >= 280 && mY <= 330) {
                        board.loseGame();
                        panel.setGameState(GameState.GAME);
                    } else if (mY >= 380 && mY <= 430) {
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
