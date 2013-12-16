/*
 * Swagman - Board
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package swagman.domain;

import java.util.ArrayList;

/**
 *
 * @author Joni
 */
public class Board {
    private ArrayList<Tileable> tiles;
    
    public Board(){
        this.tiles = new ArrayList<>();
    }
    
    public void addTile(Tileable tile){
        this.tiles.add(tile);
    }
    
    public void removeTile(Tileable tile){
        this.tiles.remove(tile);
    }
}
