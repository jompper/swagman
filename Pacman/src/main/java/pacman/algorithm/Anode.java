/*
 * Pacman - Anode
 * 29.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.algorithm;

import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public class Anode implements Comparable<Anode>{
    
    private int x;
    private int y;
    
    private int fx;
    private int fy;
    
    private Direction fromD;
    
    private int start;
    private int end;
    
    private boolean used;
    
    public Anode(int x, int y, int start, int end){
        this.start = start;
        this.end = end;
        this.x = x;
        this.y = y;
        this.used = false;
    }

    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public int getFromX(){
        return this.fx;
    }
    
    public int getFromY(){
        return this.fy;
    }
    
    public void setStart(int start){
        this.start = start;
    }
    
    public void setFrom(int x, int y){
        this.fx = x;
        this.fy = y;
    }
    
    public void setFromDirection(Direction d){
        this.fromD = d;
    }
    
    public Direction getFromDirection(){
        return this.fromD;
    }
    
    public int getStart(){
        return this.start;
    }
    
    public int getEnd(){
        return this.end;
    }
    
    public void use(){
        this.used = true;
    }
    
    public boolean isUsed(){
        return this.used;
    }
    
    @Override
    public int compareTo(Anode o) {
        return (this.getStart() + this.getEnd()) - (o.getStart() + o.getEnd());
    }
}
