import java.util.Scanner;

class CheckStand{ 
	
	private static int size = 0;//商品个数
	private static int capacity = 10;//货架容量
	
	private int num;//商品编号
	private String name;//商品名称
	private float price;//商品价格
	
	// public CheckStand(){
		// System.out.println("写入失败，请输入正确格式：商品名称 商品价格");
	// }
	
	public CheckStand(String name, float price){
		this.name = name;
		this.price = (float)price;
	}
	
	public int getCapacity(){
		return CheckStand.capacity;
	}
	
	public void setSize(int size){
		CheckStand.size = size;
	}
	
	public int getSize(){
		return CheckStand.size;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(float price){
		this.price = price;
	}
	
	public String getName(){
		return this.name;
	}
	
	public float getPrice(){
		return this.price;
	}
	
	// public static void main(String[] args){
		// //1.查看  2.设置  3.下单  4.修改  5.退出
		
	// }
}

class Watch1 extends CheckStand{
	
	public Watch1(String name, float price){
		super(name,price);
		System.out.println("Watch1构造方法");
	}
	
	public void browse(CheckStand[] things){
		System.out.println("编号\t名称\t价格");
		for(int i = 0; i<super.getCapacity(); i++){
			System.out.println((i+1)+"\t"+getName()+"\t"+getPrice());
		}
		//打印菜单
		printMenu();
		//菜单选择
		chooseForMenu(things);
	}
	
	//商品设置
	public void setThings(CheckStand[] things){
		System.out.println("编号\t名称\t价格");
		for(int i = 0; i<super.getCapacity(); i++){
			System.out.println((i+1)+"\t"+getName()+"\t"+getPrice());
		}
		System.out.println("=================\n请输入商品信息：商品名 商品价格\n=================");
		Scanner sc = new Scanner(System.in);
		String nameOfThing = sc.next();
		float priceOfThing = sc.nextFloat();
		if(things[getSize()] == null){
			things[getSize()] = new Watch1(nameOfThing, priceOfThing);
		}else{
			things[getSize()].setName(nameOfThing);
			things[getSize()].setPrice(priceOfThing);
		}
		// System.out.println(getSize());
		// System.out.println(things);
		// System.out.println(things[getSize()]);
		// System.out.print(getSize()+"-->");
		setSize(getSize()+1);
		// System.out.println(getSize());
		//打印菜单
		printMenu();
		//菜单选择
		chooseForMenu(things);
	}
	
	
	//菜单选择
	public void chooseForMenu(CheckStand[] things){
		while(true){
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt();
			if(1 == i){
				System.out.println("这是1.查看");
				//查看方法
				browse(things);
				break;
			}
			else if(2 == i){
				System.out.println("这是2.设置");
				//商品设置
				setThings(things);
				break;
			}
			else if(3 == i){
				System.out.println("这是3.下单");
				break;
			}
			else if(4 == i){
				System.out.println("这是4.修改");
				break;
			}
			else if(5 == i){
				System.out.println("这是5.退出");
				System.out.println("程序结束");
				return;
			}
			else{
				System.out.println("输入指令错误，请重新输入");
			}
		}
	}
	
	//打印菜单
	public static void printMenu(){
		System.out.println("************************************************");
		System.out.println("	  1.查看  2.设置  3.下单  4.修改  5.退出     ");
		System.out.println("************************************************");
		System.out.println("==========================================================================");
	}
	
	// //初始化货架
	// public CheckStand[] thingsInit(){
		// CheckStand[] things = new Watch1[getCapacity()];
		// System.out.println(getCapacity()+"\t"+things);
		// return things;
	// }
	
	public void main1(CheckStand[] things){
		//打印菜单
		printMenu();
		
		//初始化货架
		// CheckStand[] things = thingsInit();
		System.out.println(things);
		
		//菜单选择
		chooseForMenu(things);
		
		
	}
}

public class Test{
	public static void main(String[] args){
		
		CheckStand[] things = new Watch1[CheckStand.capacity];
		things.main1(things);
	}
}