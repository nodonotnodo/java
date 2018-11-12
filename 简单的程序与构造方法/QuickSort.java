public class QuickSort{
	
	public static void realQuickSort(int data[], int left, int right){
		if(left >= right){
			return;
		}
		int div = left + (right - left)/2;//找到基准坐标。
		int tmp = data[div];//保存基准值
		data[div] = data[right];//将数组最右边数据放在基准值位置，同时将基准值保存。
		int leftFinger = left;//小于基准值区的指针。
		int rightFinger = right;//大于基准值区的指针。
		while(leftFinger < rightFinger){//当左指针等于右指针时，本轮排序结束
			while((leftFinger < rightFinger)&&(data[leftFinger] <= tmp)){
				leftFinger++;
			}
			data[rightFinger] = data[leftFinger];
			while((leftFinger < rightFinger)&&(data[rightFinger] > tmp)){
				rightFinger--;
			}
			data[leftFinger] = data[rightFinger];
		}
		data[leftFinger] = tmp;//放入基准值。
		realQuickSort(data, left, leftFinger-1);//左子区快排
		realQuickSort(data, leftFinger+1, right);//右子区快排
	}
	
	public static void quickSort(int data[], int size){
		/* for(int i = 0; i < size; i++){
			System.out.print(data[i]+"\t");
		} */
		realQuickSort(data, 0, size-1);
	}
	
	public static void main(String[] args){
		int[] data = new int[]{23,45,12,56,76,32,3,3,45,65,23};
		int size = data.length;
		System.out.println("这个数组的长度为："+size); 
		for(int i = 0; i < size; i++){
			System.out.print(data[i]+"\t");
		}
		System.out.println();
		quickSort(data, size);
		for(int i = 0; i < size; i++){
			System.out.print(data[i]+"\t");
		}
	}
	
}