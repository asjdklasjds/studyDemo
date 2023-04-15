package com.study.searchalgorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 线性查找
 * @author yan
 */
public class LinearSearch {

    public static void main(String[] args) {
        int[] arr = {2,4,1,23,54,23,43,3,65,34};
        int linearSearchOne = linearSearchOne(arr, 23);
        System.out.println(linearSearchOne);
        List<Integer> linearSearchMany = linearSearchMany(arr, 23);
        System.out.println(linearSearchMany);
    }

    /**
     * 线性查找 单个值
     * @param arr
     * @param finalVal
     * @return
     */
    private static int linearSearchOne(int arr[], int finalVal){
        for (int i = 0; i < arr.length; i++) {
            if (finalVal == arr[i]) return i;
        }
        return -1;
    }

    /**
     * 线性查找 多个值
     * @param arr
     * @param finalVal
     * @return
     */
    private static List<Integer> linearSearchMany(int arr[], int finalVal){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (finalVal == arr[i]) list.add(i);
        }
        return list;
    }
}
