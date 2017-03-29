package flappy.states;

import java.awt.Graphics;
import java.util.ArrayList;

import flappy.Handler;
import flappy.entities.Background;
import flappy.entities.Bird;
import flappy.entities.Floor;
import flappy.entities.Pipe;
import flappy.gfx.Assets;
import flappy.ui.ClickListener;
import flappy.ui.UIImageButton;
import flappy.ui.UIManager;

public class GameState extends State {
	
	private Bird bird;
	private ArrayList<Pipe> pipes;
	private ArrayList<Floor> floor;
	private ArrayList<Background> bg;
	
	UIManager uiManager;
	
	private int score;
	
	
	public GameState(Handler handler){
		super(handler);
		
		uiManager = new UIManager(handler);
		
		score = 0;
		
		bird = new Bird(handler);
		
		bg = new ArrayList<Background>();
		bg.add(new Background(handler));
		
		pipes = new ArrayList<Pipe>();
		pipes.add(new Pipe(handler));
		
		floor = new ArrayList<Floor>();
		floor.add(new Floor(handler));
		//floor.add(new Floor(handler));
	}
	
	private void gameOver(){
		handler.setPaused(true);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 297 / 2, 120 , 297, 66, Assets.gameOverLogo
				, new ClickListener(){
			
			@Override
			public void onClick() {
				
			}
		}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2, handler.getHeight() / 2 , 180, 70, Assets.startButton, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				handler.reset();
			}
		}));
	}

	@Override
	public void update() {
		bird.update();
		uiManager.update();
		for(int i = 0; i < pipes.size(); i++){
			pipes.get(i).update();
		}
		for(int i = 0; i < floor.size(); i++){
			floor.get(i).update();
		}
		for(int i = 0; i < bg.size(); i++){
			bg.get(i).update();
		}
		
		for(int i = pipes.size() - 1; i >= 0; i--){
			if(pipes.get(i).hits(bird)){
				System.out.println("you got hit you lose");
				gameOver();
			}
			if(bird.getX() == pipes.get(i).getX() + Pipe.getWidth()){
				score++;
				System.out.println("Score: " + score);
			}
		}
		
	}
	
	

	@Override
	public void render(Graphics g) {
		if(bg.size() <= 1 && bg.get(0).getX() < 0){
			bg.add(new Background(handler));
			bg.get(1).setX(bg.get(0).getX() + bg.get(0).getWidth());
		}
		
		for(int i = bg.size() - 1; i >= 0; i--){
			bg.get(i).render(g);
		}
		
		if(bg.get(0).isOffScreen()){
			bg.remove(0);
		}
		
		if(pipes.size() <= 1 && pipes.get(0).reachedMiddleOfScreen()){
			pipes.add(new Pipe(handler));
		}
		
		if(pipes.get(0).isOffScreen()){
			pipes.remove(0);
		}
		
		for(int i = 0; i < pipes.size(); i ++){
			pipes.get(i).render(g);
		}
		
		if(floor.size() <= 1 && floor.get(0).getX() < 0){
			floor.add(new Floor(handler));
			floor.get(1).setX(floor.get(0).getX() + floor.get(0).getWidth());
		}
		
		if(floor.get(0).isOffScreen()){
			floor.remove(0);
		}
		
		for(int i = floor.size() - 1; i >= 0; i--){
			floor.get(i).render(g);
		}
		
		bird.render(g);
		uiManager.render(g);
		
	}
	
	
	
}
