package com.juniorstudio.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.juniorstudio.main.Game;

public class World {

	private static Tile[] tiles;
	private static Object[] objects;

	public static int w;
	public static int h;

	public World(String path) {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));

			w = map.getWidth();
			h = map.getHeight();

			int[] pixels = new int[w * h];
			tiles = new Tile[w * h];
			objects = new Object[w * h];

			map.getRGB(0, 0, w, h, pixels, 0, w);

			for (int xx = 0; xx < w; xx++) {
				for (int yy = 0; yy < h; yy++) {

					int pAtual = pixels[xx + (yy * w)];
					// Sempre renderiza o ch�o
					tiles[xx + (yy * w)] = new FloorTile(xx * Game.bits, yy * Game.bits, Tile.TILE_FLOOR, true);

					// Se for ch�o
					if (pAtual == 0xFFFFFFFF) {
						tiles[xx + (yy * w)] = new FloorTile(xx * Game.bits, yy * Game.bits, Tile.TILE_FLOOR, true);
					} else

					// Se for Arvore
					if (pAtual == 0xFF000000) {
						objects[xx + (yy * w)] = new TreeObject(xx * Game.bits, yy * Game.bits, Object.OBJECT_TREE);
					} else

					// Se for vida
					if (pAtual == 0xFFFF00DC) {
						objects[xx + (yy * w)] = new TreeObject(xx * Game.bits, yy * Game.bits, Object.OBJECT_LIFE);
					} else

					// Se for parede
					if (pAtual == 0xFFFF0000) {
						tiles[xx + (yy * w)] = new WallTile(xx * Game.bits, yy * Game.bits, Tile.TILE_WALL, false);
					} else {
						tiles[xx + (yy * w)] = new FloorTile(xx * Game.bits, yy * Game.bits, Tile.TILE_FLOOR, true);
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void tick() {

	}

	public static boolean isFree(double d, double y) {
		d = d / Game.bits;
		y = y / Game.bits;
		
		try {
			Tile tile = tiles[(int)d + ((int)y * w)];
			return tile.colision;
		} catch (Exception e) {
			return true;
		}

	}

	public void render(Graphics g) {

		int xstart = Camera.x / Game.bits;
		int ystart = Camera.y / Game.bits;
		int xfinal = xstart + Game.WIDTH + Game.bits;
		int yfinal = ystart + Game.HEIGHT + Game.bits;

		// Renderizando Tiles
		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				try {
					Tile tile = tiles[xx + (yy * w)];
					tile.render(g);
				} catch (Exception e) {
					// N�o renderiza nada :)
				}
			}
		}

		// Renderizando Objetos
		for (int xx = xstart; xx < xfinal; xx++) {
			for (int yy = ystart; yy < yfinal; yy++) {
				try {
					Object obj = objects[xx + (yy * w)];
					obj.render(g);
				} catch (Exception e) {
					// N�o renderiza nada :)
				}
			}
		}
	}
}
