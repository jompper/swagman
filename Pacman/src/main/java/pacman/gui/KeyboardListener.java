/*
 * Pacman - KeyboardListener
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pacman.domain.Direction;
import pacman.domain.Pacman;

/**
 * Listens to keyboard
 *
 * TODO: WASD Pause game
 *
 * @author Joni
 */
public class KeyboardListener implements KeyListener {

    private Board board;

    public KeyboardListener(Board b) {
        this.board = b;
    }

    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Sets Pacmans change direction accoring to key press
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (this.board.getGameState()) {
            case START:
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    this.board.setGameState(GameState.GAME);
                }
                break;
            case GAME:
                Pacman p = this.board.getPacman();
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    p.setChangeDirection(Direction.UP);
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    p.setChangeDirection(Direction.DOWN);
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    p.setChangeDirection(Direction.LEFT);
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    p.setChangeDirection(Direction.RIGHT);
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    this.board.setGameState(GameState.START);
                }
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
