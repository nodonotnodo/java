package onlineTest;

public class Function2 {

    public static int binInsert(int n, int m, int j, int i) {
        // write code here

        //得到m的前i-j+1位
        int tmp2 = 0;
        int tmp3 = 1;
        for(int k=0; k<i-j+1; k++){
            tmp3 = 1 << k;
            tmp2 = tmp2 | tmp3;
        }
        int tmp4 = tmp2 & m;

        //将得到m的位数变为它所应在的位
        for(int k=0; k<j; k++){
            tmp4 = tmp4 << 1;
            tmp2 = tmp2 << 1;
        }
        n = n & (~tmp2);

        return tmp4 | n;

    }

    public static void main(String[] args){
        System.out.println(binInsert(1024,19,2,6));
    }

}
