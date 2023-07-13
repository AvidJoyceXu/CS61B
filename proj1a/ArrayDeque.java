//import java.lang.reflect.Array;

public class ArrayDeque<T> {
    private int size;
    private int front = 0;
    private int last = 0; //(last - first) equals size && last holds no value
    private int max_size = 8;
    private T[] items = (T []) new Object[8]; //actually a pointer
    public ArrayDeque() {
        size = 0;
    }
    /*public ArrayDeque(ArrayDeque other){
        max_size = other.max_size;
        items = (T []) new Object[other.max_size];
        front = other.front;
        last = other.last;
        System.arraycopy(other.items,0,items,0,max_size);
    }*/
    private void resize_big() {
        T[] p = (T[]) new Object[max_size * 2];
        if (front >= last) {
            System.arraycopy(items, front, p, 0, max_size - front);
            //System.arraycopy(p,max_size-front,items,0,last);
            System.arraycopy(items, 0, p, max_size - front, last);
        }
        else {
            System.arraycopy(items, front, p, 0, size);
        }
        max_size *= 2;
        front = 0;
        last = size;
        items = p;
    }
    private void resize_small() {
        T[] p = (T[]) new Object[max_size / 2];
        if (front >= last) {
            System.arraycopy(items, front, p, 0, max_size - front);
            //System.arraycopy(p,max_size-front,items,0,last);
            System.arraycopy(items, 0, p, max_size - front, last);
        }
        else{
            System.arraycopy(items, front, p, 0, size);
        }
        max_size /= 2;
        front = 0;
        last = size;
        items = p;
    }
    private int boundary_protector(int x) {
        while (x < 0) {
            x += max_size; }
        while (x >= max_size) {
            x -= max_size; }
        return x;
    }
    public void addFirst(T item) {
        if (size < max_size) {
            front = boundary_protector(front - 1);
            size++;
            items[front] = item;
        }
        else {//resizing
            resize_big();
            addFirst(item);
        }
    }
    public void addLast(T item) {
        if (size < max_size) {
            size++;
            items[last] = item;//ensure last points to no value
            last = boundary_protector(last + 1);
        }
        else { //resizing
            resize_big();
            addLast(item);
        }
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque(){
        int cnt = 0;
        for(int p = front; cnt < size; p = (p + 1) % max_size, cnt++) {
            System.out.print(items[p] + " ");
        }
        System.out.println();
    }
    public T removeFirst() {
        if (size - 1 < max_size / 4) {
            resize_small();
            return removeFirst();
        }
        else {
            size--;
            if (front < last) {
                T ret = items[front];
                items[front] = null;
                front++;
                return ret;
            }
            else {
                T ret = items[front];
                items[front] = null;
                front = boundary_protector(front+1);
                return ret;
            }
        }
    }
    public T removeLast() {
        if (size - 1 < max_size / 4) {
            resize_small();
            return removeLast();
        }
        else {
            size--;
            last = boundary_protector(last - 1);
            T ret = items[boundary_protector(last)];//need protecting
            items[boundary_protector(last)] = null;
            return ret;
        }
    }
    public T get(int index) {
        index = boundary_protector(front + index);
        return items[index];
    }
    /*public static void main(String[]bla){
        ArrayDeque ad = new ArrayDeque();
        ad.addLast(0);
        ad.removeFirst();
        ad.addFirst(2);
        ad.addFirst(3);
        ad.removeFirst();
        ad.addFirst(5);
        ad.removeFirst();
        ad.get(0);
        ad.removeFirst();
    }*/
}
