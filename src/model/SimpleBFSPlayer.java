package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import model.PuzzleGame.action;

public class SimpleBFSPlayer extends Player {

    @Override
    public List<action> solve(PuzzleGame game) {
	
	List<action> solution = new ArrayList<>();
	
	Queue<State> fifo = new LinkedList<>();

	// initial state (heuristic value not beeing used in simple BFS)
	State current = new State(solution, game.getGameBoard(), 0);
	
	fifo.add(current);
	
	/* Until game is solved, keep searching.
	 * Slides claim that if there is a solution it will be found.
	 * Unless game is broken there will always be a solution.
	 */
	while(!game.isSolution(current.getBoard())) {
	    
	    current = fifo.poll();
	    
	    PuzzleGame.action valid_actions[] = game.getPossibleActions(current.getBoard());
	    for (int a = 0; a < valid_actions.length; a++) {
		List<action> path = copyActionList(current.getActions());
		path.add(valid_actions[a]);
		State s = new State(path, game.computeAction(valid_actions[a], current.getBoard()), 0);
		if (!fifo.contains(s)) {		    
		    fifo.add(s);
		}
	    }
	    
	    System.out.println(fifo.size());
	}
	
	return current.getActions();
    }

    /*
     * Copy an action list.
     */
    private List<action> copyActionList(List<action> list) {
	List<action> result = new LinkedList<>();
	result.addAll(list);
	return result;
    }
}
