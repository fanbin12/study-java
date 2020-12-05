package com.itheima.demo.Generic;
/*含有泛型的接口第一种使用方式，定义接口的实现类，实现接口，并指定接口的泛型*/
public interface GenericInterface<T> {
    public abstract void meth01(T t);
}
