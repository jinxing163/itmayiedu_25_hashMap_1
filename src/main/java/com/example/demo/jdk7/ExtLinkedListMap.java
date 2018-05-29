package com.example.demo.jdk7;

import org.springframework.util.CollectionUtils;

import java.util.LinkedList;

/**
 * @author: Jinxing
 * @date: 2018-05-29 20:35
 **/
public class ExtLinkedListMap<Key,Value> {

    //1.定义存放key、value容器
    LinkedList<Entry<Key,Value>>[] tables=new LinkedList[998];

    private int size;

    //存值
    public void put(Key key,Value value){
        //非空校验
        keyIsEmpty(key);
        valueIsEmpty(value);

        Entry<Key, Value> entry = getEntry(key);
        if(entry ==null){
            LinkedList<Entry<Key,Value>> linkedList=new LinkedList<>();
            Entry<Key, Value> keyValueEntry = new Entry<>(key, value);
            linkedList.add(keyValueEntry);
            // hashCode
            int hash = getHash(key);
            tables[hash]=linkedList;
            size++;
        }else {
            entry.value=value;
        }

    }


    public int size(){
        return size;
    }

    //获取value
    public Value get(Key key){
        Entry<Key, Value> entry = getEntry(key);
        return entry==null?null:entry.value;
    }

    //获取Entry对象
    private Entry<Key, Value> getEntry(Key key){

        if(size >0){
            keyIsEmpty(key);
            int hash = getHash(key);
            LinkedList<Entry<Key, Value>> table = tables[hash];
            if(!CollectionUtils.isEmpty(table)){
                for (Entry<Key, Value> obj : table) {
                    if(obj!=null && obj.key.equals(key)){
                        return obj;
                    }
                }
            }

        }

        return null;
    }

    //hash算法
    private int getHash(Key key) {
        //hash算法获取hashCode的值
        int hashCode=key.hashCode();
        //对linkedList.length求余 =(0-997之间)
        int hash=hashCode % tables.length;
        return hash;
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
