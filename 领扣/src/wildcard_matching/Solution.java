package wildcard_matching;

/*
    给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
            '?' 可以匹配任何单个字符。
            '*' 可以匹配任意字符串（包括空字符串）。
    两个字符串完全匹配才算匹配成功。
    说明:
    s 可能为空，且只包含从 a-z 的小写字母。
    p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
    示例 1:
    输入:
    s = "aa"
    p = "a"
    输出: false
    解释: "a" 无法匹配 "aa" 整个字符串。
    示例 2:
    输入:
    s = "aa"
    p = "*"
    输出: true
    解释: '*' 可以匹配任意字符串。
    示例 3:
    输入:
    s = "cb"
    p = "?a"
    输出: false
    解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
    示例 4:
    输入:
    s = "adceb"
    p = "*a*b"
    输出: true
    解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
    示例 5:
    输入:
    s = "acdcb"
    p = "a*c?b"
    输入: false
    */

public class Solution {

    public static boolean isMatch(String s, String p) throws Exception {
        if(s == null || p == null){
            throw new Exception();
        }
        int bs = 0;
        int bp = 0;
        int nbp = bp;
        int last = -1;
        char npp = 'A';
        while(!(bs == s.length() && bp == p.length())){
            if(bs == s.length() || bp == p.length()){
                if(bs == s.length()){
                    int i = 0;
                    for(; i<p.length(); i++) {
                        if (p.charAt(i) != '*') {
                            break;
                        }
                    }
                    if(i==p.length()){
                        return true;
                    }
                    for(; bp<p.length(); bp++){
                        if (p.charAt(bp) != '*') {
                            break;
                        }
                    }
                    return bp == p.length();
                }
                return false;
            }
            char ss = s.charAt(bs);
            char pp = p.charAt(bp);
            if((bp+1) < p.length()){//pp不是最后一个字符时，记下pp的下一位字符
                nbp = bp+1;
                npp = p.charAt(nbp);
            }
            if(ss == pp || '?' == pp){//两个字符串匹配
                if(last!=-1&&bp+1 == p.length()&&(bs+1)!=s.length()){
                    bs++;
                }else{
                    bs++;
                    bp++;
                }
            }else if('*' == pp){
                if((bp+1) < p.length()){//此时的'*'号字符后面还有普通字符
                    if('*' == npp){
                        bp++;
                    }else if(ss == npp || '?' == npp){//假定'*'已经结束
                        if(bp+2 == p.length()&&(bs+1)!=s.length()){
                            bs++;
                            if(last != -1){
                                bp = last;
                            }
                        }else{
                            bs++;
                            last = bp;
                            bp+=2;
                        }
                    }else{//'*'未结束
                        bs++;
                    }
                }else{//此时s中最后一个字符就是'*'
                    return true;
                }
            }else{//说明前面'*'结束的假定错误。
                if(last == -1){
                    return false;
                }
                bp = last;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        try {
            System.out.println(isMatch("asdfg","asdfg"));
            System.out.println(isMatch("asdfg","asdf"));
            System.out.println(isMatch("asdfg","a?dfg"));
            System.out.println(isMatch("asdfg","*dfg"));
            System.out.println(isMatch("asasffd","*asffd"));
            System.out.println(isMatch("asdasdasdffd","*asdffd"));
            System.out.println(isMatch("aa","*"));
            System.out.println(isMatch("aaaa","***a"));
            System.out.println(isMatch("","**"));
            System.out.println(isMatch("a",""));
            System.out.println(isMatch("a","a*"));
            System.out.println(isMatch("a","*?*"));
            System.out.println(isMatch("cabab","*ab"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
