package com.juniorstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.juniorstudio.main.Game;

public class Tile {
	public static BufferedImage TILE_FLOOR = Game.objetcsSpriteSheet.getSprite(0, 0, 16, 16);
	public static BufferedImage TILE_WALL = Game.objetcsSpriteSheet.getSprite(16, 0, 16, 16);
	
	private BufferedImage sprite;
	private int x,y;
	
	public boolean colision;
	
	public Tile(int x,int y, BufferedImage sprite,boolean colision) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.colision = colision;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,x - Camera.x,y - Camera.y,null);
	}
}
