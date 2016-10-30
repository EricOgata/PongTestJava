package gameStates;

import game.Game;
import game.Log;
import game.Pong;
import input.Keyboard;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class SecondMenuState implements State{
	
	private Font font1;
	private Keyboard keyboard;
	
	public SecondMenuState() {
	}
	
	@Override
	public void draw(Graphics2D g) {
		g.setFont(font1);
		g.setColor(Color.WHITE);
		int textPosX = Pong.getGameWidth() / 18;
		int textPosY = Pong.getGameHeight() / 2;
		g.drawString("Aperte ESC para voltar para o menu anterior", textPosX, textPosY);		
	}

	@Override
	public void update(double delta) {
		processIOEvents();				
	}

	@Override
	public void redraw() {
		
	}
	
	/**
	 * Função responsável pro carregar tudo o que será utilizado por esta classe 
	 */
	@Override
	public void load() {
		keyboard = Game.getKeyboard();
		
		try {
			Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/resources/arcade-normal.ttf"));
			font1 = dynamicFont.deriveFont(20f);
		} catch (FileNotFoundException e) {
			font1 = new Font("arial", 1, 25);
			e.printStackTrace();
		} catch (FontFormatException e) {
			font1 = new Font("arial", 1, 25);
			e.printStackTrace();
		} catch (IOException e) {
			font1 = new Font("arial", 1, 25);
			e.printStackTrace();
		}		
	}

	@Override
	public void unload() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Função aonde serão definidas quais eventos serão processados
	 */
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
				StateManager.changeCurrentState(new MainMenuState());
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

}
