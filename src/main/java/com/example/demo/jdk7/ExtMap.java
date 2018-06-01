package com.example.demo.jdk7;

/**
 * @author JinXing
 * @date 2018/5/30 11:26
 */
public interface ExtMap<K,V> {

    // 向集合中插入数据
    V put(K k, V v);

    // 根据k 从Map集合中查询元素
    V get(K k);

    // 获取集合元素个数
    int size();

    interface Entry<K, V> {
        K getKey();

        V getValue();

        V setValue(V value);
    }


}
