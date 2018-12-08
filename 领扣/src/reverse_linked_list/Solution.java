package reverse_linked_list;

public class Solution {
    /*
    反转一个单链表。
    示例:
    输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL
     */
    public static ListNode reverseList(ListNode head) {
        if(head == null){
            return null;
        }
        if(head.next == null){
            return head;
        }
        ListNode cur = head;
        ListNode next = head.next;
        ListNode prev = null;
        while(cur.next!=null){
            cur.next = prev;
            prev = cur;
            cur = next;
            next = cur.next;
        }
        cur.next = prev;
        head = cur;
        return head;
    }

    public static void printNode(ListNode head){
        if(head == null){
            System.out.println(head);
            return;
        }
        ListNode cur = head;
        while(cur!=null){
            System.out.print(cur.data+"-->");
            cur = cur.next;
        }
        System.out.print("null\n");
    }

    public static void main(String[] args) {

        ListNode<Integer> head = ListNode.head;
        ListNode<Integer> node1 = new ListNode<>(2,null);
        ListNode<Integer> node2 = new ListNode<>(3,null);
        ListNode<Integer> node3 = new ListNode<>(4,null);
        ListNode<Integer> node4 = new ListNode<>(5,null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        printNode(head);
        head = reverseList(head);
        printNode(head);
        head = new ListNode<Integer>(1,node1);
        printNode(head);
        head = reverseList(head);
        printNode(head);

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
