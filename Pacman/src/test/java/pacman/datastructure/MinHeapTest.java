/*
 * Swagman - MinHeap2Test
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */

package pacman.datastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class MinHeapTest {
    
    private MinHeap h;
    
    public MinHeapTest() {
    }
    
    @Before
    public void setUp() {
        h = new MinHeap(10);
    }
    
    @Test
    public void testHeapAddOneHeapSize(){
        h.add(1);
        assertEquals(1, h.size());
    }
    
    @Test
    public void testHeapAddTwoHeapSize(){
        h.add(1);
        h.add(2);
        assertEquals(2, h.size());
    }
    
    @Test
    public void testHeapAddOneValue(){
        h.add(1);
        assertEquals(1, h.removeMin());
    }
    
    @Test
    public void testHeapAddTwoValues(){
        h.add(2);
        h.add(1);
        assertEquals(1, h.removeMin());
        assertEquals(2, h.removeMin());
    }
    
    @Test
    public void testHeapLeft(){
        assertEquals(4, h.left(2));
    }

    @Test
    public void testHeapRight(){
        assertEquals(5, h.right(2));
    }
    
    @Test
    public void testHeapLeftParent(){
        assertEquals(2, h.parent(4));
    }
    
    @Test
    public void testHeapRightParent(){
        assertEquals(2, h.parent(5));
    }
    
}
