/*
 * Swagman - GUI
 * 17.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package swagman.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import swagman.domain.Board;
import swagman.game.SwagboardListener;
import swagman.game.Swagman;
import swagman.level.Level1;

/**
 *
 * @author Joni
 */
public class GUI implements Runnable {

    private JFrame frame;
    private Board gameBoard;

    private Swagman swagman;
    
    private int width;
    private int height;
    private int scale;

    public GUI(Swagman swagman, int width, int height, int scale) {
        this.swagman = swagman;
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    @Override
    public void run() {
        frame = new JFrame("Swagman");

        frame.setPreferredSize(new Dimension(width * scale + 50, height * scale + 50));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        this.gameBoard = new Level1(this.scale);
        container.add(gameBoard);
        SwagboardListener keyListener = new SwagboardListener(gameBoard.getPacman(), gameBoard);
        this.frame.addKeyListener(keyListener);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Board getBoard() {
        return this.gameBoard;
    }
}
