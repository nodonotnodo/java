package merge_k_sorted_lists;

public class Solution {

    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists == null || lists.length == 0){
            return null;
        }
        ListNode result = new ListNode(0);
        ListNode cur = result;
        int flag = lists.length;//一个标志位，当它为0时所有链表遍历完毕
        while(flag != 0){
            flag = lists.length;
            int min = 0;//链表最小值
            int min_f = 0;
            int lmin = 0;//此时最小值所在的链表下标
            for(int i=0; i<lists.length; i++){
                if(lists[i] == null){//每有一个链表遍历完，标志位减1
                    flag--;
                    continue;
                }
                if(min_f == 0){
                    min_f = 1;
                    min = lists[i].val;
                    lmin = i;
                }else{
                    if(lists[i].val<min){
                        min = lists[i].val;
                        lmin = i;
                    }
                }
            }
            if(lists[lmin] != null){
                lists[lmin] = lists[lmin].next;//将取值的链表更新
            }
            if(min_f != 0){
                cur.next = new ListNode(min);
                cur = cur.next;
            }
        }
        return result.next;
    }

    /*
    合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
     */

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(4);
        list1.next.next = new ListNode(5);
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(3);
        list2.next.next = new ListNode(4);
        ListNode list3 = new ListNode(2);
        list3.next = new ListNode(6);
        ListNode[] lists = new ListNode[]{list1,list2,list3};
        ListNode result = mergeKLists(lists);
    }
}

class ListNode{
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
