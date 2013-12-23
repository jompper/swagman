/*
 * Swagman - SwagmanListener
 * 17.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import swagman.domain.Board;
import swagman.domain.Pacman;

/**
 *
 * @author Joni
 */
public class SwagboardListener implements KeyListener {

    private Pacman pacman;
    private Board gameBoard;

    public SwagboardListener(Pacman p, Board b) {
        this.pacman = p;
        this.gameBoard = b;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            this.pacman.setNewDirection(Direction.UP);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            this.pacman.setNewDirection(Direction.DOWN);
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            this.pacman.setNewDirection(Direction.LEFT);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            this.pacman.setNewDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
