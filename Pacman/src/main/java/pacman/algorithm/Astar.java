/*
 * Pacman - Astar
 * 22.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import java.util.TreeSet;

/**
 *
 * @author Joni
 */
public class Astar {

    private int[][] map;
    
    public Astar(int[][] map, int sx, int sy, int dx, int dy){
        int height = map.length;
        int width = map[0].length;
        TreeSet<Integer> nodes = new TreeSet<>();
        int[][] toDestination = new int[height][width];
        int[][] fromSource = new int[height][width];
        int[][][] path = new int[height][width][2];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                fromSource[y][x] = Integer.MAX_VALUE;
                if(map[y][x]==0){
                    toDestination[y][x] = Math.abs(y-dy) + Math.abs(x-dx);
                }else{
                    toDestination[y][x] = Integer.MAX_VALUE;
                }
                path[y][x][0] = Integer.MAX_VALUE;
                path[y][x][1] = Integer.MAX_VALUE;
            }
        }
        fromSource[sy][sx] = 0;
        while(!nodes.isEmpty()){
            
        }
    }
    
}
