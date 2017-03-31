package structure.hashtable;

import java.util.HashSet;
import java.util.Set;

public class SeparateChainingHashTable<K, V> {

    static final int INITIAL_CAPACITY = 16;  // must be power of 2
    static final int MAX_CAPACITY = 1 << 30; // max capacity of the table
    static final float LOAD_FACTOR = 0.75f;  // Load Factor for the hash table

    Node<K, V>[] table;
    int size;      // The number of key-values
    int threshold; // the next size to resize (capacity * loadFactor)
    final float loadFactor;

    class Node<K, V> {

        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;

        }
    }
    public V get(K key) {
        Node<K, V> node;
        return (node = getNode(hash(key), key)) == null ? null : node.value;
    }

    /** Find a node that has the same hash and key */
    private Node<K, V> getNode(int hash, K key) {
        if (key == null)
            throw new NullPointerException("Key can't be null");

        Node<K, V> node = table == null ?
            null : table[hash & (table.length - 1)];

        while (node != null) {
            if (node.hash == hash && node.key.equals(key)) // find
                break;

            node = node.next;
        }
        return node;
    }

    public V put(K key, V value) {
        return putNode(hash(key), key, value);
    }

    private V putNode(int hash, K key, V value) {
        if
    }

    /**
     * Initializes or doubles table size.
     *
     * @return the table
     */
    private Node<K, V>[] resize() {

    }

    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
