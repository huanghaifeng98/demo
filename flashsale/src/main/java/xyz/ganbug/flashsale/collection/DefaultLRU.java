package xyz.ganbug.flashsale.collection;

import java.util.LinkedList;

public class DefaultLRU<K, V> implements LRU<K, V> {
    final LinkedList<Node<K, V>> caches = new LinkedList<>();
    int maxCapacity;

    public DefaultLRU(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = this.pop(key);
        if (node == null) {
            return null;
        }
        caches.addFirst(node);
        return node.value;
    }

    @Override
    public void put(K key, V value) {
        boolean exist = this.pop(key) != null;
        if (caches.size() == maxCapacity && !exist){
            caches.removeLast();
        }
        Node<K, V> node = new Node<>(key, value);
        caches.addFirst(node);
    }

    protected Node<K, V> pop(K key) {
        for (Node<K, V> node : caches) {
            if (node.key.equals(key)) {
                caches.remove(node);
                return node;
            }
        }
        return null;
    }

    static final class Node<K, V> {
        private final K key;
        private final V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" + "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public void print() {
        for (Node<K, V> node : caches){
            System.out.println(node);
        }
    }
}
