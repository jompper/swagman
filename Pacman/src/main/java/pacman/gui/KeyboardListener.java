/*
 * Pacman - KeyboardListener
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pacman.domain.Direction;
import pacman.sprite.Pacman;
import pacman.logic.Board;

/**
 * Listens to keyboard
 *
 * @author Joni
 */
public class KeyboardListener implements KeyListener {

    private Panel panel;
    private Board board;

    public KeyboardListener(Panel p) {
        this.panel = p;
        this.board = p.getBoard();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Sets Pacmans change direction according to key press
     * 
     * Pause/Resume with ESC
     * 
     * Toggle Paths with CTRL or P
     * Toggle God Mode with ALT or G
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (panel.getGameState()) {
            case MENU:
                if (e.getKeyCode() == KeyEvent.VK_ENTER || e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    panel.setGameState(GameState.GAME);
                }
                break;
            case GAME:
                Pacman p = board.getPacman();
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
                    case KeyEvent.VK_CONTROL:
                    case KeyEvent.VK_P:
                        board.toggleShowPaths();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        panel.setGameState(GameState.MENU);
                        break;
                    case KeyEvent.VK_ALT:
                    case KeyEvent.VK_G:
                        board.toggleDebug();
                        break;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
