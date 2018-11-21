import java.util.Scanner;
//1.下单对商品数量没有要求
public class Tcheckstand{
	
	public static void main(String[] args){
		Procedure test1 = new Procedure();
		test1.procedure();
	}
}

//这是一个商品类
class Goods{

	private int num;
	private String name;
	private float price;
	private int quantity;

	public Goods(){

	}

	public Goods(int num, String name, float price, int quantity){
		this.num = num;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public int getNum(){
		return this.num;
	}

	public void setQuantity(int quantity){
		this.quantity = quantity;
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
	
	public void setGoods(String name, float price, int quantity){
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public void setGoods(int num, String name, float price, int quantity){
		this.num = num;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
}

class Procedure{
	static int capacity = 10;//这个就是收银台的容量
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

	public Goods[] touchGoods(){//初始化商品
		Goods[] goods = new Goods[Procedure.capacity];
		System.out.println(goods);
		for(int i = 0; i<Procedure.capacity; i++){
			goods[i] = new Goods();
			goods[i].setGoods(i, null, 0.0F, 0);
		}
		return goods;
	}
	
	public void print1(){//主菜单的提示打印
		System.out.println("============================================");
		System.out.println("=======1.查看  2.设置  3.下单  4.退出=======");
		System.out.println("============================================");
	}
	
	public void realWatch(Goods[] goods){//查看商品的实现
		System.out.println("编号\t名称\t价格\t数量");
		for(int i = 0; i<Procedure.capacity; i++){
			System.out.println(goods[i].getNum()+"\t"+goods[i].getName()+"\t"+goods[i].getPrice()+"\t"+goods[i].getQuantity());
		}
		// System.out.println("=============================================");
		// System.out.println("===============1.返回上一层==================");
		// System.out.println("=============================================");
	}
	
	public void watch(Goods[] goods){//查看菜单及查看的实现
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
	
	public void addGood(Goods[] good){//实现商品上架
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入要上架的商品的 名称 ");
		String name = sc.next();
		System.out.println("请输入要上架的商品的 价格 ");
		float price = sc.nextFloat();
		System.out.println("请输入要上架的商品的 数量");
		int quantity = sc.nextInt();
		good[Procedure.sizeOfGoods].setGoods(name, price, quantity);
		Procedure.sizeOfGoods++;
		System.out.println("上架成功");
	}

	public void realSubGood(Goods[] good, int i){//真正实现下架
		//good[i].setGoods(null, 0.0F, 0);
		int j = i;
		for(; j<Procedure.sizeOfGoods-1; j++){
			good[j].setGoods(good[j+1].getName(), good[j+1].getPrice(), good[j+1].getQuantity());
		}
		good[Procedure.sizeOfGoods-1].setGoods(null, 0.0F, 0);
		Procedure.sizeOfGoods--;
		System.out.println("下架成功");
	}

	public void subGood(Goods[] good){//实现商品下架
		realWatch(good);
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("请输入要下架的商品的 名称 ");
			String name = sc.next();
			int i = 0;
			for(; i<Procedure.sizeOfGoods; i++){
				if(good[i].getName().equals(name)){
					break;
				}
			}
			if(Procedure.sizeOfGoods == i){
				System.out.println("您要下架的商品不存在，请重新输入");
			}else{
				realSubGood(good, i);//真正实现下架
				return;
			}
		}
	}
	
	public void reviseGood(Goods[] good){//实现商品修改
		realWatch(good);
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("请输入要修改的商品的 名称 ");
			String name = sc.next();
			int i = 0;
			for(; i<Procedure.sizeOfGoods; i++){
				good[i].getName().equals(name);
				break;
			}
			if(Procedure.sizeOfGoods == i){
				System.out.println("您要修改的商品不存在，请重新输入");
			}else{
				System.out.println("您要修改的商品数据为：\n名称："+good[i].getName()+"\t 价格："+good[i].getPrice()+"\t数量："+good[i].getQuantity());
				System.out.println("请输入要修改的商品的 价格 ");
				float price = sc.nextFloat();
				System.out.println("请输入要修改的商品的 数量");
				int quantity = sc.nextInt();
				good[i].setGoods(name, price, quantity);
				System.out.println("修改成功");
				System.out.println("您修改后商品数据为：\n名称："+good[i].getName()+"\t 价格："+good[i].getPrice()+"\t数量："+good[i].getQuantity());
				return;
			}
		}
	}
	
	public void set(Goods[] good){//设置菜单以及设置的实现
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("================================================");
			System.out.println("1.商品上架  2.商品下架  3.修改商品  4.返回上一层");
			System.out.println("================================================");
			int choose = sc.nextInt();
			switch(choose){
				case 1:{
					addGood(good);//商品上架的实现
					break;
				}
				case 2:{
					subGood(good);//商品下架的实现
					break;
				}
				case 3:{
					reviseGood(good);//商品修改的实现
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
	
	public float realOrder(Goods[] good){//下单的实际实现
		Scanner sc = new Scanner(System.in);
		float allPrice = 0.0F;
		while(true){
			realWatch(good);
			System.out.println("总价："+allPrice+"元");
			System.out.println("请输入你要买的商品名称(若下单结束请按 1)");
			String name = sc.next();
			if(name.equals("1")){
				return allPrice;
			}
			int i = 0;
			for(; i<Procedure.sizeOfGoods; i++){
				if(good[i].getName().equals(name)){
					break;
				}
			}
			if(Procedure.sizeOfGoods == i){
				System.out.println("您输入的商品不存在，请重新输入");
			}else{
				System.out.println("请输入你要买的商品数量");
				int num = sc.nextInt();
				if(num <= good[i].getQuantity()){
					allPrice += good[i].getPrice()*num;
					good[i].setQuantity(good[i].getQuantity()-num);
				}else{
					System.out.println("=============================警告：商品数量不足，购买失败=========================");
				}
			}
		}
	}
	
	public void placeOrder(Goods[] good){//下单菜单及实现
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("================================================");
			System.out.println("==============1.下单 2.返回上一层===============");
			System.out.println("================================================");
			int choose = sc.nextInt();
			float allPrice = 0.0F;
			switch(choose){
				case 1:{
					allPrice = realOrder(good);//下单的实际实现
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
		Goods[] goods = touchGoods();//初始化商品
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