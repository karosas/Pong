package ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import controllers.*;

public class Main extends BasicGame {

	Player player;
	AIPlayer ai;
	BallController ballCntrl;
	
	public Main(String title) {
		super(title);
		player = new Player(true);
		ai = new AIPlayer();
		ballCntrl = new BallController();
		
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] arguments)
    {
        try
        {
            AppGameContainer app = new AppGameContainer(new Main("Pong"));
            app.setDisplayMode(640, 480, false);
            app.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		// TODO Auto-generated method stub
		g.draw(ballCntrl.getBall().getShape());
		g.draw(player.getRacket().getShape());
		g.draw(ai.getRacket().getShape());
		g.draw(ballCntrl.getLeft().getShape());
		g.draw(ballCntrl.getRight().getShape());
		
		
		// Score
		g.drawString("" + player.getScore(), 50, 50);
		g.drawString("" + ai.getScore(), 550, 50);
		
		// Data for debugging manually
		g.drawString("Racket X: " + player.getRacket().getX(), 200, 200);
		g.drawString("Racket Y: " + player.getRacket().getY(), 200, 220);
		g.drawString("Ball X: " + ballCntrl.getBall().getX(), 200, 240);
		g.drawString("Ball Y: " + ballCntrl.getBall().getY(), 200, 260);
		g.drawString("Ball rotation: " + ballCntrl.getRotation(), 200, 280);
		boolean horizontal = ballCntrl.isGoingLeft();
		boolean vertical = ballCntrl.isGoingUp();
		g.drawString("Going Left: " + horizontal, 200, 300);
		g.drawString("Going Up: " + vertical, 200, 320);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		// TODO Auto-generated method stub
		ballCntrl.handleBall(delta, player, ai);
		player.handleInput(delta, gc);
		ai.handleMovement(ballCntrl);
		
	}

}
