package Game;

public class FBMain {
	public static void main(String args[]) {
		int i = 0;
		FBPhysics physics = new FBPhysics();
		FBFrame frame = new FBFrame();
		FBCanvas canvas = new FBCanvas();
		FBBird bird = new FBBird();
		FBPipe pipe = new FBPipe();
		FBBackground bg = new FBBackground();
		FBEventQueue queue = new FBEventQueue();
		FBEvent event;
		frame.addCanvas(canvas);
		canvas.addEventQueue(queue);
		bg.setImage("/Assets/Background.png");
		bg.setScrollSpeed(5);
		bird.setY(50);
		bird.setCanvas(canvas);
		bird.addSprite("/Assets/Flappy1.png");
		bird.addSprite("/Assets/Flappy2.png");
		bird.addSprite("/Assets/Flappy3.png");
		bird.setPipe(pipe);
		pipe.setImage("/Assets/Pipe.png", "/Assets/PipeDown.png");
		bg.setPipe(pipe);
		bg.setBird(bird);
		physics.SetBird(bird);
		canvas.setBird(bird);
		canvas.setBackground(bg);
		canvas.setPipe(pipe);
		pipe.generatePipes(500, 500, 300, 109242L);
		while(true) {
			if(queue.isEventToProcess()) {
				event = queue.getEvent();
				if (event.getType() == FBEvent.EVENT_MOUSE_BUTTON_PRESS & event.isMouseLeftButton() & canvas.isStarted()) {
					physics.flap();
				}
				for(int x = 464; x < 765; x ++) {
					for(int y = 522; y < 645; y ++) {
						if(event.getType() == FBEvent.EVENT_MOUSE_BUTTON_PRESS & event.isMouseLeftButton() & !canvas.isStarted() & event.getMouseX() == x & event.getMouseY() == y) {
							canvas.start();
						}
					}
				}
				if(event.getType() == FBEvent.EVENT_MOUSE_BUTTON_PRESS & event.isMouseRightButton()) {
					canvas.stop();
				}
			}
			if(canvas.isStarted()) {
				physics.updatePos();
			}
			canvas.draw();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pipe.calculateCollisionBoxes(bg);
			bird.checkCollision(pipe);
		}		
	}
}
