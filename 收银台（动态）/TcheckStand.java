import java.util.Scanner;

public class TcheckStand{

    public static void main(String[] args){
        Procedure test1 = new Procedure();
        test1.procedure();
    }
}

class Goods{//商品类
    private String name;//商品名称
    private float price;//商品价格
    private int quantity;//商品数量
    private Goods next;//下一个类
    private Goods last;//上一个类

    public String getAllInfo(){
        return this.name+"\t"+this.price+"\t"+this.quantity+"\t"+this.next+"\t"+this.last;
    }

    public String getName(){
        return this.name;
    }

    public float getPrice(){
        return this.price;
    }

    public int getQuantity(){
        return this.quantity;
    }

    public Goods getNext(){
        return this.next;
    }

    public Goods getLast(){
        return this.last;
    }

    public void setNext(Goods next){
        this.next = next;
    }

    public void setLast(Goods last){
        this.last = last;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setGoods(float price, int quantity){
        this.price = price;
        this.quantity = quantity;
    }

    public Goods(String name, float price, int quantity, Goods next, Goods last){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.next = next;
        this.last = last;
    }
}

class Procedure{
    static String possword = "1";//收银台登录密码
    static int sizeOfGoods = 0;//这个是收银台已经上架的商品数量

    public boolean possword(){//登陆收银台
        int count = 0;
        System.out.println("===============欢迎使用马氏收银台，请输入您的登录密码=================");
        while(count++ < 3){
            Scanner sc = new Scanner(System.in);
            String key = sc.next();
            if(true == Procedure.possword.equals(key)){
                return true;
            }else{
                System.out.println("====================密码错误，请重新输入==========================");
            }
        }
        System.out.println("=====================已经失败三次，程序退出=========================");
        return false;
    }

    /*
    public Goods[] touchGoods(){//初始化商品
        Goods goods = new Goods()
        goods = null;
        return goods;
    }
    */

    public void print1(){//主菜单的提示打印
        System.out.println("============================================");
        System.out.println("=======1.查看  2.设置  3.下单  4.退出=======");
        System.out.println("============================================");
    }

    public void realWatch(Goods goods){//查看商品的实现
        System.out.println("现在共有"+Procedure.sizeOfGoods+"种商品");
        System.out.println("编号\t名称\t价格\t数量");
        if(0 == Procedure.sizeOfGoods){
            System.out.println("还没有商品，先上架一些商品吧");
        }else{
            Goods cur = goods.getNext();
            int i = 1;
            for(; cur != null; cur = cur.getNext()){
                System.out.println(i+"\t"+cur.getName()+"\t"+cur.getPrice()+"\t"+cur.getQuantity());
                i++;
            }
        }
        // System.out.println("=============================================");
        // System.out.println("===============1.返回上一层==================");
        // System.out.println("=============================================");
    }

    public void watch(Goods goods){//查看菜单及查看的实现
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("=============================================");
            System.out.println("=========1.查看商品      2.返回上一层========");
            System.out.println("=============================================");
            int choose = sc.nextInt();
            switch(choose){
                case 1:{
                    realWatch(goods);//查看商品的实现
                    break;
                }
                case 2:{
                    return;
                }
                default:{
                    System.out.println("请输入菜单上的正确命令提示：");
                    break;
                }
            }
        }
    }

    //真正的商品上架
    public void realAddGood(Goods goods, String name, float price, int quantity){
        Goods first = goods.getNext();
        if(0 == Procedure.sizeOfGoods){//商品为0时，直接赋值
            Goods good = new Goods(name, price, quantity, null, null);
            goods.setNext(good);
        }else{//若之前已经有商品，则头插
            Goods good = new Goods(name, price, quantity, null, null);
            good.setLast(null);
            good.setNext(first);
            first.setLast(good);
            goods.setNext(good);
        }
        Procedure.sizeOfGoods++;
        System.out.println("上架成功");
    }

    public void addGood(Goods goods){//实现商品上架
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要上架的商品的 名称 ");
        String name = sc.next();
        System.out.println("请输入要上架的商品的 价格 ");
        float price = sc.nextFloat();
        System.out.println("请输入要上架的商品的 数量");
        int quantity = sc.nextInt();
        realAddGood(goods, name, price, quantity);//真正的商品上架
    }

    public void realSubGood(Goods goods, Goods willSub){//真正实现下架
        //good[i].setGoods(null, 0.0F, 0);
        if(willSub.getNext() == null&&willSub.getLast() == null){//要删除的既是商品链表第一个又是最后一个
            goods.setNext(null);
        } else if(willSub.getLast() == null){//要删除的是商品链表第一个
            willSub.getNext().setLast(null);
            goods.setNext(willSub.getNext());
        }else if(willSub.getNext() == null){//要删除的就是商品链表最后一个
            willSub.getLast().setNext(null);
        }else{
            willSub.getLast().setNext(willSub.getNext());
            willSub.getNext().setLast(willSub.getLast());
        }
        Procedure.sizeOfGoods--;
        System.out.println("下架成功");
    }

    public Goods findGood(Goods goods, String name){//查找商品
        Goods find = goods.getNext();
        if(find == null){
            return null;
        }
        for(; find != null; find = find.getNext()){
            if(name.equals(find.getName())){
                break;
            }
        }
        return find;
    }

    public void subGood(Goods goods){//实现商品下架
        realWatch(goods);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入要下架的商品的 名称 ");
            String name = sc.next();
            Goods willSub = findGood(goods, name);
            if(null == willSub){
                System.out.println("您要下架的商品不存在，请重新输入");
            }else{
                realSubGood(goods, willSub);//真正实现下架
                return;
            }
        }
    }

    public void reviseGood(Goods goods){//实现商品修改
        realWatch(goods);
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("请输入要修改的商品的 名称 ");
            String name = sc.next();
            Goods willRevise = findGood(goods, name);
            if(willRevise == null){
                System.out.println("您要修改的商品不存在，请重新输入");
            }else{
                System.out.println("您要修改的商品数据为：\n名称："+willRevise.getName()+"\t 价格："+willRevise.getPrice()+"\t数量："+willRevise.getQuantity());
                System.out.println("请输入要修改的商品的新 价格 ");
                float price = sc.nextFloat();
                System.out.println("请输入要修改的商品的新 数量");
                int quantity = sc.nextInt();
                willRevise.setGoods(price, quantity);
                System.out.println("修改成功");
                System.out.println("您修改后商品数据为：\n名称："+willRevise.getName()+"\t 价格："+willRevise.getPrice()+"\t数量："+willRevise.getQuantity());
                return;
            }
        }
    }

    public void set(Goods goods){//设置菜单以及设置的实现
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("================================================");
            System.out.println("1.商品上架  2.商品下架  3.修改商品  4.返回上一层");
            System.out.println("================================================");
            int choose = sc.nextInt();
            switch(choose){
                case 1:{
                    addGood(goods);//商品上架的实现
                    break;
                }
                case 2:{
                    subGood(goods);//商品下架的实现
                    break;
                }
                case 3:{
                    reviseGood(goods);//商品修改的实现
                    break;
                }
                case 4:{
                    return;
                }
                default:{
                    System.out.println("请输入菜单上的正确命令提示：");
                    break;
                }
            }
        }
    }

    public float realOrder(Goods goods){//下单的实际实现
        Scanner sc = new Scanner(System.in);
        float allPrice = 0.0F;
        while(true){
            realWatch(goods);
            System.out.println("总价："+allPrice+"元");
            System.out.println("请输入你要买的商品名称(若下单结束请按 1)");
            String name = sc.next();
            if(name.equals("1")){
                return allPrice;
            }
            Goods willBuy = findGood(goods, name);
            if(willBuy == null){
                System.out.println("您输入的商品不存在，请重新输入");
            }else{
                System.out.println("请输入你要买的商品数量");
                int num = sc.nextInt();
                if(num <= willBuy.getQuantity()){
                    allPrice += willBuy.getPrice()*num;
                    willBuy.setQuantity(willBuy.getQuantity()-num);
                }else{
                    System.out.println("=============================警告：商品数量不足，购买失败=========================");
                }
            }
        }
    }

    public void placeOrder(Goods goods){//下单菜单及实现
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("================================================");
            System.out.println("==============1.下单 2.返回上一层===============");
            System.out.println("================================================");
            int choose = sc.nextInt();
            float allPrice = 0.0F;
            switch(choose){
                case 1:{
                    allPrice = realOrder(goods);//下单的实际实现
                }
                case 2:{
                    System.out.println("下单完成，总计："+allPrice+"元");
                    return;
                }
                default:{
                    System.out.println("请输入菜单上的正确命令提示：");
                    break;
                }
            }
        }
    }

    public void procedure(){
        //首先实现进入界面
        boolean re = this.possword();
        //Goods[] goods = touchGoods();//初始化商品
        Goods goods = new Goods("头结点",0.0F,0,null,null);
        Scanner sc = new Scanner(System.in);
        while(re){//密码正确，登录收银台
            while(true){
                print1();//主菜单的提示打印
                int choose = sc.nextInt();
                switch(choose){
                    case 1:{//进入查看菜单
                        watch(goods);
                        break;
                    }
                    case 2:{//进入设置菜单
                        set(goods);
                        break;
                    }
                    case 3:{//进入下单菜单
                        placeOrder(goods);
                        break;
                    }
                    case 4:{
                        return;
                    }
                    default:{
                        System.out.println("请输入菜单上的正确命令提示：");
                        break;
                    }
                }
            }
        }
        return;//密码不正确则直接结束
    }
}