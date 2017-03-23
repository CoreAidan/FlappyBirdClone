package flappy;

public class Launcher {
	
	public static void main(String[] args){
		Game game = new Game("Crappy Bird", 400, 600);
		game.start();
	}
	
}