/*
 * Pacman - MinHeap
 * 9.1.2014
 * Copyright (c) 2014 Joni Salmi. All rights reserved.
 */
package pacman.datastructure;

import java.util.Comparator;

/**
 * Min Heap, always returns smallest value from the heap.
 * Nodes have to implement Comparable interface to self.
 * 
 * @author Joni
 * @param <T>
 */
public class MinHeap<T extends Comparable<T>> implements Comparator<T> {

    private T[] heapTable;
    private int heapSize;

    /**
     * Contruct heap with capacity for 1000 nodes
     */
    public MinHeap() {
        this(1000);
    }

    /**
     * Construct Heap, with starting heapSize of size
     * @param size 
     */
    public MinHeap(int size) {
        heapTable = (T[]) new Comparable[size + 1];
        heapSize = 0;
    }

    /**
     * Make sure there's room for new node,
     * add node to the heap (increment heapSize
     * by one) and deepify from there.
     * 
     * @param t 
     */
    public void add(T t) {
        ensureCapacity();
        heapTable[++heapSize] = t;
        deepify(heapSize);
    }

    /**
     * Return the smallest node (index one), move last node to
     * index one and heapify from there to ensure order
     * 
     * @return the smallest node, and null if heap is empty
     */
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        T t = heapTable[1];
        heapTable[1] = heapTable[heapSize--];
        heapify(1);
        return t;
    }


    /**
     * @param i
     * @return index of parent for node i
     * return self if root
     */
    private int parent(int i) {
        if (i == 1) {
            return 1;
        }
        return i / 2;
    }

    /**
     * @param i
     * @return index for left child for node i
     */
    private int left(int i) {
        return i * 2;
    }

    /**
     * @param i
     * @return index of right child for node i 
     */
    private int right(int i) {
        return i * 2 + 1;
    }

    /**
     * Swap two indexes
     * @param s
     * @param d 
     */
    private void swap(int s, int d) {
        T tmp = heapTable[s];
        heapTable[s] = heapTable[d];
        heapTable[d] = tmp;
    }

    /**
     * Basic heapify operation, checks left and right child, of 
     * the index node, selects the smaller one and checks against 
     * current index (their parent). If child is smaller swap 
     * places and heapify from the new current index
     * 
     * @param i 
     */
    private void heapify(int i) {
        int leftChild = left(i);
        int rightChild = right(i);
        if (rightChild <= heapSize) {
            int smaller = isSmaller(heapTable[leftChild], heapTable[rightChild]) ? leftChild : rightChild;
            if (isSmaller(heapTable[smaller], heapTable[i])) {
                swap(smaller, i);
                heapify(smaller);
            }
        } else if (leftChild == heapSize && isSmaller(heapTable[leftChild], heapTable[i])) {
            swap(leftChild, i);
        }
    }

    
    /**
     * Deepify from index i. Swap places with parent while smaller
     * @param i 
     */
    private void deepify(int i) {
        while (isSmaller(heapTable[i], heapTable[parent(i)])) {
            swap(i, parent(i));
            i = parent(i);
        }
    }
    
    /**
     * Makes sure that the capacity meets the requirements
     * Doubles the capacity if heap is full
     */
    private void ensureCapacity() {
        if (heapSize >= heapTable.length - 1) {
            T[] tmp = (T[]) new Comparable[heapTable.length * 2];
            for (int i = 1; i <= heapSize; i++) {
                tmp[i] = heapTable[i];
            }
            heapTable = tmp;
        }
    }

    /**
     * @return heapSize = how many objects are included in heap
     */
    public int size() {
        return heapSize;
    }

    /**
     * @return true if size is zero
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 
     * @param t
     * @return is element t in heap
     */
    public boolean contains(T t){
        for(int i = 1; i <= heapSize; i++){
            if(t == heapTable[i]){
                return true;
            }
        }
        return false;
    }
    
    private boolean isSmaller(T smaller, T bigger){
        return compare(smaller, bigger) < 0;
    }
    
    /**
     * Compare two nodes
     * @param o1
     * @param o2
     * @return < 0 if o1 smaller than o2, else > 0
     */
    @Override
    public int compare(T o1, T o2) {
        return o1.compareTo(o2);
    }

}
