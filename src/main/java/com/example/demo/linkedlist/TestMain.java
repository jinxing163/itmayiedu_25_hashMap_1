package com.example.demo.linkedlist;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Jinxing
 * @date: 2018-05-29 20:04
 **/
public class TestMain {

    //原生的map调用
    public static void test1() {
        Map<String,Integer> map=new ConcurrentHashMap<>();
        map.put("aa",10);
        map.put("bb",20);
        map.forEach((k,v)->{
            System.out.println(k+"-->"+v);
        });

        map.remove("aa");
        System.out.println(map.size());
        System.out.println(map.get("aa"));

    }

    //自定义的arrayList map调用
    public static void test2() {
        ExtArrayListHashMap<String,Integer> map=new ExtArrayListHashMap<>();
        map.put("aa",10);
        map.put("bb",20);
        map.put("bb",30);
        map.remove("bb");
        System.out.println("size:"+map.size());
        System.out.println(map.get("bb"));

    }

    //自定义的linkedList map调用
    public static void test3() {
        //hash算法
        LinkedList<Entry<String,Integer>>[] linkedList=new LinkedList[998];
        String key="aa";
        int hashCode=key.hashCode();
        int hash=hashCode%linkedList.length;

        System.out.println("hashCode:"+hashCode);
        System.out.println("hash:"+hash);
        System.out.println("............................");

        ExtLinkedListMap<String,Integer> map=new ExtLinkedListMap<>();
        map.put("aa",10);
        map.put("bb",20);
        map.put("bb",30);
        map.remove("aa");
        System.out.println("size:"+map.size());
        System.out.println("map:"+map.toString());
        System.out.println("map.get(\"bb\"):"+map.get("bb"));

    }



    public static void main(String[] args) {
//    	test1();

//        test2();

        test3();

    }

}
