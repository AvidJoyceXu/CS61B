package lab9;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  @author JoyceXu
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private static final int DEFAULT_SIZE = 16;
    private static final double MAX_LF = 0.75;
    private int dynamic_length = DEFAULT_SIZE;
    private ArrayMap<K, V>[] buckets;
    private int size;

    private int loadFactor() {
        return size / buckets.length;
    }

    public MyHashMap() {
        buckets = new ArrayMap[DEFAULT_SIZE];
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        this.size = 0;
        for (int i = 0; i < this.buckets.length; i += 1) {
            this.buckets[i] = new ArrayMap<>();
        }
    }

    /** Computes the hash function of the given key. Consists of
     *  computing the hashcode, followed by modding by the number of buckets.
     *  To handle negative numbers properly, uses floorMod instead of %.
     */
    private int hash(K key) {
        if (key == null) {
            return 0;
        }

        int numBuckets = buckets.length;
        return Math.floorMod(key.hashCode(), numBuckets);
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int bktNum = hash(key);
        return buckets[bktNum].get(key);
    }

    /* Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        int bktNum = hash(key);
        if(buckets[bktNum].containsKey(key)) {
            buckets[bktNum].put(key, value);
        }
        else{
            size++;
            buckets[bktNum].put(key, value);
            if(loadFactor() > MAX_LF){
                dynamic_length *= 2;
                ArrayMap<K, V>[] oldBkt = buckets;
                buckets = new ArrayMap[dynamic_length];
                clear();
                for (int i = 0; i < oldBkt.length; i += 1) {
                    for(int j = 0;j < oldBkt[i].size; j++){
                        for(K new_key: oldBkt[i].keySet()){
                            put(new_key, oldBkt[i].get(new_key));
                        }
                    }
                }
            }
        }
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet(){
        Set<K> ret = new HashSet();
        for(K key: this){ // the enhanced for loop employs the iterator method
            ret.add(key);
        }
        return ret;
    }

    /* Removes the mapping for the specified key from this map if exists.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        int bktNum = hash(key);
        size--;
        return buckets[bktNum].remove(key);
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for this lab. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        int bktNum = hash(key);
        if(buckets[bktNum].get(key) == value){
            size--;
            return buckets[bktNum].remove(key, value);
        }
        return null;
    }
    private class MyHashMapIter implements Iterator<K> {
        private int bktNum = 0;
        private Iterator<K> ArrayMapIter;
        private int count = 0;
        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public K next() {
            if (!hasNext()){
                return null;
            }
            if(ArrayMapIter == null){
                ArrayMapIter = buckets[bktNum].iterator();
            }
            if(hasNext() && !ArrayMapIter.hasNext()){
                ArrayMapIter = buckets[++bktNum].iterator();
            }
            count ++;
            return ArrayMapIter.next();
        }
    }
    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIter();
    }
}
