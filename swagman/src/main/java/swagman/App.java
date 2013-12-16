package swagman;

import swagman.map.Level1;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Level1 l = new Level1();
        char[][] map = l.getMap();
        int height = map.length;
        int width = map[0].length;
        for(int r = 0; r < height; r++){
            for(int s = 0; s < width; s++){
                System.out.print("" + map[r][s]);
            }
            System.out.println();
        }
    }
}
