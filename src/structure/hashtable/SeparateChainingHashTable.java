package structure.hashtable;

import java.util.HashSet;
import java.util.Set;

public class SeparateChainingHashTable<K, V> {

    static final int DEFAULT_CAPACITY = 16;  // must be power of 2
    static final int MAX_CAPACITY = 1 << 30; // max capacity of the table
    static final float LOAD_FACTOR = 0.75f;  // Load Factor for the hash table

    int size;      // number of nodes
    int capacity;  // length of the table
    int threshold; // the next size to resize (capacity * loadFactor)

    Node<K, V>[] table;

    @SuppressWarnings("unchecked")
    public SeparateChainingHashTable() {

        this.capacity = DEFAULT_CAPACITY;
        this.threshold = (int)(capacity * LOAD_FACTOR);
        table = (Node<K, V>[])new Node[this.capacity];
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        Node<K, V> node;
        return (node = getNode(hash(key), key)) == null ? null : node.value;
    }

    /** Find a node that has the same hash and key */
    private Node<K, V> getNode(int hash, K key) {
        if (key == null)
            throw new NullPointerException("Key can't be null");

        Node<K, V> node = table[hash & (table.length - 1)];

        while (node != null) {
            if (node.hash == hash && node.key.equals(key)) // find
                break;

            node = node.next;
        }
        return node;
    }

    /** Put a pair of key-value if the key exists, overwrite its value */
    public V put(K key, V value) {
        return putNode(hash(key), key, value);
    }

    private V putNode(int hash, K key, V value) {
        int index = hash & (table.length - 1);

        if (table[index] == null) { // empty bucket
            table[index] = new Node<>(hash, key, value, null);
            if (++size > threshold) table = resize(); // double table size
            return null;
        }

        Node<K, V> node = table[index];
        Node<K, V> previous = node;

        while (node != null) { // find the same key
            if (node.hash == hash && node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            previous = node;
            node = node.next;
        }

        previous.next = new Node<>(hash, key, value, null);
        if (++size > threshold) table = resize(); // double table size
        return null;
    }

    public V delete(K key) {

        return deleteNode(hash(key), key);
    }

    private V deleteNode(int hash, K key) {
        Node<K, V> node = table[hash & (table.length - 1)];
        Node<K, V> previous = null;

        while (node != null) {
            if (node.hash == hash && node.key.equals(key)) { // hit
                if (previous == null) // node is head
                    table[hash & (table.length - 1)] = node.next;
                else
                    previous.next = node.next;
                size--;
                break;
            }
            previous = node;
            node = node.next;
        }
        return node == null ? null : node.value;
    }

    private static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * Doubles table size.
     *
     * @return a new table that is resized
     */
    private Node<K, V>[] resize() {

        int oldCap = table.length;
        int newCap = oldCap << 1;       // double capacity
        threshold = threshold << 1; // double threshold

        @SuppressWarnings("unchecked")
        Node<K, V>[] newTab = (Node<K, V>[])new Node[newCap];

        for (int i = 0; i < oldCap; i ++) { // rehash 0 ~ n*2
            Node<K, V> node = table[i]; // node - n - n ...
            Node<K, V> loHead = null, loTail = null;
            Node<K, V> hiHead = null, hiTail = null;
            table[i] = null;

            while (node != null) { // iterate node from head to tail
                // separate node low: hash < old cap, high: hash >= old cap
                if ((node.hash & oldCap) == 0) {
                    if (loTail == null)
                        loHead = node;
                    else
                        loTail.next = node;
                    loTail = node;

                } else { // high hash
                    if (hiTail == null)
                        hiHead = node;
                    else
                        hiTail.next = node;
                    hiTail = node;
                }
                node = node.next;
            }
            if (loTail != null) {  // nodes have lower hash than before
                loTail.next = null;
                newTab[i] = loHead;
            }
            if (hiTail != null) {  // nodes have high hash than before
                hiTail.next = null;
                newTab[i + oldCap] = hiHead; // cap: 32, hash 7 -> 39
            }
        }
        return newTab;
    }

    public void printHashTable() {
        System.out.println("table size: " + table.length);
        for (Node node: table) {
            if (node != null) System.out.println();
            while (node != null) {
                System.out.print("[index: " + (node.hash & (table.length - 1)) + ", hash: " + node.hash + "]");
                node = node.next;
            }
        }
    }

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

}
