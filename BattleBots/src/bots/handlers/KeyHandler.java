package bots.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import bots.board.Main;


public class KeyHandler implements KeyListener{
	
	public static boolean up,down,left,right;
	private Main main;
	
	public KeyHandler(Main main){
		this.main = main;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) up = true;
		else if(key ==  KeyEvent.VK_S) down = true;
		else if(key ==  KeyEvent.VK_A) left = true;
		else if(key ==  KeyEvent.VK_D) right = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_W) up = false;
		else if(key ==  KeyEvent.VK_S) down = false;
		else if(key ==  KeyEvent.VK_A) left = false;
		else if(key ==  KeyEvent.VK_D) right = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
