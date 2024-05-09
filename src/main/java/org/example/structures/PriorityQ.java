package org.example.structures;

public class PriorityQ {

    private PriorityNode[] internalArray;
    private int maxSize;
    private int topIndex;

    public PriorityQ(int maxSize) {
        this.maxSize = maxSize;
        this.topIndex = -1;
        this.internalArray = new PriorityNode[maxSize];
    }

    public PriorityNode peekFirst() {
        return internalArray[0];
    }

    public PriorityNode peekLast() {
        return internalArray[topIndex];
    }

    public PriorityNode popFirst() {
        PriorityNode result = internalArray[0];
        this.internalArray = getShiftedDownInternalArray();
        --this.topIndex;
        return result;
    }

    public PriorityNode popLast() {
        return internalArray[topIndex--];
    }

    public boolean isEmpty() {
        return internalArray.length == 0;
    }

    public void display(String message) {
        System.out.println(message);
        for (int i=0; i < internalArray.length; i++) {
            if (internalArray[i] == null) {
                System.out.println("index: " + i + " null");
            } else {
                System.out.println("index: " + i + " " + internalArray[i].toString());
            }
        }
    }

    public void insert(PriorityNode newElement) {
        if (topIndex >= maxSize - 1) {
            createExpendedArr();
        }
        int currentIndex = this.topIndex;
        if (this.topIndex == -1) {
            this.internalArray[0] = newElement;
        } else {
            while (this.internalArray[currentIndex].getPriority() > newElement.getPriority()) {
                this.internalArray[currentIndex + 1] = this.internalArray[currentIndex];
                currentIndex--;
            }
            this.internalArray[++currentIndex] = newElement;
        }
        System.out.println("Index for new element: " + currentIndex);
        this.topIndex++;
    }

    private PriorityNode[] getShiftedDownInternalArray() {
        maxSize--;
        PriorityNode[] newArray = new PriorityNode[maxSize];
        for (int i = 0; i < maxSize; i++) {
            newArray[i] = internalArray[i+1];
        }
        return newArray;
    }

    private void createExpendedArr() {
        int newSize = (2*maxSize) - 1;
        System.out.println("New size is: " + newSize);
        PriorityNode[] newArr = moveElemsToNewArr(newSize);
        this.internalArray = newArr;
        maxSize = newSize;
    }

    private PriorityNode[] moveElemsToNewArr(int newSize) {
        PriorityNode[] newArr = new PriorityNode[newSize];
        for(int i = 0; i < this.internalArray.length; i++) {
            newArr[i] = internalArray[i];
        }
        return newArr;
    }

    public static void main(String[] args) {
        PriorityQ queue = new PriorityQ(3);
        for(int i = 0; i<4; i++) {
            queue.insert(new PriorityNode(2*i, 3*i));
        }
        queue.display("Initial queue after expanding");
        queue.insert(new PriorityNode(1, 2));
        queue.display("Initial queue after inserting with priority 1");
        queue.insert(new PriorityNode(5, 7));
        queue.display("Initial queue after inserting with priority 5");
        queue.insert(new PriorityNode(7, 10));
        queue.display("Initial queue after inserting with priority 7");
        queue.insert(new PriorityNode(6, 10));
        queue.display("Initial queue after inserting with priority 7");
    }
}
