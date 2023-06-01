package com.study.avl;

/**
 * AVL数（平衡二叉搜索数）
 * @author yan
 */
public class AVLTreeDemo {
    public static void main(String[] args) {
//        int []arr = {4,3,6,5,7,8};
//        int []arr = {10,12,8,9,7,6};
        int []arr = {10,11,7,6,8,9};
        AVLTree avlTree = new AVLTree();
        for (int val:arr){
            avlTree.add(val);
        }
        avlTree.infixPrintOrder();
        System.out.println(avlTree.maxHeight(avlTree.root));
        System.out.println(avlTree.leftMaxHeight(avlTree.root));
        System.out.println(avlTree.rightMaxHeight(avlTree.root));
        System.out.println(avlTree.root);
    }
}

class AVLTree{
    static class Node{
        int val;
        Node left,right;
        Node(int val){
            this.val = val;
        }
        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    '}';
        }
    }

    Node root;


    /**
     * 右边高度 - 左边高度 > 1
     * 则：
     * 左旋转当前二叉树 使当前二叉树成为AVL数
     * @param root
     */
    private void leftRotate(Node root){
        // 1 创建一个新节点 保存当前需要旋转的root节点的值
        Node newNode = new Node(root.val);
        // 2 将新节点的left 指向当前root节点的left
        newNode.left = root.left;
        // 3 将新节点的right 指向当前需要旋转的root节点right的left
        newNode.right = root.right.left;
        // 4 左旋 因此将当前root节点的right指向当前root节点
        root.val = root.right.val;
        // 5 将旋转过来的root节点指向 之前没旋转的root节点的right的right
        root.right = root.right.right;
        // 6 将旋转过来的root节点指向保存之前root节点的 newNode
        root.left = newNode;
    }

    /**
     * 左边高度 - 右边高度 > 1
     * 则：
     * 右旋转当前二叉树 使当前二叉树成为AVL数
     * 步骤与左旋类似
     * @param root
     */
    private void rightRotate(Node root){
        Node newNode = new Node(root.val);
        newNode.right = root.right;
        newNode.left = root.left.right;
        root.val = root.left.val;
        root.left = root.left.left;
        root.right = newNode;
    }

    public void infixPrintOrder(){
        if (root == null) throw new NullPointerException("root is null");
        infixOrder(root);
    }

    private void infixOrder(Node root){
        if (root.left != null){
            infixOrder(root.left);
        }
        System.out.println(root);
        if (root.right != null){
            infixOrder(root.right);
        }
    }

    /**
     * 获取传入节点右边的最大高度
     * @param root
     * @return
     */
    public int rightMaxHeight(Node root){
        if (root.right == null)
            return 0;
        return maxHeight(root.right);
    }

    /**
     * 获取传入节点左边的最大的高度
     * @param root
     * @return
     */
    public int leftMaxHeight(Node root){
        if (root.left == null)
            return 0;
        return maxHeight(root.left);
    }

    /**
     * 得到传入 Node 的最大高度
     * @param root
     * @return
     */
    public int maxHeight(Node root){
        return Math.max(root.left == null ? 0 : maxHeight(root.left), root.right == null ? 0 : maxHeight(root.right)) + 1;
    }
    /**
     * 封装 addNode 为了方便调用
     * @param val
     */
    public void add(int val){
        addNode(root,new Node(val));
    }
    /**
     * 添加节点
     * @param root
     * @param node
     */
    private void addNode(Node root,Node node){
        if (node == null) throw new NullPointerException("param node is null");
        if (this.root == null){
            this.root = node;
            return;
        }
        if (root.val > node.val){
            if (root.left == null) root.left = node;
            else addNode(root.left,node);
        }else {
            if (root.right == null) root.right = node;
            else addNode(root.right,node);
        }
        // 如果当前添加节点后 右边和左边的高度的绝对值大于一则向左边旋转
        if (rightMaxHeight(root) - leftMaxHeight(root) > 1){
            // 如果当前旋转的节点 右边节点的左节点高度大于右节点则向右边旋转
            if (root.right != null && leftMaxHeight(root.right) > rightMaxHeight(root.right)){
                rightRotate(root.right);
            }
            leftRotate(root);
        }
        // 如果当前添加节点后 左边和右边的高度的绝对值大于一则向右边旋转
        else if (leftMaxHeight(root) - rightMaxHeight(root) > 1) {
            // 如果当前旋转的节点 左边节点的右节点高度大于左节点则向左边旋转
            if (root.left != null && leftMaxHeight(root.left) < rightMaxHeight(root.left)){
                leftRotate(root.left);
            }
            rightRotate(root);
        }
    }

}
