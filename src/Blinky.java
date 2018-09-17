class Blinky extends Ghost {
	public Blinky(Main mainApplet) {
		super(mainApplet);
		ghostColor = mainApplet.color(255, 0, 0);
		scatterTarget = mainApplet.tiles[25][0];
		xPos = 14 * mainApplet.tileSize;
		yPos = 14 * mainApplet.tileSize + mainApplet.tileSize / 2;
		direction = Direction.Left;
		determineNextDirection();
		released = 2;
	}

	public int[] getTargetTile() {
		int[] target = { mainApplet.Pacman.tileX(), mainApplet.Pacman.tileY() };
		return target;
	}
}
