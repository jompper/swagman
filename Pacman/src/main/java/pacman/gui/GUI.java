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
 * Gui class / JFrame class
 * @author Joni
 */
public class GUI implements Runnable {


    /**
     * JFrame and panel to show GUI
     */
    private JFrame frame;
    private Panel panel;
    
    /**
     * KeyBoard listener for menu and game
     */
    private KeyboardListener keyListener;
    
    /**
     * Width and height of window
     */
    private int width;
    private int height;
    

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Build frame with components
     */
    @Override
    public void run() {
        frame = new JFrame("Pac-Man");

        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        
        
        createComponents(frame.getContentPane());

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Create components for the frame
     * @param container 
     */
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
