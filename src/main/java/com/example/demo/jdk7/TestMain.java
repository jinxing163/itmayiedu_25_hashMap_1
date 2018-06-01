package com.example.demo.jdk7;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JinXing
 * @date 2018/5/30 14:32
 */
public class TestMain {


    //原生HashMap
    public static void tes1(){

        Map<Integer,Object> map=new HashMap<>();
        for (int i = 0; i <20 ; i++) {
            map.put(i,"a"+i);
        }

        System.out.println("map:"+map);
        System.out.println("map.remove(5):"+map.remove(5));
        System.out.println("map.size():"+map.size());
        System.out.println("map.get(0):"+map.get(0));
        System.out.println("map:"+map);

    }

    //自定义HashMap
    public static void test2(){
        ExtMap<Integer,Object> map=new ExtHashMap<>();
        for (int i = 0; i <36 ; i++) {
            map.put(i,"a"+i);
        }
//        map.put(32,"a32-aa");

        System.out.println("map:"+map);
        System.out.println("size:"+map.size());
        System.out.println("map.get(32):"+map.get(32));

    }

    public static void test3(){

        long startTime=System.currentTimeMillis();
        int count=100000000;
        for (int i = 0; i <count ; i++) {
            int num=100%10;
        }

        System.out.println("耗时："+(System.currentTimeMillis()-startTime)/1000.00+"秒");
        startTime=System.currentTimeMillis();
        for (int i = 0; i <count ; i++) {
            int num=100&10;
        }
        System.out.println("耗时："+(System.currentTimeMillis()-startTime)/1000.00+"秒");
    }


    public static void test4(){



    }

    public static void main(String[] args) {
//        tes1();

        test2();
//          test3();


    }




}
