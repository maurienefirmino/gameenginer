package com.juniorstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.juniorstudio.main.Game;

public class World {
	
	private Tile[] tiles;
	private Object[] objects;

	private int w,h;
	
	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			
			w = map.getWidth();
			h = map.getHeight();
			
			int[] pixels = new int[w*h];
			tiles = new Tile[w*h];
			objects = new Object[w*h];
			
			map.getRGB(0, 0, w,h, pixels, 0, w);
			
			for(int xx = 0; xx <w;xx++) {
				for(int yy=0;yy<h;yy++) {
					
					int pAtual = pixels[xx+(yy*w)];
					//Sempre renderiza o chão
					tiles[xx+(yy*w)] = new FloorTile(xx*Game.bits,yy*Game.bits,Tile.TILE_FLOOR);
					
					//Se for chão
					if(pAtual == 0xFFFFFFFF) {
						tiles[xx+(yy*w)] = new FloorTile(xx*Game.bits,yy*Game.bits,Tile.TILE_FLOOR);
					}else
					
					//Se for Arvore
					if( pAtual == 0xFF000000) {
						objects[xx+(yy*w)] = new TreeObject(xx*Game.bits,yy*Game.bits,Object.OBJECT_TREE);
					}else
					
					//Se for vida
					if( pAtual == 0xFFFF00DC) {
						objects[xx+(yy*w)] = new TreeObject(xx*Game.bits,yy*Game.bits,Object.OBJECT_LIFE);
					}else
					
					//Se for parede
					if( pAtual == 0xFFFF0000) {
						tiles[xx+(yy*w)] = new WallTile(xx*Game.bits,yy*Game.bits,Tile.TILE_WALL);
					}else {
						tiles[xx+(yy*w)] = new FloorTile(xx*Game.bits,yy*Game.bits,Tile.TILE_FLOOR);
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
		//Renderizando Tiles
		for(int xx=0;xx<w;xx++) {
			for(int yy=0;yy<h;yy++) {
				Tile tile = tiles[xx + (yy*w)];
				tile.render(g);
			}
		}
		
		//Renderizando Objetos
		for(int xx=0;xx<w;xx++) {
			for(int yy=0;yy<h;yy++) {
				Object obj = objects[xx + (yy*w)];
				try {
					obj.render(g);
				}catch(Exception e) {
					
				}
			}
		}
	}
}
