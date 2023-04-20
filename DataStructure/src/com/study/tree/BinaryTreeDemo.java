package com.study.tree;

import java.util.Objects;

/**
 * 二叉树
 * @author yan
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();

        HeroNode root = new HeroNode(1, "A");
        HeroNode node2 = new HeroNode(2, "B");
        HeroNode node3 = new HeroNode(3, "C");
        HeroNode node4 = new HeroNode(4, "D");
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(new HeroNode(5,"E"));
        binaryTree.setRoot(root);
//        System.out.println("前序");
//        binaryTree.preOrder();
//        System.out.println("中序");
//        binaryTree.midOrder();
//        System.out.println("后序");
//        binaryTree.postOrder();
//        System.out.println(binaryTree.postSearch(5));
//        binaryTree.midSearch(3);
//        binaryTree.postSearch(3);

        System.out.println("删除前 preOrder");
        binaryTree.preOrder();
        binaryTree.delNode2(3);
        System.out.println("删除后 preOrder");
        binaryTree.preOrder();
    }
}

class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void preOrder(){
        root.preOrder();
    }
    public void midOrder(){
        root.midOrder();
    }
    public void postOrder(){
        root.postOrder();
    }
    public HeroNode preSearch(int no){
        return this.root.preSearch(no);
    }
    public HeroNode midSearch(int no){
        return this.root.midSearch(no);
    }
    public HeroNode postSearch(int no){
        return this.root.postSearch(no);
    }

    public void delNode1(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.delNode1(no);
            }
        }
    }

    public void delNode2(int no){
        if (root != null){
            if (root.getNo() == no){
                root = null;
            }else {
                root.delNode2(no);
            }
        }
    }

}

class HeroNode{
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 删除节点 2
     * 如果删除的节点是叶子节点则直接删除
     * 如果不是 则使用该节点的左节点替代当前节点 如果当前节点的左节点为null则使用右边节点替代当前节点
     */
    public void delNode2(int no){
        if (this.left != null && this.left.no == no){
            // 当前节点是删除的节点 先判断删除的几点左边是否有节点
            // 有就把删除节点左边节点替换当前节点 并把删除节点的右边节点挂到删除节点的右边
            if (this.left.left != null) {
                HeroNode right = this.left.right;
                this.left = this.left.left;
                this.left.right = right;
            }
            // 如果删除节点的左节点为null 则把删除节点的右节点获取替换节点
            // 右节点不管是否为空都可以直接覆盖需要删除的节点 所以下面不做处理
            else
                // 上面this.left.left 已经为空了 不需要再获取this.left.left 添加到this.left.left 中了
                this.left = this.left.right;
            return;
        }
        if (this.right != null && this.right.no == no){
            if (this.right.left != null) {
                HeroNode right = this.right.right;
                this.right = this.right.left;
                this.right.right = right;
            } else
                this.right = this.right.right;
            return;
        }
        // 如果上面两个条件都不满足则先向左边递归查找
        if (this.left != null){
            this.left.delNode1(no);
        }
        // 后向右边递归查找
        if (this.right != null){
            this.right.delNode1(no);
        }
    }

    /**
     * 删除节点
     * 不管该节点是否为叶子节点 都直接删除
     */
    public void delNode1(int no){
        // 判断当前节点的左节点是否为删除的节点 如果是的话就直接删除并返回
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
        }
        // 判断当前节点的右节点是否为删除的节点 如果是的话就直接删除并返回
        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
        }
        // 如果上面两个条件都不满足则先向左边递归查找
        if (this.left != null){
            this.left.delNode1(no);
        }
        // 后向右边递归查找
        if (this.right != null){
            this.right.delNode1(no);
        }
    }

    /**
     * 前序查找
     */
    public HeroNode preSearch(int no){
        System.out.println("pre search ...");
        // 先比较当前节点是否相等  如果相等则直接返回当前节点
        if (this.no == no){
            return this;
        }
        HeroNode resNode = null;
        // 如果当前节点未找到则向左递归查找
        if (!Objects.isNull(this.left))
            resNode = this.left.preSearch(no);
        // 向左递归找到了就返回该节点
        if (resNode != null)
            return resNode;
        // 如果当前节点 和向左递归都没找到 则向右递归查找
        if (!Objects.isNull(this.right))
            resNode = this.right.preSearch(no);
        return resNode;
    }
    /**
     * 中序查找
     */
    public HeroNode midSearch(int no){
        HeroNode resNode = null;
        // 先向左递归查找
        if (!Objects.isNull(this.left))
            resNode = this.left.midSearch(no);
        // 如果找到则返回该节点
        if (resNode != null)
            return resNode;
        System.out.println("mid search ...");
        // 比较当前节点 如果相等则直接返回当前节点
        if (this.no == no)
            return this;
        // 如果向左和对当前节点都没找到则向右递归查找
        if (!Objects.isNull(this.right))
            resNode = this.right.midSearch(no);
        return resNode;
    }

    /**
     * 后序查找
     */
    public HeroNode postSearch(int no){
        HeroNode resNode = null;
        // 先向左递归查找
        if (!Objects.isNull(this.left))
            resNode = this.left.postSearch(no);
        // 如果找到就返回该节点
        if (resNode != null)
            return resNode;
        // 向右递归查找
        if (!Objects.isNull(this.right))
            resNode = this.right.postSearch(no);
        // 如果找到则返回该节点
        if (resNode != null)
            return resNode;
        System.out.println("post search ...");
        // 如果左右递归都没找打就对当前节点进行比较 找到直接返回当前节点
        if (this.no == no)
            return this;
        return resNode;
    }

    /**
     * 前序输出
     */
    public void preOrder(){
        System.out.println(this);
        if (!Objects.isNull(this.left)) this.left.preOrder();
        if (!Objects.isNull(this.right)) this.right.preOrder();
    }

    /**
     * 中序输出
     */
    public void midOrder(){
        if (!Objects.isNull(this.left)) this.left.midOrder();
        System.out.println(this);
        if (!Objects.isNull(this.right)) this.right.midOrder();
    }

    /**
     * 后序输出
     * @return
     */
    public void postOrder(){
        if (!Objects.isNull(this.left)) this.left.postOrder();
        if (!Objects.isNull(this.right)) this.right.postOrder();
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }
}
