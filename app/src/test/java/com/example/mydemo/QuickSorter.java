package com.example.mydemo;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @param <T> 元素类型
 */
public class QuickSorter<T extends Comparable<T>> {
    public void sort(T array[], int start, int end) {
        T key = array[start];
        int i = start;
        int j = end;
        while (i < j) {
            if (array[j].compareTo(key) > 0) {
                j--;
            } else if (array[i].compareTo(key) <= 0) {
                i++;
            } else {
                swap(array, i, j);
            }
            if (i == j) {
                if (i != start) {
                    swap(array, i, start);
                } else {
                    break;
                }
            }

        }
        System.out.println(Arrays.toString(array));
        assert i == j;
        if (i - 1 > start) sort(array, start, i - 1);
        if (i + 1 < end) sort(array, i + 1, end);
    }

    private void swap(T[] array, int i, int j) {
        T temp;
        temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
