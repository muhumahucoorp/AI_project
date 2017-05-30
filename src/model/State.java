package model;

import java.util.Arrays;
import java.util.List;

import model.PuzzleGame.action;

/**
 * Set with an action, its heuristic value and
 * the board, the action causes.
 * 
 * @author Malte Lucius
 *
 */
public class State {
	
	private List<action> actions;
	private Integer[][] board;
	private int heuristicValue;
	
	public State(List<action> actions, Integer[][] board, int heuristicValue) {
		this.actions = actions;
		this.board = board;
		this.heuristicValue = heuristicValue;
	}
	
	
	public boolean equals(State other) {
	    
	    if (this == other) return true;
	    if (heuristicValue != other.getHeuristicValue()) return false;
	    if (!Arrays.deepEquals(board, other.getBoard())) return false;
	    // The algorithm does not care for the action path.
	    
	    return true;
	}
	
	/**
	 * Standard setter of the action
	 */
	public void setActions(List<action> actions) {
		this.actions = actions;
	}
	
	/**
	 * Standard getter of the action
	 */
	public List<action> getActions() {
		return this.actions;
	}
	
	/**
	 * Standard setter of a board
	 */
	public void setBoard(Integer[][] board) {
		this.board = board;
	}
	
	/**
	 * Standard getter of a board
	 */
	public Integer[][] getBoard() {
		return this.board;
	}
	
	/**
	 * Standard setter of the heuristicValue
	 */
	public void setHeuristicValue(int heuristicValue) {
		this.heuristicValue = heuristicValue;
	}
	
	/**
	 * Standard getter of the heuristicValue
	 */
	public int getHeuristicValue() {
		return this.heuristicValue;
	}
}
