package Game;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class FBPipe {
	int ka = 500;
	int[] x = {0,0,0,0,0,0,0,0,0,0};
	int[] length = {0,0,0,0,0,0,0,0,0,0};
	int[] collisionBoxX = {0,0,0,0,0,0,0,0,0,0};
	int[] collisionBoxY = {0,0,0,0,0,0,0,0,0,0};
	int[] collisionBoxLength = {0,0,0,0,0,0,0,0,0,0};
	int offset;
	int maxGap;
	int minGap;
	int dispersion;
	long seed = 9L;
	Image image1;
	Image image2;
	FBBird bird;
	private int i = 0;
	public void createPipe(int x, int length, int length2) {
		this.x[i] = x;
		this.length[i] = 600 - length;
		i++;
		this.x[i] = x;
		this.length[i] = length2 - 600;
		i ++;
	}
	public void reset() {
		offset = 0;
	}
	public int getRealPipeX(int pipe) {
		return x[pipe - 1];
	}
	public int getPipeHeight(int pipe) {
		return length[pipe - 1];
	}
	public void setBird(FBBird bird) {
		this.bird = bird;
	}
	public int getDispersion() {
		return this.dispersion;
	}
	public void setDispersion(int dispersion) {
		this.dispersion = dispersion;
	}
	public void createPipe(int x, int length, int length2, int i) {
		System.out.println("create pipe:" + x);
		this.x[i] = x;
		this.length[i] = 600 - length;
		i++;
		this.x[i] = x;
		this.length[i] = length2 - 600;
		i ++;
	}
	public int getPipeX(int pipe) {
		return x[pipe - 1] + offset;
	}
	public void scrollPipe(int scrollRate) {
		offset -= scrollRate;
	}
	public int getPipeLength(int pipe) {
		return length[pipe - 1];
	}
	public void calculateCollisionBoxes(FBBackground bg) {
		for(int i = 0; i < 10; i ++) {
			collisionBoxX[i] = this.getPipeX(i + 1) - (bg.getScrollSpeed() - 1) - 1;
		}
		for(int i1 = 1; i1 < 11; i1 ++) {
			if(getPipeHeight(i1) > 0) {
				collisionBoxY[i1 - 1] = (640 - getPipeHeight(i1));
			} else if(getPipeHeight(i1) < 0) {
				collisionBoxY[i1 - 1] = 640 - Math.abs(getPipeHeight(i1));
			}
			if(((i1 - 1)%2)==0) {
				collisionBoxY[i1 - 1] = 720 - collisionBoxY[i1 - 1] - 81;
			}
		}
	}
	public void setImage(String s1, String s2) {
		image1 = new ImageIcon(getClass().getResource(s1)).getImage();
		image2 = new ImageIcon(getClass().getResource(s2)).getImage();
	}
	public Image getImage(int i) {
		if((i%2)==0) {
			return image1;
		} else {
			return image2;
		}
	}
	public void regenerate() {
		x[0] = 0;
		length[0] = 0;
		for(int i = 0; i < 8;i ++) {
			x[i] = x[i + 2];
			length[i] = length[i + 2];
			this.i = 0;
		}
		seed ++;
		generateLastPipe();
		/*
		300
		-569
		579
		-290
		312
		-557
		278
		-591
		471
		-398
		*/
	}
	public void generatePipes(int maxGap, int minGap, int dispersion, long seed) {
		int calls = 0;
		this.maxGap = maxGap;
		this.minGap = minGap;
		this.dispersion = dispersion;
		Random rand1 = new Random(seed);
		for(int i = 0; i < 5; i ++) {
			int i2 = rand1.nextInt(600);
			int j = rand1.nextInt(600);
			while(true) {
				if(i2 + j <= 600 - minGap & i2 + j >= 600 - maxGap & calls < 9) {
					ka = dispersion + ka;
					calls ++;
					this.createPipe(ka, i2, j);
					break;
				} else if(calls < 9){
					i2 = rand1.nextInt(600);
					j = rand1.nextInt(600);
				} else if(calls > 8) {
					break;
				}
			}
		}
	}
	private void generateLastPipe() {
		int calls = 0;
		Random rand = new Random(seed);
			int i2 = rand.nextInt(600);
			int j = rand.nextInt(600);
			while(true) {
				System.out.println("SEARCHING....");
				if(i2 + j <= 600 - minGap & i2 + j >= 600 - maxGap & calls < 9) {
					ka = dispersion + ka;
					System.out.println("Generate pipe at x ofcdyswdrcftvgbhvfcdrxescftgvybhgvfcdsxerdtfgybhgyftvcdresxcdrfvbghnbgvfcdxcrftvbgyhgfdcsercftvbgyftvcdrfvbghgvftcdrcftvgbhnbgyvftdrftvgybhgyvftcdrbh:" + ka);
					calls ++;
					this.createPipe(ka, j, i2, 8);
					System.out.println(x[8] + " " + x[9]);
					break;
				} else if(calls < 9){
					i2 = rand.nextInt(600);
					j = rand.nextInt(600);
				} else if(calls > 8) {
					break;
				}
			}
	}
}
