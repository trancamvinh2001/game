package Entity.Objects;

import Entity.*;
import Entity.Object;
import TileMap.TileMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class FireObject extends Object{

private BufferedImage[] sprites;
	
	public FireObject(TileMap tm) {
		
		super(tm);
		
		fire = true;

		width = 15;
		height = 15;
		cwidth = 10;
		cheight = 10;
		
		// load sprites
		try {
			
			BufferedImage spritesheet = ImageIO.read(
				getClass().getResourceAsStream(
					"/Sprites/Objects/fireobject.gif"
				)
			);
			
			sprites = new BufferedImage[3];
			for(int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(
					i * width,
					0,
					width,
					height
				);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);
		
		right = true;
		facingRight = true;
		
	}
	
	private void getNextPosition() {

		if(y >=tileMap.getHeight()- height){
			y = tileMap.getHeight()-height;
			falling = false;
		}
		// falling
		if(falling) {
			dy += fallSpeed;
		}
	}
	
	public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// update animation
		animation.update();
		
	}
	
	public void draw(Graphics2D g) {

		setMapPosition();
		super.draw(g);
		
	}
	
}
