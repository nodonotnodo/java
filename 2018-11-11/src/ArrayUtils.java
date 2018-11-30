//Java数组章节练习题
public class ArrayUtils{

    //1.计算数组中最大值
    public static int arrayMaxElement(int[] data){
		 int max = 0;
		 try{
			for(int i = 1; i<data.length; i++){
				if(data[max] < data[i]){
					max = i;
				}
			}
			return data[max];
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.print("这是程序异常的return，只是表示异常而已:");
			 return -1;
		 }
    }
    
    //2.计算数组中最小值
    public static int arrayMinElement(int[] data){
		 int min = 0;
		 try{
			for(int i = 1; i<data.length; i++){
				if(data[min] > data[i]){
					min = i;
				}
			}
			 return data[min];
		 }catch(Exception e){
			 e.printStackTrace();
			 System.out.print("这是程序异常的return，只是表示异常而已:");
			 return -1;
		 }
    }
    
    
    //3.计算数组值之和
    public static int arrayElementSum(int[] data){
		int num = 0;
		try{
			for(int i = 0; i<data.length; i++){
				num += data[i];
			}
			return num;
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("这是程序异常的return，只是表示异常而已:");
			return -1;
		}
    }
    
    //4.数组拼接
    public static int[] arrayJoin(int[] a, int[] b)throws NullPointerException{
		int[] newArr = new int[a.length+b.length];
		for(int i = 0; i<a.length; i++){
			newArr[i] = a[i];
		}
		for(int i = a.length; i<newArr.length; i++){
			newArr[i] = b[i-a.length];
		}
        return newArr;
    }

    //5.数组截取
    //[start, end)
    public static int[] arraySub(int[] data, int start , int end)throws NullPointerException{
		if((start<0)||(end<0)||(start>=data.length)){
			System.out.println("截取失败");
			return null;
		}
		if(end > data.length){
			System.out.print("执行了这一步\n");
			end = data.length;
		}
		int[] getArr = new int[end - start];
		for(int i = 0; i<end-start; i++){
			getArr[i] = data[i+start];
		}
        return getArr;
    }
    
    //6.数组打印
    public static void printArray(int[] data){

		try {
			for(int i = 0; i<data.length; i++){
				System.out.print(data[i]+"\t");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("这是程序异常的return，只是表示异常而已:");
			return;
		}
	}
    
    //7.数组反转
    // 比如：[1,2,3,4] => [4,3,2,1]
    public static void printReversal(int[] data)throws NullPointerException{
        int left = 0,right = data.length-1;
		while(left < right){
			data[left] = data[left] ^ data[right];
			data[right] = data[left] ^ data[right];
			data[left] = data[left] ^ data[right];
			// int tmp = data[left];
			// data[left] = data[right];
			// data[right] = tmp;
			left++;
			right--;
		}
    }
    
    public static void main(String[] args){
		int[] arr = new int[]{1,2,3,4,5};
		int[] arr1 = new int[]{6,7,8,9,10};
		System.out.print("数组arr:");
		printArray(arr);
		int maxNumInArr = arrayMaxElement(null);
		int minNumInArr = arrayMinElement(arr);
		System.out.println("数组arr最大值："+maxNumInArr+"\t\n数组arr最小值："+minNumInArr);
		int sumOfArr = arrayElementSum(arr);
		System.out.println("数组arr之和："+sumOfArr);
		System.out.print("数组arr1:");
		printArray(arr1);
		printArray(arrayJoin(arr,arr1));
		System.out.print("数组arr从[2,6)截取结果：");
		printArray(arraySub(arr,2,6));
		printArray(arr);
		printReversal(arr);
		System.out.println("数组arr的反转结果为：");
		printArray(arr);
    }
}