package com.example.demo.linkedlist;


import java.util.ArrayList;
import java.util.List;

/**
 * 1.纯手写HasMap集合 完全采用Arraylist实现 缺点效率低 <br>
 * @author: Jinxing
 * @date: 2018-05-29 19:59
 **/
public class ExtArrayListHashMap<Key,Value> {

    //1.定义存放map的容器
    List<Entry<Key,Value>> entryList=new ArrayList<>();

    //存值
    public void put(Key key,Value value){

        keyIsEmpty(key);
        valueIsEmpty(value);

        //校验entry对象是否存在
        Entry entry = getEntry(key);
        if(entry ==null){
            entry= new Entry<>(key, value);
            entryList.add(entry);
        }else{
            //存在的话只需要改变value的值
            entry.value=value;
        }

    }

    //删除
    public Value remove(Key key) {
        keyIsEmpty(key);
        Entry<Key, Value> entry = getEntry(key);
        if(entry==null){
           return null;
        }
        entryList.remove(entry);
        return entry.value;
    }

    //集合本身是有长度的
    public int size(){
        return entryList.size();
    }

    //获取value值
    public Value get(Key key){
        Entry<Key,Value> entry = getEntry(key);
        return entry==null?null:entry.value;
    }

    //获取entry对象
    private Entry<Key,Value> getEntry(Key key) {

        keyIsEmpty(key);

        //循环找出相应的key 已经value
        for (Entry<Key, Value> obj : entryList) {
            if(obj!=null && obj.key.equals(key)){
                return obj;
            }
        }
        return null;
    }

    //key、value非空校验
    private void keyIsEmpty(Key key) {
        if(key ==null){
            throw new NullPointerException("key 不能为空");
        }
    }
    private void valueIsEmpty(Value value) {
        if(value ==null){
            throw new NullPointerException("value 不能为空");
        }
    }

}
