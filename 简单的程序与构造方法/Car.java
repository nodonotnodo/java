public class Car{

	private String maker;//产地
	public String color;//颜色
	public String speed;//速度
	private String brand;//品牌
	
	public String getMaker(){
		return this.maker;
	}
	
	public void setMaker(String maker){
		this.maker = maker;
	}
	
	public String getBrand(){
		return this.brand;
	}
	
	public void setBrand(String brand){
		this.brand = brand;
	}
	
	public Car(String maker, String brand){
		this.maker = maker;
		this.setBrand(brand);
	}
	
	public String getCarInfo(){
		return ("\t产地："+this.maker+"\t速度："+this.speed+"\t颜色："+this.color+"\t品牌："+this.brand);
	}
	
	public static void main(String[] args){
		// Car car1 = new Car("中国","解放");
		// car1.color = "红色";
		// car1.speed = "300km/h";
		// System.out.println(car1.getCarInfo());
		// car1.setBrand("国产");
		// System.out.println(car1.getCarInfo());
		// System.out.println(car1.brand);
		// car1.brand = "大众";
		// System.out.println(car1.getCarInfo());
	}
	
}