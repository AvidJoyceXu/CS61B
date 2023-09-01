package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Comparator;

import static java.util.Collections.reverse;

public class Solver {
    /*
    * Constructor which solves the puzzle, computing
      everything necessary for moves() and solution() to
      not have to solve the problem again. Solves the
      puzzle using the A* algorithm. Assumes a solution exists.*/
    private int moves;
    private ArrayList<WorldState> solution;
    private class SearchNode {
        public WorldState cur;
        public int move;
        public SearchNode prev;
        private int priority = -1;
        private int priority(){
            if(priority<0)
                priority = move + cur.estimatedDistanceToGoal();
            return priority;
        }
        public SearchNode(WorldState c, int m, SearchNode p){
            cur = c; move = m; prev = p;
        }
    }
    private class MinPQComparator implements Comparator<SearchNode>{
        @Override
        public int compare(SearchNode t0, SearchNode t1) {
            return t0.priority() - t1.priority();
        }
    }
    public Solver (WorldState initial){
        MinPQ<SearchNode> pq = new MinPQ<>(new MinPQComparator());
        pq.insert(new SearchNode(initial, 0, null));
        while(!pq.isEmpty()){
            SearchNode nd = pq.delMin();
            if(nd.cur.isGoal()){
                moves = nd.move;
                solution = new ArrayList<WorldState>();
                while(nd.prev != null){
                    solution.add(nd.cur);
                    nd = nd.prev;
                }
                solution.add(nd.cur);//adding the initial state
                reverse(solution);
                return;
            }
            for (WorldState nxt : nd.cur.neighbors()) {
                if(nd.prev == null || !nxt.equals(nd.prev.cur)){
                    pq.insert(new SearchNode(nxt, nd.move+1, nd));
                }
            }// a critical optimization
        }
    }
    /*
    * Returns the minimum number of moves to solve the puzzle starting
      at the initial WorldState.*/
    public int moves(){
        return moves;
    }
    /*
    Returns a sequence of WorldStates from the initial WorldState
    to the solution.*/
    public Iterable<WorldState> solution(){
        return solution;
    }
}
