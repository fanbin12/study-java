package com.itheima.zongheanli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class doudizhu {
    public static void main(String[] args) {
        ArrayList<String> poker = new ArrayList<>();
        //定义两个数组用于存储花色和序号
        String[] colors={"♠","♥","♣","♦"};
        String[] number={"2","A","K","Q","J","10","9","8","7","6","5","4","3"};
        poker.add("大王");
        poker.add("小王");
        for (String numbers:number){
            for(String color:colors){
                poker.add(color+numbers);
            }
        }
        //System.out.println(poker);
        //洗牌
        Collections.shuffle(poker);
        //发牌
        /*定义4个集合存储玩家牌和底牌*/
        ArrayList<String> list1 = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>();
        ArrayList<String> list3 = new ArrayList<>();
        ArrayList<String> list4 = new ArrayList<>();
        for(int i=0;i<poker.size();i++){
            String P=poker.get(i);
            if(i<51){
                if((i+1)%3==1){
                    list1.add(P);
                }
                if((i+1)%3==2){
                    list2.add(P);
                }
                if((i+1)%3==0){
                    list3.add(P);
                }
            }
            else{
                list4.add(P);
            }
        }
        System.out.println(list1);
        System.out.println(list2);
        System.out.println(list3);
        System.out.println(list4);
        //整理牌
        
    }
}
