package bots.items;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import bots.board.Tile;
import bots.entites.Entity;
import bots.entites.Sprite;

public class Item {
	
	private ItemType type;
	private int tx,ty;
	private Sprite sprite;
	
	public Item(int tx, int ty) {
		this.tx = tx;
		this.ty = ty;
		type = getType();
		sprite = getSprite();
	}
	
	public ItemType getType() {
		Random rand = new Random();
		int n =  rand.nextInt(4);
		switch(n) {
		case 0:
			return ItemType.DAMAGE;
		case 1:
			return ItemType.HEALTH;
		case 2:
			return ItemType.G_POINTS;
		case 3:
			return ItemType.L_POINTS;
		}
		return null;
	}
	
	public boolean collides(Entity e) {
		if(tx == (e.getX()/Tile.SIZE) && ty == (e.getY()/Tile.SIZE)) return true;
		return false;
	}
	
	public Sprite getSprite() {
		String s = "";
		switch(type) {
		case DAMAGE: s = "skull.png";break;
		case HEALTH: s = "heart.png";break;
		case G_POINTS: s = "blue.png";break;
		case L_POINTS: s = "red.png";break;
		}
		String filePath = "res/"+ s;
		return new Sprite(filePath, Tile.SIZE, Tile.SIZE);
	}
	
	public void drawItem(Graphics g) {
		g.setColor(Color.BLACK);
		sprite.paintComponent(g, tx * Tile.SIZE, ty * Tile.SIZE);
	}
	
	public ItemType getItemType() {
		return type;
	}

}
