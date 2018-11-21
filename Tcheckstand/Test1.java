public class Test1{
	
	// public Test1(){
		// System.out.println("这是父类的无参构造方法");
	// }
	
	// public Test1()
	
	public static void main(String[] args){
		Father child1 = new Child("王二");
	}
	
}

class Father{
	
	public Father(){
		System.out.println("这是父类的无参构造方法");
	}
	
	public Father(String name){
		System.out.println("这是父类的无参构造方法"+name);
	}
	
}

class Child extends Father{
	public Child(String name){
		super(name);
		System.out.println("这是子类的无参构造方法"+name);
	}
}