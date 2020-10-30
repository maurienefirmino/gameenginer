package com.juniorstudio.entities;

import java.awt.image.BufferedImage;

import com.juniorstudio.main.Game;
import com.juniorstudio.world.Camera;
import com.juniorstudio.world.World;


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
			if(World.isFree(x,y-speed)) {
				y-=speed;
				Camera.y-=speed;
			}
			
		}else if(down) {
			if(World.isFree(x,y+speed+Game.bits)) {
				y+=speed;
				Camera.y+=speed;
			}
		}
		
		if(left) {
			if(World.isFree(x-speed,y)) {
				if(World.isFree(x-speed,y+13)) {
					x-=speed;
					Camera.x-=speed;
				}
			}
			
		}else if(right) {
			if(World.isFree(x+speed+Game.bits,y)) {
				if(World.isFree(x+speed+Game.bits,y+13)) {
					x+=speed;
					Camera.x+=speed;
				}
			}
			
		}
		
		
//		Movimentando Câmera
//		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2) + (6*Game.bits), 0, (World.w*Game.bits) - Game.WIDTH);
//		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2) + (6*Game.bits), 0, (World.h*Game.bits)- Game.HEIGHT);
	}
	
	public void changeSprite() {
		
		if(countChangeSprite < speedChangeSprite) {
			countChangeSprite++;
			return;
		}
		
		
		if(changeSprite == 1) {
			super.sprite = Game.playerSpritesheet.getSprite(Game.bits, 0, Game.bits, Game.bits);
			changeSprite++;
		}else if(changeSprite == 2) {
			super.sprite = Game.playerSpritesheet.getSprite(Game.bits*2, 0, Game.bits, Game.bits);
			changeSprite++;
		}else if(changeSprite == 3) {
			super.sprite = Game.playerSpritesheet.getSprite(Game.bits*3, 0, Game.bits, Game.bits);
			changeSprite++;
		}else if(changeSprite == 4) {
			super.sprite = Game.playerSpritesheet.getSprite(0, 0, Game.bits, Game.bits);
			changeSprite = 1;
		}
		
		countChangeSprite = 1;
		
		
	}
}
