package flappy.entities;

import java.awt.Color;
import java.awt.Graphics;

import flappy.Handler;
import flappy.gfx.Animation;
import flappy.gfx.Assets;

public class Bird {
	
	private static final int WIDTH = 64;
	private static final int HEIGHT = 64;
	
	//private static final float SPEED = 3.0f;
	//private static final int SCALE = 2;
	
	private static final float GRAVITY = 1.1f;
	private static final float RESISTANCE = 0.9f;
	private static final int LIFT = -5;
	private float velocity = 0;
	
	private Animation up;
	private Animation fall;
	
	private Animation currentAnimation;
	
	private Handler handler;
	
	private float y;
	private float x = 50;
	
	public Bird(Handler handler){
		this.handler = handler;
		y = handler.getHeight() / 2;
		
		up = new Animation(100, Assets.birdUp);
		fall = new Animation(100, Assets.birdFall);
		
		currentAnimation = fall;
	}
	
	public void up(){
		if(handler.getKeyboard().up){
			currentAnimation = up;
			currentAnimation.start();
			velocity += LIFT;
		}else{
			currentAnimation = fall;
		}
		
	}
	
	public void update(){
		velocity += GRAVITY;
		velocity *= RESISTANCE;
		y += velocity;
		
		up();
		
		if(y > handler.getHeight() - HEIGHT){
			y = handler.getHeight() - HEIGHT -1;
			this.velocity = 0;
		}
		
		if(y < 0){
			y = 0;
			this.velocity = 0;
		}
		
		currentAnimation.update();
	}
	
	public void render(Graphics g){
		//g.setColor(Color.BLACK);
		//g.fillOval((int)x, (int)y, WIDTH, HEIGHT);
		g.drawImage(currentAnimation.getCurrentFrame(), (int) x,(int) y, WIDTH, HEIGHT, null);
	}
	
	public float getY(){
		return y;
	}
	
	public float getX(){
		return x;
	}
}
