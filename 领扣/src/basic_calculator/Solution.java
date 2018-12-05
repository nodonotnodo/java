package basic_calculator;


//public class Solution {
//
//    /*
//    * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
//    字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
//    示例 1:
//    输入: "1 + 1"
//    输出: 2
//    示例 2:
//    输入: " 2-1 + 2 "
//    输出: 3
//    示例 3:
//    输入: "(1+(4+5+2)-3)+(6+8)"
//    输出: 23
//    * */
//
//    public static void main(String[] args) {
//        String str = new String("1-(2+3)");
//        System.out.println(calculate(str));
////        String str1 = new String("hello");
////        String str2 = new String(" world");
////        System.out.println(str1.concat(str2));
////        System.out.println(str1);
//    }
//
//    public static int calculate(String s) {
//        String[] stack = new String[100];
////        for(int i = 0; i<100; i++){
////            stack[i] = null;
////        }
//        char[] c = s.toCharArray();
//        int size = 0;
//        int flag = 1;
//        for(int i = 0; i<c.length; i++){
//            if(c[i]>='0'&&c[i]<='9'){//数字
//                if(size == 0){
//                    stack[size] = new String(c,i,1);
//                    size++;
//                }else if("+".equals(stack[size-1])){
////                    int num = getNumOfString(stack[size-2])
////                    int re = num + (c[i]-'0');
////                    stack[size-2] = String.valueOf(re);
////                    size--;
//                    stack[size] = String.valueOf(c[i]);
//                    size++;
//                }else if("-".equals(stack[size-1])){
////                    int num = getNumOfString(stack[size-2]);
////                    int re = num + (c[i]-'0');
////                    stack[size-2] = String.valueOf(re);
////                    size--;
//                    stack[size] = String.valueOf(c[i]);
//                    size++;
//                }else {//这里说明前面存的是数字
//                    stack[size-1] = stack[size-1].concat(String.valueOf(c[i]));
//                }
//            }else if(c[i] == '+'){
//                if(size>=2){
//                    if("+".equals(stack[size-2])){
//                        int num1 = getNumOfString(stack[size-3])*flag;
//                        int num2 = getNumOfString(stack[size-1])*flag;
//                        stack[size-3] = String.valueOf(num1+num2);
//                        size-=2;
//                    }else if("-".equals(stack[size-2])){
//                        int num1 = getNumOfString(stack[size-3])*flag;
//                        int num2 = getNumOfString(stack[size-1])*flag;
//                        stack[size-3] = String.valueOf(num1-num2);
//                        size-=2;
//                    }
//                }
//                stack[size] = String.valueOf(c[i]);
//                size++;
//            }else if(c[i] == '-'){
//                if(size>=2){
//                    if("+".equals(stack[size-2])){
//                        int num1 = getNumOfString(stack[size-3])*flag;
//                        int num2 = getNumOfString(stack[size-1])*flag;
//                        stack[size-3] = String.valueOf(num1+num2);
//                        size-=2;
//                    }else if("-".equals(stack[size-2])){
//                        int num1 = getNumOfString(stack[size-3])*flag;
//                        int num2 = getNumOfString(stack[size-1])*flag;
//                        stack[size-3] = String.valueOf(num1-num2);
//                        size-=2;
//                    }
//                }
//                stack[size] = String.valueOf(c[i]);
//                size++;
//            }else if(c[i] == '('){
//                if("-".equals(stack[size-1])){
//                    flag = -1;
//                    stack[size-1] = "+";
//                }
//            }else if(c[i] == ')'){
//                if(flag == -1){
//                    flag = 1;
//                }
//            }
//            if(size>=2&&c.length-1 == i){
//                if("+".equals(stack[size-2])){
//                    int num1 = getNumOfString(stack[0]);
//                    int num2 = getNumOfString(stack[size-1]);
//                    stack[0] = String.valueOf(num1+num2);
//                    size-=2;
//                }else{
//                    int num1 = getNumOfString(stack[0]);
//                    int num2 = getNumOfString(stack[size-1]);
//                    stack[0] = String.valueOf(num1-num2);
//                    size-=2;
//                }
//            }
//        }
//        int num = getNumOfString(stack[0]);
//        return num;
//    }
//
//    public static int getNumOfString(String str){
//        int sizeOfNum = str.length();
//        int j = 1;
//        int num = 0;
//        for(int k = sizeOfNum-1; k>=0; k--){
//            if(str.charAt(k) == '-'){
//                num = num*(-1);
//            }else{
//                num += (str.charAt(k)-'0')*j;
//            }
//            j = j*10;
//        }
//        return num;
//    }
//}

import java.util.Stack;

class Solution {

    private static Stack<Integer> stack = new Stack<>();

    public static int calculate(String s) {
        int res = 0;//得数
        int operator = 1;//标志位
        int num = 0;//位数
        if(s ==null || s.length() == 0){
            return res;
        }
        for(int i=0,size=s.length(); i < size; i++){
            char singleChar = s.charAt(i);
            if(singleChar == '+'){
                res = res + num * operator;
                num = 0;
                operator = 1;
            } else if(singleChar == '-'){
                res = res + num * operator;
                num = 0;
                operator = -1;
            } else if(singleChar == '('){
                stack.push(res);
                stack.push(operator);
                res = 0;
                operator = 1;
                num = 0;
            } else if(singleChar == ')'){
                res = res + num * operator;
                num = 0;
                res = res * stack.pop();
                res = stack.pop() + res;
            } else if(singleChar != ' '){
                num = num*10 + singleChar - '0';
            }
        }
        res = res + num * operator;
        return res;
    }

    public static void main(String[] args) {
        System.out.println(calculate("-(1)+(4)-(8)+(-(3)+(5))"));
        System.out.println(calculate("-(100)+(40)-(8)+(-(3)+(5))"));
    }
}