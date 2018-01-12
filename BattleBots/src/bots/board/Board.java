package bots.board;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import bots.entites.Entity;
import bots.entites.Player;
import bots.items.Item;
import bots.items.ItemType;


public class Board extends JPanel{
	
	private Tile[][] tiles;

	public static int WIDTH,HEIGHT;

	private Main main;
	
	private boolean endGame;
	
	private Player player;
	private Enemy enemy;
	
	private ArrayList<Item> items;
	
	public Board(int WIDTH, int HEIGHT, Main main){
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;
		endGame = false;
		this.main = main;
		tiles = new Tile[WIDTH][HEIGHT];
		createTiles();
		player = new Player(100,0,0,Tile.SIZE,Tile.SIZE);
		enemy = new Enemy(100,WIDTH*Tile.SIZE - Tile.SIZE,0,Tile.SIZE,Tile.SIZE);
		items = new ArrayList<Item>();
		genItems();
	}
	
	public void genItems() {
		Random rand = new Random();
		for(int i = 0; i < WIDTH; i++) {
			int randX = rand.nextInt(6) + 2;
			int randY = rand.nextInt(6) + 2;
			items.add(new Item(randX,randY));
 		}
	}
	
	public void createTiles(){
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				Color c;
				if((i + 1) % 2 != 0){
					c = Color.BLACK;
					if((j + 1) % 2 == 0)
						c = Color.WHITE;
				}else{
					c = Color.WHITE;
					if((j + 1) % 2 == 0)
						c = Color.BLACK;
				}
				tiles[i][j] = new Tile(j,i,c);
			}
		}
	}
	
	public void interact() {
		for(int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			if(item.collides(player)) {
				pickUp(item.getItemType(),enemy,player);
				items.remove(i);
			}else if(item.collides(enemy)) {
				pickUp(item.getItemType(),player,enemy);
				items.remove(i);
			}
		}
		
	}
		
	public void pickUp(ItemType type, Entity opp, Entity current) {
		switch(type) {
		case DAMAGE: opp.damaged(12);break;
		case HEALTH: current.healed(12);break;
		case G_POINTS: current.incrPoints(12);break;
		case L_POINTS: opp.losePoints(12);break;
		}
	}
	
	public void update() {
		player.move();
		enemy.move();
				
		//Turn Based Moves
		if(player.move()) {
			enemy.setMoved(false);
		}
		if(enemy.move()) {
			player.setMoved(false);
		}
		
		//Generate items if no items left
		if(items.size() == 0)genItems();
		
		//If Bots on the Same Tile End Game
		if(player.getX() == enemy.getX() && player.getY() == enemy.getY()) {
			System.out.println("Game Over! Its a Draw!");
			System.exit(0);
		}
		
		interact();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GRAY);
		int width = (int) main.getSize().getWidth(), height = (int) main.getSize().getHeight();
		g.fillRect(0, 0, width, height);
		for(Tile [] i : tiles){
			for(Tile t : i){
				g.setColor(t.getColor());
				g.fillRect((int) (t.getX()), (int)(t.getY()), Tile.SIZE, Tile.SIZE);
			}
		}
		for(Item i : items) {
			i.drawItem(g);
		}
		player.render(g);
		enemy.render(g);
		
		//Draw Scores and Points
		g.setColor(Color.blue);
		g.setFont(new Font("serif", Font.BOLD, 20));
		//Player
		g.drawString("Player Health: " + (int) player.getHealth(), 0, height - 100);
		g.drawString("Player Score: " + player.getPoints(), 0, height - 50);
		//Enemy
		g.setColor(Color.YELLOW);
		g.drawString("Enemy Health: " + Integer.toString((int) enemy.getHealth()), width - 300, height - 100);
		g.drawString("Enemy Score: " + enemy.getPoints(), width - 300, height - 50);
		
	}
	
}
