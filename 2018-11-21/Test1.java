public class Test1{
	
	public static void main(String[] args){
		String str1 = "Hello";
		str1 = str1+" world";
		str1 += "!!!";//字符串不可改变，实际上str1的每次改变都会在堆上重新开辟空间，重新赋值，最终不进入对象池。
		String str2 = "Hello world!!!";//直接赋值，str2的值在堆空间的字符串对象池中
		//使用new构造方法赋值，str3的值在堆空间中新开辟空间，没有在字符串对象池中
		String str3 = new String("Hello world!!!").intern();//intern():手动入池方法
		System.out.println("str1:"+str1+"\n"+"str2:"+str2+"\nstr3:"+str3);
		System.out.println("str1==str2\t"+(str1==str2));//在字符串比较时，==比较的是字符串变量在栈空间中所存的堆空间的地址。
		System.out.println("str1==str3\t"+(str2==str3));
	}
}