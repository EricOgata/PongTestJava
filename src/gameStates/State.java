package gameStates;

import java.awt.Graphics2D;

import components.IControllable;
import components.IDrawable;
import components.IUpdatable;
import entities.GameObject;

public interface State extends IDrawable, IUpdatable, IControllable{
	/**
	 * re-draw everything on screen
	 */
	public void redraw();
	
	/**
	 * Load all of the data and graphics that this scene needs to function.
	 */
	public void load();
	
	/**
	 * unload everything that the garbage collector won't unload, itself, including graphics
	 */
	public void unload();
	
}
