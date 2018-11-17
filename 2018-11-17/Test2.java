public class Test2{
	/*
		接口
	*/
	
	public void code1(){
		//New这个类实现了 IMessage1 和 IMessage2 这两个接口。
		/* New new1 = new New();
		new1.print1();
		new1.print2(); */
	}
	
	public void code2(){
		// New1 这个类继承了 New 这个类。
		New1 new1 = new New1();
		new1.print3();
	}
	
	public void code3(){
		// New3 是一个继承了 New2 并且实现了 IMessage1 和 IMessage2 接口的类。
		//一个类可以同时继承类和实现接口，但是必须先继承 extends 后实现接口 implements 
		
		// New3 new1 = new New3();
		IMessage1 new1 = new New3();//向上转型，子类可以为父类实例化对象。
		new1.print1();
		/* new1.print2();
		new1.print4(); */
	}
	
	public void code4(){
		//一个抽象类实现接口可以不实现接口的抽象方法，但是它必须有子类来实现接口的抽象方法。
		New6 new1 = new New6();
		new1.print7();
	}
	
	public void code5(){
		//最终证明：普通类实现接口时必须实现接口的抽象方法。
		//或者说，当一个类实现接口时，它就已经是一个抽象类了。
		New9 new1 = new New9();
		new1.print9();
	}
	
	public static void main(String[] args){
		//接口可以实现多个接口
		New10 new1 = new New10();
		new1.print1();
		new1.print2();
		new1.print10();
	}
}

//定义一个接口
//注意：接口中定义全局常量和抽象方法（jdk.1.6及以下版本）
//***:全局常量默认被public,static和final关键字修饰，方法默认被public和abstarct修饰
interface IMessage1{
	public static final String DATE = "IMessage1：这个文件的创建日期是2018-11-17";
	public abstract void print1();
}

interface IMessage2{
	public static final String THING = "IMessage2：今天我学习了Java中关于接口的一些知识";
	public abstract void print2();
}

//类实现接口
//类必须实现接口中的所有抽象方法
//一个类可以实现多个接口
class New implements IMessage1, IMessage2{
	
	public void print1(){
		System.out.println(IMessage1.DATE);
	}
	
	public void print2(){
		System.out.println(IMessage2.THING);
	}
}

class New1 extends New{
	public void print3(){
		super.print1();
		super.print2();
	}
}

class New2{
	public void print4(){
		System.out.println("这是New2这个类的方法");
	}
}

class New3 extends New2 implements IMessage1, IMessage2{
	public void print1(){
		System.out.println(IMessage1.DATE);
	}
	
	public void print2(){
		System.out.println(IMessage2.THING);
	}
}

abstract class New4 implements IMessage1, IMessage2{
	public void print5(){
		System.out.println("这个是一个抽象类New4，它继承了两个接口");
	}
}

class New5 extends New4{
	//子类继承了父类抽象类，若父类抽象类为实现了接口并且为实现接口的抽象方法，那么子类就要实现接口的抽象方法
	//可以理解为“父债子偿”
	
	public void print6(){
		System.out.println("这是一个类普通的类New5，它继承与抽象类New4，并且New4没有实现它所实现的接口的抽象方法");
	}
	
	public void print1(){
		System.out.println(IMessage1.DATE);
	}
	
	public void print2(){
		System.out.println(IMessage2.THING);
	}
}

class New6 extends New5{
	public void print7(){
		print1();
		print2();
		print5();
		print6();
	}
}

//尝试：普通类能否像抽象类一样不实现接口方法，而是由它的子类实现。
//失败。普通类不能由子类实现接口
class New7 implements IMessage1, IMessage2{
	public void print5(){
		System.out.println("这个是一个普通类New7，它继承了两个接口");
	}
	
	public void print1(){
		System.out.println(IMessage1.DATE);
	}
	
	public void print2(){
		System.out.println(IMessage2.THING);
	}
}

class New8 extends New7{
	public void print8(){
		System.out.println("这是一个类普通的类New8，它继承与普通类New7，并且New7没有实现它所实现的接口的抽象方法");
	}
	
}

class New9 extends New8{
	public void print9(){
		print1();
		print2();
		print5();
		print8();
		System.out.println("我使用来一个普通类实现两个接口，但是接口的抽象方法由这个普通类的子类来实现，但是编译错误。在New7中实现了接口的抽象方法后程序运行成功。");
	}
}

interface IMessage3 extends IMessage1, IMessage2{
	public abstract void print10();
}

class New10 implements IMessage3{
	public void print1(){
		System.out.println(IMessage1.DATE);
	}
	
	public void print2(){
		System.out.println(IMessage2.THING);
	}
	
	public void print10(){
		System.out.println("这是接口 IMessage3 的抽象方法实现");
	}
}