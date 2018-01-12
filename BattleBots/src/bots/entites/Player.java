package bots.entites;

import java.awt.Graphics;

import bots.handlers.KeyHandler;

public class Player extends Entity{
	
	private boolean up,down,left,right;
	

	public Player(double health, double x, double y, double width, double height) {
		super(health, x, y, width, height, "res/bot.png",false);
	}
	
	public boolean move() {
		up = KeyHandler.up;
		down = KeyHandler.down;
		left = KeyHandler.left;
		right = KeyHandler.right;
		if(moved) return true;
		if((up || down || left || right) && !moved) {
			if(up) KeyHandler.up = false;
			else if(down) KeyHandler.down = false;
			else if(right) KeyHandler.right = false;
			else KeyHandler.left = false;
			int n;
			if(up) n = 0;
			else if(down) n = 1;
			else if(left) n = 2;
			else n =3;
			super.move(n);
			moved = true;
			return true;
		}
		return false;
	}


}
