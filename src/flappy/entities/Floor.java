package flappy.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import flappy.Handler;
import flappy.gfx.Assets;

public class Floor {
	
	private static int SPEED = 2;
	
	private int width = 0;
	private int height = 0;
	
	private int x;
	private int y;
	
	private BufferedImage floor;
	private Handler handler;
	
	public Floor(Handler handler){
		
		this.handler = handler;
		
		floor = Assets.floor;
		
		width = handler.getWidth() * 2;
		height = 250;
		x = 0;
		y = handler.getHeight() - 50;
		
	}
	
	public boolean isOffScreen(){
		return x == 0 - width;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getX(){
		return x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	
	public void render(Graphics g){
		g.drawImage(floor, x, y,width, height, null);
	}
	
	public void update(){
		x -= SPEED;
	}

}
