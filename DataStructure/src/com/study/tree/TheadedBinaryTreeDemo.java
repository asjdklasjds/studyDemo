package com.study.tree;

import java.util.Objects;

/**
 * 线索化二叉树
 * @author yan
 */
public class TheadedBinaryTreeDemo {
    public static void main(String[] args) {
        TNode root = new TNode(1, "A");
        TNode b = new TNode(2, "B");
        TNode c = new TNode(3, "C");
        TNode d = new TNode(4, "D");
        TNode e = new TNode(5, "E");
        TNode f = new TNode(6, "F");
        root.setLeft(b);
        root.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setLeft(f);
        TBinaryTree tBinaryTree = new TBinaryTree(root);
        System.out.println("中序线索化前： " + d.getLeft() + "\t" + d.getRight());
        tBinaryTree.theadedBinaryTree();
        System.out.println("中序线索化后： " + d.getLeft() + "\t" + d.getRight());
        tBinaryTree.midOrder();
        System.out.println("中序线索化二叉树遍历：");
        tBinaryTree.midTheadedBinaryTreeList();

//        TBinaryTree tBinaryTree = new TBinaryTree(root);
//        System.out.println("前序线索化前： " + d.getLeft() + "\t" + d.getRight());
//        tBinaryTree.preTheadedBinaryTree();
//        System.out.println("前序线索化后： " + d.getLeft() + "\t" + d.getRight());
//        tBinaryTree.midOrder();
    }
}

/**
 * 创建一个线索化二叉树
 */
class TBinaryTree{
    // 根节点
    private TNode root;

    // 指向当前节点的前一个节点
    private TNode pre;

    public TBinaryTree(TNode root){
        this.root = root;
    }

    // 重载theadedBinaryTree(TNode node) 并进行调用
    protected void theadedBinaryTree(){
        this.theadedBinaryTree(root);
    }

    protected void preTheadedBinaryTree(){
        this.preTheadedBinaryTree(root);
    }

    /**
     * 中序线索化
     * 线索化二叉树具体实现方法
     */
    protected void theadedBinaryTree(TNode node){
        if (Objects.isNull(node)) return;
        // 1、先向左边进行递归线索化
        theadedBinaryTree(node.getLeft());
        // 2、对当前节点进行线索化
        if (Objects.isNull(node.getLeft())) {
            // 对左边 left 为 null 的节点 进行线索化
            node.setLeft(pre);
            node.setLeftType(TNodeType.YES.getVal());
        }
        if (!Objects.isNull(pre) && Objects.isNull(pre.getRight())){
            // 对右边为null 的节点进行线索化
            pre.setRight(node);
            pre.setRightType(TNodeType.YES.getVal());
        }
        // ！！！得到当前节点的 前驱 节点
        pre = node;
        // 3、向右边进行递归线索化
        theadedBinaryTree(node.getRight());
    }


    /**
     * 前序线索化
     * 线索化二叉树具体实现方法
     */
    protected void preTheadedBinaryTree(TNode node){
        if (Objects.isNull(node)) return;
        // 1、对当前节点进行线索化
        if (Objects.isNull(node.getLeft())) {
            // 对左边 left 为 null 的节点 进行线索化
            node.setLeft(pre);
            node.setLeftType(TNodeType.YES.getVal());
        }
        if (!Objects.isNull(pre) && Objects.isNull(pre.getRight())){
            // 对右边为null 的节点进行线索化
            pre.setRight(node);
            pre.setRightType(TNodeType.YES.getVal());
        }
        // ！！！得到当前节点的 前驱 节点
        pre = node;
        // 2、先向左边进行递归线索化
        theadedBinaryTree(node.getLeft());
        // 3、向右边进行递归线索化
        theadedBinaryTree(node.getRight());
    }

    protected void midOrder(){
        this.midOrder(root);
    }

    protected void midOrder(TNode node){
        if (Objects.isNull(node)) return;
        if (node.getLeftType() != TNodeType.YES.getVal())
            midOrder(node.getLeft());
        System.out.println(node);
        if (node.getRightType() != TNodeType.YES.getVal())
            midOrder(node.getRight());
    }

    /**
     * 线索化二叉树 遍历
     */
    protected void midTheadedBinaryTreeList(){
        TNode temp = root;
        while (!Objects.isNull(temp)){
            // 得到最左边的线索化二叉树的节点
            while (temp.getLeftType() == 0)
                temp = temp.getLeft();
            System.out.println(temp);
            // 使用当前节点向右边找节点 并输出
            while (temp.getRightType() == 1){
                temp = temp.getRight();
                System.out.println(temp);
            }
            temp = temp.getRight();
        }
    }
}

/**
 * 创建一个用于连接用的节点类
 */
class TNode{
    private int no;
    private String name;
    private TNode left;
    private TNode right;
    // leftType == 1 代表该节点的left指向了 当前节点的 【前驱】 节点了 默认值为0
    private int leftType = TNodeType.NO.getVal();
    // rightType == 1 代表该节点的right指向了 当前节点的 【后继】 节点了  默认值为0
    private int rightType = TNodeType.NO.getVal();

    public TNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "TNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TNode getLeft() {
        return left;
    }

    public void setLeft(TNode left) {
        this.left = left;
    }

    public TNode getRight() {
        return right;
    }

    public void setRight(TNode right) {
        this.right = right;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }
}

enum TNodeType{

    YES(1),
    NO(0);

    private final int val;
    TNodeType(int val) {
        this.val = val;
    }
    public int getVal() {
        return val;
    }
}
