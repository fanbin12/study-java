package com.itheima.demo.Generic;


import com.sun.xml.internal.ws.api.server.LazyMOMProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

/*泛型的上限界定：？ extend E 代表使用的泛型只能时E类型的子类或本省
泛型的下线界定： ？ super E 代表使用的泛型只能是E的父类或本类
* */

public class demo6Generic {
    public static void main(String[] args) {
       Collection<String> list1 = new ArrayList<>();//多态的应用，实现了类与实现接口的分离
       Collection<Object> list2 = new ArrayList<>();
       Collection<Integer> list3 = new ArrayList<>();
       Collection<Number> list4 = new ArrayList<>();
       /*这里的报错是用于检测泛型的上限界定*/
       /*get1(list1);
       get1(list2);*/
       get1(list3);
       get1(list4);
    }
    //虽然实现了接口，但是还是由前面的变脸类型来确定函数中的参数类型
    public static void get1(Collection<? extends Number> coll){}

}
