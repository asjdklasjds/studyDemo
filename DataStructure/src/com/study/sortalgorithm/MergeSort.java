package com.study.sortalgorithm;

import java.util.Date;

/**
 * 归并排序
 * 采用经典的分治理念
 * @author yan
 */
public class MergeSort {
    static int arrSize = 80000000;
    public static void main(String[] args){
//        int arr[] = {8,5,3,2,1,6,4,7};
        int arr[] = new int[arrSize];
        CommonUtil.paddingArr(arr);
        int[] temp = new int[arr.length];
        System.out.println(CommonUtil.sdf.format(new Date()));
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println(CommonUtil.sdf.format(new Date()));
//        System.out.println(Arrays.toString(arr));
    }


    private static void mergeSort(int[] arr,int left,int right,int[] temp){
        if (left < right){
            int mid = (left + right) / 2;
            // 通过递归拆分 左边的数据
            mergeSort(arr,left,mid,temp);
            // 通过递归拆分 右边的数据
            mergeSort(arr,mid + 1,right,temp);
            // 合并
            merge(arr,left,mid,right,temp);
        }
    }

    /**
     * 合并已经分好了的数据
     * @param arr 需要排序的原始数组
     * @param left 左侧下标
     * @param mid 中间下标
     * @param right 右边下标
     * @param temp 辅助数组 用来进行排序合并
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp){
        int i = left;
        int j = mid + 1;
        int t = 0;
        // 1、合并排序 通过left与right 合并排序添加到temp中
        while (i <= mid && j <= right){
            if (arr[i] <= arr[j])
                temp[t] = arr[i++];
             else
                temp[t] = arr[j++];
            t++;
        }
        // 2、将left 或者 right 没有添加到temp的元素全部添加到temp中
        while (i <= mid)
            temp[t++] = arr[i++];
        while (j <= right)
            temp[t++] = arr[j++];
        // 3、将temp中的元素copy到arr中
        int l = left;
        t = 0;
        while (l <= right)
            arr[l++]= temp[t++];
    }
}
