package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard extends EventQueue<KeyEvent> implements KeyListener {
	
	private final int KEY_CODE_MAX = 256;
	private boolean[] keys = new boolean[KEY_CODE_MAX];
	
	/**
	 * Returns whether a given key is currentrly pressed or not.
	 * 
	 * @param keyCode the code for the given key
	 * @param true if the key is pressed, false otherwise
	 * */
	
	public boolean isKeyPressed(int keyCode){
		if((keyCode >= 0) && (keyCode < KEY_CODE_MAX)){
			return keys[keyCode];
		}
		return false;
	}
	
	/**
     * Process a key event.
     *
     * @param event
     */
	@Override
	protected void processEvent(KeyEvent event) {
		if(event.getID() == KeyEvent.KEY_PRESSED){
			if( (event.getKeyCode() >= 0) &&
					(event.getKeyCode() < KEY_CODE_MAX)){
				keys[event.getKeyCode()] = true;
			}
		}else if(event.getID() == KeyEvent.KEY_RELEASED){
			if( (event.getKeyCode() >= 0) &&
					(event.getKeyCode() < KEY_CODE_MAX)){
				keys[event.getKeyCode()] = false;
			}
		}
	}
	
	 /**
     * Adds a key pressed event to the queue.
     *
     * @param event
     */
	@Override
	public void keyPressed(KeyEvent event) {
		addEvent(event);		
	}

	  /**
     * Adds a key released event to the queue.
     *
     * @param event
     */
	@Override
	public void keyReleased(KeyEvent event) {
		addEvent(event);
	}

	/**
     * Adds a key typed event to the queue.
     *
     * @param event
     */
	@Override
	public void keyTyped(KeyEvent event) {
		addEvent(event);
	}

}
