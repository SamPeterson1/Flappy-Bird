package Game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class FBBackground {
	private int X = 0;
	private Image image;
	private int ScrollSpeed;
	FBPipe pipe;
	FBBird bird;
	public void setBird(FBBird bird) {
		this.bird = bird;
	}
	public void setPipe(FBPipe pipe) {
		this.pipe = pipe;
	}
	public int getX() {
		return X;
	}
	public void setScrollSpeed(int ScrollSpeed) {
		this.ScrollSpeed = ScrollSpeed;
	}
	public void setImage(String s) {
		image = new ImageIcon(getClass().getResource(s)).getImage();
	}
	public int getScrollSpeed() {
		return this.ScrollSpeed;
	}
	public Image getImage() {
		if(bird.getProgresss() == 100000) {
			pipe.setDispersion(pipe.getDispersion() - 50);;
		}
		pipe.scrollPipe(ScrollSpeed);
		bird.progressBird(ScrollSpeed);
		X -= ScrollSpeed;
		if(X <= -1280) {
			X = 0;
		}
		return image;
	}
	public void reset() {
		X = 0;
	}
}
