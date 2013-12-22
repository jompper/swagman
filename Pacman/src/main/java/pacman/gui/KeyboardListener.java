/*
 * Pacman - KeyboardListener
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pacman.domain.Board;
import pacman.domain.Direction;
import pacman.domain.Pacman;

/**
 *
 * @author Joni
 */
public class KeyboardListener implements KeyListener {
    private Pacman pacman;
    private Board gameBoard;

    public KeyboardListener(Board b) {
        this.pacman = b.getPacman();
        this.gameBoard = b;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.pacman.setChangeDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.pacman.setChangeDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.pacman.setChangeDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.pacman.setChangeDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
