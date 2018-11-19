//游戏1：
//	猜数字：随机生成[0,100）。

public class Test1{
	
	public static void code1(){
		Game1 game = new Game1();
		game.game1();
	}
	
	public static void code2(){
		Real2 real = new Real2();
		real.sendGift();
		Proxy proxy = new Proxy(real);
		proxy.proxy();
	}
	
	public static void main(String[] args){
		code1();
		// code2();
	}
}

class Game1{
	public void game1(){
		while(true){
			System.out.println("欢迎来到猜数字游戏\n\t1.开始游戏\t2.退出");
			java.util.Scanner sc = new java.util.Scanner(System.in);
			int choose = sc.nextInt();
			if(1 == choose){
				java.util.Random rd = new java.util.Random();
				int key = rd.nextInt(100);
				System.out.println("系统已经随机生成了一个数字，现在开始猜数字吧");
				int userNum = 101;
				do{
					System.out.println("==========输入-1可以直接退出游戏==================\n请输入您猜的数字");
					userNum = sc.nextInt();
					if(-1 == userNum){
						return;
					}
					if(userNum > key){
						System.out.println("你猜的数字为："+userNum+"\t大于正确答案");
					}
					if(userNum < key){
						System.out.println("你猜的数字为："+userNum+"\t小于正确答案");
					}
				}while(key != userNum);
				System.out.println("你猜的数字为："+userNum+"\t就是正确答案，游戏胜利");
			}else if(2 == choose){
				System.out.println("游戏退出");
				return;
			}else{
				System.out.println("请输入正确指令");
			}
		}
	}
}

//送礼物sendgift（一个代理模式的实现）
//张三送一个礼物   要快递员帮他送

interface SendGift{
	void sendGift();
}

abstract class Real1 implements SendGift{
	public String nameOfUser(){
		System.out.println("请输入要送礼物的人");
		java.util.Scanner sc = new java.util.Scanner(System.in);
		String name = sc.next();
		return name;
	}
}

class Real2 extends Real1{
	public void sendGift(){
		String name = nameOfUser();
		System.out.println("这是 真正业务实现类（被代理类）");
		System.out.println(name+"将礼物送给他的女朋友");
	}
}

class Proxy{
	
	public final Real2 real;
	
	public Proxy(Real2 real){
		this.real = real;
	}
	
	public void proxy(){
		real.sendGift();
		System.out.println("这是 辅助真正业务实现类（代理类）");
		System.out.println("张三买了礼物");
		System.out.println("张三联系物流快递礼物");
		System.out.println("物流指派快递员给张三的女朋友送礼物");
		System.out.println("快递将礼物送到张三的女朋友后，告诉张三");
	}
}