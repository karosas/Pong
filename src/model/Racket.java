package model;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
/**
 * Racket class which extends Rectangle of Slick2D game engine.
 * Stores information of racket rectangle (coordinates, dimensions).
 * Stores racket holder's score as well.
 * @author Edgaras Ausvicas
 *
 */
public class Racket extends Rectangle {

	private int score;
	
	
	/**
	 * Constructor which creates a rectangle which represents racket and sets score to zero.
	 * 
	 * @param x top left x coordinate of racket.
	 * @param y top left y coordinate of racket.
	 * @param width width of racket.
	 * @param height height of racket.
	 */
	public Racket(float x,float y,float width,float height) {
		super(x,y,width,height);
		score=0;
	}
	
	/**
	 * Get method used to get score of a racket holder.
	 * @return score of racket holder.
	 */
	
	public int getScore() {
		return score;
	}
	
	/**
	 * Increments the score by one.
	 */
	public void incrementScore() {
		score++;
	}
	
	/**
	 * resets the score of Racket holder. 
	 */
	public void resetScore() {
		score=0;
	}
	
	
	
}
