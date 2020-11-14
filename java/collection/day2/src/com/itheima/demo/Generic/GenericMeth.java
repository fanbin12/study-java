package com.itheima.demo.Generic;
/*泛型定义的方法
* 修饰符 <泛型> 返回值 方法名（E e）
* */
public class GenericMeth {
    public static void main(String[] args) {
        meth01("afa");
    }

    //要使用静态方法，泛型中的E可以是任意定义
    public static <E> void meth01(E e){
        System.out.println(e);
    }
}
