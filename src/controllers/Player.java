package controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import model.Racket;

public class Player {
	private Racket racket;
	private int score = 0;
	private boolean isLeftOne;
	
	public Player(boolean isLeft) {
		isLeftOne = isLeft;
		racket = new Racket(isLeft);
	}
	
	public Racket getRacket() {
		return racket;
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
	
	public void handleInput(float delta, GameContainer gc) {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_DOWN)) {
			if(racket.getY() + (racket.getHeight()/2) <= 480) {
				racket.setY(racket.getY() /*+ racket.getY() */ + (0.6f/delta));
			}
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			if(racket.getY() - (racket.getHeight()/2) >= 0) {
				racket.setY(racket.getY() /*- racket.getY()*/  - (0.6f/delta));
			}
		}
	}
}
