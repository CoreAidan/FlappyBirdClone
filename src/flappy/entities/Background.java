package flappy.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import flappy.Handler;
import flappy.gfx.Assets;

public class Background {
	
	private  int width = 0;
	private   int height = 0;
	
	private BufferedImage bg;
	private BufferedImage floor;
	
	private Handler handler;
	
	public Background(Handler handler){
		this.handler = handler;
		bg = Assets.background;
		floor = Assets.floor;
		
		width = handler.getWidth();
		height = handler.getHeight();
	}
	
	public void update(){
		
	}
	
	public void render(Graphics g){
		g.drawImage(bg, 0, 0, width, height, null);
		g.drawImage(floor, 0, height - 20,width, 100, null);
	}
}
