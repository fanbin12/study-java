package com.itheima.demo.Generic;
/*模拟一个含有泛型的类，泛型是一个未知的类，可以接受未知的数据类型
* 创建对象的时候确定泛型类型
* */
public class GenerClass<E> {
    private E name;


    public E getName() {
        return name;
    }

    public void setName(E name) {
        this.name = name;
    }
}
