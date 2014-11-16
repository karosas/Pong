package controllers;

import model.Racket;

public class AIPlayer {
	private Racket racket;
	private int score = 0;
	BallController ballCntr;
	
	public AIPlayer() {
		racket = new Racket(false);
	}
	
	public Racket getRacket() {
		return racket;
	}
	
	public int getScore(){
		return score;
	}
	public void incrementScore() {
		score++;
	}
	public void resetScore() {
		score=0;
	}
	
	
	
	public void handleMovement(BallController ballController) {
		ballCntr = ballController;
		if(ballCntr.getBall().getY() - (racket.getHeight()/2) >= 0
		|| ballCntr.getBall().getY() + (racket.getHeight()/2) < 480) {
			racket.setY(ballCntr.getBall().getY());
		}
	}
	
}
