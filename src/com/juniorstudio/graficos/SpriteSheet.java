package com.juniorstudio.graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public BufferedImage spritesheet;
		
	public SpriteSheet(String path) {
		try {
			spritesheet = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite(int x, int y,int w, int h) {
		return spritesheet.getSubimage(x, y, w, h);
	}
}
