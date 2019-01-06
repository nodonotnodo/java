package prime_number_of_set_bits_in_binary_representation;

public class Solution {

    /*
    给定两个整数 L 和 R ，找到闭区间 [L, R] 范围内，计算置位位数为质数的整数个数。

（注意，计算置位代表二进制表示中1的个数。例如 21 的二进制表示 10101 有 3 个计算置位。还有，1 不是质数。）

示例 1:

输入: L = 6, R = 10
输出: 4
解释:
6 -> 110 (2 个计算置位，2 是质数)
7 -> 111 (3 个计算置位，3 是质数)
9 -> 1001 (2 个计算置位，2 是质数)
10-> 1010 (2 个计算置位，2 是质数)
     */

    public static int countPrimeSetBits(int L, int R) {
        int data = L;
        int result = 0;
        while(data<=R){
            if(data == 257){
                int c = 2;
            }
            int num = 0;
            for(int i=0; i<32; i++){
                if((1 & (data>>i)) == 1){
                    num++;
                }
            }
            if(isPrime(num)){
                result++;
            }
            data++;
        }
        return result;
    }

    public static boolean isPrime(int num){
        if(num == 1 || num == 0){
            return false;
        }
        if(num == 2 || num == 0){
            return false;
        }
        for(int i=2; i<num; i++){
            if(num%i == 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countPrimeSetBits(244,269));
    }
}
