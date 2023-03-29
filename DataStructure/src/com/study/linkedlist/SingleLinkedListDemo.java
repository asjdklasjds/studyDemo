package com.study.linkedlist;

import java.util.LinkedHashMap;

/**
 * 单向链表实现
 * @author yan
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
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
        singleLinkedList.delete(new HerdNode(1,"",""));
        singleLinkedList.delete(new HerdNode(4,"",""));
        singleLinkedList.delete(new HerdNode(3,"",""));
        singleLinkedList.delete(new HerdNode(2,"",""));


        singleLinkedList.list();

    }
}


class SingleLinkedList{
    // 初始化一个头节点  头节点不能动
    private HerdNode head = new HerdNode(0,"","");

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
