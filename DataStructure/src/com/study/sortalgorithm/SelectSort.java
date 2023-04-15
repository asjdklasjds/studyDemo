package com.study.sortalgorithm;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择排序
 * 时间复杂度为 O(n^2) = O(n²)
 * @author yan
 */
public class SelectSort {

    // 数组大小
    static int arrSize = 80000;

    public static void main(String[] args) {
        int[] arr = new int[arrSize];
        CommonUtil.paddingArr(arr);
//        int[] arr = {10,2,33,4,23,5};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));
//        System.out.println(Arrays.toString(arr));
        new SelectSort().selectSort(arr);
        System.out.println(sdf.format(new Date()));
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 选择排序思路
     * 1、 选择排序共有 arrSize - 1 轮排序
     * 2、 每一轮里面又是一个循环
     * 2.1、 先假定arr[i]是最小数
     * 2.2、 通过这个数与后面的每个数进行比较，如果发现比当前数更小的数据就重新确定最小数，并得到下标
     * 2.3、 当遍历到数组最后时就得到了本轮最小数和下标
     * 2.4、 将当前下标数与 循环得到的最小下标数进行交换
     * @param arr
     */
    public void selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i];
            int k = -1;
            for (int j = (i+1); j < arr.length; j++) {
                if (temp > arr[j]){
                    k = j;
                    temp = arr[j];
                }
            }
            if (k>-1){
                arr[k] = arr[i];
                arr[i] = temp;
            }
        }
    }
}
