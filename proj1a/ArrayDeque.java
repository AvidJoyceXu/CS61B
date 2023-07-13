import java.lang.reflect.Array;

public class ArrayDeque<T>{
    private int size;
    private int front = 0;
    private int last = 0;//(last - first) equals size && last holds no value
    private int max_size = 8;
    private T[] items = (T []) new Object[8];//actually a pointer
    public ArrayDeque(){
        size = 0;
    }
    public ArrayDeque(ArrayDeque other){
        max_size = other.max_size;
        items = (T []) new Object[other.max_size];
        front = other.front;
        last = other.last;
        System.arraycopy(other.items,0,items,0,max_size);
    }
    private void resize_big(){
        T[] p = (T[]) new Object[max_size*2];
        if(front>=last) {
            System.arraycopy(items, front, p, 0, max_size - front);
            //System.arraycopy(p,max_size-front,items,0,last);
            System.arraycopy(items, 0, p, max_size - front, last);
        }
        else{
            System.arraycopy(items,0,p,0,max_size);
        }
        max_size *= 2;
        front = 0;
        last = size;
        items = p;
    }
    private void resize_small(){
        T[] p = (T[]) new Object[max_size/2];
        if(front>=last) {
            System.arraycopy(items, front, p, 0, max_size - front);
            //System.arraycopy(p,max_size-front,items,0,last);
            System.arraycopy(items, 0, p, max_size - front, last);
        }
        else{
            System.arraycopy(items,front,p,0,size);
        }
        max_size /= 2;
        front = 0;
        last = size;
        items = p;
    }
    public void addFirst(T item){
        if(front<=last){
            front = max_size - 1;
        }
        else{
            front--;
        }
        if(size<max_size) {
            size++;
            items[front] = item;
        }
        else{//resizing
            resize_big();
            addFirst(item);
        }
    }
    public void addLast(T item){
        if(size<max_size) {
            size++;
            items[last++] = item;//ensure last points to no value
        }
        else{//resizing
            resize_big();
            addLast(item);
        }
    }
    public boolean isEmpty(){
        return size==0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        for(int p = front;p!=last;p = (p+1)%max_size){
            System.out.print(items[p]+" ");
        }
        System.out.println();
    }
    public T removeFirst(){
        if(size-1<0.25 * max_size){
            resize_small();
            return removeFirst();
        }
        else {
            size--;
            if (front <= last) {
                T ret = items[front];
                items[front] = null;
                front++;
                return ret;
            }
            else {
                T ret = items[front];
                items[front] = null;
                front = (front + 1) % max_size;
                return ret;
            }
        }
    }
    public T removeLast(){
        if(size-1<0.25 * max_size){
            resize_small();
            return removeLast();
        }
        else {
            size--;
            T ret = items[last - 1];
            items[last - 1] = null;
            last--;
            return ret;
        }
    }
    public T get(int index){
        if(front<last){
            return items[index];
        }
        else return items[(front+index)%max_size];
    }
    public static void main(String[] args){
        ArrayDeque test = new ArrayDeque();
        for(int i=0;i<=8;i++){
            test.addLast(i);
        }
        for(int i=0;i<5;i++){
            test.removeFirst();
        }
        test.removeFirst();
        ArrayDeque copy = new ArrayDeque(test);

    }

}
