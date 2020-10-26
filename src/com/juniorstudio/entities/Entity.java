package com.juniorstudio.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {
	protected int x,y,w,h;
	protected BufferedImage sprite; 
	
	public Entity(int x,int y, int w, int h, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.sprite = sprite;
	}

	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getW() {
		return this.w;
	}
	
	public int getH() {
		return this.h;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,this.getX(),this.getY(),null);
	}
	
	public void tick() {
		
	}

}
