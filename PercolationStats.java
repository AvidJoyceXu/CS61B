package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import java.lang.Math;
public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private double[] prob;
    private double mean;
    private double stddev;
    private int t;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if(N <= 0 || T <= 0 ){
            throw new IllegalArgumentException();
        }
        prob = new double[T];
        t = T;
        for(int i = 0;i < T;i++){
            //reconstruct a brand-new percolation model.
            Percolation sample = pf.make(N);
            while(! sample.percolates()){
                int x = StdRandom.uniform(N);
                int y = StdRandom.uniform(N);
                sample.open(x,y);
            }
            prob[i] = sample.numberOfOpenSites() / (N * N + 0.0);
        }
        mean = StdStats.mean(prob);
        stddev = StdStats.stddev(prob);
    }
    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(t);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(t);
    }
}
