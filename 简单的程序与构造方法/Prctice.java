public class Prctice{
	
	public static void Code1(){
		
		int numA = 10;
		int numB = (int)10.5;
		String result = "计算结果:" + numA + numB;  //这个+号表示的是字符连接。
		System.out.println(result);
		String result1 =  + (numA + numB) + "计算结果：";
		System.out.println(result1);
		System.out.println("比特科技\n\t第一节\"java\"课开课了");
		// String changeOfNumA = numA;
		// System.out.println(changeOfNumA);
		
	}
	
	public static void mulTable(){
		
		for(int i = 1; i<10; i++){//第一个乘数，并且也是行控制		
			for(int j = 1; j<i; j++){//第二个乘数
				System.out.print(i+"*"+j+"="+i*j+"\t");
			}
			System.out.println();
		}
		
	}
	
	//0的阶乘也是0
	//如果n<0，java中可以抛出一个异常。
	public static int factorial(int n){
		
		if(n < 0){
			System.out.printf("赋值错误\n");
			return -1;
		}
		if(1 == n || n == 0){
			return 1;
		}
		return n * factorial(n-1);
	}
	
	public static void main(String[] args){
		int n = 100;
		int result = factorial(100);
		System.out.println(n+"!="+result);
		
	}
	
}