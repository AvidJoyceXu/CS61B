package hw4.puzzle;

import java.util.ArrayList;
import edu.princeton.cs.algs4.Queue;
public class Board implements WorldState{
    /* Constructs a board from an N-by-N array of tiles where
       tiles[i][j] = tile at row i, column j*/
    private int[][] tiles;
    private final int N;
    private int[][] ds = {{0,1},{0,-1},{1,0},{-1,0}};
    public Board(int[][] tiles){
        N = tiles.length;
        //this.tiles = tiles.clone(); clone is shallow copy!
        this.tiles = new int[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                this.tiles[i][j] = tiles[i][j];//deep copy manually
            }
        }
    }
    private boolean inBounds(int i, int j){
        return i>-1 && i<N && j>-1 && j<N;
    }
    /*Returns value of tile at row i, column j (or 0 if blank)*/
    public int tileAt(int i, int j){
        if(!inBounds(i, j)){
            throw new java.lang.IndexOutOfBoundsException("tileAt illegal (i, j)");
        }
        return tiles[i][j];
    }
    /*Returns the board size N*/
    public int size(){
        return N;
    }
    @Override
    //Returns the neighbors of the current board
    public Iterable<WorldState> neighbors(){
        /*int x = -1,y = -1;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(tiles[i][j] == 0){
                    x = i;y = j;break;
                }
            }
        }
        int nx,ny;
        ArrayList<WorldState> ret = new ArrayList<>();
        for(int i=0;i<4;i++){
            nx = x + ds[i][0];
            ny = y + ds[i][1];
            if(inBounds(nx, ny)){
                int med = tiles[nx][ny];
                tiles[nx][ny] = tiles[x][y];
                tiles[x][y] = med;
                ret.add(new Board(tiles));
                tiles[x][y] = tiles[nx][ny];
                tiles[nx][ny] = med;
            }
        }
        return ret;*/
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == 0) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 -     zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = 0;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = 0;
                }
            }
        }
        return neighbors;
    }
    //Hamming estimate described below
    public int hamming(){
        int ret=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!(i==N-1 && j==N-1) && tiles[i][j]!=(i*N+j+1))
                    ret++;
            }
        }
        return ret;
    }
    //Manhattan estimate described below
    public int manhattan(){
        int ret=0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(tiles[i][j]==0)
                    continue;
                int nx = tiles[i][j]/N;
                int ny = tiles[i][j]%N - 1;
                if(ny<0){
                    ny = N-1;
                    i = tiles[i][j]/N - 1;
                }
                ret += java.lang.Math.abs(nx-i)+java.lang.Math.abs(ny-j);
            }
        }
        return ret;
    }
    /*Returns true if this board's tile values are the same
      position as y's*/
    public boolean equals(Object y){
        Board z = (Board) y;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(tiles[i][j]!=z.tiles[i][j])
                    return false;
            }
        }
        return true;
    }
    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    @Override
    /*Estimated distance to goal. This method should
      simply return the results of manhattan() when submitted to
      Gradescope.*/
    public int estimatedDistanceToGoal() {
        return hamming();
    }

}
