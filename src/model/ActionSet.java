package model;

import model.PuzzleGame.action;

/**
 * Set with an action and the step in the game
 * in which it occured
 * 
 * @author Malte Lucius
 *
 */
public class ActionSet {
	
	private int step;
	private action action;
	
	/**
	 * Standard constructor
	 * 
	 * @param step		step in the PuzzleGame
	 * @param action	action in the PuzzleGame
	 */
	public ActionSet(int step, action action) {
		this.step = step;
		this.action = action;
	}
	
	/**
	 * Standard setter of the step
	 */
	public void setStep(int step) {
		this.step = step;
	}
	
	/**
	 * Standard getter of the step
	 */
	public int getStep() {
		return this.step;
	}
	
	/**
	 * Standard setter of the action
	 */
	public void setAction(action action) {
		this.action = action;
	}
	
	/**
	 * Standard getter of the action
	 */
	public action getAction() {
		return this.action;
	}
	
}
