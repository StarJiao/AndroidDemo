package com.example.mydemo;

public class Node {
    private int data = -1;
    private Node next;

    public Node(int i, Node o) {
        this.data = i;
        this.next = o;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
