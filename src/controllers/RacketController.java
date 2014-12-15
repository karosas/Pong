package controllers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import model.Racket;
/**
 * Racket controller who handles input of player, AI player's movement.
 * @author Edgaras Ausvicas
 *
 */
public class RacketController {

	private Racket player;
	private Racket ai;
	
	/**
	 * Instantiates rackets of player and ai.
	 */
	public RacketController()
	{
		player = new Racket(20,20, 10, 120);
		ai = new Racket(600,20,10,120); 
	}
	/**
	 * Returns a racket of a player
	 * @return player's Racket
	 */
	public Racket getPlayer()
	{
		return player;
	}
	/**
	 * Returns a racket of an AI
	 * @return ai's racket
	 */
	public Racket getAi()
	{
		return ai;
	}
	
	/**
	 * Handles human player's input and moves his racket accordingly.
	 * @param delta The change in time since the last render or update was made.
	 * @param gc GameContainer object, comes from Slick2D game engine
	 */
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
	
	/**
	 * Controlls AI racket.
	 * Basically just follows ball's Y coordinate.
	 * @param ballCntr BallController object.
	 */
	public void handleAiMovement(BallController ballCntr) {
		
		if(ai.getCenterY() + (ai.getHeight()/2) > 0
		&& ai.getCenterY() - (ai.getHeight()/2) < 480) {
			ai.setCenterY(ballCntr.getBall().getY());
		}
	}
	
	
}
