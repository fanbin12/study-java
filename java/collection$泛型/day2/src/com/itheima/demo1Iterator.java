package com.itheima;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class demo1Iterator {
    public static void main(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("姚明");
        collection.add("麦迪");
        /*使用增强for循环变量数组和集合，但是其实现原理是iteration*/
        for(String ee:collection){
            System.out.println(ee);
        }
        /*使用迭代器对循环进行遍历*/
        Iterator<String> iterator = collection.iterator();
        while (iterator.hasNext()){
            String it=iterator.next();//会自动加1
            System.out.println(it);
        }
       /* Iterator<String> iterator = collection.iterator();
        for (;iterator.hasNext();){
            String ee=iterator.next();
            System.out.println(ee);
        }
        *在上vi迭代器中已经到达末尾
        */


    }

}
