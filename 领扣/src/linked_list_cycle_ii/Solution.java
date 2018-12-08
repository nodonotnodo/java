package linked_list_cycle_ii;

public class Solution {

    /*
    给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
说明：不允许修改给定的链表。

示例 1：
输入：head = [3,2,0,-4], pos = 1
输出：tail connects to node index 1
解释：链表中有一个环，其尾部连接到第二个节点。

示例 2：
输入：head = [1,2], pos = 0
输出：tail connects to node index 0
解释：链表中有一个环，其尾部连接到第一个节点。

示例 3：
输入：head = [1], pos = -1
输出：no cycle
解释：链表中没有环。

     */

    public static ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        do{
            slow = slow.next;
            fast = fast.next;
            if(fast == null){
                return null;
            }
            fast = fast.next;
        }while(!(fast == slow || slow==null || fast==null));
        if(slow == null || fast == null){
            return null;
        }else{
            ListNode p = head;
            while(slow != p){
                slow = slow.next;
                p = p.next;
            }
            return p;
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> head = ListNode.head;
        ListNode<Integer> node1 = new ListNode<>(2,null);
        ListNode<Integer> node2 = new ListNode<>(3,null);
        ListNode<Integer> node3 = new ListNode<>(4,null);
        ListNode<Integer> node4 = new ListNode<>(5,null);
        head = new ListNode<Integer>(1,node1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = head;
        System.out.println(detectCycle(head).data);
    }
}

class ListNode<T> {
    public static ListNode head;
    public T data;
    public ListNode next;

    public ListNode getHead(){
        return head;
    }

    public ListNode(T data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    public void set(T data, ListNode next){
        this.data = data;
        this.next = next;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}
