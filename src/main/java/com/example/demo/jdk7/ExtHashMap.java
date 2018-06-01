package com.example.demo.jdk7;

/**
 * HashMap采用的是单链表的方式
 * 单链表：只存下个节点的值
 * 双链表：存上个节点和下个节点的值
 * 1.7 采用数组+单链表的方式实现
 *
 * @author JinXing
 * @date 2018/5/30 11:26
 */
public class ExtHashMap<K,V>  implements ExtMap<K,V> {

    //定义容器 (容器存放Node对象,Node对象为单链表)
    Node<K, V> [] tables=null;

    // 负载因子默认是0.75,负载因子越小,hash冲突概率越低
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    // 默认hashMap初始容量大小为16 (初始容量会改变)
    private static int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    //真实的数组长度
    private int size;

    @Override
    public V put(K key, V value) {

        keyIsEmpty(key);
        valueIsEmpty(value);

        //1.是否初始化容器大小
        if(tables ==null){
            tables=new Node[DEFAULT_INITIAL_CAPACITY];
        }

        //2.是否进行扩容
        resize();

        //3.判断Key是否存在
        int hash = getHash(key,DEFAULT_INITIAL_CAPACITY);
        //根据hash算法获取Key对应的Index
        Node<K, V> oldNode = tables[hash];
        if(oldNode ==null){
            //key不存在新建一个节点,将下个节点置空
            oldNode= new Node<>(key, value, null);
            size++;
        }else{

            //key存在：是否有hash碰撞
            if(oldNode.key.equals(key) || oldNode.key==key){
                oldNode.setValue(value);
            }else{
                //后进先出原则：最后添加的最先查出来
                oldNode= new Node<>(key, value, oldNode);
                size++;
            }
        }

        //赋值
        tables[hash]=oldNode;
        return oldNode.value;
    }

    @Override
    public V get(K key) {
        keyIsEmpty(key);
        Node<K, V> node = getNode(key);
        return node==null?null:node.value;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * hashMap扩容机制
     * 扩容条件：实际长度>当前容量的0.75
     * 扩容后的长度：当前容量的2倍  << 1
     * 扩容后的操作：原数组存放node的值，需要重新计算hash，放入新数组并维护新数组中的单链表关系
     * @author JinXing
     * @date 2018/5/30 15:26
     */
    private void resize(){
        //扩容条件：实际长度>当前容量的0.75
        if(size > DEFAULT_INITIAL_CAPACITY*DEFAULT_LOAD_FACTOR){
            //扩容后的长度：当前容量的2倍  << 1
            int newTablesLegth=DEFAULT_INITIAL_CAPACITY <<1 ;
            newTablesLegth=20;
            Node<K, V> [] newTables=new Node[newTablesLegth];

            //扩容后的操作：原数组存放node的值，需要重新计算hash，放入新数组并维护新数组中的单链表关系
            int newHash;
            Node<K, V> next;
            for (Node<K, V> oldNode : tables) {
                while(oldNode!=null){
                    //1.需要重新计算hash
                    newHash = getHash(oldNode.key, newTablesLegth);
                    //获取下个节点
                    next = oldNode.next;
                    //如果发生hash冲突 以链表方式累加
                    oldNode.next=newTables[newHash];
                    //新数组赋值
                    newTables[newHash]=oldNode;
                    //将下个节点赋值给当前节点 (保证有链表有节点能继续执行)
                    oldNode=next;
                }
            }
            tables=newTables;
            //数组置空等待垃圾回收
            newTables=null;
            DEFAULT_INITIAL_CAPACITY=newTablesLegth;
        }
    }

    //hash算法获取Key对应的Index
    private int getHash(K key,int length){
        //获取hashCode值
//        int hashCode = key.hashCode();
//        int hash = hashCode % length;
        return key.hashCode() & (length - 1);
    }

    //获取key对应的node节点
    private Node<K, V> getNode(K key) {
        int hash = getHash(key,DEFAULT_INITIAL_CAPACITY);
        Node<K, V> node = tables[hash];
        while (node!=null){
            if(node.key.equals(key) || node.key==key){
                return node;
            }
        }
        return null;
    }

    //key、value非空校验
    private void keyIsEmpty(K key) {
        if(key ==null){
            throw new NullPointerException("key 不能为空");
        }
    }
    private void valueIsEmpty(V value) {
        if(value ==null){
            throw new NullPointerException("value 不能为空");
        }
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer=new StringBuffer("");
        //打印当前tables的值
        int hashIndex;

        for (int i = 0; i < tables.length ; i++) {
            Node<K, V> node = tables[i];
            stringBuffer.append("\n index:"+i);
            while (node!=null){
                stringBuffer.append("\t ->key:"+node.key+",value:"+node.value);
                node=node.next;
            }
        }
        return stringBuffer.toString();
    }

    //节点
    class Node<K, V> implements Entry<K,V>{

        //对应map的key和value
        private K key;
        private V value;

        public Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        //下个节点
        private Node<K,V> next;

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        //设置新Value并返回老的Value
        @Override
        public V setValue(V value) {
            //老的Value
            V oldValue=this.value;
            //设置新Value
            this.value=value;
            return oldValue;
        }
    }

}
