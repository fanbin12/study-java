package it.itcast;

import java.util.HashMap;
import java.util.Scanner;

/*统计一个字符串出现的个数*/
public class count {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串");
        String str=scanner.next();
        //Character是字符
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c:str.toCharArray()) {
            if(map.containsKey(c)){
                Integer value = map.get(c);
                value++;
                map.put(c,value);
                System.out.println("存在相同集合");
            }
            else{
                map.put(c,1);
            }
        }
    }
}
