package com.study.sortalgorithm;

import java.util.Date;

/**
 * 基数排序（桶排序）
 * @author yan
 */
public class RadixSort {
    static int arrSize = 800000;
    public static void main(String[] args) {
//        int[] arr = {123,34,65,76,324,9,123,1,4};
        int[] arr = new int[arrSize];
        CommonUtil.paddingArr(arr);
        System.out.println(CommonUtil.sdf.format(new Date()));
        radixSort(arr);
        System.out.println(CommonUtil.sdf.format(new Date()));
//        System.out.println(Arrays.toString(arr));
    }

    /**
     * 基数排序是一个经典的已空间换时间的排序算法
     * 注意：
     *  基数排序中如果含有小数或者负数需要特殊处理  下面的方式不建议使用负数或小数
     *  也可以使用其他的排序算法
     *  使用基数排序时如果数据量过大会出现 OutOfMemoryError 内存不够的异常  谨慎使用！！！
     *  80000000 个int类型的数据 大概需要如下内存空间  80000000*11*4/1024/1024/1024 = 3.278255462646484 （GB）
     * @param arr 需要排序的原始数组
     */
    private static void radixSort(int[] arr){
        // 1、定义桶 （二维数组）
        int[][] bucket = new int[10][arr.length];
        // 定义记录每个桶中 一维数组的下标值
        int[] bucketElement = new int[10];

        int maxVal = arr[0];
        // 获取数组中的最大值 并求出最大数值的长度
        for (int i = 1; i < arr.length; i++) {
            if (maxVal < arr[i]) maxVal = arr[i];
        }
        int maxLength = (""+maxVal).length();

        // 下面进行基数排序  基数排序需要 循环maxLength 次
        for (int i = 0,n = 1; i < maxLength; i++,n*=10) {
            // 循环arr 将值填充到桶排序中  第一次取个位数、第二次取十位数、第三次取百位数进行比较 以此类推...
            for (int j = 0; j < arr.length; j++) {
                // 依次获取 个十百千 位数
                int bucketIndex = arr[j] / n % 10;
                // 得到bucketIndex 就是桶的下标 然后将该数填充到对应下标的桶中
                bucket[bucketIndex][bucketElement[bucketIndex]++] = arr[j];
            }
            // 按从小到大的顺序取出桶中的数据
            int arrIndex = 0;
            for (int j = 0; j < bucket.length; j++) {
                if (bucketElement[j] > -1){
                    // 取出当前桶中的所有数据
                    for (int k = 0; k < bucketElement[j]; k++) {
                        arr[arrIndex++] = bucket[j][k];
                    }
                }
                // 清空该桶中的下标索引值
                bucketElement[j] = 0;
            }
        }
    }
}
