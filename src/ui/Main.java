package ui;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import controllers.*;

public class Main extends BasicGame {

	BallController ballCntrl;
	RacketController racketCntrl;
	
	public Main(String title) {
		super(title);
		racketCntrl = new RacketController();
		ballCntrl = new BallController(racketCntrl);
		
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
		g.draw(ballCntrl.getBall());
		g.draw(racketCntrl.getPlayer());
		g.draw(racketCntrl.getAi());
		
		// Score
		g.drawString("" + racketCntrl.getPlayer().getScore(), 50, 50);
		g.drawString("" + racketCntrl.getAi().getScore(), 550, 50);
		
		// Data for debugging manually
		g.drawString("Racket X: " + racketCntrl.getPlayer().getX(), 200, 200);
		g.drawString("Racket Y: " + racketCntrl.getPlayer().getY(), 200, 220);
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
		ballCntrl.handleBall(delta);
		racketCntrl.handleInput(delta, gc);
		racketCntrl.handleAiMovement(ballCntrl);
		
	}

}
