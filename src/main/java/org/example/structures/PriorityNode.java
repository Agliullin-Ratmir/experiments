package org.example.structures;

public class PriorityNode {

    private int priority;
    private int value;

    public PriorityNode(int priority, int value) {
        this.priority = priority;
        this.value = value;
    }

    @Override
    public String toString() {
        return "PriorityNode{" +
                "priority= " + priority +
                ", value= " + value +
                '}';
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
