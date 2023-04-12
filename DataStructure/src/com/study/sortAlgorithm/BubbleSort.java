package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序
 * 冒泡排序时间复杂度为 O(n^2)  n^2 = n²
 */
public class BubbleSort {
    static int arrSize = 80000;
    public static void main(String[] args) {
        int arr[] = new int[arrSize];
        CommonUtil.paddingArr(arr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
        bubble(arr);
        System.out.println(sdf.format(new Date()));
    }

    public static void bubble(int[] arr){
        int temp;  // 定义一个临时变量 用于交换数据
        boolean flag = false; // 定义一个变量 用于优化冒泡排序  判断是否进入交换判断 如没有则代表当前数组是有序的 则停止循环
        for (int i = arr.length-1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                // 如果当前元素大于后面的元素则交换
                if (arr[j] > arr[j+1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            if (!flag){
                break;
            } else {
                flag = false;
            }
        }
    }
}
