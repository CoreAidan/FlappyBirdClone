package flappy.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import flappy.Handler;
import flappy.gfx.Assets;

public class Pipe {
		
	private Handler handler;
	
	private static final int WIDTH = 128;
	private static final int HEIGHT = 540;
	private static final int SPACE = 150;
	private static final int SPEED = 2;
	
	private int topY;
	private int bottomY;  
	
	private int x;
	
	private boolean highlight = false;
	
	private BufferedImage pipeUp;
	private BufferedImage pipeDown;
	
	public Pipe(Handler handler){
		this.handler = handler;
		
		bottomY = (int)(Math.random() * (handler.getHeight() - 300) + (handler.getHeight() - HEIGHT + 140));
		topY = (bottomY) - HEIGHT - SPACE;
		//topHeight = (int)(Math.random() * 0 - HEIGHT);
		x = handler.getWidth() + WIDTH;
		
		pipeUp = Assets.pipeUp;
		pipeDown = Assets.pipeDown;
	}
	
	public void update(){
		x -= SPEED;
	}
	
	public void render(Graphics g){
		//g.setColor(Color.BLACK);
		//if(highlight)
		//g.setColor(Color.RED);
			
		//g.fillRect(x, 0, WIDTH, topHeight);
		//g.fillRect(x, handler.getHeight() - bottomHeight, WIDTH, bottomHeight);
		
		g.drawImage(pipeUp, x, topY, WIDTH, HEIGHT, null);
		g.drawImage(pipeDown, x, bottomY, WIDTH, HEIGHT, null);
		
		//g.fillRect(x + 4, topY, WIDTH - 15, HEIGHT - 2);
		//g.fillRect(x + 4, bottomY, WIDTH - 15, HEIGHT - 2);
	}
	
	public boolean hits(Bird bird) {
		Rectangle bounds = bird.getBounds();
		//bounds.setBounds((int)bird.getX(), (int)bird.getY(), bird.getWidth(), bird.getHeight());
		
		if(bounds.intersects(x + 4, topY, WIDTH - 15, HEIGHT - 2) || bounds.intersects(x + 4, bottomY, WIDTH - 15, HEIGHT - 2)){
			return true;
		}else{
			return false;
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public static int getWidth() {
		return WIDTH;
	}

	public static int getHeight() {
		return HEIGHT;
	}

	public boolean reachedMiddleOfScreen(){
		return x <= handler.getWidth() /2;
	}
	
	public boolean isOffScreen(){
		return x == 0 - WIDTH;
	}

	/*public boolean hits(Bird bird) {
		if(bird.getY() <= topHeight || bird.getY() > handler.getHeight() - bottomHeight){
			if(bird.getX() > x && bird.getX() < x + WIDTH){
				highlight = true;
				return true;
			}
		}
		highlight = false;
		return false;
	}*/
	
	
	
}
