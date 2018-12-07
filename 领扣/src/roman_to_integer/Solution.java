package roman_to_integer;

/*
*罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
示例 1:
输入: "III"
输出: 3
示例 2:
输入: "IV"
输出: 4
示例 3:
输入: "IX"
输出: 9
示例 4:
输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
示例 5:
输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
*
* */

public class Solution {

    //击败50%用户
//    public static int romanToInt(String s) {
//        char[] ch = new char[]{'a','a','a','a','a','a'};
//        int result = 0;
//        for(int i = 0; i<s.length(); i++){
//            char c = s.charAt(s.length()-1-i);
//            if(c == 'I'){
//                result+=1*IsAdd(c,ch);
//            }else if(c == 'V'){
//                ch[0] = 'I';
//                result+=5*IsAdd(c,ch);
//            }else if(c == 'X'){//TODO
//                ch[0] = 'I';
//                ch[1] = 'V';
//                result+=10*IsAdd(c,ch);
//            }else if(c == 'L'){//TODO
//                ch[2] = 'X';
//                result+=50*IsAdd(c,ch);
//            }else if(c == 'C'){
//                ch[2] = 'X';
//                ch[3] = 'L';
//                result+=100*IsAdd(c,ch);
//            }else if(c == 'D'){
//                ch[4] = 'C';
//                result+=500*IsAdd(c,ch);
//            }else{
//                ch[4] = 'C';
//                ch[5] = 'D';
//                result+=1000;
//            }
//        }
//        return result;
//    }
//
//    public static int IsAdd(char c,char[] ch){
//        for(char v:ch){
//            if(c == v){
//                return -1;
//            }
//        }
//        return 1;
//    }

//击败54%用户
    public static int romanToInt(String s) {
        int result = 0;
        for(int i = 0; i<s.length(); i++){
            char c = s.charAt(s.length()-1-i);
            if(c == 'I'){
                result+=1;
            }else if(c == 'V'){
                if(i+1<s.length() && s.charAt(s.length()-2-i) == 'I'){
                    result+=4;
                    i++;
                }else{
                    result+=5;
                }
            }else if(c == 'X'){
                if(i+1<s.length() && s.charAt(s.length()-2-i) == 'I'){
                    result+=9;
                    i++;
                }else{
                    result+=10;
                }
            }else if(c == 'L'){
                if(i+1<s.length() && s.charAt(s.length()-2-i) == 'X'){
                    result+=40;
                    i++;
                }else{
                    result+=50;
                }
            }else if(c == 'C'){
                if(i+1<s.length() && s.charAt(s.length()-2-i) == 'X'){
                    result+=90;
                    i++;
                }else{
                    result+=100;
                }
            }else if(c == 'D'){
                if(i+1<s.length() && s.charAt(s.length()-2-i) == 'C'){
                    result+=400;
                    i++;
                }else{
                    result+=500;
                }
            }else{
                if(i+1<s.length() && s.charAt(s.length()-2-i) == 'C'){
                    result+=900;
                    i++;
                }else{
                    result+=1000;
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }
}
