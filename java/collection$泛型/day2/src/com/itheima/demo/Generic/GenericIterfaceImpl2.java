package com.itheima.demo.Generic;
/*泛型接口的实现类
* 第二种使用方式：
* 接口使用什么泛型，类就是用什么泛型，创建对象的时候确定泛型的的类型*/
public class GenericIterfaceImpl2<T> implements GenericInterface<T>{
    @Override
    public void meth01(T t) {
        System.out.println(t);
    }
}
