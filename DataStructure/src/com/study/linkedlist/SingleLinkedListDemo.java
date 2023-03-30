package com.study.linkedlist;

import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * 单向链表实现
 * @author yan
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        /**
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        HerdNode node1 = new HerdNode(1,"宋江","及时雨");
        HerdNode node2 = new HerdNode(2,"吴用","智多星");
        HerdNode node3 = new HerdNode(3,"林冲","豹子头");
        HerdNode node4 = new HerdNode(4,"李逵","sad");
        singleLinkedList.addByOrder(node1);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node3);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node2);
        singleLinkedList.addByOrder(node4);

        singleLinkedList.list();


        HerdNode newNode4 = new HerdNode(4,"黑人","sadddd");
        System.out.println("修改后的值");
        singleLinkedList.update(newNode4);


        singleLinkedList.list();


        HerdNode delNode4 = new HerdNode(2,"黑人","sadddd");
        System.out.println("删除后的链表");
//        singleLinkedList.delete(delNode4);
//        singleLinkedList.delete(new HerdNode(1,"",""));
//        singleLinkedList.delete(new HerdNode(4,"",""));
//        singleLinkedList.delete(new HerdNode(3,"",""));
//        singleLinkedList.delete(new HerdNode(2,"",""));


        singleLinkedList.list();

        System.out.println("链表Size： " + singleLinkedList.getNodeSize());


        System.out.println(singleLinkedList.getLastIndexNode(3));

        System.out.println("======================================");
        singleLinkedList.list();
        System.out.println();
//        System.out.println("反转单向链表~~");
//        singleLinkedList.singleLinkedListReversal(singleLinkedList.getHead());
//        singleLinkedList.list();
//        使用反转逆向输出  会造成原始数据结构改变
//        reversalLinkedList(singleLinkedList.getHead());
        // 使用栈的特性逆向输出链表

        reversalPrintLinkedListStack(singleLinkedList.getHead());

        System.out.println("======================================");

        singleLinkedList.list();
         */

        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        HerdNode node1 = new HerdNode(1,"","");
        HerdNode node2 = new HerdNode(2,"","");
        HerdNode node3 = new HerdNode(3,"","");
        HerdNode node4 = new HerdNode(4,"","");
        HerdNode node5 = new HerdNode(5,"","");
        HerdNode node6 = new HerdNode(6,"","");
        HerdNode node7 = new HerdNode(7,"","");
        HerdNode node8 = new HerdNode(8,"","");
        singleLinkedList1.addByOrder(node2);
        singleLinkedList1.addByOrder(node8);
        singleLinkedList1.addByOrder(node3);
        singleLinkedList1.addByOrder(node1);
        singleLinkedList2.addByOrder(node7);
        singleLinkedList2.addByOrder(node4);
        singleLinkedList2.addByOrder(node5);
        singleLinkedList2.addByOrder(node6);

        HerdNode node = mergeLinkedList(singleLinkedList1.getHead(), singleLinkedList2.getHead());
        HerdNode temp = node.next;
        while (!Objects.isNull(temp)){
            System.out.println(temp);
            temp = temp.next;
        }

    }

    /**
     * 逆向输出链表
     * 方式一： 反转之后输出  不建议：会导致数据结构改变
     * @param node
     */
    public static void reversalLinkedList(HerdNode node){
        if (Objects.isNull(node.next)){
            return;
        }
        if (Objects.isNull(node.next.next)){
            System.out.println(node.next);
            return;
        }

        HerdNode cur = node.next;
        HerdNode next = null;
        HerdNode reversal = new HerdNode(0,"","");
        while (!Objects.isNull(cur)){
            // 1 获得遍历的当前节点的后面节点指向
            next = cur.next;
            // 2 将改几点之前的节点保存到当前节点后面  然后将链表重新指向 reversal
            cur.next = reversal.next;
            reversal.next = cur;
            cur = next;
        }
//        node.next = reversal.next;

        while (!Objects.isNull(reversal.next)){
            System.out.println(reversal.next);
            reversal = reversal.next;
        }
    }

    /**
     * 逆向输出链表
     * 方式二： 使用栈的先进后出的特性实现
     * @param node
     */
    public static void reversalPrintLinkedListStack(HerdNode node){
        if (Objects.isNull(node.next)){
            return;
        }
        HerdNode temp = node.next;
        Stack<HerdNode> stack = new Stack<>();
        while (!Objects.isNull(temp)){
            stack.push(temp);
            temp = temp.next;
        }

        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }


    /**
     * 合并两个链表  并保证合并后的链表是有序的
     * @param node1
     * @param node2
     * @return 合并后的链表
     */
    public static HerdNode mergeLinkedList(HerdNode node1,HerdNode node2){
        HerdNode newNode = new HerdNode(0,"","");
        HerdNode temp = node1.next;
        while (true){
            if (Objects.isNull(temp.next)){
                temp.next = node2.next;
                break;
            }
            temp = temp.next;
        }
        HerdNode newNode1 = node1.next;
        HerdNode next = null;
        while (!Objects.isNull(newNode1)){
            next = newNode1.next;
            addByOrder(newNode1,newNode);
            newNode1 = next;

        }
        return newNode;
    }

    public static void addByOrder(HerdNode herdNode,HerdNode head){
        HerdNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null){
                break;
            }
            if (temp.next.no > herdNode.no){
                break;
            }else if (temp.next.no == herdNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            herdNode.next = temp.next;
            temp.next = herdNode;
        }
    }

    /**
     * 对传入的链表进行排序
     * @param node
     */
    public static void linkedListOrder(HerdNode node){
        if (Objects.isNull(node.next) || Objects.isNull(node.next.next)){
            return;
        }

    }

}


class SingleLinkedList{
    // 初始化一个头节点  头节点不能动
    private HerdNode head = new HerdNode(0,"","");

    public HerdNode getHead() {
        return head;
    }

    // 向链表中添加节点 在最后节点进行添加 无顺序
    public void add(HerdNode herdNode){
        HerdNode temp = head;
        while (temp.next != null) temp = temp.next ;
        temp.next = herdNode;
    }

    // 按照no排名顺序进行添加
    public void addByOrder(HerdNode herdNode){
        HerdNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null){
                break;
            }
            if (temp.next.no > herdNode.no){
                break;
            }else if (temp.next.no == herdNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag){
            herdNode.next = temp.next;
            temp.next = herdNode;
        }
    }

    // 修改链表节点中的值
    public void update(HerdNode newHerdNode){
        if (head.next == null){
            System.out.println("当前链表为空~~");
            return;
        }
        HerdNode temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == newHerdNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.nickname = newHerdNode.nickname;
            temp.name = newHerdNode.name;
        }
    }

    public void delete(HerdNode delNode){
        if (head.next == null){
            System.out.println("当前链表为空~~");
            return;
        }
        HerdNode temp = head;
        while (true){
            if (temp.next == null){
                System.out.println("未找到传入的节点！！");
                break;
            }
            HerdNode frontNode = temp;
            if (temp.next.no == delNode.no){
                frontNode.next = temp.next.next;
                break;
            }
            temp = temp.next;
        }
    }

    // 输出链表中所有的节点
    public void list(){
        HerdNode temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 获取链表大小
    public int getNodeSize(){
        if (head.next == null){
            return 0;
        }
        int size = 0;
        HerdNode temp = head.next;
        while (temp != null){
            size++;
            temp = temp.next;
        }
        return size;
    }

    /**
     * 获取链表中 倒数lastIndex的node节点
     * 下标从0开始
     * @param lastIndex
     * @return
     */
    public HerdNode getLastIndexNode(int lastIndex){
        if (head.next == null)
            throw new RuntimeException("链表为空！！");
        int nodeSize = getNodeSize();
        if (lastIndex < 0 || lastIndex >= nodeSize)
            throw new RuntimeException("超过链表下标值了！！！");
        int i = 0;
        HerdNode node = head;
        while (i < (nodeSize - lastIndex)) {
            node = node.next;
            i++;
        }
        return node;
    }

    /**
     * 单向链表反转
     */
    public void singleLinkedListReversal(HerdNode head){
        if (Objects.isNull(head.next) || Objects.isNull(head.next.next))
            return;
        HerdNode cur = head.next;
        HerdNode next = null;
        HerdNode reversalNode = new HerdNode(0,"","");
        while (!Objects.isNull(cur)){
            /**
             * 思路：使用一个新的链表来存储 每次添加都添加在新链表的next -> oldNext reversalNode的指向一直是指向head
             *  1、创建一个新的节点保存循环当前节点的下一个节点
             *  2、cur的指向为循环当前的节点 使用cur.next保存 reversalNode.next 原来的值
             *  3、next = 循环当前节点的下一个节点  前面执行完成之后只需将next 指向 cur 则获取到 循环当前节点的后面的节点
             */
            // next 保存当前循环的 下一个节点
            next = cur.next;
            // cur 循环当前的节点 reversalNode则为之前循环的节点 将当前节点放在前面 则 cur.next = reversalNode.next
            // 此时是为反转动作
            cur.next = reversalNode.next;
            // 将新的反转节点保存到 reversalNode
            reversalNode.next = cur;
            // 将当前循环的下一个节点 指向 cur
            cur = next;
        }
        // 将反转的单链表 赋值给 head
        head.next = reversalNode.next;
    }

}

class HerdNode{
    public int no;
    public String name;
    public String nickname;
    public HerdNode next;

    public HerdNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HerdNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
