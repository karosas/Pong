package model;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Rectangle;

public class Ball extends Rectangle{


	
	public Ball(float ballX,  float ballY, float ballWidth, float ballHeight) {
		super(ballX, ballY, ballWidth, ballHeight);
	}
	
	
	public void resetBall() {
		x = 320;
		y = 240;
		setCenterX(x);
		setCenterY(y);
	}

}
