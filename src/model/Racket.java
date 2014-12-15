package model;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public class Racket extends Rectangle {

	private int score;
	
	
	public Racket(float x,float y,float width,float height) {
		super(x,y,width,height);
		score=0;
	}
	
	public int getScore() {
		return score;
	}
	
	public void incrementScore() {
		score++;
	}
	
	public void resetScore() {
		score=0;
	}
	
	
	
}
