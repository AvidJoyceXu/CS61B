package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // create N-by-N grid, with all sites initially blocked
    // false in grids indicates blocked, true otherwise.
    private int openSitesNum = 0;
    private WeightedQuickUnionUF grids;
    private boolean[][] bGrids;
    private int n;
    private int xyToInt(int r, int c){
        return r * n + c + 1;
    }
    private int[][] ds = {{0,1},{0,-1},{1,0},{-1,0}};
    private boolean inBorder(int r, int c){
        return (r>-1) && (r<n) && (c>-1) && (c<n);
    }
    public Percolation(int N) {
        if(N <= 0){
            throw new IllegalArgumentException("N <= 0");
        }
        n = N;
        grids = new WeightedQuickUnionUF(N * N + 2);
        bGrids = new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j = 0;j<N;j++){
                bGrids[i][j] = false;
            }
        }
        for(int i = 0;i < N;i++){
            //connect the top site to the N sites in the first line
            grids.union(0,xyToInt(0, i));
            //connect the bottom site to the N sites in the last line
            grids.union( N*N+1, xyToInt(N-1, i));
        }

    }

    public void open(int row, int col) {      // open the site (row, col) if it is not open already
        if(! inBorder(row, col)){
            throw new IndexOutOfBoundsException("Illegal (row, col)");
        }
        if(! bGrids[row][col]){//not opened
            openSitesNum ++ ;
            bGrids[row][col] = true;
            for(int i=0;i<4;i++){
                int next_x = row + ds[i][0];
                int next_y = col + ds[i][1];
                //be careful to do in-border checking!
                if(inBorder(next_x,next_y) && bGrids[next_x][next_y]){
                    grids.union(xyToInt(next_x, next_y), xyToInt(row, col));
                }
            }
        }
    }
    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if(! inBorder(row, col)){
            throw new IndexOutOfBoundsException("Illegal (row, col)");
        }
        return bGrids[row][col];
    }
    public boolean isFull(int row, int col) { // is the site (row, col) full?
        if(! inBorder(row, col)){
            throw new IndexOutOfBoundsException("Illegal (row, col)");
        }
        //first opened, then connected.
        return isOpen(row, col) && grids.connected(0, xyToInt(row, col));
    }
    public int numberOfOpenSites() {         // number of open sites
        return openSitesNum;
    }
    public boolean percolates(){// does the system percolate?
        return grids.connected(0, n*n + 1);
    }
    // use for unit testing (not required)
    public static void main(String[] args){

    }

}
