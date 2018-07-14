package com.example.mydemo;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static org.junit.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        //        fun();
        //        foo();
        foo1();
    }

    private void fun() {
        Node node = createList();
        print(node);
        reverseList(node);
        print(node);
    }

    private Node createList() {
        Node head = new Node(-1, null);

        Node p = head;

        for (int i = 0; i < 10; i++) {
            Node node = new Node(i, null);
            p.setNext(node);
            p = node;
        }
        return head;
    }

    private void print(Node node) {
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

    private void reverseList(Node head) {
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

    private void foo() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("call");
                Thread.sleep(1000);
                System.out.println("sleep end");
                return "hello";
            }
        };
        FutureTask<String> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println("Start");
            System.out.println(task.get());
            System.out.println("end");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    void foo1() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(30000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        };
        thread.start();
        thread.interrupt();
        thread.join();
        System.out.println("线程已经退出!");
    }
}