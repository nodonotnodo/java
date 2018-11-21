//String类扩展功能实现
public class Strings{

    
     /**
     * 重复某个字符
     * 
     * 例如： 
     * 'a' 5   => "aaaaa"  
     * 'a' -1  => ""
     * 
     * @param c     被重复的字符
     * @param count 重复的数目，如果小于等于0则返回""
     * @return 重复字符字符串
     */
    public static String repeat(char c, int count) {
        if(count <= 0){
            return "";
        }else{
            char[] array = new char[count];
            for(int i = 0; i<count; i++){
                array[i] = c;
            }
            String str = new String(array);
            return str;
        }
    }
    
    
     /**
     * 将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串
     * 字符填充于字符串前
     *
     * 例如： 
     * "abc" 'A' 5  => "AAabc"
     * "abc" 'A' 3  => "abc"
     *
     * @param str        被填充的字符串
     * @param filledChar 填充的字符
     * @param len        填充长度
     * @return 填充后的字符串
     */
    public static String fillBefore(String str, char filledChar, int len) {
        int length = str.length();
        if(len < length){
            return str;
        }else{
            char[] array = new char[len];
            int j = length-1;
            for(int i = len-1; i>=0; i--){
                if(j>=0){
                    array[i] = str.charAt(j);
                    j--;
                }else{
                    array[i] = filledChar;
                }
            }
            String str1 = new String(array);
            return str1;
        }
    }
    
    /**
     * 将已有字符串填充为规定长度，如果已有字符串超过这个长度则返回这个字符串<br>
     * 字符填充于字符串后
     * 例如： 
     * "abc" 'A' 5  => "abcAA"
     * "abc" 'A' 3  => "abc"
     *
     * @param str        被填充的字符串
     * @param filledChar 填充的字符
     * @param len        填充长度
     * @return 填充后的字符串
     */
    public static String fillAfter(String str, char filledChar, int len) {
        int length = str.length();
        if(len < length){
            return str;
        }else{
            char[] array = new char[len];
            int j = 0;
            for(int i = 0; i<len; i++){
                if(j<length){
                    array[i] = str.charAt(j);
                    j++;
                }else{
                    array[i] = filledChar;
                }
            }
            String str1 = new String(array);
            return str1;
        }
    }

    /**
     * 移除字符串中所有给定字符串
     * 例：removeAll("aa-bb-cc-dd", "-") => aabbccdd
     *
     * @param str         字符串
     * @param strToRemove 被移除的字符串
     * @return 移除后的字符串
     */
    public static String removeAll(CharSequence str, CharSequence strToRemove) {
        //CharSequence是一个char型可读序列接口，只提供部分放法。
        if(null == str){
            return null;
        }
        int length1 = str.length();
        char[] array1 = new char[length1];
        for(int i = 0; i<length1; i++){
            array1[i] =str.charAt(i);
        }
        String str1 = new String(array1);
        int length2 = strToRemove.length();
        char[] array2 = new char[length2];
        for(int i = 0; i<length2; i++){
            array2[i] = strToRemove.charAt(i);
        }
        String str2 = new String(array2);
        String str3 = str1.replace(str2, "");
        return str3;
    }
    
    /**
     * 反转字符串
     * 例如：abcd => dcba
     *
     * @param str 被反转的字符串
     * @return 反转后的字符串
     */
    public static String reverse(String str) {
        if(null == str){
            return null;
        }
        int length = str.length();
        if(length <= 1){
            return str;
        }
        char[] array = new char[length];
        for(int i = 0; i<length; i++){
            array[i] = str.charAt(length-1-i);
        }
        String str1 = new String(array);
        return str1;
    }

    public static void code1(){
        System.out.println(repeat('r',0));
        System.out.println(repeat('d',-1));
        System.out.println(repeat('f',5));
    }

    public static void code2(){
        String str = "abcd";
        System.out.println(fillBefore(str, 'A', 6));
        System.out.println(fillBefore(str, 'A', 3));
    }

    public static void code3(){
        String str = "abcd";
        System.out.println(fillAfter(str, 'A', 6));
        System.out.println(fillAfter(str, 'A', 3));
    }

    public static void code4(){
        CharSequence str = "aabbcdefefddgt";
        System.out.println(str);
        System.out.println(removeAll(null, "bb"));
        System.out.println(removeAll(str, "bb"));
        System.out.println(removeAll(str, "e"));
        System.out.println(removeAll(str, "aabbcdefefddgt"));
        System.out.println(removeAll(str, "aa"));
    }

    public static void code5(){
        String str = "abcdefg";
        System.out.println(reverse(str));
        System.out.println(reverse(null));
        System.out.println(reverse("a"));
        System.out.println(reverse(""));
    }

    public static void main(String[] args){

        code1();
        System.out.println("=====================================");
        code2();
        System.out.println("=====================================");
        code3();
        System.out.println("=====================================");
        code4();
        System.out.println("=====================================");
        code5();
    }
}