package flappy.gfx;

import java.awt.image.BufferedImage;

import flappy.utils.Utils;

public class Assets {
	
	public static BufferedImage[] birdUp;
	public static BufferedImage[] birdFall;
	public static BufferedImage pipeUp, pipeDown;
	public static BufferedImage floor, background;
	
	public static BufferedImage gameLogo;
	public static BufferedImage startButton;
	public static BufferedImage getReadyLogo;
	public static BufferedImage tapIcon;
	public static BufferedImage gameOverLogo;
	
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
		
		getReadyLogo = sheet.crop(145, 220, 89, 24);
		startButton = sheet.crop(242, 213, 40, 14);
		tapIcon = sheet.crop(151, 121, 60, 50);
		gameLogo = sheet.crop(145, 173, 99, 22);
		gameOverLogo = sheet.crop(146, 199, 94, 19);
		
		//birdUp = sheet.crop(x, y, width, height);
	}
	
}
