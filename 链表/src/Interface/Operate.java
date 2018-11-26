package Interface;

public interface Operate {

    //创建一个链表
    Object creatLink();

    //链表头插
    void insertFront(Object head);

    //链表尾插
    void insertBack(Object head);

    //链表打印
    void printLink(Object head);

    //链表头删
    void deleteFront(Object head);

    //链表尾删
    void deleteBack(Object head);

    //判断指定内容在链表中是否存在;存在返回索引，不存在返回-1
    int condtains(Object head);

    //删除指定内容
    void remove(Object head);

    //链表清空
    void clear(Object head);

    //获得链表长度
    int getSize(Object head);

    //将链表转换为一个数组
    Object[] toArray(Object head);

}
