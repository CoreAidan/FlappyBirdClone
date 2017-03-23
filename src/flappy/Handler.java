package flappy;

import flappy.keyInput.KeyInput;

public class Handler {
	
	private Game game;
	
	public Handler(Game game){
		this.game = game;
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

	public void setGame(Game game) {
		this.game = game;
	}

}