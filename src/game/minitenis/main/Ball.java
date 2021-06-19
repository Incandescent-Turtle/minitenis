package game.minitenis.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

public class Ball 
{
	private Game game;
	private float speed = 1;
	
	private float x, y, xa = 1, ya = 1;
	
	public Ball(Game game)
	{
		this.game = game;
	}
	
	protected void tick()
	{
		if (x + xa < 0) xa = speed;
		
		if (x + xa > game.getWidth() - 30) 
		{
			xa = -speed;
			speed+=0.1;
		}
		
		if (y + ya < 0) ya = speed;
		
		x+= xa;
		y+= ya;
		
		if(game.getRacquet().getBounds().intersects(getBounds()))
		{
			ya = -speed;
			game.changeScore(10);
			speed+=0.2;
		}
		
		if(y > game.getHeight() - 30) game.gameOver();
	}
	
	protected void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fill(new Ellipse2D.Float(x, y, 30, 30));
	}
	
	public Rectangle.Float getBounds()
	{
		return new Rectangle.Float(x, y, 30, 30);
	}
}
