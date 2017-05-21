package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import model.PuzzleGame.action;
import model.ActionSet;

public class GreedyPlayer extends Player {

	@Override
	public List<action> solve(PuzzleGame game) {
		//List of steps to solve the game
		List<action> result = new ArrayList<>();
		//List of items possible to make in the game
		List<ActionSet> possibleActions = new ArrayList<>();
		//Current step in the game
		int curStep = 1;
		
		//Board in the previous step
		Integer[][] oldBoard = null;
		
		//Current board
		Integer[][] currentBoard = game.getGameBoard();
		
		while(game.isSolution(currentBoard)) {
			Random rand = new Random();
			
			//Allowed actions in the current step
			action[] allowedActions = game.getPossibleActions(currentBoard);
			//Next action
			action next = null;
			//Heuristic value of the next action
			int effect = Integer.MAX_VALUE;
			//Step in which the next action was possible to take
			int stepOfNext = 0;
			
			//Adds all new available actions into the list of possible actions
			for(action newAction: allowedActions) {
				possibleActions.add(new ActionSet(curStep, newAction));
			}
			
			for(ActionSet nextActionSet: possibleActions) {
				//New board if the action from nextActionSet is used
				Integer[][] possibleBoard = game.computeAction(nextActionSet.getAction(), currentBoard);
				
				//System.out.print("->" + a + ", " + game.getHeuristicValue(possibleBoard));
				//System.out.println(possibleBoard.toString() + ", " + oldBoard.toString() + ", " + Arrays.equals(possibleBoard, oldBoard));
				
				/*Checks whether the new action in the nextActionSet is better than the one currently selected
				and if the new actions is not cancels the last one*/
				if(game.getHeuristicValue(possibleBoard) <= effect && !Arrays.equals(possibleBoard, oldBoard)) {
					//If both actions are equally good, a random one is taken
					if(game.getHeuristicValue(possibleBoard) == effect) {
						if(rand.nextBoolean() == true){
							effect = game.getHeuristicValue(possibleBoard);
							next = nextActionSet.getAction();
						}
					} else {
						effect = game.getHeuristicValue(possibleBoard);
						next = nextActionSet.getAction();
					}
				}
			}
			
			System.out.print("\n");
			System.out.println("Event: " + next + ": " + effect);
			
			oldBoard = currentBoard;
			currentBoard = game.computeAction(next, currentBoard);
			
			//Removes the actions that has been made till the step the new action is from
			for(int j = 0; j < (curStep-stepOfNext); j++) {
				result.remove(result.size()-1);
			}
			
			//Adds the new action to the result
			result.add(next);
		}
		return result;
	}

}
