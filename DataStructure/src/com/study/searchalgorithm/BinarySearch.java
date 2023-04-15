package com.study.searchalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 二分查找算法
 * 二分查找的数组或者集合必须是有序的
 * @author yan
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {12,34,54,65,65,65,68,69,77,78};
        int binarySearchOne = binarySearchOne(arr, 0, arr.length - 1, 12);
        System.out.println(binarySearchOne);
        List<Integer> list = binarySearchMany(arr, 0, arr.length - 1, 65);
        System.out.println(list);
    }

    /**
     * 二分查找  查询数组中一个相等的元素 并返回其下标值
     * @param arr 查找目标的数组
     * @param left 查找左边的下标值
     * @param right 查找右边的下标值
     * @param finalVal 需要查找的值
     * @return 返回其找到的下标值  未找到则返回 -1
     */
    private static int binarySearchOne(int[] arr, int left, int right, int finalVal){
        System.out.println("===");
        // 没有找到 finalVal 值
        if (left > right) return -1;
        // 取数组的中间值
        int mid = (left + right) / 2;
        // 如果中间值大于 finalVal就继续向左查找
        if (arr[mid] > finalVal)
            return binarySearchOne(arr,left,mid-1,finalVal);
        // 如果中间值小于 finalVal 值就继续向右查找
        else if (arr[mid] < finalVal)
            return binarySearchOne(arr,mid+1,right,finalVal);
        // 如果既不大于又不小于 则就是相等  代表找到finalVal值 直接返回 mid值 mid为数组的下标
        else
            return mid;
    }

    /**
     * 查找arr中所有出现 finalVal的下标
     * @param arr 查找目标的数组
     * @param left 查找左边的下标值
     * @param right 查找右边边的下标值
     * @param finalVal 需要查找的值
     * @return 返回其找到的下标值的集合  未找到则返回 空集合[]
     */
    private static List<Integer> binarySearchMany(int[] arr,int left, int right, int finalVal){
        // 没有找到 finalVal 值
        if (left > right) return Collections.emptyList();
        // 取数组的中间值
        int mid = (left + right) / 2;
        // 如果中间值大于 finalVal就继续向左查找
        if (arr[mid] > finalVal)
            return binarySearchMany(arr,left,mid-1,finalVal);
        // 如果中间值小于 finalVal 值就继续向右查找
        else if (arr[mid] < finalVal)
            return binarySearchMany(arr,mid+1,right,finalVal);
        else {
            // 如果既不大于又不小于 则就是相等  代表找到finalVal值
            // 不直接返回 继续向左和右边找与finalVal相等的值 添加到List中 然后返回List
            List<Integer> list = new ArrayList<>();
            int temp = mid;
            while (temp-- > left && arr[temp] == finalVal) list.add(temp);
            list.add(mid);
            temp = mid;
            while (temp++ < right && arr[temp] == finalVal) list.add(temp);
            return list;
        }
    }
}
