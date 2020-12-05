package com.itheima;
import java.util.*;

public class demo2Iterator{
    public static void main(String[] args) {
        Collection<String> coll=new ArrayList<>();
        System.out.println(coll);//虽然创建了array list方法，但是返回空集，这说明调用了toString方法
        coll.add("fanbin");
        coll.add("zhangsan ");
        coll.add("lisi");
        System.out.println(coll);
        /*
        * remove方法*/
        boolean b1=coll.remove("lisi");
        boolean b2 = coll.remove("cai");
        System.out.println(b1);
        System.out.println(b2);//没有cai，返回false
        /*contains
        * 如果遇到了基本类的equel
        * */
        boolean c1 = coll.contains("fanbin");
        System.out.println(c1);
        /*isEmpty*/
        System.out.println("集合是否为空"+coll.isEmpty());
        /*size*/
        System.out.println("集合的大小"+coll.size());
        /*toArray，使用object类型数据*/
        Object[] arr = coll.toArray();
        for(int i=0;i<arr.length;i++)
        {
            System.out.println(arr[i]);
        }
        /*clear方法*/
        coll.clear();
        System.out.println(coll.isEmpty());
        List<String> list = new ArrayList<>();
        list.add("wzf");
        Iterator<String> iterator = list.iterator();

    }
}
