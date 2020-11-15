package it.itcast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
//hashmap與hashset原理是相同的
public class demo1Map {
    public static void main(String[] args) {
        Map<String, Integer> map=new HashMap<>();
        map.put("赵丽颖",200);
        map.put("杨幂",400);
        map.put("范斌",300);
        Set<String> jihe=map.keySet();
        for (String s : jihe) {
            System.out.println(s);
            System.out.println(map.get(s));
        }
        Iterator<String> iterator = jihe.iterator();
        while (iterator.hasNext()){
            String it=iterator.next();
            System.out.println(map.get(it));
        }
        //entry是键值对对象，可以通过获取map集合的entry对象
        //将其存储到set集合中，然后将其读出。
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator1 = entries.iterator();
        while (iterator1.hasNext()){
            Map.Entry<String, Integer> next = iterator1.next();
            System.out.println(next.getKey()+next.getValue());
        }
    }
}
