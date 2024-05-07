package org.example.structures;

import java.util.random.RandomGenerator;

public class UniversalQ {

    private int[] internalArray;
    private int maxSize;
    private int topIndex;

    public UniversalQ(int maxSize) {
        this.maxSize = maxSize;
        this.topIndex = -1;
        internalArray = new int[maxSize];
    }

    public int peekFirst() {
        return internalArray[0];
    }

    public int peekLast() {
        return internalArray[topIndex];
    }

    public int popFirst() {
        int result = internalArray[0];
        this.internalArray = getShiftedDownInternalArray();
        --this.topIndex;
        return result;
    }

    public int popLast() {
        return internalArray[topIndex--];
    }

    public boolean isEmpty() {
        return internalArray.length == 0;
    }

    public void display(String message) {
        System.out.println(message);
        for (int i=0; i < internalArray.length; i++) {
            System.out.println("index: " + i + " " + internalArray[i]);
        }
    }

    public void insertLast(int newElement) {
        if (topIndex >= maxSize - 1) {
            createExpendedArr();
        }
        internalArray[++topIndex] = newElement;
    }

    public void insertFirst(int newElement) {
        this.internalArray = getShiftedUpInternalArray();
        this.internalArray[0] = newElement;
    }

    private int[] getShiftedDownInternalArray() {
        maxSize--;
        int[] newArray = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            newArray[i] = internalArray[i+1];
        }
        return newArray;
    }

    private int[] getShiftedUpInternalArray() {
        maxSize++;
        int[] newArray = new int[maxSize];
        for (int i = 0; i < internalArray.length; i++) {
            newArray[i+1] = internalArray[i];
        }
        return newArray;
    }

    private void createExpendedArr() {
        int newSize = (2*maxSize) - 1;
        System.out.println("New size is: " + newSize);
        int[] newArr = moveElemsToNewArr(newSize);
        this.internalArray = newArr;
        maxSize = newSize;
    }

    private int[] moveElemsToNewArr(int newSize) {
        int[] newArr = new int[newSize];
        for(int i = 0; i < this.internalArray.length; i++) {
            newArr[i] = internalArray[i];
        }
        return newArr;
    }

    public static void main(String[] args) {
        UniversalQ queue = new UniversalQ(3);
        queue.display("Original queue");
        for (int i = 0; i<4; i++) {
            queue.insertLast(RandomGenerator.getDefault().nextInt());
        }
        queue.display("Queue after increasing");
        queue.popFirst();
        queue.display("After taking first item");
        System.out.println("Top index: " + queue.topIndex);
        queue.insertLast(20);
        queue.display("After inserting last");
        System.out.println("Top index: " + queue.topIndex);
        queue.popLast();
        queue.display("After taking last item");
        System.out.println("Top index: " + queue.topIndex);
        queue.insertFirst(10);
        queue.display("After inserting first");
        System.out.println("Top index: " + queue.topIndex);
    }
}
