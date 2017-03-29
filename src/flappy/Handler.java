package flappy;

import flappy.keyInput.KeyInput;
import flappy.keyInput.MouseManager;

public class Handler {
	
	private Game game;
	
	public Handler(Game game){
		this.game = game;
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public KeyInput getKeyboard(){
		return game.getKeyboard();
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setPaused(boolean b){
		this.game.setPaused(b);
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	public void reset(){
		game.restart();
	}

}