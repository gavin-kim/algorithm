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

    class Node<K ,V> {

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
}
