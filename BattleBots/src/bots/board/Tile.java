package bots.board;

import java.awt.Color;
import java.awt.Rectangle;


public class Tile extends Rectangle{
	
	private Color color, origColor;

	public static final int SIZE = 64;
	private boolean selected;
	
	public Tile(int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.color = color;
		this.origColor = color;
		this.setBounds(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
	}
	
	public Color getColor() {
		return color;
	}
	

}
