package entities;

import game.Game;
import game.Log;
import game.Pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import components.IMoveable;

public class Enemy extends GameObject implements IMoveable {
	
	private int speed;
	private float targetY;
	private boolean moveAI;
	
	public Enemy(){
		setY((Pong.getGameHeight() / 2) - 60);
		setX((60));
		moveAI = false;
		targetY = getY();
		speed = 0;
		body = new Rectangle(getX(), getY(), 30, 120);
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fill(body);
		g.draw(body);		
	}

	@Override
	public void update(double delta) {
		ai();
		move(delta);		
	}

	@Override
	public void move(double delta) {
		y += (int)(speed * delta);
		y = (int) Game.clamp(y, 0, (int)Pong.getGameHeight() - body.height);
		body.setLocation(x, y);			
	}

	public void ai() {
		if(moveAI == true){
			if(body.getCenterY() != targetY){
				speed = ((float)(body.getCenterY() - targetY) > 0)?-300:300;
			}else{
				speed = 0;
			}
		}else{
			speed = 0;
		}
	}
	
	public void setTargetY(float y){
		this.targetY = y;
	}
	
	public void setMoveAI(boolean value){
		this.moveAI = value;
	}
}
