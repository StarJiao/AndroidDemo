package com.example.mydemo;

import org.junit.Test;

import java.util.Arrays;
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
//        foo1();
//        sorter();
    }

    private void sorter() {
        QuickSorter<Integer> quickSorter = new QuickSorter<>();
        Integer numbers[] = new Integer[]{6, 9, 1, 0, 3, 2, 4, 8, 5, 7};
        quickSorter.sort(numbers, 0, numbers.length - 1);
        System.out.println(Arrays.toString(numbers));
    }

    private void fun() {
        Reverser reverser = new Reverser();
        Node node = reverser.createList();
        reverser.print(node);
        reverser.reverseList(node);
        reverser.print(node);
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