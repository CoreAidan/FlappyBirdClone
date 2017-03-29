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

public class MenuState extends State{
	
	private Bird bird;
	private ArrayList<Floor> floor;
	private ArrayList<Background> bg;
	private UIManager uiManager;
	

	public MenuState(Handler handler){
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2, handler.getHeight() / 2 , 180, 70, Assets.startButton, new ClickListener(){

			@Override
			public void onClick() {
				handler.getMouseManager().setUIManager(null);
				State.setState(handler.getGame().gameState);
			}
		}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 297 / 2, 120 , 297, 66, Assets.gameLogo
												, new ClickListener(){

			@Override
			public void onClick() {
				
			}
		}));
		
		bird = new Bird(handler);
		
		bg = new ArrayList<Background>();
		bg.add(new Background(handler));
		
		floor = new ArrayList<Floor>();
		floor.add(new Floor(handler));
	}
	
	
	@Override
	public void update() {
		uiManager.update();
		
		for(int i = 0; i < floor.size(); i++){
			floor.get(i).update();
		}
		for(int i = 0; i < bg.size(); i++){
			bg.get(i).update();
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
