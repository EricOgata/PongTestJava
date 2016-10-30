package entities;

import game.Game;
import game.Log;
import game.Pong;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import components.IMoveable;
import components.IPlayerCollision;
import components.IWallCollision;

public class Ball extends GameObject implements IMoveable, IWallCollision, IPlayerCollision{
	
	private float speedX;
	private int speedY;
	private float maxSpeedX;
	
	public Ball() {
		body = new Rectangle(10, 10);
		x = Pong.getGameWidth()/2 - 5;
		y = Pong.getGameHeight()/2 - 5;		
		body.setLocation(x, y);
		speedX = 400;
		maxSpeedX = 1500;
		speedY = (Game.showRandomInteger(70, 140));
		if(Game.showRandomInteger(-1, 0) < 0){
			speedY *= -1;
		}
	
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fill(body);
		g.draw(body);
	}

	public float getSpeedX() {
		return speedX;
	}

	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
	
	public void invertSpeedX(){
		this.speedX *= -1;
		speedUpBall();
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	@Override
	public void update(double delta) {		
		move(delta);
		checkWallCollision();
	}

	@Override
	public void move(double delta) {
		y += (speedY * delta);
		x += (speedX * delta);
		body.setLocation(x, y);	
	}

	@Override
	public void checkWallCollision() {
		if(body.getMaxX() + 1 >= Pong.getGameWidth() || body.getMinX() - 1 <= 0){
			invertSpeedX();
		}
		
		if(body.getMaxY() + 1 >= Pong.getGameHeight() || body.getMinY() - 1 <= 0){
			speedY *= -1;
		}		
	}
	
	public void speedUpBall(){
		if(speedX>-(maxSpeedX) && speedX<maxSpeedX){ // Max Velocidade
			if(speedX < 0){
				speedX -= 20;
			}else if(speedX > 0){
				speedX += 20;
			}
		}
	}

	@Override
	public void checkPlayerCollision(double playerCenterY, double ballCenterY) {
		if(playerCenterY >= ballCenterY){
			speedY = -140;
		}else{
			speedY = 140;
		}
		invertSpeedX();		
	}
	
}
