package CircleLink;

/**
 * Author: nodonotnodo
 * Created: 2018/12/8
 */
public class CircleLinkList<E> {

  private static class Node<E> {

        E data;
        Node next;

        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
  }

  private Node<E> first;
  private Node<E> last;
  private int size;

  public CircleLinkList() {
      this.first = null;
      this.last = null;
      this.size = 0;
  }

  //添加数据(默认尾插)
  public void add(E element) {
      //链表为空，则插入的节点既是头节点也是尾节点
      if(first == null){
          first = new Node<E>(element,first);
          last = first;
          last.next = first;
          size++;
          return;
      }

      //链表不为空，尾插
      last.next = new Node<E>(element,first);
      last = last.next;
      size++;
  }

  //检查插入时下标
  public void checkAddIndex(Integer index){

      if(index<0 || index>size){
          throw new ArrayIndexOutOfBoundsException("输入的下标不存在");
      }
  }

  //向指定下标添加数据
  public void add(int index, E element) {
      checkAddIndex(index);
      if(index == 0){
          first = new Node<>(element,first);
          last.next = first;
          size++;
      }else if(index == size){
          add(element);
      }else{
          Node prev = null;
          Node cur = first;
          int i = 0;
          //找到要插入的地址
          while(cur != null){
              if(index == i){
                  break;
              }
              prev = cur;
              cur = cur.next;
              i++;
          }
          prev.next = new Node<E>(element,cur);
          size++;
      }
  }

  //默认头删，返回删除的数据。
  public <E> E remove() {
      if(first == last){
          first = null;
          last = null;
          if(size != 0){
              size--;
          }
          return null;
      }
      E move = (E) first.data;
      first = first.next;
      last.next = first;
      size--;
      return move;
  }

  public void checkNotAddIndex(Integer index){
      if(index<0 || index>=size){
          throw new ArrayIndexOutOfBoundsException("输入的下标不存在");
      }
  }

  public <E> E remove(int index) {
      checkNotAddIndex(index);
      E move = null;
      if(0 == index){
          return remove();
      } else{
          Node prev = null;
          Node cur = first;
          int i = 0;
          //找到要删除的地址
          while(cur != null){
              if(index == i){
                  break;
              }
              prev = cur;
              cur = cur.next;
              i++;
          }
          move = (E) cur.data;
          prev.next = cur.next;
          size--;
          if(cur == last){
              last = prev;
              prev.next = first;
          }
      }
      return move;
  }

  public void set(int index, E element) {
      checkNotAddIndex(index);
      Node cur = first;
      int i = 0;
      while(i < this.size){
          if(index == i){
              cur.data = element;
              return;
          }
          cur = cur.next;
          i++;
      }
  }

  public E get(int index) {
      checkNotAddIndex(index);
      if(size == 0){
          return null;
      }
      Node cur = first;
      int i = 0;
      while(i < this.size){
          if(index == i){
              break;
          }
          cur = cur.next;
          i++;
      }
      return (E) cur.data;
  }

  public int size() {
      return this.size;
  }

}

class Test{

    public static void main(String[] args) {

        CircleLinkList circleLinkList = new CircleLinkList();
        circleLinkList.add("第一个元素");
        circleLinkList.add("第二个元素");
        circleLinkList.add("第三个个元素");
        circleLinkList.add(0,"第四个个元素");
        circleLinkList.add(circleLinkList.size(),"第五个个元素");
        circleLinkList.add(circleLinkList.size()-2,"Jack");
//        System.out.println(circleLinkList.remove());
//        System.out.println(circleLinkList.remove());
//        System.out.println(circleLinkList.remove());
//        System.out.println(circleLinkList.remove());
//        System.out.println(circleLinkList.remove());
//        System.out.println(circleLinkList.remove());
//        System.out.println(circleLinkList.remove());

//        System.out.println(circleLinkList.remove(0));
//        System.out.println(circleLinkList.remove(1));
//        System.out.println(circleLinkList.remove(circleLinkList.size()-1));

//        circleLinkList.set(0,"0");
//        circleLinkList.set(3,"3");
//        circleLinkList.set(circleLinkList.size()-1,"10");
//        circleLinkList.set(10,"10");

        System.out.println(circleLinkList.get(0));
        System.out.println(circleLinkList.get(3));
        System.out.println(circleLinkList.get(circleLinkList.size()-1));
        System.out.println(circleLinkList.get(10));

    }
}
