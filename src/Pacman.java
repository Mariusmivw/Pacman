public class Pacman {
	private Main mainApplet;

	public Direction direction = Direction.Left;
	private int xPos, yPos;

	public Pacman(Main mainApplet) {
		this.mainApplet = mainApplet;
		xPos = (int) (14 * mainApplet.tileSize);
		yPos = (int) (26 * mainApplet.tileSize + mainApplet.tileSize / 2);
	}

	private void move() {
		
	}

	private void draw() {
		mainApplet.pushStyle();
			mainApplet.fill(255, 255, 0);
			mainApplet.ellipse(xPos, yPos, mainApplet.tileSize, mainApplet.tileSize);
		mainApplet.popStyle();
	}

	public void update() {
		move();
		draw();
	}

	public int tileX() {
		return (int) ((float) xPos / mainApplet.width * 28);
	}

	public int tileY() {
		return (int) ((float) yPos / mainApplet.height * 36);
	}
}