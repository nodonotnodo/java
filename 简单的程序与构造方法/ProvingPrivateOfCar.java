public class ProvingPrivateOfCar{
	public static void main(String[] args){
		Car car2 = new Car("2中国","2东风");
		car2.color = "绿色";
		car2.speed = "150km/h";
		System.out.println(car2.getCarInfo());
		//以上操作均可实现。但是Car类中的maker和brand属性被private修饰过，我们来试试操作它们。
		// System.out.println(car2.maker+car2.brand);
		//此时编译错误，错误为：maker和brand只可以在类Car中进行访问
		System.out.println(car2.getBrand());
		// car2.brand = "南风";
		// 想要直接修改brand属性也是不被允许的，因为当前类并没有权限访问和修改brand这个属性
		
		/*
			总结，当一个类中的属性被private修饰后，其它类将再也无法访问到这个属性，更不要说修改它。
			其它类此时只能通过调用自身所有有权限调用的方法（这个方法有权限访问修改被private修改的属
			性）来访问和修改这个属性。
			
		*/
		car2.setBrand("南风");
		System.out.println(car2.getCarInfo());
		car2.setMaker("英国");
		System.out.println(car2.getCarInfo());
	}
}