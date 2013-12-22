/*
 * Swagman - GUI
 * 17.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import pacman.domain.Board;
import pacman.level.LevelOne;
/**
 *
 * @author Joni
 */
public class GUI implements Runnable {

    private JFrame frame;
    
    private Board board;
    
    private int width;
    private int height;
    

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        frame = new JFrame("Pacman");

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        createComponents(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        this.board = new Board(new LevelOne());
        container.add(this.board);
        KeyboardListener keyListener = new KeyboardListener(this.board);
        this.frame.addKeyListener(keyListener);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Board getBoard() {
        return this.board;
    }
}
