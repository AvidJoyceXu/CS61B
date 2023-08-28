package hw3.hash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int[] map = new int[M];
        //initialization
        for(int i=0;i<M;i++){
            map[i] = 0;
        }
        for(Oomage oom: oomages){
            int bktNum = (oom.hashCode() & 0x7FFFFFFF) % M;
            map[bktNum] ++;
        }
        int N = oomages.size();
        for(int i = 0;i<M;i++){
            if(map[i] < N/50 || map[i] > N/2.5)
                return false;
        }
        return true;
    }
}
