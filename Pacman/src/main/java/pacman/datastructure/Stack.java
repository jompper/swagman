/*
 * Pacman - Stack
 * 9.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.datastructure;

/**
 * Basic Java Stack, LIFO
 * Last-In-First-Out
 * 
 * @author Joni
 */
public class Stack<T> {

    private T[] stackTable;
    private int stackSize;

    public Stack() {
        this(1000);
    }

    /**
     * Construct Stack with capacity of size
     * @param size 
     */
    public Stack(int size) {
        stackTable = (T[]) new Object[size];
        stackSize = -1;
    }

    /**
     * Add node t to top of stack
     *
     * @param t
     */
    public void push(T t) {
        ensureCapacity();
        stackTable[++stackSize] = t;
    }

    /**
     * Last in first out
     *
     * @return node from the top
     */
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return stackTable[stackSize--];
    }

    /**
     * @param i
     * @return node from index i, null if outside range
     */
    public T get(int i) {
        if (i < 0 || i > stackSize) {
            return null;
        }
        return stackTable[i];
    }

    /**
     * Makes sure that the capacity meets the requirements Doubles the capacity
     * if stack is full
     */
    private void ensureCapacity() {
        if (stackSize >= stackTable.length - 1) {
            T[] tmp = (T[]) new Object[stackTable.length * 2];
            for (int i = 0; i <= stackSize; i++) {
                tmp[i] = stackTable[i];
            }
            stackTable = tmp;
        }
    }

    /**
     * @return is stack empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     *
     * @return stack size (node count)
     */
    public int size() {
        return stackSize + 1;
    }
}
