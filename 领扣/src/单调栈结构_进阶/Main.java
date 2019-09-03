package 单调栈结构_进阶;

import java.util.Scanner;

/*

题目描述
    给定一个可能含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
输入描述:
    第一行输入一个数字 n，表示数组 arr 的长度。
    以下一行输入 n 个数字，表示数组的值
输出描述:
    输出n行，每行两个数字 L 和 R，如果不存在，则值为 -1，下标从 0 开始。

输入
    7
    3 4 1 5 6 2 7
输出

    -1 2
    0 2
    -1 -1
    2 5
    3 5
    2 -1
    5 -1

 */

public class Main{

    public static void main(String args[]){

        Scanner sc = new Scanner(System.in);
        //数组长度
        int size = sc.nextInt();
        //数组初始化
        int[] arr = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = sc.nextInt();
        }

        StringBuilder sb = new StringBuilder(size * 16);

        //对每一个数组元素进行判断
        for(int i=0; i<arr.length; i++){

            int left = -1;
            int right = -1;
            //取左边值
            left = i-1;
            while(left >= 0){
                if(arr[left] < arr[i]){
                    break;
                }else{
                    left--;
                }
            }
            sb.append(left).append(" ");
            //取右边值
            right = i+1;
            while(right < arr.length){
                if(arr[right] < arr[i]){
                    break;
                }else{
                    right++;
                }
            }
            if(right >= arr.length){
                right = -1;
            }
            sb.append(right).append("\n");
        }
        System.out.print(sb.toString());
    }

}