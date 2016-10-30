package game;

import input.Keyboard;
import input.Mouse;
import input.MouseWheel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.Random;


/**
 * Game that creates a window and handles input.
 **/
public abstract class Game extends GameLoop{
	
	private Frame frame;
	private Canvas canvas;
	private BufferStrategy buffer;
	private static Keyboard keyboard;
	private static Mouse mouse;
	private static MouseWheel mouseWheel;
	
	/**
	 * Creates a new game window
	 * 
	 * @param title title of the window.
	 * @param width width of the window.
	 * @param height height of the window.
	 **/
	public Game(String title, int width, int height){
		Log.debug("Game", "Creating game "+title+"( "+width+", "+height+")");
		
		// create frame and canvas
		frame = new Frame(title);
		frame.setResizable(false);
		canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		frame.add(canvas);
		// resize canvas and make the window visible
		canvas.setSize(width,height);
		frame.pack();
		frame.setVisible(true);
		
		// create buffer strategy
		canvas.createBufferStrategy(2);
		buffer = canvas.getBufferStrategy();
		
		// create our input classes and add them to the canvas
		keyboard = new Keyboard();
		mouse = new Mouse();
		mouseWheel = new MouseWheel();
		canvas.addKeyListener(keyboard);
		canvas.addMouseListener(mouse);
		canvas.addMouseWheelListener(mouseWheel);
		canvas.requestFocus();
	}
	
	/**
	 * Get the width of the window.
	 * @return the width of the window.
	 */
	public int getWidth(){
		return canvas.getWidth();
	}
	
	/**
	 * Get the height of the window.
	 * @return the height of the window.
	 */
	public int getHeight(){
		return canvas.getHeight();
	}
	
	/**
	 * Returns the title of the window.
	 * @return the title of the window.
	 */
	public String getTitle(){
		return frame.getTitle();
	}
	
	/**
	 * Returns the keyboard input manager.
	 * @return the keyboard.
	 */
	public static Keyboard getKeyboard(){
		return keyboard;
	}
	
	/**
	 * Returns the mouse input manager.
	 * @return the mouse.
	 */
	public static Mouse getMouse(){
		return mouse;
	}
	
	/**
	 * Returns the mouse wheel input manager.
	 * @return the mouse wheel.
	 */
	public static MouseWheel getMouseWheel(){
		return mouseWheel;
	}
	
	// Função obriga que as variáveis fiquem dentro de um periodo, ex. minimo <= variável <= máximo. 
	public static float clamp(float var, float min, float max){
		if(var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		else
			return var;
	}
	
	public static int showRandomInteger(int aStart, int aEnd){
		Random aRandom = new Random();
	    if (aStart > aEnd) {
	      throw new IllegalArgumentException("Start cannot exceed End.");
	    }
	    //get the range, casting to long to avoid overflow problems
	    long range = (long)aEnd - (long)aStart + 1;
	    // compute a fraction of the range, 0 <= frac < range
	    long fraction = (long)(range * aRandom.nextDouble());
	    int randomNumber =  (int)(fraction + aStart);    
	    return randomNumber;
	  }
	
	/**
	 * Calls gameStartup();
	 */
	public void startup(){
		gameStartup();
	}
	
	/**
	 * Updates the input classes then calls gameUpdate(double).
	 * @param delta time difference between the last two updates.
	 */
	public void update(double delta){
		// call the input updates first.
		keyboard.update();
		mouse.update();
		mouseWheel.update();
		// call the abstract update
		gameUpdate(delta);
	}
	
	/**
	 * Calls gameDraw(Graphics2D) using the current Graphics2D
	 */
	public void draw() {
		// get the current graphics object
		Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
		// clear the window
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		// send the graphics object to gameDraw() for our main drawing
		gameDraw(g);
		// show our changes on the canvas
		buffer.show();
		// release the graphics resources
		g.dispose();		
	}
	
	/**
	 * Calls gameShutdown();
	 */
	public void shutdown(){
		gameShutdown();
	}
	
	
	
	public abstract void gameStartup();
	public abstract void gameUpdate(double delta);
	public abstract void gameDraw(Graphics2D g);
	public abstract void gameShutdown();
	
}
