package gameStates;

import java.awt.Graphics2D;

public class StateManager {
	private static StateManager instance;
	private State currentState;
	private double delta;
	
	public static StateManager getInstance(){
		if(instance == null){
			instance = new StateManager();
		}
		return instance;
	}
	
	public void updateCurrentState(){
		currentState.update(delta);
	}
	
	public void drawCurrentState(Graphics2D g){
		currentState.draw(g);
	}
	
	public void setCurrentState(State newState){
		currentState = newState;
		currentState.load();
	}
	
	public void setDelta(double delta){
		this.delta = delta;
	}
	
	public static void changeCurrentState(State newState){
		if(instance.currentState != null){
			instance.currentState.unload();
		}
		newState.load();
		instance.currentState = newState;
	}
}
