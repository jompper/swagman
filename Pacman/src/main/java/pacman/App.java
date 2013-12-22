package pacman;

import javax.swing.SwingUtilities;
import pacman.gui.Game;
import pacman.gui.GUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Game frame = new Game();
        GUI gui = new GUI(465,600);
        SwingUtilities.invokeLater(gui);
        while (gui.getBoard() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        frame.setBoard(gui.getBoard());
        frame.start();
    }
}
