import java.util.Scanner;
/*
	这是一个买手机的简单工厂模式:
								优点：代码简单，添加商品时用户端不用修改（解耦）
								缺点：每次添加商品时都要对代码进行修改，不符合OCP开闭原则
*/

/* public class Test3{
	
	public static void main(String[] args){
		Factory user = new Factory();
		user.buyPhone();
	}
	
}

interface IPhone{
	
	void buyPhone();
}

class Factory{
	public void buyPhone(){
		Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("请输入您所要购买的手机型号；‘Mi’ ‘Apple’ ‘HuaWei’\n退出：‘q’");
			String order = sc.nextLine();
			if(order.equals("q")){
				return;
			}
			switch(order){
				case "Mi":{
					IPhone buy = new Mi();
					buy.buyPhone();
					break;
				}
				case "Apple":{
					IPhone buy = new Apple();
					buy.buyPhone();
					break;
				}
				case "HuaWei":{
					IPhone buy = new HuaWei();
					buy.buyPhone();
					break;
				}
				default:{
					break;
				}
			}
		}
	}
}

class Mi implements IPhone{
	public void buyPhone(){
		System.out.println("买了一个小米手机");
	}
}

class Apple implements IPhone{
	public void buyPhone(){
		System.out.println("买了一个苹果手机");
	}
}

class HuaWei implements IPhone{
	public void buyPhone(){
		System.out.println("买了一个华为手机");
	}
} */



/*
	这是一个买手机的工厂方法模式:
								优点：每次添加商品时只要对原代码进行扩展，符合OCP开闭原则
								缺点：若要开辟其它产品属性，仍然需要修改代码，不符合OCP开闭原则
*/

/* public class Test3{
	
	public static void main(String[] args){
		IPhone user = new Mi();
		user.buyPhone();
	}
	
}

interface IPhone{
	
	void buyPhone();
}

class Mi implements IPhone{
	public void buyPhone(){
		System.out.println("买了一个小米手机");
	}
}

class Apple implements IPhone{
	public void buyPhone(){
		System.out.println("买了一个苹果手机");
	}
}

class HuaWei implements IPhone{
	public void buyPhone(){
		System.out.println("买了一个华为手机");
	}
}  */


/*
	这是一个买手机的工厂抽象模式:
								优点：若要开辟其它产品属性，直接在工厂中添加就可以。
								缺点：扩展商品属性要修改代码，不符合OCP开闭原则
*/

public class Test3{
	
	public static void print(Factory user){
		user.PhoneInfo();
		user.buyPhone();
	}
	
	public static void main(String[] args){
		Factory user = new Apple();
		print(user);
	}
	
}

interface IPhone{
	
	void buyPhone();
}

abstract class Factory implements IPhone{
	abstract public void PhoneInfo();
}

class Mi extends Factory{
	public void buyPhone(){
		System.out.println("这是一个小米手机");
	}
	public void PhoneInfo(){
		System.out.println("小米手机是安卓系统");
	}
}

class Apple extends Factory{
	public void buyPhone(){
		System.out.println("这是一个苹果手机");
	}
	public void PhoneInfo(){
		System.out.println("苹果手机是安卓ios系统");
	}
}

class HuaWei extends Factory{
	public void buyPhone(){
		System.out.println("这是一个华为手机");
	}
	public void PhoneInfo(){
		System.out.println("华为手机是安卓系统");
	}
}