package com.itheima.demo.Generic;
/*泛型接口的实现类
* 第一种使用方式：
* 定义接口的实现类，实现接口，指定接口的泛型*/
public class GenericIterfaceImpl1 implements GenericInterface<String>{
    @Override
    public void meth01(String s) {
        System.out.println(s);
    }

}
