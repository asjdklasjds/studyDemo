package com.study.tree;

import java.util.Objects;

/**
 * 顺序存储二叉树
 * 要求使用数组来存储
 * 数组与二叉树之间可以互相转换
 * 要求在遍历数组时仍然可以使用前、中、后序进行输出
 * 顺序存储二叉树的特点
 * 1） 顺序二叉树通常只考虑完全二叉树
 * 2） 第n个元素的左子节点为 2*n+1
 * 3)  第n个元素的右子节点为 2*n+2
 * 4)  第n个元素的父节点为（n-1）/ 2
 * n：表示二叉树中的第几个元素（按0开始编号） 也就是数组的下标
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.midOrder();
    }
}

class ArrBinaryTree {
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }

    protected void preOrder() {
        this.preOrder(0);
    }

    /**
     * 前序遍历
     */
    protected void preOrder(int n) {
        if (Objects.isNull(arr) || arr.length == 0) throw new RuntimeException("ArrBinaryTree arr is null");
        System.out.println(arr[n]);
        // 往左边递归遍历
        if ((2 * n + 1) < arr.length) this.preOrder(2 * n + 1);
        // 往右边递归遍历
        if ((2 * n + 2) < arr.length) this.preOrder(2 * n + 2);
    }


    protected void midOrder() {
        this.midOrder(0);
    }

    /**
     * 中序遍历
     */
    protected void midOrder(int n) {
        if (Objects.isNull(arr) || arr.length == 0) throw new RuntimeException("ArrBinaryTree arr is null");
        // 往左边递归遍历
        if ((2 * n + 1) < arr.length) this.preOrder(2 * n + 1);
        System.out.println(arr[n]);
        // 往右边递归遍历
        if ((2 * n + 2) < arr.length) this.preOrder(2 * n + 2);
    }

    protected void postOrder() {
        this.postOrder(0);
    }

    /**
     * 后序遍历
     */
    protected void postOrder(int n) {
        if (Objects.isNull(arr) || arr.length == 0) throw new RuntimeException("ArrBinaryTree arr is null");
        // 往左边递归遍历
        if ((2 * n + 1) < arr.length) this.preOrder(2 * n + 1);
        // 往右边递归遍历
        if ((2 * n + 2) < arr.length) this.preOrder(2 * n + 2);
        System.out.println(arr[n]);
    }
}
