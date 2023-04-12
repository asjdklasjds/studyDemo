package com.study.sortAlgorithm;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 * @author yan
 */
public class InsertingSort {
    static int arrSize = 80000;
    public static void main(String[] args) {
        int[] arr = {2,34,87,65,1,-1,193,213,34,3};
        System.out.println(Arrays.toString(arr));
//        int[] arr = new int[arrSize];
//        CommonUtil.paddingArr(arr);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(new Date()));
        insertingSortFor(arr);
//        System.out.println(sdf.format(new Date()));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 插入排序实现思路 按从小到大
     * 从下标n开始 往前找
     * 如果下标为n的数小于前面的数
     * 则使用一个临时变量保存下标为n的数 然后将前面的数移到下标n的位置
     * 待到大于前面的数后 停止查找 得到插入的下标
     * 将下标为m的数插入到 找到的下标
     * 循环如上操作
     * @param arr
     */
    private static void insertingSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            // 得到需要插入的数
            int temp = arr[i];
            // 获取要往前找几次
            int insertIndex = i-1;
            // 如果 insertIndex >= 0 并且 temp 需要插入的数小于 arr[insertIndex]
            while (insertIndex >= 0 && temp < arr[insertIndex]){
                // temp 需要插入的数小于 arr[insertIndex]
                // 此处将arr[insertIndex] 往后移一个位置
                arr[insertIndex+1] = arr[insertIndex];
                // 继续往前走
                insertIndex--;
            }
            // 将temp插入到找到的下标位置
            arr[insertIndex+1] = temp;
        }
    }

    private static void insertingSortFor(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            // 得到需要插入的数
            int temp = arr[i];
            int insertIndex = i - 1;
            for (int j = insertIndex; j > -1; j--,insertIndex--) {
                if (arr[j] > temp) {
                    arr[j + 1] = arr[j];
                }
                else break;

            }
            arr[insertIndex+1] = temp;
        }
    }
}
