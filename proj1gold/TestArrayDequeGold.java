import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    /*public String messageProducer(String[] strings){
        StringBuilder ans = new StringBuilder();
        for(String str : strings){
            ans.append(str).append('\n');
        }
        return ans.toString();
    }*/
    private String message = "";
    @Test
    public void StudentArrayDequeTester(){
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        while(true){
            Integer rd = StdRandom.uniform(4);
            Integer med;
            switch(rd){
                case 0: {
                    med = StdRandom.uniform(100);
                    message += "addFirst(" + med + ")" + '\n';
                    sad.addFirst(med);
                    ads.addFirst(med);
                    assertEquals(message, ads.getFirst(), sad.get(0));
                    break;}
                case 1: {
                    med = StdRandom.uniform(100);
                    message += "addLast(" + med + ")" + '\n';
                    sad.addLast(med);
                    ads.addLast(med);
                    assertEquals(message, ads.getLast(), sad.get(sad.size()-1));
                    break;}
                case 2: {
                    message += "removeFirst()\n";
                    assertEquals(message, ads.removeFirst(), sad.removeFirst());
                    break;}
                case 3: {
                    message += "removeLast()\n";
                    assertEquals(message, ads.removeLast(), sad.removeLast());
                    break;}
            }
        }
    }
}
