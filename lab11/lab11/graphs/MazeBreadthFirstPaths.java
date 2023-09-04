package lab11.graphs;

/**
 *  @author Josh Hug
 */
import java.util.AbstractQueue;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);//first construct the parent-class object
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        System.out.println(t);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> qu = new ArrayDeque<>();
        qu.add(s);
        marked[s] = true;
        announce();
        while(!qu.isEmpty()){
            int nxt = qu.remove();
            System.out.println("Current node is :"+nxt);
            if(nxt == t){
                return;
            }
            for(int neighbor: maze.adj(nxt)){
                if(!marked[neighbor]){
                    marked[neighbor] = true;
                    distTo[neighbor] = distTo[nxt]+1;
                    edgeTo[neighbor] = nxt;
                    announce();
                    qu.add(neighbor);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

