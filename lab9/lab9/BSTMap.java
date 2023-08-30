package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author JoyceXu
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;
        public boolean isLeaf(){
            return left == null && right == null;
        }

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        throw new UnsupportedOperationException();
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        Node here = root;
        V ans = null;
        while(here!=null){
            if(here.key.compareTo(key) == 0){
                ans = here.value;
                break;
            }
            else if (here.key.compareTo(key) > 0){
                here = here.left;
            }
            else{
                here = here.right;
            }
        }
        return ans;
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        throw new UnsupportedOperationException();
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        Node here = root;
        //boolean changed = false;
        if(here == null){
            root = new Node(key, value);
            size++;
            return;
        }
        while(true){
            if(here.key.compareTo(key) == 0){//equals: update the mapping
                here.value = value;
                //changed = true;
                break;
            }
            else if (here.key.compareTo(key) > 0){//bigger: choose the left tree, continue binary searching
                if(here.left != null){
                    here = here.left;}
                else{//reach a leaf node: add a new node
                    size++;
                    here.left = new Node(key,value);
                    break;
                }
            }
            else{
                if(here.right != null){
                    here = here.right;}
                else{
                    size++;
                    here.right = new Node(key,value);
                    break;
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
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
