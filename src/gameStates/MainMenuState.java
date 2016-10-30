package gameStates;

import game.Game;
import game.Log;
import game.Pong;
import input.Keyboard;
import input.Mouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MainMenuState implements State{
	
	private Font fontTitle;
	private Font fontMenu;
	private Keyboard keyboard;
	private Mouse mouse;
	
	
	public MainMenuState() {
		
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setFont(fontTitle);
		g.setColor(Color.WHITE);
		FontMetrics metrics = g.getFontMetrics(fontTitle);
		int x = (Pong.getGameWidth() - metrics.stringWidth("PONG"))/2;
		int y = ( (Pong.getGameHeight() - metrics.getHeight()) / 2 ) + metrics.getAscent();
		
		g.drawString("PONG", x, y);
		
		g.setFont(fontMenu);
		metrics = g.getFontMetrics(fontMenu);
		
		Rectangle boxNewGame = new Rectangle(Pong.getGameWidth() / 2 - 190, Pong.getGameHeight() / 2 + 120, 180, 50 );
		x = (boxNewGame.width - metrics.stringWidth("New Game")) / 2;
		y = ((boxNewGame.height - metrics.getHeight()) / 2 ) + metrics.getAscent();
		g.draw(boxNewGame);
		g.drawString("New Game",boxNewGame.x + x, boxNewGame.y + y);
		
		Rectangle boxSair = new Rectangle(Pong.getGameWidth() / 2 + 10, Pong.getGameHeight() / 2 + 120, 180, 50 );
		x = (boxSair.width - metrics.stringWidth("Sair")) / 2;
		y = ((boxSair.height - metrics.getHeight()) / 2 ) + metrics.getAscent();
		g.draw(boxSair);
		g.drawString("Sair",boxSair.x + x, boxSair.y + y);
	}

	@Override
	public void update(double delta) {
		processIOEvents();		
	}

	@Override
	public void redraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		
		keyboard = Game.getKeyboard();
		mouse = Game.getMouse();
		
		try {
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/arcade-normal.ttf"));
			fontTitle = dynamicFont.deriveFont(150f);
			fontMenu = dynamicFont.deriveFont(20f);
		} catch (FileNotFoundException e) {
			fontTitle = new Font("arial", 1, 25);
			e.printStackTrace();
		} catch (FontFormatException e) {
			fontTitle = new Font("arial", 1, 25);
			e.printStackTrace();
		} catch (IOException e) {
			fontTitle = new Font("arial", 1, 25);
			e.printStackTrace();
		}		
	}

	@Override
	public void unload() {
		
	}
	
	public void gameShutdown() {
		Log.info(getClass().getSimpleName(), "Shutting down game.");
		System.exit(0);		
	}

	@Override
	public void processIOEvents() {
		processKeyboard();
		processMouse();
	}

	@Override
	public void processKeyboard() {
		LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
		for(KeyEvent event : keyEvents){
			if( (event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_ESCAPE) ){
				Log.info(getClass().getSimpleName(), "Esc Apertado");
				gameShutdown();
			}
			if( (event.getID() == KeyEvent.KEY_PRESSED) && (event.getKeyCode() == KeyEvent.VK_LEFT) ){
				Log.info(getClass().getSimpleName(), "Esc Apertado");
				StateManager.changeCurrentState(new SecondMenuState());
			}
		}		
	}

	@Override
	public void processMouse() {		
		LinkedList<MouseEvent> mouseEvents = mouse.getEvents();		
		for(MouseEvent event : mouseEvents){
			if( (event.getID() == MouseEvent.MOUSE_CLICKED) 
					&& (event.getButton() == MouseEvent.BUTTON1)
					&& (mouse.mouseOver(event.getX(), event.getY(), 490, 670, 390, 440))){
				gameShutdown();
			}
			if( (event.getID() == MouseEvent.MOUSE_CLICKED) 
					&& (event.getButton() == MouseEvent.BUTTON1)
					&& (mouse.mouseOver(event.getX(), event.getY(), 290, 470, 390, 440))){
				StateManager.changeCurrentState(Pong.mainGame);
			}
		}		
	}

	@Override
	public void processMouseWheel() {
		// TODO Auto-generated method stub
		
	}
}
