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
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        p.setChangeDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        p.setChangeDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        p.setChangeDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        p.setChangeDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_ESCAPE:
                        this.board.setGameState(GameState.START);
                        break;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
