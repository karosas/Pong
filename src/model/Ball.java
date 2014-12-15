package model;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Rectangle;

public class Ball extends Rectangle{


	/**
	 * Constructor which basically creates a Rectangle which represents the racket.
	 * @param ballX top left x coordinate of ball.
	 * @param ballY top left y coordinate of ball.
	 * @param ballWidth ball width.
	 * @param ballHeight ball height.
	 */
	public Ball(float ballX,  float ballY, float ballWidth, float ballHeight) {
		super(ballX, ballY, ballWidth, ballHeight);
	}
	
	/**
	 * Resets the ball coordinates to center of the screen
	 */
	public void resetBall() {
		setCenterX(320);
		setCenterY(240);
	}

}
