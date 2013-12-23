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
import pacman.level.LevelOne;
/**
 *
 * @author Joni
 */
public class GUI implements Runnable {

    private Game game;
    private JFrame frame;
    private Board board;
    
    private KeyboardListener keyListener;
    
    private int width;
    private int height;
    

    public GUI(Game game, int width, int height) {
        this.game = game;
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
        this.keyListener = new KeyboardListener(this.board);
        MiceListener miceListener = new MiceListener(this.board);
        this.frame.addKeyListener(this.keyListener);
        this.frame.addMouseListener(miceListener);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Board getBoard() {
        return this.board;
    }
}
