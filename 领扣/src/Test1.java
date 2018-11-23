public class Test1 {

    public static void print(int[][] arr){
        if(arr.length == 0){
            System.out.println("[]");
            return;
        }
        System.out.println("[");
        for(int i = 0; i<arr[0].length; i++){
            System.out.print("[");
            for(int j = 0; j<arr[0].length; j++){
                if(j == arr[0].length-1){
                    System.out.println(arr[i][j]+"]");
                }else{
                    System.out.print(arr[i][j]+",");
                }
            }
        }
        System.out.println("]");
    }

    public static void codeForSolution1(){
        Solution1 solu = new Solution1();
        int [][] arr1 = solu.generateMatrix(3);
        print(arr1);
        int [][] arr2 = solu.generateMatrix(0);
        print(arr2);
        int [][] arr3 = solu.generateMatrix(5);
        print(arr3);
    }

    public static void main(String[] args){
        //codeForSolution1();
        Solution2 solu = new Solution2();
        System.out.println("solu.canMeasureWater(3,4,5)的结果为："+solu.canMeasureWater(3,4,5));
        System.out.println("solu.canMeasureWater(4,8,3)的结果为："+solu.canMeasureWater(4,8,3));
        System.out.println("solu.canMeasureWater(4,8,0)的结果为："+solu.canMeasureWater(4,8,0));
        System.out.println("solu.canMeasureWater(4,8,0)的结果为："+solu.canMeasureWater(0,0,0));
        System.out.println("solu.canMeasureWater(4,8,0)的结果为："+solu.canMeasureWater(3,3,3));
    }
}

/*
    给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
        示例:
        输入: 3
        输出:
        [
        [ 1, 2, 3 ],
        [ 8, 9, 4 ],
        [ 7, 6, 5 ]
        ]
*/
class Solution1 {
    public int[][] generateMatrix(int n) {
        int[][] arr = new int[n][n];
        int lRow = 0, rRow = n-1;//行
        int lColumn = 0, rColumn = n-1;//列
        int num = 1;
        while(!((lRow>rRow)&&(lColumn>rColumn))){
            int i = 0;
            for(i = lColumn; i<=rColumn; i++){
                arr[lRow][i] = num++;
            }
            lRow++;
            for(i = lRow; i<=rColumn; i++){
                arr[i][rColumn] = num++;
            }
            rColumn--;
            for(i = rColumn; i>=lColumn; i--){
                arr[rRow][i] = num++;
            }
            rRow--;
            for(i = rRow; i>=lRow; i--){
                arr[i][lColumn] = num++;
            }
            lColumn++;
        }
        return arr;
    }
}

/*
        有两个容量分别为 x升 和 y升 的水壶以及无限多的水。请判断能否通过使用这两个水壶，从而可以得到恰好 z升 的水？
        如果可以，最后请用以上水壶中的一或两个来盛放取得的 z升 水。
        你允许：
        装满任意一个水壶
        清空任意一个水壶
        从一个水壶向另外一个水壶倒水，直到装满或者倒空
        示例 1: (From the famous "Die Hard" example)
        输入: x = 3, y = 5, z = 4
        输出: True
        示例 2:
        输入: x = 2, y = 6, z = 5
        输出: False
         */
class Solution2 {//未完成
    public boolean canMeasureWater(int x, int y, int z) {
        if(z == 0){
            return true;
        }
        if((x<0)||(y<0)||((x==y)&&(x!=z))){
            return false;
        }
        if(z > x+y){//z大于x,z之和，无法得到
            return false;
        }else if(z == y){
            return true;
        }else if(z == x){
            return true;
        }else if(z == x+y){
            return true;
        }else if(z == y-x){
            return true;
        }else if(z == x-y){
            return true;
        }else{if(
                canMeasureWater(x,y-x,z)||
                        canMeasureWater(x,x-y,z)||
                        canMeasureWater(x-y,y,z)||
                        canMeasureWater(y-x,y,z))
            return true;
        }
        return false;
    }
}
