package flappy;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import flappy.display.Display;
import flappy.entities.Background;
import flappy.entities.Bird;
import flappy.entities.Pipe;
import flappy.gfx.Assets;
import flappy.keyInput.KeyInput;
import flappy.keyInput.MouseManager;
import flappy.states.GameState;
import flappy.states.MenuState;
import flappy.states.State;

public class Game implements Runnable{
	
	private Display display;
	private int width, height;
	public String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean running = false;
	private boolean paused = false;
	private Thread thread;
	
	private KeyInput keyboard;
	private MouseManager mouseManager;
	private Handler handler;
	
	public State gameState;
	public State menuState;
	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyboard = new KeyInput();
		mouseManager = new MouseManager();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyboard);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	public void restart(){
		Assets.init();
		paused = false;
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
	}
	
	
	private void update(){
		keyboard.update();
			if(State.getState() != null){
				if(!paused)
					State.getState().update();
			}
		
	}
	
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		//clear the screen before we draw
		g.clearRect(0, 0, width, height);
		//draw here
		if(State.getState() != null){
			State.getState().render(g);
		}
		//end drawing
		bs.show();
		g.dispose();
	}
	
	public void run(){
		init();
		int fps = 60;
		double timePerUpdate = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int updates = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerUpdate;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				updates++;
				delta--;
			}
			
			if(timer >= 1000000000){
				//System.out.println("Updates and Frames: " + updates);
				updates = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public MouseManager getMouseManager(){
		return mouseManager;
	}
	
	public KeyInput getKeyboard(){
		return keyboard;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public void setPaused(boolean b){
		this.paused = b;
	}
	
	
	public synchronized void start(){
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		//the start method will exacute the run() method
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try{
			thread.join();
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
