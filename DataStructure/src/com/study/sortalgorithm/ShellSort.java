package com.study.sortalgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 希尔排序  又叫（缩小增量排序）
 * 时间复杂度为 O(n³)
 * @author yan
 */
public class ShellSort {
    static int arrSize = 80000;
    public static void main(String[] args) {
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
        int[] arr = new int[arrSize];
        CommonUtil.paddingArr(arr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
        System.out.println(sdf.format(new Date()));
        shellSortShift(arr);
        System.out.println(sdf.format(new Date()));
    }

    /**
     * 采用【交换式】 碰到一个就交换  效率较低
     * @param arr 需要排序的数组
     */
    private static void shellSortExchange(int[] arr){
        int temp = 0;
        // 先对数组进行分组
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 循环得到当前组的数据
            for (int i = gap; i < arr.length; i++) {
                // 循环判断比较前面的数与后面的数是否大于或小于
                for (int j = i-gap; j >= 0; j -= gap) {
                    // 条件成立就交换位置
                    if (arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 基于希尔排序【交换试】优化为 移动试 效率高
     * @param arr 需要排序的数组
     */
    private static void shellSortShift(int[] arr){
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                while (j - gap >= 0 && temp < arr[j-gap]){
                    arr[j] = arr[j-gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
        }
    }
}
