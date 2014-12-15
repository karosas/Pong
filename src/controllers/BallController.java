package controllers;

import java.util.Random;

import model.Ball;
import model.Racket;
/**
 * BallController class responsible for handling the ball - checking and handling collisions mostly.
 * @author Edgaras
 *
 */
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
	/**
	 * Constructor creates a new RacketController, sets random ball rotation, creates a ball.
	 * 
	 * @param racketCntrl RacketController object.
	 */
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
	/**
	 * Returns a Ball object
	 * @return ball
	 */
	public Ball getBall() {
		return ball;
	}
	/**
	 * Gets rotation of a ball.
	 * @return rotation of the ball.
	 */
	public float getRotation() {
		return rotationDeg;
	}
	/**
	 * Returns boolean whether ball is going left or not.
	 * @return boolean if ball is going left or not.
	 */
	public boolean isGoingLeft() {
		return goingLeft;
	}
	/**
	 * Returns boolean whether ball is going up or not.
	 * @return boolean if ball is going up or not.
	 */
	public boolean isGoingUp() {
		return goingTop;
	}
	/**
	 * Method to handle speed and rotation of the ball.
	 * Calculates new x and y coordinates of the ball.
	 * Calls methods 'checkForCollision()' and 'checkForScore()' at the end.
	 * @param delta The change in time since the last render or update was made.
	 */
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
	
	/**
	 * Checks for collision and changes rotation accordingly.
	 */
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
	/**
	 * Checks if point was scored or not.
	 * If point was scored adds it to player who scored it and resets the ball.
	 */
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
	/**
	 * Generates random rotation and checks if ball wont get stuck with it
	 * For example go absolutely straight up or down and wont reach any racket.
	 * @return rotation
	 */
	private float genRandRotation() {
		Random r = new Random();
		float nRotation = r.nextFloat() * (360f - 0f) + 0f;
		if(nRotation == 0f || nRotation == 90f || nRotation == 180f 
				|| nRotation == 270f || nRotation == 360f) {
			genRandRotation();
		}
		return nRotation;
	}
	/**
	 * Calculates directions.
	 * According to the rotation sets 'goingLeft', 'goingUp' booleans to true or false.
	 */
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
