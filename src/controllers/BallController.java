package controllers;

import java.util.Random;

import model.Ball;
import model.Racket;

public class BallController {
	private RacketController racketCntrl;
	private Ball ball;
	private float rotationDeg;
	private boolean goingLeft;
	private boolean goingTop;
	private int touched=0;
	private boolean touchedLeft;
	private boolean leftScored;
	private boolean rightScored;
	
	public BallController(RacketController racketCntrl) {
		this.racketCntrl = racketCntrl;
		leftScored = false;
		rightScored = false;
		rotationDeg = genRandRotation();
		ball = new Ball(320,240,20,20);

		calcDirections();
		
		if(goingLeft) {
			touchedLeft = false;
		}
		else {
			touchedLeft = true;
		}
	}
	
	public Ball getBall() {
		return ball;
	}
	
	public float getRotation() {
		return rotationDeg;
	}
	
	public boolean isGoingLeft() {
		return goingLeft;
	}
	public boolean isGoingUp() {
		return goingTop;
	}
	
	public void handleBall(float delta) {
		if(ball.getX() + (float)Math.cos(Math.toRadians(rotationDeg)) * (float)(0.3*delta) <= 640 - (ball.getWidth()/2) &&
				ball.getX() + (float)Math.cos(Math.toRadians(rotationDeg)) * (float)(0.3*delta) >= 0 + (ball.getWidth()/2)) {
			
			ball.setX( ball.getX() + (float)Math.cos(Math.toRadians(rotationDeg)) * (float)(0.3*delta));
		}
		if(ball.getY() + (float)Math.sin(Math.toRadians(rotationDeg)) * (float)(0.3*delta) <= 480 - (ball.getHeight()/2) &&
				ball.getY() + (float)Math.sin(Math.toRadians(rotationDeg)) * (float)(0.3*delta) >= 0 +  (ball.getHeight()/2)) {
			
					ball.setY( ball.getY() + (float)Math.sin(Math.toRadians(rotationDeg)) * (float)(0.3*delta));
		}
				
		checkForCollision();
		checkForScore();
	}
	
	
	private void checkForCollision() {
		
		Racket player = racketCntrl.getPlayer();
		Racket ai = racketCntrl.getAi();
		
		// Top border collisions
		if(ball.getY() - (ball.getHeight()/2)  <= 1 && goingTop) {
			if(goingLeft && touched != 1) {
				rotationDeg = 180 - (rotationDeg - 180);
				goingTop= false;
				touched = 1;
			}
			else if(!goingLeft &&touched != 2) {
				rotationDeg = 90 - (rotationDeg - 270);
				goingTop = false;
				touched = 2;
			}
		}
		// Bottom border collisions
		else if(ball.getY() + (ball.getHeight()/2) >= 479 && !goingTop) {
			if(goingLeft && touched != 3) {
				rotationDeg = 270 -(rotationDeg - 90);
				goingTop = true;
				touched = 3;
			}
			else if(!goingLeft && touched != 4) {
				rotationDeg = 360 - rotationDeg;
				goingTop = true;
				touched = 4;
			}
		}
		
		// Rackets collisions
		
		if(player.intersects(ball) && (!touchedLeft || leftScored || rightScored))
		{
			if(goingTop) {
				rotationDeg = 360 - (rotationDeg - 180);
				goingLeft = false;
			}
			else {
				rotationDeg = 90 - (rotationDeg - 90);
				goingLeft = false;
			}
			touchedLeft = true;
		}
		else if(ai.intersects(ball) && (touchedLeft || leftScored || rightScored)) {
			if(goingTop) {
				rotationDeg = 270 - (rotationDeg - 270);
				goingLeft = true;
			}
			else {
				rotationDeg = 180 - rotationDeg;
				goingLeft = true;
			}
			touchedLeft = false;
		}
		
	}
	
	private void checkForScore() {
		Racket player = racketCntrl.getPlayer();
		Racket ai = racketCntrl.getAi();
		
		if(ball.getCenterX() - (ball.getWidth()/2) <= 0) {
			ai.incrementScore();
			ball.resetBall();
			rotationDeg = genRandRotation();
			calcDirections();
			leftScored = true;
			rightScored = false;
			//rotationDeg = a;
			
		}
		else if(ball.getCenterX() + (ball.getWidth()/2) >= 640) {
			player.incrementScore();
			ball.resetBall();
			rotationDeg = genRandRotation();
			calcDirections();
			rightScored = true;
			leftScored = false;
			//rotationDeg = a;
		}
		 
	}
	private float genRandRotation() {
		Random r = new Random();
		float nRotation = r.nextFloat() * (360f - 0f) + 0f;
		if(nRotation == 0f || nRotation == 90f || nRotation == 180f 
				|| nRotation == 270f || nRotation == 360f) {
			genRandRotation();
		}
		return nRotation;
	}
	
	private void calcDirections() {
		if(rotationDeg > 180 && rotationDeg < 360) {
			goingTop = true;
		}
		else if(rotationDeg > 0 && rotationDeg < 180) {
			goingTop = false;
		}
		
		if(rotationDeg > 90 && rotationDeg < 270) {
			goingLeft = true;
		}
		else if((rotationDeg > 270 && rotationDeg <360) && (rotationDeg > 0 && rotationDeg < 90)) {
			goingLeft = false;
		}
	}
	
	
}
