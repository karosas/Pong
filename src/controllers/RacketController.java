package controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import model.Racket;

public class RacketController {

	private Racket player;
	private Racket ai;
	
	public RacketController()
	{
		player = new Racket(20,20, 10, 120);
		ai = new Racket(600,20,10,120); 
	}
	
	public Racket getPlayer()
	{
		return player;
	}
	
	public Racket getAi()
	{
		return ai;
	}
	
	
	public void handleInput(float delta, GameContainer gc) {
		Input input = gc.getInput();
		if (input.isKeyDown(Input.KEY_DOWN)) {
			if(player.getCenterY() + (player.getHeight()/2) <= 480) {
				player.setY(player.getY() /*+ racket.getY() */ + (0.6f/delta));
			}
		}
		if (input.isKeyDown(Input.KEY_UP)) {
			if(player.getCenterY() - (player.getHeight()/2) >= 0) {
				player.setY(player.getY() /*- racket.getY()*/  - (0.6f/delta));
			}
		}
	}
	
	public void handleAiMovement(BallController ballCntr) {
		
		if(ai.getCenterY() + (ai.getHeight()/2) > 0
		&& ai.getCenterY() - (ai.getHeight()/2) < 480) {
			ai.setCenterY(ballCntr.getBall().getY());
		}
	}
	
	
}
