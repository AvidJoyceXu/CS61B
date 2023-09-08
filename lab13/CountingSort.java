/**
 * Class with 2 ways of doing Counting sort, one naive way and one "better" way
 *
 * @author Akhil Batra, Alexander Hwang
 *
 **/
public class CountingSort {
    /**
     * Counting sort on the given int array. Returns a sorted version of the array.
     * Does not touch original array (non-destructive method).
     * DISCLAIMER: this method does not always work, find a case where it fails
     *
     * @param arr int array that will be sorted
     * @return the sorted array
     */
    public static int[] naiveCountingSort(int[] arr) {
        // find max
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.max(max, i);
        }

        // gather all the counts for each value
        int[] counts = new int[max + 1];
        for (int i : arr) {
            counts[i]++;
        }

        // when we're dealing with ints, we can just put each value
        // count number of times into the new array
        int[] sorted = new int[arr.length];
        int k = 0;
        for (int i = 0; i < counts.length; i += 1) {
            for (int j = 0; j < counts[i]; j += 1, k += 1) {
                sorted[k] = i;
            }
        }

        // however, below is a more proper, generalized implementation of
        // counting sort that uses start position calculation
        int[] starts = new int[max + 1];
        int pos = 0;
        for (int i = 0; i < starts.length; i += 1) {
            starts[i] = pos;
            pos += counts[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int i = 0; i < arr.length; i += 1) {
            int item = arr[i];
            int place = starts[item];
            sorted2[place] = item;
            starts[item] += 1;
        }

        // return the sorted array
        return sorted;
    }
    private static int getPos(int neg, int curMax){
        return Math.abs(neg);
    }
    /**
     * Counting sort on the given int array, must work even with negative numbers.
     * Note, this code does not need to work for ranges of numbers greater
     * than 2 billion.
     * Does not touch original array (non-destructive method).
     *
     * @param arr int array that will be sorted
     */
    public static int[] betterCountingSort(int[] arr) {
        // TODO make counting sort work with arrays containing negative numbers.
        // a better way to do this is to project the smallest negative number to 0, then only need one coherent traversal
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int i: arr){
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        if(min >= 0){
            return naiveCountingSort(arr);
        }
        int[] counts_pos = new int[max + 1];
        int[] counts_neg = new int[-min];
        for (int i : arr) {
            if(i>=0){
                counts_pos[i]++;
            }
            else{
                counts_neg[-i-1]++;
            }
        }
        int[] starts_pos = new int[max + 1];
        int[] starts_neg = new int[-min];
        int pos = 0;
        for (int i = -min-1; i >= 0; i--){
            starts_neg[i] = pos;
            pos += counts_neg[i];
        }
        for (int i = 0; i <= max; i += 1) {
            starts_pos[i] = pos;
            pos += counts_pos[i];
        }

        int[] sorted2 = new int[arr.length];
        for (int item: arr) {
            int place;
            if(item >=0 ){
                place = starts_pos[item]++;
            }
            else{
                place = starts_neg[-item-1]++;
            }
            sorted2[place] = item;
        }
        return sorted2;
    }
}
