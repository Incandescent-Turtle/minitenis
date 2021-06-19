package game.minitenis.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Racquet 
{
	private Game game;
	int x;
	boolean leftPressed = false, rightPressed = false;
	
	public Racquet(Game game) 
	{
		this.game = game;
		x = game.getWidth()/2 - 50;
	}

	protected void tick()
	{
		if(leftPressed) x-=3;
		if(rightPressed) x+=3;
		
		x = x > game.getWidth() - 100 ? game.getWidth() - 100 : x < 0 ? x = 0: x;
	}
	
	protected void render(Graphics2D g) 
	{
		g.setColor(Color.BLACK);
		g.fillRect(x, game.getHeight()-40, 100, 20);
	}
	
	protected void keyPressed(int key)
	{
		switch(key)
		{
			case KeyEvent.VK_LEFT:
				leftPressed = true;
				break;
				
			case KeyEvent.VK_RIGHT:
				rightPressed = true;
				break;
		}
	}
	
	protected void keyReleased(int key)
	{
		switch(key)
		{
			case KeyEvent.VK_LEFT:
				leftPressed = false;
				break;
				
			case KeyEvent.VK_RIGHT:
				rightPressed = false;
				break;
		}
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, game.getHeight()-40, 100, 20);
	}
}
