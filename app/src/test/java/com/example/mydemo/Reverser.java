package com.example.mydemo;

/**
 * 链表逆序输出
 */
public class Reverser {
    public void reverseList(Node head) {
        if (head == null) {//错误
            return;
        }
        if (head.getNext() == null) {//空表
            return;
        }
        if (head.getNext().getNext() == null) {//长度为1
            return;
        }

        Node p = head.getNext();
        Node q = head.getNext().getNext();

        Node t;
        while (q != null) {
            t = q.getNext();
            q.setNext(p);
            p = q;
            q = t;
        }
        head.getNext().setNext(null);
        head.setNext(p);
    }

    public void print(Node node) {
        Node p = node;
        if (p == null) {
            return;
        }
        do {
            System.out.print(p.getNext().getData() + " ");
            p = p.getNext();
        } while (p.getNext() != null);
        System.out.println();
    }

    public Node createList() {
        Node head = new Node(-1, null);

        Node p = head;

        for (int i = 0; i < 10; i++) {
            Node node = new Node(i, null);
            p.setNext(node);
            p = node;
        }
        return head;
    }
}
