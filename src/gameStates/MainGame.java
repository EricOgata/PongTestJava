package gameStates;

import game.Game;
import game.Log;
import game.Pong;
import game.Sound;
import input.Keyboard;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.border.StrokeBorder;

import components.IMoveable;
import entities.Ball;
import entities.Enemy;
import entities.GameObject;
import entities.Player;
import entities.ScoreManager;

public class MainGame implements State{
	
	// Game Objects
	protected GameObject player;
	private GameObject enemy;
	private GameObject ball;
	protected GameObject scoreManager;
	
	private Sound hitPaddle;
	
	private Keyboard keyboard;
	
	private Rectangle midLine;
	
	@Override
	public void draw(Graphics2D g) {
		drawMidLine(g);
		player.draw(g);
		enemy.draw(g);
		ball.draw(g);
		scoreManager.draw(g);
	}

	@Override
	public void update(double delta) {
		processIOEvents();
		player.update(delta);
		boolean value = (ball.getBody().getCenterX() <= midLine.getCenterX()?true:false);
		((Enemy)enemy).setMoveAI(value);
		((Enemy)enemy).setTargetY((float)ball.getBody().getCenterY());
		enemy.update(delta);
		ball.update(delta);
		checkBallPlayerCollision();
		verificaPontos();
	}

	private void verificaPontos() {
		switch(((Ball)ball).checkWallCollision()){
		case 1:
			((ScoreManager)scoreManager).addPointEnemy();
			ball = new Ball();
			break;
		case 2:
			((ScoreManager)scoreManager).addPointPlayer();
			ball = new Ball();
			break;
		default:
			break;
		}
		
	}

	@Override
	public void processIOEvents() {
		processKeyboard();		
	}

	@Override
	public void processKeyboard() {
		LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
		for(KeyEvent event : keyEvents){
			if( (event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE) ){
				Log.info(getClass().getSimpleName(), "Esc Apertado");
				StateManager.changeCurrentState(Pong.mainMenu);
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
	public void redraw() {
		
	}

	@Override
	public void load() {
		keyboard = Game.getKeyboard();
		player = new Player();
		enemy = new Enemy();
		ball = new Ball();
		scoreManager = new ScoreManager();
		
		try {
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/arcade-normal.ttf"));
			((ScoreManager)scoreManager).setFont(dynamicFont.deriveFont(20f));
		} catch (FileNotFoundException e) {
			((ScoreManager)scoreManager).setFont(new Font("arial", 1, 25));
			e.printStackTrace();
		} catch (FontFormatException e) {
			((ScoreManager)scoreManager).setFont(new Font("arial", 1, 25));
			e.printStackTrace();
		} catch (IOException e) {
			((ScoreManager)scoreManager).setFont(new Font("arial", 1, 25));
			e.printStackTrace();
		}
		
		hitPaddle = Sound.hitPaddle;
		midLine = new Rectangle(10, Pong.getGameHeight());
		midLine.setLocation(Pong.getGameWidth()/2 - 5, 0);
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}
	
	public void checkBallPlayerCollision(){
		Rectangle ballBody = ball.getBody();
		Rectangle playerBody = player.getBody();
		Rectangle enemyBody = enemy.getBody();
		
		if(ballBody.getBounds().intersects(playerBody)){
			((Ball) ball).checkPlayerCollision(playerBody.getCenterY(), ballBody.getCenterY());
			hitPaddle.play();
		}
		
		if(ballBody.getBounds().intersects(enemyBody)){
			((Ball) ball).checkPlayerCollision(enemyBody.getCenterY(), ballBody.getCenterY());
			hitPaddle.play();
		}
		
	}
	
	public void drawMidLine(Graphics2D g){
		final Graphics2D g2 = (Graphics2D)g.create();
		try{
			g2.setColor(Color.WHITE);
			Stroke dashed = new BasicStroke(3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL, 3F, new float[]{9}, 0);
			g2.setStroke(dashed);
			g2.drawLine(Pong.getGameWidth()/2, 0, Pong.getGameWidth()/2, Pong.getGameHeight());
		}finally{
			g2.dispose();
		}		
	}
}
