package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import model.PuzzleGame.action;

/**
 * 
 * @author Dennis Rachui
 */
public class AStarPlayerTwo extends Player {

	@Override
	public List<action> solve(PuzzleGame game) {
		
		long start = System.currentTimeMillis();

		List<State> allStates = new ArrayList<>();
		
		Queue<State> fifo = new PriorityQueue<>(new Comparator<State>() {
			@Override
			public int compare(State s1, State s2) {
				int s1_len = s1.getHeuristicValue() + s1.getActions().size();
				int s2_len = s2.getHeuristicValue() + s2.getActions().size();
				
				if(s1_len < s2_len) {
					return -1;
				} else if(s1_len > s2_len) {
					return 1;
				} 
				
				return 0;
			}
		});
		
	
		// initial state (heuristic value not beeing used in simple BFS)
		State current = new State(null, game.getGameBoard(), 0);
		
		fifo.add(current);
		
		/* Until game is solved, keep searching.
		 * Slides claim that if there is a solution it will be found.
		 * Unless game is broken there will always be a solution.
		 */
		while(!game.isSolution(current.getBoard())) {
		    
			current = fifo.poll();
		    
		    PuzzleGame.action valid_actions[] = game.getPossibleActions(current.getBoard());
		    for (int a = 0; a < valid_actions.length; a++) {
		    	List<action> path = new ArrayList<>(current.getActions());
				path.add(valid_actions[a]);
				
				Integer[][] newBoard = game.computeAction(valid_actions[a], current.getBoard());		
				State s = new State(path, newBoard, game.getHeuristicValue(newBoard));
				
				if (!allStates.contains(s)) {	    
					fifo.add(s);
					allStates.add(s);
				}
		    }
		}

		System.out.println(this.getClass().toString() + " computing time: " + (System.currentTimeMillis()-start) + "ms");
		
		return current.getActions();
	}
}
