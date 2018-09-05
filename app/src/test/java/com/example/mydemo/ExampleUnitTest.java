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
//        testFutureTask();
//        testReverser();
//        testThreadJoin();
//        testQuickSort();
//        testWaitNotify();
//        testRxDemo();
        System.out.println((float) (4 >> 1));
    }

    private void testRxDemo() {
        RxDemo demo = new RxDemo();
        demo.fun();
    }

    private void testWaitNotify() {
        Object mute = new Object();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                synchronized (mute) {
                    System.out.println(Thread.currentThread().getName() + " printing ...");
                    try {
                        Thread.sleep(3000);
                        mute.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("print end");
                }
            }
        };

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("outer start");
                try {
                    System.out.println("before wait");
                    mute.wait();
                    System.out.println("after wait");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (mute) {
                    try {
                        System.out.println("doing");
                        Thread.sleep(1000);
                        mute.notify();
                    } catch (InterruptedException ignored) {
                    }
                }
                System.out.println("outer end");
            }
        };

        Thread t = new Thread(runnable);
        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable1);
        Thread t3 = new Thread(runnable1);

        t.start();
        t1.start();
        t2.start();
        t3.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("+++++++++++++++++++");
    }

    private void testQuickSort() {
        QuickSorter<Integer> quickSorter = new QuickSorter<>();
        Integer numbers[] = new Integer[]{6, 9, 1, 0, 3, 2, 4, 8, 5, 7};
        quickSorter.sort(numbers, 0, numbers.length - 1);
        System.out.println(Arrays.toString(numbers));
    }

    private void testReverser() {
        Reverser reverser = new Reverser();
        Node node = reverser.createList();
        reverser.print(node);
        reverser.reverseList(node);
        reverser.print(node);
    }

    private void testFutureTask() {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("sleep start");
                Thread.sleep(2000);
                System.out.println("sleep end");
                return "hello";
            }
        };
        FutureTask<String> task = new FutureTask<>(callable);
        Thread thread = new Thread(task);
        thread.start();
        try {
            System.out.println("START");
            System.out.println(task.get());
            System.out.println("END");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }


    private void testThreadJoin() throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e.toString());
                }
            }
        };
        thread.start();
//        thread.interrupt();
        thread.join();
        System.out.println("线程已经退出!");
    }
}