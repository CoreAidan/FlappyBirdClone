package flappy.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	private boolean stopped;
	private boolean playedOnce;
	
	public Animation(int speed, BufferedImage[] frames){
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		this.playedOnce = false;
		this.stopped = true;
	}
	
	public void start(){
		 if (!stopped) {
			 return;
	     }

	     if (frames.length == 0) {
	    	 return;
	     }
	     stopped = false;
	}
	
	public void stop(){
		if(frames.length == 0){
			return;
		}
		
		stopped = true;
	}
	
	public void reset(){
		//this.stopped = true;
		this.index = 0;
		this.playedOnce = false;
	}
	
	public void update(){
		if(!stopped) {
			timer += System.currentTimeMillis() - lastTime;
			lastTime = System.currentTimeMillis();
			
			if(timer > speed){
				index++;
				timer = 0;
				if(index >= frames.length){
					index = 0;
					playedOnce = true;
				}
			}
		}
	}
	
	public void setAnimation(Animation animation){
		this.frames = animation.frames;
		this.speed = animation.speed;
		playedOnce = false;
	}
	
	public boolean hasPlayedOnce(){
		return playedOnce;
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
}
