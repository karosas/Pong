package model;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Rectangle;

public class Ball{

	private Shape ball;
	private float x = 320;
	private float y = 240;
	private int width = 20;
	private int height = 20;
	
	public Ball() {
		ball = new Rectangle(x,y,width,height);
		ball.setCenterX(x);
		ball.setCenterY(y);
	}
	
	
	public void resetBall() {
		x = 320;
		y = 240;
		ball.setCenterX(x);
		ball.setCenterY(y);
	}
	
	public Shape getShape() {
		return ball;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x=x;
		ball.setCenterX(x);
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y=y;
		ball.setCenterY(y);
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width=width;
	}
	
	public int getHeight(){
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
