package com.itheima.demo.Generic;
/*使用含有泛型的类*/
public class demo02GenericClass {
    public static void main(String[] args) {
        /*创建对象时确定泛型*/
        GenerClass<Integer> generClass = new GenerClass();
        generClass.setName(123);
        System.out.println(generClass.getName());
    }
}
