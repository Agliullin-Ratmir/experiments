package org.example.structures;


import java.util.random.RandomGenerator;


public class Stack {

    private int[] internalArray;
    private int maxSize;
    private int topIndex;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        this.internalArray = new int[this.maxSize];
        this.topIndex = -1;
    }

    public int pop() {
        return internalArray[topIndex--];
    }

    public void push(int newElement) {
        if (topIndex >= maxSize - 1) {
            createExpendedArr();
        }
        internalArray[++topIndex] = newElement;
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

    public int peek() {
        return internalArray[topIndex];
    }

    public boolean isEmpty() {
        return internalArray.length == 0;
    }

    public void display() {
        for (int i=0; i < internalArray.length; i++) {
            System.out.println("index: " + i + " " + internalArray[i]);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(3);
        for (int i = 0; i<4; i++) {
            stack.push(RandomGenerator.getDefault().nextInt());
        }
        System.out.println("Top: " + stack.pop());
        stack.display();
        for (int i = 0; i<7; i++) {
            stack.push(RandomGenerator.getDefault().nextInt());
        }
        stack.display();
    }
}
