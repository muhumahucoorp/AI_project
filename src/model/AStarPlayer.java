package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import model.PuzzleGame.action;

public class AStarPlayer extends Player {

	@Override
	public List<action> solve(PuzzleGame game) {
		
		List<State> oldStates = new ArrayList<>();
		
		PriorityQueue<State> possibleStates = new PriorityQueue<>(new Comparator<State>() {
			@Override
			public int compare(State s1, State s2) {
				// TODO Auto-generated method stub
				if(s1.getHeuristicValue() + s1.getActions().size() < s2.getHeuristicValue() + s2.getActions().size()) {
					return -1;
				} else if(s1.getHeuristicValue() + s1.getActions().size() > s2.getHeuristicValue() + s2.getActions().size()) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		
		State solutionState = new State(new ArrayList<action>(), game.getGameBoard(), game.getHeuristicValue(game.getGameBoard()));		
		
		while(!game.isSolution(solutionState.getBoard())) {
			
			State newState = null;
			
			
			//Allowed actions in the current step
			action[] allowedActions = game.getPossibleActions(solutionState.getBoard());
				
			//Adds all new available actions into the list of possible actions
			for(action newAction : allowedActions) {
				//New board if the action from nextActionSet is used
				Integer[][] possibleBoard = game.computeAction(newAction, solutionState.getBoard());
				boolean makesLoop = false;
				List<State> allStates = new ArrayList<>(oldStates);
				allStates.addAll(possibleStates);
				for(State state : allStates) {
					if(Arrays.deepEquals(state.getBoard(), possibleBoard)) {
						//System.out.println("Loop detected");
						makesLoop = true;
						break;
					}
				}
				
				if(!makesLoop) {
					//System.out.println("New possible action found");
					List<action> actions = new ArrayList<>(solutionState.getActions());
					actions.add(newAction);
					//System.out.println("Making new state");
					State state = new State(actions, possibleBoard, game.getHeuristicValue(possibleBoard));
					possibleStates.add(state);
				}
			}
			
			oldStates.add(solutionState);
			
			solutionState = possibleStates.poll();
		}
		
		System.out.println("Solved");
		
		return solutionState.getActions();
	}
	
}
