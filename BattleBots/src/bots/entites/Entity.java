package bots.entites;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import bots.board.Board;
import bots.board.Tile;

public abstract class Entity extends Rectangle{
	
	protected double health, x, y, width, height;

	protected Tile tile;
	protected Sprite sprite;
	protected boolean moved;
	protected int points = 0;

	public Entity(double health, double x, double y, double width, double height, String filePath, boolean moved) {
		this.health = health;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.moved = moved;
		points = 0;
		sprite = new Sprite(filePath,Tile.SIZE, Tile.SIZE);
	}
	
	public boolean collides(Tile t) {
		if(t.x == this.x && t.y == this.y) return true;
		return false;
	}
	
	public void move(int n) {
		if(n == 0) {//UP
			if(this.y/Tile.SIZE - 1 >= 0) this.y -= Tile.SIZE;
		}else if(n == 1) {//Down
			System.out.println();
			if(this.y/Tile.SIZE + 1 < Board.HEIGHT) this.y += Tile.SIZE;
		}else if(n == 2) {//Left
			if(this.x/Tile.SIZE - 1 >= 0) this.x -= Tile.SIZE;
		}else if(n == 3) {//Right
			if(this.x/Tile.SIZE + 1 < Board.WIDTH) this.x += Tile.SIZE;
		}
		
	}
	
	public void damaged(int dp) {
		if(health - dp <= 0) {
			System.out.println("Game Over");
			System.exit(0);
		}
		health -= dp;
	}
	
	public void healed(int hp) {
		if(health + hp <= 100) health += hp;
		else health = 100;
	}
	
	public void losePoints(int lp) {
		if(points - lp <= 0) points = 0;
		else points -= lp;
	}
	
	public void incrPoints(int gp) {
		points += gp;
	}
	
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		sprite.paintComponent(g, (int) this.x, (int) this.y);
	}
	
	public boolean hasMoved() {
		return moved;
	}
	
	public void setMoved(boolean b) {
		moved = b;
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	
	public double getHealth() {
		return health;
	}

	public int getPoints() {
		return points;
	}
	
}
