package game.minitenis.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private Ball ball;
	private Racquet racquet;
	private int score;

	private Game() 
	{		
		createWindow();
		ball = new Ball(this);
		racquet = new Racquet(this);

		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        
        while(true)
        {
	        long now = System.nanoTime();
	        delta += (now - lastTime) / ns;
	        lastTime = now;
	        while(delta >=1)
            {
                tick();
                delta--;
            }
            repaint();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
	}
	
	@Override
	public void paint(Graphics gb) 
	{
		super.paint(gb);
		Graphics2D g = (Graphics2D) gb;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		ball.render(g);
		racquet.render(g);
		g.setColor(Color.GRAY);
		g.setFont(new Font("Verdana", Font.BOLD, 30));
		g.drawString(Integer.toString(score), 10, 30);
	}
	
	private void tick()
	{
		ball.tick();
		racquet.tick();
	}
	
	public static void main(String[] args)
	{
		new Game();
	}
	
	private void createWindow()
	{
		JFrame frame = new JFrame("Mini Tennis");
		frame.setSize(300, 300);
		frame.setVisible(true);
		frame.add(this);
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) 
			{
				racquet.keyPressed(e.getKeyCode());
				if(e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
			}

			@Override
			public void keyReleased(KeyEvent e) 
			{
				racquet.keyReleased(e.getKeyCode());
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.requestFocus();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}
	
	public Racquet getRacquet()
	{
		return racquet;
	}
	
	public void changeScore(int amount)
	{
		score+=amount;
	}
	
	public int getScore()
	{
		return score;
	}

	public void gameOver() 
	{
		JOptionPane.showMessageDialog(this, "You score was: " + getScore(), "Game Over", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}
}