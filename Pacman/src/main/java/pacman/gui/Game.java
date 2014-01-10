/*
 * Pacman - Frame
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * Main class, that handles that game updates + redraws itself.
 *
 * @author Joni
 */
public class Game extends Timer implements ActionListener {

    private Panel panel;

    public Game() {
        super(1000, null);
        this.addActionListener(this);
        setInitialDelay(300);
        setDelay(17);
    }

    
    /**
     * Update game every n millisecond
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        panel.update();
        panel.repaint();
    }

    public void setPanel(Panel b) {
        this.panel = b;
    }

    public Panel getBoard() {
        return this.panel;
    }
}
