package com.study.tree;

import java.util.Objects;

/**
 * 二叉排序（搜索）数
 * @author yan
 */
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {4,6,1,3,9,2,5};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new BinarySortTreeNode(arr[i]));
        }
        binarySortTree.infixOrder();
        BinarySortTreeNode binarySortTreeNode = binarySortTree.searchNode(4);
        System.out.println(binarySortTreeNode.val);
//        BinarySortTreeNode binarySortTreeNode1 = binarySortTree.searchParentNode(4);
//        System.out.println(binarySortTreeNode1.val);
//        binarySortTree.delLeafNode(5);
//        binarySortTree.delLeafNode(9);
//        binarySortTree.delLeafNode(6);
//        binarySortTree.delLeafNode(2);
//        binarySortTree.delLeafNode(3);
//        binarySortTree.delLeafNode(1);
//        binarySortTree.delLeafNode(4);
//        binarySortTree.delNode(1);
//        binarySortTree.delNode(2);
//        binarySortTree.delNode(3);
//        binarySortTree.delNode(4);
//        binarySortTree.delNode(5);
//        binarySortTree.delNode(6);
//        binarySortTree.delNode(9);
        binarySortTree.delete(1);
        System.out.println(binarySortTree.getRoot());
        System.out.println("删除后的二叉排序数");
        binarySortTree.infixOrder();
    }
}

/**
 * 二叉排序数
 */
class BinarySortTree{
    private BinarySortTreeNode root;

    public BinarySortTreeNode getRoot() {
        return root;
    }

    public void delete(int val){
        deleteNode(root,val);
    }

    /**
     * TODO 简化delNode()方法 有问题 删除不了
     * @param val
     * @return
     */
    private BinarySortTreeNode deleteNode(BinarySortTreeNode root, int val){
        BinarySortTreeNode node = root;
        if (node == null)
            return null;
        // 往左边递归查找
        if (node.val > val){
            node = deleteNode(node.left,val);
        }
        // 往右边递归查找
        else if (node.val < val){
            node = deleteNode(node.right,val);
        }
        // 找到要删除的当前节点
        else {
            // 当前节点是叶子节点 直接将当前节点置空
            if (node.left == null && node.right == null){
                node = null;
                return null;
            }
            // 当前节点左右都有子节点
            else if (node.left != null && node.right != null){
                BinarySortTreeNode maxNode = node.findMaxNode();
                node.val = maxNode.val;
                node.left = deleteNode(node.left, maxNode.val);
            }
            // 当前节点只有左边 或者 右边 有节点
            else {
                if (node.left != null){
                    node = node.left;
                }else {
                    node = node.right;
                }
            }
        }
        return root;
    }

    /**
     * 删除二叉排序数中的节点
     * @param val 需要删除节点的val值
     */
    public void delNode(int val){
        // 如果当前root节点等于null 或者传入的val值等于 root.val值则直接将root置空 返回
        if (Objects.isNull(root) || (root.val == val && Objects.isNull(root.left) && Objects.isNull(root.right))){
            root = null;
            return;
        }
        // 得到需要删除节点
        BinarySortTreeNode targetNode = searchNode(val);
        // 如果需要删除的节点未找到则直接返回
        if (Objects.isNull(targetNode))
            return;
        // 得到需要删除节点的父节点
        BinarySortTreeNode searchParentNode = searchParentNode(targetNode.val);
        // 如果targetNode为叶子节点才执行下面逻辑
        if (Objects.isNull(targetNode.left) && Objects.isNull(targetNode.right)) {
            //targetNode 节点为 searchParentNode 的左子节点 否则为右子节点
            if (!Objects.isNull(searchParentNode.left) && searchParentNode.left.val == val)
                searchParentNode.left = null;
            else
                searchParentNode.right = null;
        }else if (!Objects.isNull(targetNode.left) && !Objects.isNull(targetNode.right)){
            //  两边都有子节点
            BinarySortTreeNode maxNode = targetNode.findMaxNode();
            delNode(maxNode.val);
            targetNode.val = maxNode.val;
        }else {
            // 只有左边有子节点 或者 只有右边有子节点
            if (!Objects.isNull(targetNode.left)){
                if (!Objects.isNull(searchParentNode)){
                    if (searchParentNode.left.val == val){
                        searchParentNode.left = targetNode.left;
                    }else {
                        searchParentNode.right = targetNode.left;
                    }
                }else {
                   root = targetNode.left;
                }
            }else {
                if (!Objects.isNull(searchParentNode)){
                    if (searchParentNode.left.val == val){
                        searchParentNode.left = targetNode.right;
                    }else {
                        searchParentNode.right = targetNode.right;
                    }
                }else {
                    root = targetNode.right;
                }
            }
        }

    }

    /**
     * 删除二叉排序中的叶子节点 不是叶子节点删除不了
     * @param val 需要删除节点的val值
     */
    public void delLeafNode(int val){
        // 如果当前root节点等于null 或者传入的val值等于 root.val值则直接将root置空 返回
        if (Objects.isNull(root) || (root.val == val && Objects.isNull(root.left) && Objects.isNull(root.right))){
            root = null;
            return;
        }
        // 得到需要删除节点
        BinarySortTreeNode targetNode = searchNode(val);
        // 如果需要删除的节点未找到则直接返回
        if (Objects.isNull(targetNode))
            return;
        // 如果targetNode为叶子节点才执行下面逻辑
        if (Objects.isNull(targetNode.left) && Objects.isNull(targetNode.right)) {
            // 得到需要删除节点的父节点
            BinarySortTreeNode searchParentNode = searchParentNode(targetNode.val);
            //targetNode 节点为 searchParentNode 的左子节点 否则为右子节点
            if (!Objects.isNull(searchParentNode.left) && searchParentNode.left.val == val)
                searchParentNode.left = null;
            else
                searchParentNode.right = null;
        }

    }

    /**
     * 查找传入的val值的节点
     * @param val
     * @return
     */
    public BinarySortTreeNode searchNode(int val){
        if (Objects.isNull(root))
            return null;
        return root.searchNode(val);
    }

    /**
     * 查找传入的val值的父节点
     * @param val
     * @return
     */
    public BinarySortTreeNode searchParentNode(int val){
        if (Objects.isNull(root))
            return null;
        return root.searchParentNode(val);
    }

    public void add(BinarySortTreeNode node){
        if (node == null) throw new NullPointerException();
        if (root == null) root = node;
        else root.add(node);
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (Objects.isNull(root))
            throw new RuntimeException("root 节点已经为null了 ！！");
        root.infixOrder();
    }
}

/**
 * 节点类
 */
class BinarySortTreeNode{
    int val;
    BinarySortTreeNode left,right;

    BinarySortTreeNode(int val){
        this.val = val;
    }

    /**
     * 找到传入节点最大的值
     * @return
     */
    protected BinarySortTreeNode findMaxNode(){
        BinarySortTreeNode node = this;
        while (!Objects.isNull(node.right))
            node = node.right;
        return node;
    }

    /**
     * 找到传入节点最小的值
     * @return
     */
    protected BinarySortTreeNode findMinNode(){
        BinarySortTreeNode node = this;
        while (!Objects.isNull(node.left))
            node = node.left;
        return node;
    }

    /**
     * 找到传入的 val值的Node节点
     * @param val
     * @return
     */
    protected BinarySortTreeNode searchNode(int val){
        if (this.val == val)
            return this;
        if (this.val > val) {
            if (Objects.isNull(this.left))
                return null;
            return this.left.searchNode(val);
        } else {
            if (Objects.isNull(this.right))
                return null;
            return this.right.searchNode(val);
        }

    }

    /**
     * 找到传入val节点的父节点
     * @param val
     * @return
     */
    protected BinarySortTreeNode searchParentNode(int val){
        if (this.val == val)
            return null;
        if ((!Objects.isNull(this.left) && this.left.val == val) || (!Objects.isNull(this.right) && this.right.val == val))
            return this;
        else {
            if (this.val > val) {
                if (Objects.isNull(this.left))
                    return null;
                return this.left.searchParentNode(val);
            } else {
                if (Objects.isNull(this.right))
                    return null;
                return this.right.searchParentNode(val);
            }
        }
    }


    /**
     * 添加节点 按照二叉排序树来添加
     * @param node 需要添加的节点
     */
    protected void add(BinarySortTreeNode node){
        if (this.val >= node.val){
            if (this.left == null) this.left = node;
            else this.left.add(node);
        }else {
            if (this.right == null) this.right = node;
            else this.right.add(node);
        }
    }

    protected void infixOrder(){
        if (this.left != null) this.left.infixOrder();
        System.out.println(this);
        if (this.right != null) this.right.infixOrder();
    }

    @Override
    public String toString() {
        return "BinarySortTreeNode{" +
                "val=" + val +
                '}';
    }
}
