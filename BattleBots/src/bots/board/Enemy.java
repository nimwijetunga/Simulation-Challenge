package bots.board;

import java.util.Random;

import bots.entites.Entity;

public class Enemy extends Entity{

	public Enemy(double health, double x, double y, double width, double height) {
		super(health, x, y, width, height,"res/enemy.png",true);
	}
	
	public boolean move() {
		Random rn = new Random();
		int rand =  rn.nextInt(4);
		if(moved) return true;
		if(rand >= 0 && rand <= 3 && !moved) {
			super.move(rand);
			moved = true;
			return true;
		}
		return false;
	}

}
