public class LinkedListDeque<T> {
    //private Node sentinel;
    private Node sentinel; //keep track of these nodes to ensure constant-time operation
    // we have no 'last' pointer cause sentinel.prev is last!
    private int size;
    private class Node{
        public Node prev;
        public T item;
        public Node next;
        public Node(Node prv,T it,Node nxt){
            item = it;
            prev = prv;
            next = nxt;
        }
        public Node(){
        };
    }
    public LinkedListDeque(){
        size = 0;
        sentinel = new Node();
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        //nothing happens
    }
    /*public LinkedListDeque(T item){
        size = 1;
        first = new Node(null,item,null);
        last = first;
    }*/
    /*public LinkedListDeque(LinkedListDeque other){
        Node p = other.first;
        int t = other.size();
        while(t>0){
            addLast(p.item);//invoked by object 'this'
            p = p.next;
            t--;
        }
    }*/
    public void addFirst(T item){
        Node p = new Node(sentinel,item,sentinel.next);
        sentinel.next.prev = p;
        sentinel.next = p;
        size += 1;
    }
    public void addLast(T item){
        Node p = new Node(sentinel.prev,item,sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node p = sentinel.next;
        while(p!=sentinel){
            System.out.print(p.item);
            p = p.next;
        }
        System.out.println();
        /*Node p = sentinel.next;
        while(p!=last){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println();//print a new line*/
    }
    public T removeFirst(){
        size -= 1;
        T ret = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return ret;
        /*T ret = first.item;
        first = first.next;
        if(first!=null)
            first.prev = null;
        return ret;*/
    }
    public T removeLast(){
        size -= 1;
        T ret = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return ret;
        /*T ret = last.item;
        last = last.prev;
        if(last !=null)
            last.next = null;
        return ret;*/
    }
    public T get(int index){
        Node p = sentinel.next;
        while(index>0){
            p = p.next;
            index--;
        }
        return p.item;
    }
    public  T getRecursive(int index){
        if(index==0) {
            return sentinel.next.item;
        }
        return getRecursive(index-1);
    }
}
