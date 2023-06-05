package com.binarysearchalgorithm;

/**
 * 二分查找算法
 * @author
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {4,6,25,78,95,100,104,135};
        System.out.println(binarySearchRecursion(arr,1,0,arr.length - 1));
        System.out.println(binarySearchNotRecursion(arr,1));
    }

    /**
     * 二分查找算法 递归查找
     * @param arr 需要查找的数组 传入的arr需要是有序的 asc
     * @param target 需要找的的值
     * @return 找到返回数组的下标 没找到返回 -1
     */
    private static int binarySearchRecursion(int arr[], int target, int left, int right){
        int mid = (left + right) / 2;
        if (left > right){
            return -1;
        }
        if (arr[mid] == target){
            return mid;
        } else if (arr[mid] > target){
            return binarySearchRecursion(arr,target,left,mid-1);
        } else {
            return binarySearchRecursion(arr, target, mid+1, right);
        }
    }

    /**
     * 二分查找算法 非递归查找
     * @param arr 需要查找的数组 传入的arr需要是有序的 asc
     * @param target 需要找的的值
     * @return 找到返回数组的下标 没找到返回 -1
     */
    private static int binarySearchNotRecursion(int arr[], int target){
        int left = 0,
            right = arr.length - 1;
        while (right >= left){
            int mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            }else if (arr[mid] > target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
