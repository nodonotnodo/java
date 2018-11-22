//String特殊类
public class Test1{

	/*
	String类的两种实例化方式：
		1.直接赋值	2.通过构造方法String()来进行实例化
		区别：直接赋值在赋值完毕后会将堆空间上的内容放进字符串对象池，而构造方法会在堆上开辟一块空间。

		注意：String str1 = "Hello"，其中"Hello"实际上也是String类的实例化对象,它是一个匿名对象，先建立了"Hello"这个String的实例化对象后，
			在将他赋值给str1。
			String str1 = new String("Hello"),其中"Hello"也是一个对象，先建立了"Hello"这个实例化对象，之后在将其值赋给了
			为str1开辟好的堆空间上。所以此处开辟了两块空间。

	==:它是对变量数值的比较，而使用它对String类的实例化对象比较时，它实际上比较的是对象在栈空间上的内容（实际上就是对象在堆空
		间上开辟的空间的地址）。
	equals():它是String类中的一个成员方法，它比较的是String类对象的内容。
	*/
	public static void code1(){
		String str1 = "Hello";
		str1 = str1+" world";
		str1 += "!!!";//字符串不可改变，实际上str1的每次改变都会在堆上重新开辟空间，重新赋值，最终不进入对象池。
		String str2 = "Hello world!!!";//直接赋值，str2的值在堆空间的字符串对象池中
		//使用new构造方法赋值，str3的值在堆空间中新开辟空间，没有在字符串对象池中
		String str3 = new String("Hello world!!!").intern();//intern():手动入池方法
		System.out.println("str1:"+str1+"\n"+"str2:"+str2+"\nstr3:"+str3);
		System.out.println("str1==str2\t"+(str1==str2));//在字符串比较时，==比较的是字符串变量在栈空间中所存的堆空间的地址。
		System.out.println("str1==str3\t"+(str2==str3));
		System.out.println("str1.equals(str2)\t"+(str1.equals(str2)));
		System.out.println("str2.equals(str3)\t"+(str2.equals(str3)));

	}

	//字符，字符数组与字符串
	public static void code2(){
		char[] array1 = {'H','e','l','l','o'};
		String str1 = new String(array1);//重载的构造方法：直接将一个字符数组变为字符串
		System.out.println("str1:\t"+str1);
		String str2 = new String(array1, 1, 3);//重载的构造方法：将一个字符数组变为字符串，并且给出变化的起始位置和变化字符个数。
		System.out.println("str2:\t"+str2);
		char c1 = str1.charAt(0);
		System.out.println("str1.charAt(0)结果:\t"+c1);//charAt()是String类中提供的方法，可以将字符串的转为字符。
		char[] array3 = str1.toCharArray();
		System.out.print("str1.toCharArray()结果:\t");//toCharArray()是String类中提供的方法，可以将字符串转换为字符数组。
		printArray(array3);
	}

	//打印字符数组
	public static void printArray(char[] array){
		if(null == array){
			System.out.println("地址为空");
			return;
		}
		for(int i = 0; i<array.length; i++){
			if(array.length-1 == i){
				System.out.print(array[i]);
			}else{
				System.out.print(array[i]+",");
			}
		}
		System.out.println();
	}

	public static void printArray(String[] array){
		if(null == array){
			System.out.println("地址为空");
			return;
		}
		for(int i = 0; i<array.length; i++){
			if(array.length-1 == i){
				System.out.print(array[i]);
			}else {
				System.out.print(array[i] + "\t");
			}
		}
		System.out.println();
	}

	//判断一个字符串是否全部由数字组成
	public static boolean isNumArrayy(String str){
		if(null == str){
			return false;
		}else if("".equals(str)){
			return false;
		}else{
			for(int i = 0; i<str.length(); i++){
				if((str.charAt(i) < '0')||(str.charAt(i) > '9')){
					return false;
				}
			}
			return true;
		}
	}

	public static void codeForIaNumArray(){
		String str2 = new String("");
		String str1 = new String("132431");
		String str3 = null;
		String str4 = new String("34dsfdig");
		System.out.println("str1:\t"+str1+"\t它是否是一个由数字组成的字符串:\t"+isNumArrayy(str1));
		System.out.println("str2:\t"+str2+"\t它是否是一个由数字组成的字符串:\t"+isNumArrayy(str2));
		System.out.println("str3:\t"+str3+"\t它是否是一个由数字组成的字符串:\t"+isNumArrayy(str3));
		System.out.println("str4:\t"+str4+"\t它是否是一个由数字组成的字符串:\t"+isNumArrayy(str4));
	}

	//字节与字符串
	public static void code3(){
		String str1 = "Hello world!!!";
		System.out.println("str1:\t"+str1);
		byte[] byte1 = str1.getBytes();
		System.out.println("byte[] byte1 = str1.getBytes():\t"+byte1);//getbytes()是String类中提供的方法，将一个字符串转化为字节数组
		String str2 = new String(byte1);
		System.out.println("str2:\t"+str2);//重载的构造方法：将一个字节数组赋给字符串。
		String str3 = new String(byte1, 0, 10);
		System.out.println("str3:\t"+str3);//重载的构造方法：将一个字节数组赋给字符串，给定起始位置，和转换的字节个数。
	}

	//字符串比较
	public static void code4(){
		String str1 = "HelloWorld!!!";
		String str2 = "Helloworld!!!";
		String str3 = "helloworld!!!";
		String str4 = new String("HelloWorld!!!");
		System.out.println("str1:\t"+str1+"\nstr2:\t"+str2+"\nstr3:\t"+str3+"\nstr4:\t"+str4);
		System.out.println("str1 == str4:"+(str1 == str4)+"\t\tstr1.equals(str4):"+(str1.equals(str4)));
		System.out.println("str1.equals(str2):"+(str1.equals(str2))+"\t\tstr1.equalsIgnoreCase(str2):"+(str1.equalsIgnoreCase(str2)));
		//equalsIgnoreCase()是String类提供的方法，在比较两个字符串时忽略大小写。
		System.out.println("str1.compareTo(str2):"+(str1.compareTo(str2)));
		//compareTo()是String类提供的方法，比较两个字符串的大小。
	}

	//字符串查找
	public static void code5(){
		String str1 = "HelloWorld!!!";
		String str2 = "d!";
		String str3 = "He";
		String str4 = "!!!";
		System.out.println("str1:\t"+str1+"\nstr2:\t"+str2+"\nstr3:\t"+str3+"\nstr4:\t"+str4);
		System.out.println("str1.contains(str2):"+(str1.contains(str2)));
		//contains()是String类提供的方法，判断一个字符串是否存在，返回值为boolean型。
		System.out.println("str1.indexOf(str2):"+(str1.indexOf(str2)));
		//indexOf()是String类提供的方法，判断一个字符是否存在，找到返回索引，未找到返回-1。
		System.out.println("str1.indexOf(str2, 10):"+(str1.indexOf(str2, 10)));
		//indexOf()方法的重载，从指定位置开始判断一个字符是否存在，找到返回索引，未找到返回-1。
		System.out.println("str1.lastIndexOf(str2):"+(str1.lastIndexOf(str2)));
		//lastIndexOf()是String类提供的方法，由后向前判断一个字符是否存在，找到返回索引，未找到返回-1。
		System.out.println("str1.lastIndexOf(str2, 8):"+(str1.lastIndexOf(str2, 8)));
		//lastIndexOf()方法的重载，从指定位置由后向前判断一个字符是否存在，找到返回索引，未找到返回-1
		System.out.println("str1.startWith(str3):"+(str1.startsWith(str3)));
		//startsWith()是String类提供的方法，判断是否以指定字符串开头，返回值为boolean型。
		System.out.println("str1.startWith(str3, 2):"+(str1.startsWith(str3, 2)));
		//startsWith()方法的重载，从指定位置判断是否以指定字符串开头，返回值为boolean型。
		System.out.println("str1.endsWith(str4):"+(str1.endsWith(str4)));
		//endsWith()是String类提供的方法，判断是否以指定字符串结尾。
	}

	//字符串替换
	public static void code6(){
		String str1 = "Helloworld !!!";
		String str2 = "o";
		System.out.println("str1:\t\t\t\t"+str1);
		System.out.println("str1.replaceFirst(str2,\"l\"):\t"+str1.replaceFirst(str2,"l"));
		//replaceFirst()是String类提供的方法，以新的字符串替换字符串中的第一个指定字符串
		System.out.println("str1.replaceAll(str2,\"l\"):\t"+str1.replaceAll(str2,"l"));
		//replaceAll()是String类提供的方法，以新的字符串替换字符串中的所有指定字符串
	}

	//字符串拆分
	public  static void code7(){
		String str1 = "http://www.githup/nodonotnodo/java";
		System.out.println("str1:\t\t\t\t"+str1);
		String[] str2 = str1.split("/");
		//split()是String类提供的方法，将字符串以指定字符串拆分为字符串数组。
		printArray(str2);
		String[] str3 = str1.split("/", 4);
		//split()方法的重载，将字符串以指定字符串拆分为指定个数字符串数组。
		printArray(str3);
	}

	//字符串截取
	public static void code8(){
		String str1 = "helloworld";
		System.out.println("str1:\t"+str1);
		System.out.println("str1.substring(5):\t"+str1.substring(5));
		//substring()是String类提供的方法，得到一个从指定位置截取到结尾的字符串。
		System.out.println("str1.substring(2, 5):\t"+str1.substring(2, 5));
		//substring()方法的重载，得到一个指定范围截取到的字符串。注意：范围为：[begin, end)
	}

	//其它方法
	public static void code9(){
		String str1 = "  heLLo World !!!  ";
		System.out.println("str1:\t["+str1+"]");
		System.out.println("str1.trim():\t["+str1.trim()+"]");
		//trim()是String类提供的方法，去掉字符串的头部和尾部空格。
		System.out.println("str1.toUpperCase():\t["+str1.toUpperCase()+"]");
		//toUpperCase()是String类提供的方法，将字符串转换为大写。
		System.out.println("str1.toLowerCase():\t["+str1.toLowerCase()+"]");
		//toLowerCase()是String类提供的方法，将字符串转换为小写。
		System.out.println("str1.concat(\"123\"):\t["+str1.concat("123")+"]");
		//concat()是String类提供的方法，将字符串拼接，等同于"+"。
		System.out.println("str1.isEmpty():\t"+str1.isEmpty());
		//isEmpty()是String类提供的方法，判断字符串是否是一个空""字符串。
	}
	public static void main(String[] args){
		//code1();
		//code2();
		//codeForIaNumArray();
		//code3();
		//code4();
		//code5();
		//code6();
		//code7();
		//code8();
		code9();
	}
}