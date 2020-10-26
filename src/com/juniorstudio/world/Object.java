package com.juniorstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.juniorstudio.main.Game;

public class Object {
	public static BufferedImage OBJECT_TREE = Game.objetcsSpriteSheet.getSprite(32, 0, 16, 16);
	public static BufferedImage OBJECT_LIFE = Game.objetcsSpriteSheet.getSprite(48, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Object(int x,int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,x - Camera.x,y - Camera.y,null);
	}
}
