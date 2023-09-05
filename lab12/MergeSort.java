import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item> > ret = new Queue<>();
        for(Item item: items){
            Queue<Item> newQ = new Queue<>();
            newQ.enqueue(item);
            ret.enqueue(newQ);
        }
        return ret;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        if(q1.isEmpty() && q2.isEmpty())
            return q1;
        Item min = getMin(q1, q2);
        Queue<Item> ret = new Queue<>();
        ret.enqueue(min);
        Queue<Item> newQ = mergeSortedQueues(q1, q2);
        for (Item item : newQ) {
            ret.enqueue(item);
        }
        return ret;
    }
    /*@Test
    public void testMergeSortedQueues(){
        Queue<String> s1 = new Queue<String>();
        s1.enqueue("Alice");
        s1.enqueue("Vanessa");
        Queue<String> s2 = new Queue<>();
        s2.enqueue("Ethan");
        Queue<String> s = mergeSortedQueues(s1, s2);
        assertEquals("Alice",s.dequeue());
        assertEquals("Ethan", s.dequeue());
        assertEquals("Vanessa", s.dequeue());
    }*/
    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if(items.isEmpty())
            return items;
        Queue<Queue<Item> > split = makeSingleItemQueues(items);
        while(split.size() > 1){
            Queue<Queue<Item> > newSplit = new Queue<>();
            while(!split.isEmpty()){
                Queue<Item> q1 = split.dequeue();
                if(!split.isEmpty()){
                    Queue<Item> q2 = split.dequeue();
                    newSplit.enqueue(mergeSortedQueues(q1, q2));
                }
                else{
                    newSplit.enqueue(q1);
                }
            }
            split = newSplit;
        }
        return split.dequeue(); // dequeue from a zero-sized queue will cause stack underflow
    }
    public static void main(String[] bur){
        Queue<String> students = new Queue<String>();
        students.enqueue("B: ZhuBaJie");
        students.enqueue("A: Monkey King");
        students.enqueue("C: ShaSeng");
        Queue<String> ABC = mergeSort(students);
        for(String bla: students){
            System.out.println(bla);
        }
        for(String bla: ABC){
            System.out.println(bla);
        }

    }
}
