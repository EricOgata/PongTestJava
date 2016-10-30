package entities;

import java.awt.Rectangle;

import components.IDrawable;
import components.IUpdatable;

public abstract class GameObject implements IDrawable, IUpdatable {
	protected int x;
	protected int y;
	protected Rectangle body;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Rectangle getBody() {
		return body;
	}
	public void setBody(Rectangle body) {
		this.body = body;
	}	
}
