package swagman;

import javax.swing.SwingUtilities;
import swagman.game.Swagman;
import swagman.gui.GUI;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Swagman swagger = new Swagman();
        GUI gui = new GUI(swagger, 27, 32, 16);
        SwingUtilities.invokeLater(gui);
        while (gui.getBoard() == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }
        swagger.setBoard(gui.getBoard());
        swagger.start();
    }
}
