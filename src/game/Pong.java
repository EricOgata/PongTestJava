package game;

import gameStates.MainGame;
import gameStates.MainMenuState;
import gameStates.SecondMenuState;
import gameStates.State;
import gameStates.StateManager;

import java.awt.Graphics2D;


public class Pong extends Game {
	
	private static String title = "Pong";
	private static int width = 960;
	private static int height = (width / 16) * 9; // 16x9
	private static StateManager stateManager = StateManager.getInstance();
	
	// gameStates
	public static final State mainMenu = new MainMenuState();
	public static final State secondMenu = new SecondMenuState();
	public static final State mainGame = new MainGame();
	
	
	public Pong() {
		super(title, width, height);
	}
	
	public static void main(String[] args) {
		Game game = new Pong();
		game.run(1.0/60.0);
		//System.exit(0);
	}
	
	@Override
	public void gameStartup() {
		Log.info(getClass().getSimpleName(), "Starting up game.");
		stateManager.setCurrentState(mainMenu);
		
	}

	/**
	 * Função gameUpdate será executada a cada loop do gameLoop
	 */
	@Override
	public void gameUpdate(double delta) {
		stateManager.setDelta(delta);
		stateManager.updateCurrentState();
	}
	
	/**
	 * 
	 */
	@Override
	public void gameDraw(Graphics2D g) {
		stateManager.drawCurrentState(g);
	}

	/**
	 * Função execultada antes de finalizar o jogo
	 */
	@Override
	public void gameShutdown() {
		Log.info(getClass().getSimpleName(), "Shutting down game.");
		System.exit(0);		
	}

	public static int getGameHeight(){
		return height;
	}
	
	public static int getGameWidth(){
		return width;
	}

}
