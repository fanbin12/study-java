package VarArgs;

import java.lang.reflect.Array;

/*使用：当方法参数类型已经确定，但是个数不确定，使用可变参数
* （数据类型...变量名）
* 原理：可变参数是一个数组，当传递参数数量不同时，创建不同大小的数组
* */
/*
* 可变参数的应场景：一个方法只能存在一个可变参数
* 如果方法有多个类型的参数，可变参数只能写在末尾
* */
public class DemoVarArg {
    public static void main(String[] args) {
        add();
        add(10);
        add(10,20);
        int[] array ={10,20};
        for(int a:array){
            System.out.println(a);
        }

    }
    public static int add(int...arr){
        System.out.println(arr);//arr是一个数组，所以返回地址名
        System.out.println(arr.length);//返回数组长度
        return 0;
    }
}
