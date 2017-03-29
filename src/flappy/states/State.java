package flappy.states;

import java.awt.Graphics;

import flappy.Handler;

public abstract class State {
	
	private static State currentState = null;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public static void setState(State state){
		currentState = state;
	}
	
	public static State getState(){
		return currentState;
	}
	//CLASS
	protected Handler handler;
	
	
	
	public abstract void update();
	
	public abstract void render(Graphics g);
	
}
