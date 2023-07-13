public class LinkedListDeque<T> {
    //private Node sentinel;
    private Node first,last;//keep track of these nodes to ensure constant-time operation
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
    }
    public LinkedListDeque(){
        size = 0;
        first = null;last = null;//nothing happens
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
        if(first!=null){
            first.prev = new Node(null,item,first);
            first = first.prev;
        }
        else{
            first = new Node(null,item,first);
            last = first;
        }
        size += 1;
    }
    public void addLast(T item){
        if(last!=null){
            last.next = new Node(last,item,null);
            last = last.next;
        }
        else {
            last = new Node(last, item, null);
            first = last;
        }
        size += 1;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        Node p = first;
        while(p!=null){
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println();//print a new line
    }
    public T removeFirst(){
        size -= 1;
        T ret = first.item;
        first = first.next;
        if(first!=null)
            first.prev = null;
        return ret;
    }
    public T removeLast(){
        size -= 1;
        T ret = last.item;
        last = last.prev;
        if(last !=null)
            last.next = null;
        return ret;
    }
    public T get(int index){
        Node p = first;
        while(index>0){
            p = p.next;
            index--;
        }
        return p.item;
    }
    public  T getRecursive(int index){
        if(index==0) {
            return first.item;
        }
        return getRecursive(index-1);
    }
}
