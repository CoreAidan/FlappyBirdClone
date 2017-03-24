package flappy.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage[] birdUp;
	//private static BufferedImage[] birdDown;
	public static BufferedImage[] birdFall;
	
	public static BufferedImage pipeUp, pipeDown;
	
	public static BufferedImage floor, background;
	
	public static void init(){
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/sprites.png"));
		
		birdUp = new BufferedImage[2];
		birdFall = new BufferedImage[1];
		
		//pipeUP
		
		birdUp[0] = sheet.crop(262, 60, 21, 20);
		birdUp[1] = sheet.crop(262, 86, 21, 20);
		
		birdFall[0] = sheet.crop(221, 120, 21, 20);
		
		pipeUp = sheet.crop(301, 0, 28, 135);
		pipeDown = sheet.crop(330, 0, 28, 135);
		
		background = sheet.crop(0, 0, 144, 256);
		floor = sheet.crop(146, 0, 83, 55);
		
		//birdUp = sheet.crop(x, y, width, height);
	}
	
}
