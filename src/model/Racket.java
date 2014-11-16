package model;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Racket {

	private Shape racket;
	private float x;
	private float y=240;
	private int width=10;
	private int height=50;
	
	public Racket(boolean isLeftOne) {
		if(isLeftOne) {
			x = 10;
		}
		else {
			x = 610;
		}
		racket = new Rectangle(x,y,width,height);
	}
	
	public Shape getShape() {
		return racket;
	}
	
	public float getX() {
		return x;
		
	}
	public void setX(float x) {
		this.x = x;
		racket.setCenterX(x);
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
		racket.setCenterY(y);
	}
	
	public float getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public float getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
