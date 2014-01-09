/*
 * Pacman - MinHeapTest
 * 9.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.datastructure;

import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joni
 */
public class MinHeapTest {

    MinHeap<Integer> myHeap;

    public MinHeapTest() {
    }

    @Before
    public void setUp() {
        myHeap = new MinHeap<>(2);
    }

    @Test
    public void emptyHeapSizeIsZeroAndIsEmpty() {
        assertEquals(0, myHeap.size());
        assertTrue(myHeap.isEmpty());
    }

    @Test
    public void testAddOneHeapSizeOne() {
        myHeap.add(1);
        assertEquals(1, myHeap.size());
    }

    @Test
    public void testAddOneGetOne() {
        myHeap.add(2);
        int i = myHeap.remove();
        assertEquals(2, i);
    }

    @Test
    public void testAddTwoTakeTwoInOrder() {
        myHeap.add(5);
        myHeap.add(2);
        assertEquals(2, (int) myHeap.remove());
        assertEquals(5, (int) myHeap.remove());
    }

    @Test
    public void testAddThreeTakeTheeInOrder() {
        myHeap.add(10);
        myHeap.add(5);
        myHeap.add(12);
        assertEquals(5, (int) myHeap.remove());
        assertEquals(10, (int) myHeap.remove());
        assertEquals(12, (int) myHeap.remove());
        assertTrue(myHeap.isEmpty());
    }

    @Test
    public void testAddOneTakeOneAddOneTakeOne() {
        testAddOneGetOne();
        testAddOneGetOne();
    }

    @Test
    public void testRemoveFromEmptyReturnsNull() {
        assertNull(myHeap.remove());
    }

    @Test
    public void testFillHeapEnsureSize() {
        for (int i = 0; i < 10; i++) {
            myHeap.add(i);
        }
        assertEquals(10, myHeap.size());
    }
    
     @Test
    public void testFillWithOneToHundredReturnOneToHundred(){
        for(int i = 1; i <= 100; i++){
            myHeap.add(i);
        }
        for(int i = 1; i <= 100; i++){
            assertEquals(i, (int)myHeap.remove());
        }
    }
    
    @Test
    public void testFillWithHundredToOneReturnOneToHundred(){
        for(int i = 100; i > 0 ; i--){
            myHeap.add(i);
        }
        for(int i = 1; i <= 100; i++){
            assertEquals(i, (int)myHeap.remove());
        }
    }
    
    @Test
    public void testAddHundredQuoteRandomQuoteNumbersTakeHundredNumbers(){
        int[] numbers = new int[100];
        Random r = new Random();
        for(int i = 0; i < 100; i++){
            int rand = r.nextInt(99);
            numbers[rand]++;
            myHeap.add(rand);
        }
        assertEquals(100, myHeap.size());
        int current = 0;
        while(current < numbers.length){
            while(numbers[current]>0){
                assertEquals(current, (int)myHeap.remove());
                numbers[current]--;
            }
            current++;
        }
    }
    
    @Test
    public void testAddTenContainsTen(){
        myHeap.add(10);
        assertTrue(myHeap.contains(10));
        assertFalse(myHeap.contains(9));
    }
}
