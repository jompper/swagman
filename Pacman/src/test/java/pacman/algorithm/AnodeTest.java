/*
 * Pacman - AnodeTest
 * 5.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.algorithm;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import pacman.domain.Direction;

/**
 *
 * @author Joni
 */
public class AnodeTest {

    private Anode anode;

    @Before
    public void setUp() {
        this.anode = new Anode(10, 20, 5, 3);
    }

    @Test
    public void testSetUpData() {
        checkBasicState(10, 20, 5, 3);
    }
    
    @Test
    public void testSetNewStartValue(){
        anode.setStart(10);
        checkBasicState(10, 20, 10, 3);
    }
    
    @Test
    public void testSetFromLocation(){
        anode.setFrom(10, 21);
        checkAdvancedState(10,20,5,3,false,10,21,null);
    }
    
    @Test
    public void testSetFromDirection(){
        anode.setFromDirection(Direction.UP);
        checkAdvancedState(10,20,5,3,false,0,0,Direction.UP);
    }
    
    @Test
    public void testUseAnode(){
        anode.use();
        checkAdvancedState(10,20,5,3,true,0,0,null);
    }
    
    @Test
    public void testCompareLower(){
        Anode t1 = new Anode(0,0,0,2);
        Anode t2 = new Anode(0,0,2,2);
        assertEquals(-2, t1.compareTo(t2));
    }
    
    @Test
    public void testCompareHigher(){
        Anode t1 = new Anode(0,0,0,2);
        Anode t2 = new Anode(0,0,2,2);
        assertEquals(2, t2.compareTo(t1));
    }
    
    private void checkBasicState(int x, int y, int start, int end){
        assertEquals(x, anode.getX());
        assertEquals(y, anode.getY());
        assertEquals(start, anode.getStart());
        assertEquals(end, anode.getEnd());
    }
    
    private void checkAdvancedState(int x, int y, int start, int end, boolean used, int fromX, int fromY, Direction fromDirection){
        checkBasicState(x, y, start, end);
        assertEquals(used, anode.isUsed());
        assertEquals(fromX, anode.getFromX());
        assertEquals(fromY, anode.getFromY());
        assertEquals(fromDirection, anode.getFromDirection());
    }
}
