
/*

--购物流程
	
	1.天猫
	2.京东
	3.唯品会
	4.拼多多
	
	流程：浏览F-->选择F-->客服C-->配送F-->结算C（积分，优惠券）-->支付C（支付宝，微信，银行卡，货到付款）-->查看订单C

*/
import java.util.Scanner;

public class Test1{
	
	public static void main(String[] args){
		TianMao user1 = new TianMao("张三");
		user1.shop();
		user1.setIsCallService();
		user1.shop();
	}
	
}

abstract class Shopping{
	
	private boolean isCallService1;
	
	public Shopping(){
	}
	
	public final void shop(){
		System.out.println("用户：尊敬的"+this.getUserNmae()+"先生");
		//浏览商品是必须的
		this.browseGoods();
		//选择商品也是必须的
		this.chooseGoods();
		//客服是用户选择天猫还是京东
		if(isCallService()){
			this.callService();
		}
		//配送是是用户选择天猫还是京东
		this.sendGoods();
		//结算优惠给用户选择，子类控制
		this.computePrice();
		//支付方式用户选择，子类控制
		this.orderPay();
		//查看订单是必须的
		this.showOrder();
		System.out.println("=============================");
	}
	
	public void browseGoods(){
		System.out.println("用户浏览商品");
	}
	
	public void chooseGoods(){
		System.out.println("用户选择商品");
	}
	
	public abstract String getUserNmae();
	
	public abstract void callService();
	
	public abstract void sendGoods();
	
	public void computePrice(){
		System.out.println("请选择优惠方式：1.积分  2.优惠券");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		if(1 == i){
			System.out.println("用户选择了积分优惠");
		}else if(2 == i){
			System.out.println("用户选择了优惠券优惠");
		}
	}
	
	public void orderPay(){
		System.out.println("请选择支付方式：1.支付宝  2.微信  3.银行卡  4.货到付款");
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		if(1 == i){
			System.out.println("用户选择支付宝支付");
		}else if(2 == i){
			System.out.println("用户选微信支付");
		}else if(3 == i){
			System.out.println("用户选择银行卡支付");
		}else if(4 == i){
			System.out.println("用户选择货到支付");
		}
	}
	
	public void showOrder(){
		System.out.println("用户查看订单");
	}
	
	public boolean isCallService(){
		return this.isCallService1;
	}
	
	public void setIsCallService(){
		this.isCallService1 = true;
	}
}

class TianMao extends Shopping{
	private String userName;//用户姓名
	
	public TianMao(String name){
		this.userName = name;
	}
	
	public String getUserNmae(){
		return this.userName;
	}
	
	//用户选择客服
	public void callService(){
		System.out.println("用户咨询了天猫客服");
	}
	
	public void sendGoods(){
		System.out.println("用户选择了天猫配送");
	}
	
}

class JingDong extends Shopping{
	
	private String userName;//用户姓名
	
	public JingDong(String name){
		this.userName = name;
	}
	
	public String getUserNmae(){
		return this.userName;
	}
	
	//用户选择客服
	public void callService(){
		System.out.println("用户咨询了京东客服");
	}
	
	public void sendGoods(){
		System.out.println("用户选择了京东配送");
	}
	
}