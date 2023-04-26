package com.study.tree;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 赫夫曼数（哈夫慢数|霍夫曼数）--> 最优二叉树
 * 如何创建一棵赫夫曼数
 * wpl值最小的是霍夫曼数
 * wpl：树的所有叶结点的带权路径长度之和，称为树的带权路径长度表示为WPL。
 * wpl 全称 Weighted Path Length of Tree
 *
 * @author yan
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6 , 1}; // 1 3 6 7 8 13 29
        Node node = huffmanTree(arr);
        node.preOrder();
    }

    public static Node huffmanTree(int[] arr){
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : arr) nodes.add(new Node(i));

        // （5） 循环执行 （1）（2）（3）（4）步骤 直到nodes中只剩一个元素 也就是赫夫曼数的 root 节点
        while (nodes.size() > 1){
            // （1）先将nodes 进行排序
            Collections.sort(nodes);
            // (2) 创建数
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node parson = new Node(leftNode.val + rightNode.val);
            parson.left = leftNode;
            parson.right = rightNode;

            // （4） 删除已创建的Node 并且添加 parson
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parson);
        }

        return nodes.get(0);
    }

}

class Node implements Comparable<Node> {
    int val;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if (this.left != null) this.left.preOrder();
        if (this.right != null) this.right.preOrder();
    }

    public Node(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.val;
    }
}
