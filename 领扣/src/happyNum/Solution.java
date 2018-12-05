package happyNum;

/*
    快乐数就是得到每位数的平方和，在继续重复进行，直到得数为1为止。
    100以内的快乐数：1,7,10，13,19,23,28,31,32,44,49,68,70,79,82,86,91,94,97,100.
 */

public class Solution {

    public static boolean solution(Integer num){
        Link root = new Link();
        while(true){
            Integer res = 0;
            while(num/10 != 0){
                res += (num%10)*(num%10);
                num = num/10;
            }
            res += num*num;
            if(1 == res){
                return true;
            }
            if(root.findDate(res)){
                return false;
            }
            root.pushFront(res);
            num = res;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(1));
        System.out.println(solution(19));
        System.out.println(solution(31));
        System.out.println(solution(97));
        System.out.println(solution(98));
    }
}

class Link{
    private Link first;
    private Integer data;
    private Link next;

    public Link(){
        first = new Link(0,null);
    }

    public Link(Integer data, Link next) {
        this.data = data;
        this.next = next;
    }

    public Integer getData() {
        return data;
    }

    public void setData(Integer data) {
        this.data = data;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }

    //头插
    public void pushFront(Integer data){
        Link root = this.first;
        Link node = new Link(data,root.next);
        root.setNext(node);
    }

    //在链表中查找数据，找到返回true,否则返回false
    public boolean findDate(Integer data){
        Link cur = this.first.next;
        while(cur != null){
            if(data == cur.data){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

}
