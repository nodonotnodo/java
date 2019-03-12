package onlineTest;

import java.util.HashMap;
import java.util.Map;

public class Function1 {

    public static void main(String[] args) {

        function("sdf  sfd  sf  asd");

    }

    public static void function(String str){

        str = str.replaceAll(" ","");
        char[] c = str.toCharArray();
        HashMap<Character,Integer> map = new HashMap<>();
        for (char aC : c) {
            if (map.containsKey(aC)) {
                int size = map.get(aC);
                map.replace(aC, size + 1);
            } else {
                map.put(aC, 1);
            }
        }
        StringBuilder sb = new StringBuilder("{");
        for(Map.Entry<Character,Integer> entry : map.entrySet()){
            sb.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(", ");
        }
        sb.append("}");
        System.out.println(sb);

    }

}
