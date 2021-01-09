package xyz.ganbug.flashsale.collection;

import java.util.Map;

public class TestLRU {
    public static void main(String[] args){
        LRU<Integer, String> lru = new DefaultLRU<>(5);
        lru.put(0, "life is fantastic");
        lru.put(1, "huanghaifeng performed well");
        lru.put(2, "spring in action");
        lru.put(3, "mysql innodb");
        lru.put(4, "java core");

        int[] bookIds = {0,1,3,2,4,0,2,1,3,2,0,4,4,4,3,0,1,0,1,0,4,4,1,0,1,0,5,3,4,4};
        for (int id : bookIds){
            lru.get(id);
        }
        lru.print();
        lru.put(5, "effective java");
        lru.put(1, "hhj performed well");
        lru.print();
    }
}
