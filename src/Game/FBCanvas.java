package Game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;


public class FBCanvas extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5518902386379874055L;
	private boolean begin = false;
	private boolean first = false;
	Image splash = new ImageIcon(getClass().getResource("/Assets/SplashScreen.png")).getImage();
	FBBird bird;
	FBBackground bg;
	FBPipe pipe;

	public FBCanvas() {
		this.setBackground(Color.BLACK);
	}
	public void draw() {
		BufferedImage image;
		Graphics g;
		image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
		g = image.getGraphics();	
		/* DO ALL OF YOUR DRAWING BETWEEN HERE */
		g.drawImage(bg.getImage(), bg.getX(), 0, null);
		if(this.isStarted()) {
			if(!first) {
				first = true;
				bg.reset();
				bird.reset();
				pipe.reset();
			}
			for(int i = 1; i < 11; i ++) {
				//System.out.println("PIPE X: " + pipe.getPipeX(i) + " " + pipe.getPipeLength(i) + " " + i);
				if(pipe.getPipeX(i) > -63 && pipe.getPipeX(i) <= 1280) {
					g.drawImage(pipe.getImage(i - 1), pipe.getPipeX(i), pipe.getPipeLength(i), null);
				} else if(pipe.getPipeX(i) <= -63) {
					pipe.regenerate();
				}
			}
			g.drawImage(bird.getSprite(), 605, bird.getY(), null);
			g.setColor(Color.WHITE);
			for(int i = 0; i < 10; i ++) {
				if((i%2)==1) {
					g.drawRect(pipe.collisionBoxX[i], 0, 63, pipe.collisionBoxY[i]);
				} else {
					g.drawRect(pipe.collisionBoxX[i],pipe.collisionBoxY[i], 63, pipe.collisionBoxY[i]);
				}
			}
			Integer i = bird.score;
			Font font = new Font("Jokerman", Font.PLAIN, 100);
			g.setFont(font);
			g.setColor(Color.ORANGE);
			g.drawString(i.toString(), 20, 100);
		} else {
			first = false;
			g.drawImage(splash, 0, 0, null);
		}
		/* AND HERE */
		g.dispose();
		g = this.getGraphics();
		g.drawImage(image, 0, 0, null); 
	}
	public void setBird(FBBird bird) {
		this.bird = bird;
	}
	public void setBackground(FBBackground bg) {
		this.bg = bg;
	}
	public void addEventQueue(FBEventQueue queue)  {

		this.addKeyListener(queue);
		this.addMouseListener(queue);
		this.addMouseMotionListener(queue);

		return;
	}
	public void setPipe(FBPipe pipe) {
		this.pipe = pipe;
	}
	public boolean isStarted() {
		return begin;
	}
	public void start() {
		begin = true;
	}
	public void stop() {
		begin = false;
	}
}
