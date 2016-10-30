package entities;

import game.Game;
import game.Log;
import game.Pong;
import gameStates.StateManager;
import input.Keyboard;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import components.IControllable;
import components.IDrawable;
import components.IMoveable;
import components.IUpdatable;

public class Player extends GameObject implements IControllable, IMoveable {
	
	private int speed;
	private Keyboard keyboard;
	private boolean[] moves;
	private int dragEffect;
	
	public Player() {
		setY((Pong.getGameHeight() / 2) - 60);
		setX((Pong.getGameWidth() - 60));
		speed = 0;
		body = new Rectangle(getX(), getY(), 30, 120);
		moves = new boolean[2]; // [0] up; [1] down;
		dragEffect = 10;
	}

	@Override
	public void processIOEvents() {
		processKeyboard();
		if(!(moves[0] == true && moves[1] == true) && !(moves[0] == false && moves[1] == false)){
			if(moves[0] == true){
				speed = -300;
			}else if(moves[1] == true){
				speed = +300;
			}
		}else{
			if(speed < 0){
				speed += dragEffect;
			}else if(speed > 0){
				speed -= dragEffect;
			}
		}		
	}

	@Override
	public void processKeyboard() {
		keyboard = Game.getKeyboard();
		LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
		
		for (KeyEvent event : keyEvents) {
			switch (event.getID()) {
			case KeyEvent.KEY_PRESSED:
					switch (event.getKeyCode()) {
					case KeyEvent.VK_UP:
						moves[0] = true;
						break;
					case KeyEvent.VK_DOWN:
						moves[1] = true;
						break;
					default:
						break;
					}
				break;
			case KeyEvent.KEY_RELEASED:
				switch (event.getKeyCode()) {
				case KeyEvent.VK_UP:
					moves[0] = false;
					break;
				case KeyEvent.VK_DOWN:
					moves[1] = false;
					break;
				default:
					break;
				}
				break;
			case KeyEvent.KEY_TYPED:
				
				break;
			default:
				break;
			}
		}		
	}

	@Override
	public void processMouse() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMouseWheel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(double delta) {
		y += (int)(speed * delta);
		y = (int) Game.clamp(y, 0, (int)Pong.getGameHeight() - body.height);
		body.setLocation(x, y);		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fill(body);
		g.draw(body);
		
	}

	@Override
	public void update(double delta) {
		processIOEvents();
		move(delta);
	}

}
