package com.juniorstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World {
	
	private Tile[] tiles; 
	private int w,h;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			w = map.getWidth();
			h = map.getHeight();
			
			int[] pixels = new int[w*h];
			tiles = new Tile[w*h];
			
			map.getRGB(0, 0, w,h, pixels, 0, w);
			
			for(int xx = 0; xx <w;xx++) {
				for(int yy=0;yy<h;yy++) {
					
					int pAtual = pixels[xx+(yy*w)];
					
					
					//Se for chão
					if(pAtual == 0xFFFFFFFF) {
						tiles[xx+(yy*w)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else
					
					//Se for Arvore
					if( pAtual == 0xFF000000) {
						tiles[xx+(yy*w)] = new TreeTile(xx*16,yy*16,Tile.TILE_TREE);
					}else
					
					//Se for vida
					if( pAtual == 0xFFFF00DC) {
						tiles[xx+(yy*w)] = new LifeTile(xx*16,yy*16,Tile.TILE_LIFE);
					}else
					
					//Se for parede
					if( pAtual == 0xFFFF0000) {
						tiles[xx+(yy*w)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL);
					}else {
						tiles[xx+(yy*w)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int xx=0;xx<w;xx++) {
			for(int yy=0;yy<h;yy++) {
				Tile tile = tiles[xx + (yy*w)];
				tile.render(g);
			}
		}
	}
}
