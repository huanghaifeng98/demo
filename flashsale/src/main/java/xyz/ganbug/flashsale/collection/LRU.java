package xyz.ganbug.flashsale.collection;

public interface LRU<K, V> {
    V get(K key);
    void put(K key, V value);
    void print();
}
