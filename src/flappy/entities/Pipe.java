package flappy.entities;

import java.awt.Color;
import java.awt.Graphics;

import flappy.Handler;

public class Pipe {
		
	private Handler handler;
	
	private static final int WIDTH = 30;
	private static final int SPEED = 2;
	
	private int topHeight;
	private int bottomHeight;  
	
	private int x;
	
	private boolean highlight = false;
	
	public Pipe(Handler handler){
		this.handler = handler;
		
		topHeight = (int)(Math.random() * (handler.getHeight() / 2) + 0);
		bottomHeight = (int)(Math.random() * (handler.getHeight() / 2) + 0);
		
		x = handler.getWidth() + WIDTH;
	}
	
	public void update(){
		x -= SPEED;
	}
	
	public void render(Graphics g){
		g.setColor(Color.BLACK);
		if(highlight)
			g.setColor(Color.RED);
			
		g.fillRect(x, 0, WIDTH, topHeight);
		g.fillRect(x, handler.getHeight() - bottomHeight, WIDTH, bottomHeight);
	}
	
	public boolean reachedMiddleOfScreen(){
		return x <= handler.getWidth() /2;
	}
	
	public boolean isOffScreen(){
		return x == 0 - WIDTH;
	}

	public boolean hits(Bird bird) {
		if(bird.getY() <= topHeight || bird.getY() > handler.getHeight() - bottomHeight){
			if(bird.getX() > x && bird.getX() < x + WIDTH){
				highlight = true;
				return true;
			}
		}
		highlight = false;
		return false;
	}
	
	
	
}
