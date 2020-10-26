package com.juniorstudio.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.juniorstudio.entities.Entity;
import com.juniorstudio.entities.Player;
import com.juniorstudio.graficos.SpriteSheet;
import com.juniorstudio.world.World;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	private Thread thread;
	public static JFrame frame;
	public boolean estaRodando;
	public static final int bits = 16;
	private final int altura = bits*13;
	private final int largura = bits*13;
	private final int escala = 3;
	private BufferedImage image;

	public List<Entity> entities;

	public static SpriteSheet spritesheet, playerSpritesheet, objetcsSpriteSheet;
	public Player player;
	public World world;

	public Game() {
		addKeyListener(this);
		setPreferredSize(new Dimension(altura * escala, largura * escala));
		iniciaFrame();
		image = new BufferedImage(altura, largura, BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		playerSpritesheet = new SpriteSheet("/playerSpriteSheet.png");
		objetcsSpriteSheet = new SpriteSheet("/objectsSpriteSheet.png");
		//MUNDO
		world = new World("/map.png");
		// Entidades
		player = new Player(6*bits,6*bits, bits, bits, playerSpritesheet.getSprite(0, 0, bits, bits));
		// Adicionando entidadades
		entities.add(player);

	}

	public synchronized void start() {
		thread = new Thread(this);
		estaRodando = true;
		thread.start();

	}

	public synchronized void stop() {
		estaRodando = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void iniciaFrame() {
		frame = new JFrame("Corona Vírus - O anti-herói");
		frame.add(this);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
	}

	public void renderiza() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = image.getGraphics();
		g.setColor(new Color(19, 19, 19));
		g.fillRect(0, 0, altura, largura);
		/* Renderizando o mundo */
		world.render(g);
		/* Renderização o jogo */
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		/***/

		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.setColor(Color.white);
		g.dispose();
		g.drawString("Meu jogo", 90, 10);
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, altura * escala, largura * escala, null);
		bs.show();

	}

	public void run() {
		long ultimoTempo = System.nanoTime();
		double fps = 60.0;
		double ns = 1000000000 / fps;
		double delta = 0;

		while (estaRodando) {
			long agora = System.nanoTime();
			delta += (agora - ultimoTempo) / ns;
			ultimoTempo = agora;
			if (delta >= 1) {
				tick();
				renderiza();
				delta--;
			}
		}

		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// Tecla apertada
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.up = (true);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.down = (true);
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = (true);
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = (true);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO tecla soltada
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.up = (false);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.down = (false);
		}

		if (e.getKeyCode() == KeyEvent.VK_D) {
			player.right = (false);
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			player.left = (false);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// Tecla digitada

	}

}
