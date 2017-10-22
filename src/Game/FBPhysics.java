package Game;

public class FBPhysics {
	private int i = 0;
	private final int path[] = {-8, 0, 1, 3, 5, 8, 11, 15, 20, 25, 30, 40};
	FBBird bird = new FBBird();
	public void SetBird(FBBird bird) {
		this.bird = bird;
	}
	public void updatePos() {
		bird.raise(path[i]);
		if(i > 0) {
			i--;
		}
	}
	public void flap() {
		i = 11;
	}
}
