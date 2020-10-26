package com.juniorstudio.entities;

import java.awt.image.BufferedImage;

import com.juniorstudio.main.Game;


public class Player extends Entity{
	
	public boolean up,down,right,left;
	public double speed = 1;
	public int changeSprite = 1, countChangeSprite = 1, speedChangeSprite = 10; 

	
	public Player(int x,int y, int w, int h, BufferedImage sprite){
		super(x,y,w,h,sprite);
	}	
	
	public void tick() {
		//Mudando animação
		this.changeSprite();
		
		if(up) {
			y-=speed;
		}else if(down) {
			y+=speed;
		}
		
		if(left) {
			x-=speed;
		}else if(right) {
			x+=speed;
		}
	}
	
	public void changeSprite() {
		
		if(countChangeSprite < speedChangeSprite) {
			countChangeSprite++;
			return;
		}
		
		
		if(changeSprite == 1) {
			super.sprite = Game.playerSpritesheet.getSprite(16, 0, 16, 16);
			changeSprite++;
		}else if(changeSprite == 2) {
			super.sprite = Game.playerSpritesheet.getSprite(32, 0, 16, 16);
			changeSprite++;
		}else if(changeSprite == 3) {
			super.sprite = Game.playerSpritesheet.getSprite(48, 0, 16, 16);
			changeSprite++;
		}else if(changeSprite == 4) {
			super.sprite = Game.playerSpritesheet.getSprite(0, 0, 16, 16);
			changeSprite = 1;
		}
		
		countChangeSprite = 1;
		
		
	}
}
