/*
 * Pacman - StackTest
 * 9.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */

package pacman.datastructure;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class StackTest {
    
    private Stack<Integer> stack;
    
    public StackTest() {
    }
    
    @Before
    public void setUp() {
        stack = new Stack<>(2);
    }

    @Test
    public void testStackIsEmptyOnCreate(){
        assertTrue(stack.isEmpty());
    }
    
    @Test
    public void testPushOneSizeOne(){
        stack.push(1);
        assertEquals(1, stack.size());
    }
    
    @Test
    public void testPushOnePopOneSameValue(){
        stack.push(10);
        assertEquals(10, (int)stack.pop());
    }
    
    @Test
    public void testPushTwoGetTwoLastFirst(){
        stack.push(5);
        stack.push(10);
        assertEquals(10, (int)stack.pop());
        assertEquals(5, (int)stack.pop());
    }
    
    @Test
    public void testPushTwoPopOnePushOnePopTwo(){
        stack.push(5);
        stack.push(10);
        assertEquals(10, (int)stack.pop());
        stack.push(4);
        assertEquals(4, (int)stack.pop());
        assertEquals(5, (int)stack.pop());
    }
    
    @Test
    public void testPushHunderPopHundred(){
        for(int i = 1; i <= 100; i++){
            stack.push(i);
        }
        assertEquals(100, stack.size());
        for(int i = 100; i > 0; i--){
            assertEquals(i, (int)stack.pop());
        }
    }
    
    @Test
    public void testEmptyPopNull(){
        assertNull(stack.pop());
    }
    
    @Test
    public void testGetIndexBelowAllowed(){
        assertNull(stack.get(-1));
    }
    
    @Test
    public void testGetIndexAboveAllowed(){
        assertNull(stack.get(0));
        assertNull(stack.get(1));
    }
    
    @Test
    public void testSecondFromStack(){
        stack.push(5);
        stack.push(10);
        stack.push(15);
        stack.push(20);
        assertEquals(10, (int)stack.get(1));
    }

}
