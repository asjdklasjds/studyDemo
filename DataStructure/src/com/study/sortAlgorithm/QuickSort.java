package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 快速排序
 * 快速排序是对冒泡排序的升级
 * 通过使用一个n的数
 * 用数组中的其他元素对n进行对比
 * 小于的話则将小于的数放到n左边
 * 大于的話则将大于的数放到n的右边
 * （从大到小的話则相反）
 * 然后对左边以及右边进行递归调用
 * @author yan
 */
public class QuickSort {
    static int arrSize = 8000000;
    public static void main(String[] args) {
        int[] arr = {23,65,12,23,3,-342,4,23,432,654,203,2,-349,234,543,-231};
//        int[] arr = new int[arrSize];
//        CommonUtil.paddingArr(arr);
//        System.out.println(CommonUtil.sdf.format(new Date()));
        System.out.println(Arrays.toString(arr));
        quickSort(arr,0,arr.length-1);
//        System.out.println(CommonUtil.sdf.format(new Date()));
    }

    /**
     * 快速排序算法
     * @param arr 需要排序的数组
     */
    private static void quickSort(int[] arr,int leftIndex,int rightIndex){
        int pivot = arr[leftIndex];
        int l = leftIndex;
        int r = rightIndex;
        int temp = 0;
        while (l < r){
            // 找到比 pivot 大的值
            while (pivot < arr[r]) r--;
            // 找到比 pivot 小的值
            while (pivot > arr[l]) l++;
            // 停止循环
           if (l >= r) break;
           // 交换
           temp = arr[l];
           arr[l] = arr[r];
           arr[r] = temp;
            // 如果交换完成之后 arr[leftPoint] 等于pivot 则让rightPoint-- 不然会出现死循环
            if (arr[l] == pivot) r--;
            // 如果交换完成之后 arr[rightPoint] 等于pivot leftPoint++ 不然会出现死循环
            if (arr[r] == pivot) l++;
        }
        System.out.println(Arrays.toString(arr));
        if (l == r){
            l++;
            r--;
        }
        // 向左递归
        if (leftIndex < r) quickSort(arr,leftIndex,r);
        // 向右递归
        if (rightIndex > l) quickSort(arr,l,rightIndex);
    }
}
