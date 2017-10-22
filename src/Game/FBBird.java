package Game;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FBBird {
	ArrayList<Image> sprites;
	FBPipe pipe;
	FBCanvas canvas;
	int progress;
	int spriteCurrent;
	int y;
	int score = 0;
	int[] frontCorner1 = {674,0};
	int[] frontCorner2 = {674,0};
	Image sprite;
	int calls;
	
	public FBBird() {
		sprites = new ArrayList<Image>();
		spriteCurrent = 0;
		calls = 4;
	}
	public void checkCollision(FBPipe pipe) {
		this.findBirdFront();
		for(int i = 0; i < 10; i ++) {
			if(frontCorner1[0] >= pipe.collisionBoxX[i] & frontCorner1[0] <= pipe.collisionBoxX[i] + 63) {
				if((i%2)==1 & frontCorner1[1] <= pipe.collisionBoxY[i]) {
					System.out.println("COLLISIONA");
				}
				
			}
		}
	}
	public void score() {
		score += 1;
	}
	public void findBirdFront() {
		frontCorner1[1] = this.getY();
		frontCorner2[1] = this.getY() + 49;
	}
	public void setCanvas(FBCanvas canvas) {
		this.canvas = canvas;
	}
	public void setPipe(FBPipe pipe) {
		this.pipe = pipe;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return this.y;
	}
	public void raise(int Y) {
		if(this.y - Y >= 0 & this.y - Y <= 570) {
			this.y -= Y;
		}
	}
	public void progressBird(int scrollRate) {
		progress += scrollRate;
	}
	public void reset() {
		progress = 0;
	}
	public int getProgresss() {
		return this.progress;
	}
	public void addSprite(String asset) {
		sprites.add(new ImageIcon(getClass().getResource(asset)).getImage());
	}
	public Image getSprite() {
		if(calls >= 4) {
			if(spriteCurrent >= sprites.size()) {
				spriteCurrent = 0;
			}
			sprite = sprites.get(spriteCurrent);
			spriteCurrent++;
			calls = 0;
		}
		calls++;
		return sprite;
	}
}
