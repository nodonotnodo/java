package specificsorts;

/**
 * 现在有一个无序的数组，对它进行排序。
 * 要求前半部分是奇数，后半部分是偶数，
 * 并且奇数是降序，偶数是升序。
 */

public class Soulution1 {

    public static void main(String[] args) {

        int[] ints = {12,234,23,54,22,56,75,45,89,45,23,13};
        spSort(ints);
        myPrint(ints);

    }

    public static void myPrint(int[] ints){
        if(ints == null){
            System.out.println("数组为空");
        }
        StringBuilder str = new StringBuilder("数组内容为：\n");
        for(int i : ints){
            str.append(i).append(", ");
        }
        String str1 = str.toString();
        System.out.println(str1.substring(0,str1.length()-2));
    }

    private static void spSort(int[] ints) {

        //先将数组的奇数放在前面，偶数放在后面
        setNum(ints);

        //数组按照要求排序
        reSort(ints);

    }

    //排序
    private static void reSort(int[] ints) {

        int flag = 0;
        for(int i=0; i<ints.length; i++){
            if(ints[i]%2 == 0){
                flag = i;
                break;
            }
        }

        //降序排序
        bigTOSmall(ints,0,flag-1);

        //升序排序
        smallTOBig(ints, flag, ints.length-1);

    }

    private static void smallTOBig(int[] ints, int left, int right) {

        bQuickSort(ints,left,right);

    }

    private static void bigTOSmall(int[] ints, int left, int right) {
        sQuickSort(ints,left,right);
    }

    //实际奇数偶数放置
    private static void setNum(int[] ints) {
        int[] result = new int[ints.length];

        //存放奇数的下标和存放偶数的下标
        int left = 0;
        int right = result.length-1;
        for(int i=0; i<ints.length; i++){
            if(ints[i]%2 == 0){
                result[right--] = ints[i];
            }else{
                result[left++] = ints[i];
            }
        }
        for(int i=0; i<ints.length; i++){
            ints[i] = result[i];
        }
    }

    //升序快排
    public static void bQuickSort(int[] ints, int left, int right){
        if(left >= right){
            return;
        }

        int start = left;
        int end = right;

        int flag = ints[right];
        while(left < right){
            while(left<right && ints[left]<=flag){
                left++;
            }
            ints[right] = ints[left];
            while(left<right && ints[right]>=flag){
                right--;
            }
            ints[left] = ints[right];
        }
        ints[left] = flag;
        bQuickSort(ints,start,left-1);
        bQuickSort(ints,left+1,end);

    }

    //降序快排
    public static void sQuickSort(int[] ints, int left, int right){
        if(left >= right){
            return;
        }

        int start = left;
        int end = right;

        int flag = ints[right];
        while(left < right){
            while(left<right && ints[left]>=flag){
                left++;
            }
            ints[right] = ints[left];
            while(left<right && ints[right]<=flag){
                right--;
            }
            ints[left] = ints[right];
        }
        ints[left] = flag;
        sQuickSort(ints,start,left-1);
        sQuickSort(ints,left+1,end);
    }

}
