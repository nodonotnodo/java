import java.util.Scanner;

public class Practice{
	// 请手写如下所示格式的个人信息 Java 代码：
// Name: Mary 			Post: HR Director
// Sex: female 			Age: 26
// Tel: 18081318888 	Adress: DaTun road no. 6,chaoyang,Beijing

	public static void practice1(){
		System.out.println("Name:Mary\t\tPost:HR Directot");
		System.out.println("Sex:female\t\tAge:26");
		System.out.println("Tel:18001318888\t\tAdress:Datun road no. 6,chaoyang,Beijing");
	}
	
	/*
	购物计算
		 小明去商场购买Ｔ恤 2 件，运动鞋 1 双，网球拍 2 个，其中Ｔ恤价格为 245 元，运动鞋
		价格 370 元，网球拍价格 345.5 元，由于商场打折，购买 2 件以上打 8 折，计算下小明一个
		消费了多少钱？
	*/
	public static void practice2(){
		int numOfTshirt = 2;
		int numOfShoe = 1;
		int numOfRacket = 2;
		float sumOfAllThings = numOfTshirt*245 + numOfShoe*370 + numOfRacket*345.5F;
		float realSumOfAllThings = 0.8F * sumOfAllThings;
		System.out.println("小明一共花费："+realSumOfAllThings+"元");
	}
	
	/*
		交换两个数，1）不使用第三方变量。2）使用第三方变
	*/
	public static void practice3(){
		int a = -1,b = 20;
		System.out.println("交换前：a = "+a+"\tb = "+b);
		// int tmp = a;
		// a = b;
		// b = tmp;
		a = a ^ b;
		b = a ^ b;
		a = a ^ b;
		System.out.println("交换后：a = "+a+"\tb = "+b);
	}
	
	/*
		  输入本金，算出1年，2年，3年，5年后的本息是多少？（1年利息2.25/100，2
		年利息为2.7/100，3年利息3.24/100，5年利息3.6/100）
	*/
	public static void practice4(float principal){
		float princopalAndInterest = principal + principal*2.25F/100;
		System.out.println("一年后的本息："+princopalAndInterest);
		princopalAndInterest = princopalAndInterest + princopalAndInterest*2.7F/100;
		System.out.println("二年后的本息："+princopalAndInterest);
		princopalAndInterest = princopalAndInterest + princopalAndInterest*3.24F/100;
		System.out.println("三年后的本息："+princopalAndInterest);
		princopalAndInterest = princopalAndInterest + princopalAndInterest*3.24F/100;
		princopalAndInterest = princopalAndInterest + princopalAndInterest*3.6F/100;
		System.out.println("二年后的本息："+princopalAndInterest);
	}
	
	/*
		编写一个程序，根据矩形的长 length=6.9m（float 类型）和 width=10m（int 类型），计算
	矩形周长和面积。（请在周长和面积的值后面加上它们个自的单位“m”和“㎡”周长必须
	为 float 类型，面积必须为 int 类型。周长=2*（长+宽）；面积=长*宽。）
	*/
	public static void practice5(float length, int width){
		float perimeter = 2*(length + width);
		int area = (int)length * width;
		System.out.println("周长为："+perimeter+"m\n面积为："+area+"㎡");
	}
	
	/*
	从控制台接收一个整数，计算出它的平方并输出
	*/
	public static void practice6(int a){
		System.out.println(a+"的平方为："+a*a);
	}
	
	public static void main(String[] args){
		practice1();
		practice2();
		practice3();
		float principal = 230000F;
		practice4(principal);
		float length = 6.9F;
		int width = 10;
		practice5(length, width);
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个int型数字");
		int a = sc.nextInt();
		practice6(a);
	}
}