/*
 * Swagman - MinHeap
 * 16.12.2013
 * Copyright (c) 2013 Joni Salmi. All rights reserved.
 */
package pacman.datastructure;

/**
 *
 * @author Joni
 */
public class MinHeap<E> {

    private int heap[];
    private int tableSize;
    private int heapSize;

    public MinHeap(){
        this(50);
    }
    
    public MinHeap(int size) {
        tableSize = size + 1;
        heapSize = 0;
        heap = new int[tableSize];
    }

    private void swap(int s, int d){
        int tmp = heap[s];
        heap[s] = heap[d];
        heap[d] = tmp;
    }
    
    public int size(){
        return heapSize;
    }
    
    public void add(int e) {
        heapSize++;
        if (tableSize < heapSize) {
            tableSize(tableSize * 2);
        }
        heap[heapSize] = e;
        int current = heapSize;
        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    public int removeMin(){
        int r = heap[1];
        heap[1] = heap[heapSize--];
        heapify(1);
        return r;
    }
    
    private void heapify(int i){
        int l = left(i);
        int r = right(i);
        if(r <= heapSize){
            int s = r;
            if(heap[l] < heap[r]){
                s = l;
            }
            if(heap[s] < heap[i]){
                swap(s,i);
                heapify(s);
            }
        }else if(l == heapSize && heap[l] < heap[i]){
            swap(l,i);
        }
    }
    
    private void tableSize(int size) {
        if (size > tableSize) {
            int[] newHeap = new int[size];
            for (int i = 1; i < tableSize; i++) {
                newHeap[i] = heap[i];
            }
            heap = newHeap;
        }
    }

    public boolean empty(){
        return heapSize == 0;
    }
    
    public int left(int i) {
        return 2 * i;
    }

    public int right(int i) {
        return 2 * i + 1;
    }

    public int parent(int i) {
        return i / 2;
    }
}
