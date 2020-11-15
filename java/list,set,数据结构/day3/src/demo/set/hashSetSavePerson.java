package demo.set;

import javax.print.attribute.HashPrintServiceAttributeSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class hashSetSavePerson {
    public static void main(String[] args) {
        HashSet<person> set1=new HashSet<>();
        //HashSet<String> set2 = new HashSet<>();
        person person1= new person("范彬",10);
        person person2 =new person("范静洁",5);
        person person3 =new person("范静洁",50);
        person person4= new person("范彬",13);
        set1.add(person1);
        set1.add(person2);
        set1.add(person3);
        set1.add(person4);
        System.out.println(set1);
        System.out.println(person3.hashCode());
        System.out.println(person2.hashCode());
        System.out.println(person2==person3);
        System.out.println(person2.equals(person3));
    }
}
