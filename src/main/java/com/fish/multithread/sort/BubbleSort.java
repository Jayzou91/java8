package com.fish.multithread.sort;

import java.util.Arrays;

/**
 * @Description:
 * @Author: Jayzou
 * @Date: 2018/11/30
 */
public class BubbleSort {

    public void sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int len = arr.length;
        int sortCount = 0;
        int exchangeCount = 0;
        for (int i = len - 1; i > 0; i--) {
            boolean exchanged = false;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    exchanged = true;
                    exchangeCount++;
                }
            }

            System.out.println(String.format("第%d趟排序，已交换%d次。", ++sortCount, exchangeCount));

            if (!exchanged) {
                return;
            }
        }
    }

    public static void main(String[] args) {
//        int[] arr = {12, 34, 100, 350, 700, 1000};
        int[] arr = {12, 3, 10, 350, 0, 1000};
        System.out.println("Before sort: " + Arrays.toString(arr));
        new BubbleSort().sort(arr);
        System.out.println("After sort: " + Arrays.toString(arr));
    }


}
