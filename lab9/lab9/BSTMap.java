package lab9;

import java.util.HashSet;
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


    private Set<K> keyset;
    private void keySetHelper(Node nd){
        if(nd != null){
            keyset.add(nd.key);
            keySetHelper(nd.left);
            keySetHelper(nd.right);
        }
    }
    @Override
    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        keyset = new HashSet<>();
        keySetHelper(root);
        return keyset;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private boolean hasOneChild(Node nd){
        return !nd.isLeaf() && (nd.left == null || nd.right == null);
    }
    @Override
    public V remove(K key) {
        Node here = root;
        Node parent = null;
        String dir = "Default";
        while(here!=null){
            if(here.key.compareTo(key) == 0){
                break;
            }
            else if (here.key.compareTo(key) > 0){
                parent = here;
                dir = "Left";
                here = here.left;
            }
            else{
                parent = here;
                dir = "Right";
                here = here.right;
            }
        }
        if(here == null){//failed removal in that the specific key is nowhere to be found
            return null;
        }
        V ret = here.value;
        if(here.isLeaf()){
            switch(dir){
                case "Left":parent.left = null;break;
                case "Right":parent.right = null;break;
                default:root = null;break;
            }
        }
        else if (hasOneChild(here)){
            if(parent!=null && dir == "Left"){
                if(here.right != null){
                    parent.left = here.right;
                }
                else{parent.left = here.left;}
            }
            else if (parent!=null && dir == "Right"){
                if(here.right != null){
                    parent.right = here.right;
                }
                else{parent.right = here.left;}
            }
            else if (parent == null){
                if(here.right != null){
                    root = here.right;
                }
                else root = here.left;
            }
        }
        else{//has two child
            //find out the right-most node in the left subtree
            Node rightMost;
            Node here_2 = here.left;
            while(!here_2.right.isLeaf()){
                here_2 = here_2.right;
            }
            rightMost = here_2.right;
            here_2.right = null;
            rightMost.left = here.left;
            rightMost.right = here.right;
            switch(dir){
                case "Left": parent.left = rightMost;
                case "Right": parent.right = rightMost;
                default: root = rightMost;
            }
        }
        return ret;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if(get(key) == value){
            return remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
