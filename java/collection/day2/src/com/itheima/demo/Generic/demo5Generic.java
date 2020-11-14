package com.itheima.demo.Generic;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*泛型的通配符
* 代表任意的数据类型
* 使用方式：
* 不能创建对象的时候使用，只能创建方法的时候使用
* */

public class demo5Generic {
    public static void main(String[] args) {
        ArrayList<String> list1 =new ArrayList<>();
        list1.add("fanbin");
        list1.add("cdi");
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(3);
        printArr(list1);
        printArr(list2);
    }
    public static void printArr(ArrayList<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
