package Linklist;

import java.util.Scanner;
import Interface.Operate;

public class LinkList {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Operate link = new LinkOperate();
        LinkNode head = (LinkNode) link.creatLink();
        while(true){
            System.out.println("=====0.打印链表 1.头插 2.尾插 3.头删 4.尾删 5.查找内容 6.删除 7. 获得链表长度 8.将链表转换为一个数组 10.链表清空且退出=====");
            System.out.println("请按照菜单输入选择");
            int choose = sc.nextInt();
            switch(choose){
                case 0:{
                    link.printLink(head);
                    break;
                }
                case 1:{
                    link.insertFront(head);
                    break;
                }
                case 2:{
                    link.insertBack(head);
                    break;
                }
                case 3:{
                    link.deleteFront(head);
                    break;
                }
                case 4:{
                    link.deleteBack(head);
                    break;
                }
                case 5:{
                    int result = link.condtains(head);
                    System.out.println(result);
                    break;
                }
                case 6:{
                    link.remove(head);
                    break;
                }
                case 7:{
                    int size = link.getSize(head);
                    System.out.println("链表长度为"+size);
                    break;
                }
                case 8:{
                    Object[] array = link.toArray(head);
                    break;
                }
                case 10:{
                    link.clear(head);
                    return;
                }
                default:{

                }
            }
        }
    }
}

class LinkNode{

    //类属性
    //private static LinkNode head;//链表头

    //成员属性
    private String name;//节点名称
    private float data;//节点数据
    private LinkNode last;//节点的上一个节点
    private LinkNode next;//节点的下一个节点

    //构造方法
    public LinkNode(String name, float price, LinkNode last, LinkNode next){
        this.name = name;
        this.data = price;
        this.last = last;
        this.next = next;
    }

    //getter，setter和普通方法

    /*
    public LinkNode getHead() {
        return head;
    }

    public void setHead(LinkNode head) {
        this.head = head;
    }
    */

    public String getName(){ return this.name; }

    public float getData(){
        return this.data;
    }

    public LinkNode getLast(){
        return this.last;
    }

    public LinkNode getNext(){
        return this.next;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setData(float data){
        this.data = data;
    }

    public void setLast(LinkNode last){
        this.last = last;
    }

    public void setNext(LinkNode next){
        this.next = next;
    }
}

class LinkOperate implements Operate{

    //创建一个链表
    public Object creatLink(){
        LinkNode head = new LinkNode("头",0.0F,null,null);
        return head;
    }

    //链表头插
    public void insertFront(Object head){
        LinkNode rhead = (LinkNode)head;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要插入的节点的名称：");
        String name = sc.next();
        System.out.println("请输入你要插入的节点的数据：");
        float price = sc.nextFloat();
        LinkNode node = new LinkNode(name, price, null, null);
        if(rhead.getNext() == null){
            node.setLast(rhead);
            rhead.setNext(node);
        }else{
            node.setNext(rhead.getNext());
            rhead.getNext().setLast(node);
            rhead.setNext(node);
        }
    }

    //链表尾插
    public void insertBack(Object head){
        LinkNode rhead = (LinkNode)head;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要插入的节点的名称：");
        String name = sc.next();
        System.out.println("请输入你要插入的节点的数据：");
        float price = sc.nextFloat();
        LinkNode node = new LinkNode(name, price, null, null);
        if(rhead.getNext() == null){
            rhead.setNext(node);
        }else{
            LinkNode cur = rhead.getNext();
            while(cur.getNext() != null){
                cur = cur.getNext();
            }
            node.setLast(cur);
            cur.setNext(node);
        }
    }

    //链表打印
    public void printLink(Object head){
        LinkNode rhead = (LinkNode)head;
        if(rhead.getNext() == null){
            System.out.println("root(表头)->null");
            return;
        }
        System.out.print("root(表头)->");
        LinkNode cur = rhead.getNext();
        while(cur.getNext() != null){
            System.out.print("名称："+cur.getName()+",数据："+cur.getData()+"-->");
            cur = cur.getNext();
        }
        System.out.println("名称："+cur.getName()+",数据："+cur.getData()+"-->null");
    }

    //链表头删
    public void deleteFront(Object head){
        LinkNode rhead = (LinkNode)head;
        if(rhead.getNext() == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        rhead.setNext(rhead.getNext().getNext());
    }

    //链表尾删
    public void deleteBack(Object head){
        LinkNode rhead = (LinkNode)head;
        if(rhead.getNext() == null){
            System.out.println("链表为空，无法删除");
            return;
        }
        LinkNode cur = rhead.getNext();
        while(cur.getNext() != null){
            cur = cur.getNext();
        }
        cur.getLast().setNext(null);
    }

    //判断指定内容在链表中是否存在;存在返回索引，不存在返回-1
    public int condtains(Object head){
        LinkNode rhead = (LinkNode)head;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要查找的节点的数据：");
        float data = sc.nextFloat();
        int count = 0;
        LinkNode cur = rhead.getNext();
        while(cur != null){
            if(data == cur.getData()){
                return count;
            }
            cur = cur.getNext();
            count++;
        }
        System.out.println("要查找的节点不存在");
        return -1;
    }

    //删除指定内容
    public void remove(Object head){
        LinkNode rhead = (LinkNode)head;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入你要删除的节点的数据：");
        float data = sc.nextFloat();
        if(null == rhead.getNext()){
            System.out.println("链表为空");
            return;
        }
        LinkNode cur = rhead.getNext();
        if(data == cur.getData() && null == cur.getNext()){
            rhead.setNext(null);
            return;
        }
        while(cur != null){
            if(data == cur.getData()){
                cur.getLast().setNext(cur.getNext());
                cur.getNext().setLast(cur.getLast());
                return;
            }
            cur = cur.getNext();
        }
        System.out.println("要删除的节点不存在");
        return;
    }

    //链表清空
    public void clear(Object head) {
        LinkNode rhead = (LinkNode) head;
        rhead.setNext(null);
    }

    //获得链表长度
    public int getSize(Object head){
        LinkNode rhead = (LinkNode) head;
        LinkNode cur = rhead.getNext();
        int count = 0;
        while(cur != null){
            cur = cur.getNext();
            count++;
        }
        return count;
    }

    //将链表转换为一个数组
    public Object[] toArray(Object head){
        LinkNode rhead = (LinkNode)head;
        int size = getSize(head);
        Object[] array = new Object[size];
        LinkNode cur = rhead.getNext();
        for(int i = 0; i<size; i++){
            array[i] = cur.getData();
            cur = cur.getNext();
        }
        return array;
    }
}
