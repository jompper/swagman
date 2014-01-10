/*
 * Pacman
 * Copyright (c) 2013 - 2014 Joni Salmi. All rights reserved.
 */

package pacman;

import javax.swing.SwingUtilities;
import pacman.gui.Game;
import pacman.gui.GUI;

/**
 * Interesting classes:
 * 
 * -logic
 * --Board
 * -domain
 * --AbstractMovingTile
 * --AbstractMonster
 * --AbstractEatable
 * -level
 * --LevelOne
 * -Util
 * --LevelBuilder
 */
public class App 
{
    /**
     * Start the game, run for your life
     * @param args nothing
     */
    public static void main( String[] args )
    {
        Game game = new Game();
        GUI gui = new GUI(455,600);
        SwingUtilities.invokeLater(gui);
        while (gui.getPanel() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        game.setPanel(gui.getPanel());
        game.start();
    }
}
