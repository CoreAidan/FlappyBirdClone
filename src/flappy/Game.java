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

public class Game implements Runnable{
	
	private Display display;
	private int width, height;
	public String title;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private boolean running = false;
	private Thread thread;
	
	private KeyInput keyboard;
	private Handler handler;
	
	private Bird bird;
	private ArrayList<Pipe> pipes;
	private Background bg;
	
	
	public Game(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		keyboard = new KeyInput();
	}
	
	private void init(){
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyboard);
		Assets.init();
		
		handler = new Handler(this);
		bird = new Bird(handler);
		
		bg = new Background(handler);
		
		pipes = new ArrayList<Pipe>();
		pipes.add(new Pipe(handler));
		
	}
	
	private void update(){
		keyboard.update();
		bird.update();
		for(int i = 0; i < pipes.size(); i ++){
			pipes.get(i).update();
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
		bg.render(g);
		
		bird.render(g);
		
		
		if(pipes.size() <= 1 && pipes.get(0).reachedMiddleOfScreen()){
			pipes.add(new Pipe(handler));
		}
		
		if(pipes.get(0).isOffScreen()){
			pipes.remove(0);
		}
		
		for(int i = 0; i < pipes.size(); i ++){
			
			if(pipes.get(i).hits(bird)){
				System.out.println("hits");
			}
			pipes.get(i).render(g);
			
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
				System.out.println("Updates and Frames: " + updates);
				updates = 0;
				timer = 0;
			}
		}
		
		stop();
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
