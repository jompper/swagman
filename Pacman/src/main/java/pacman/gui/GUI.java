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
    private Panel panel;
    
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

        frame.setResizable(false);
        
        
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void createComponents(Container container) {
        this.panel = new Panel(new LevelOne());
        container.add(this.panel);
        this.keyListener = new KeyboardListener(this.panel);
        MiceListener miceListener = new MiceListener(this.panel);
        this.frame.addKeyListener(this.keyListener);
        this.frame.addMouseListener(miceListener);
    }

    public JFrame getFrame() {
        return frame;
    }

    public Panel getPanel() {
        return this.panel;
    }
}
