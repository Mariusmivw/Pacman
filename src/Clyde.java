class Clyde extends Ghost {
	public Clyde(Main mainApplet) {
		super(mainApplet);
		ghostColor = mainApplet.color(255, 127, 0);
		scatterTarget = mainApplet.tiles[0][35];
		xPos = 16 * mainApplet.tileSize;
		yPos = 17 * mainApplet.tileSize + mainApplet.tileSize / 2;
		direction = Direction.Up;
		nextDirection = Direction.Down;
	}

	public int[] getTargetTile() {
		int[] target = { mainApplet.Pacman.tileX(), mainApplet.Pacman.tileY() };
		if (Math.sqrt(Math.pow(tileX() - target[0], 2) + Math.pow(tileY() - target[1], 2)) < 8) {
			target[0] = scatterTarget.tileX();
			target[1] = scatterTarget.tileY();
		}
		return target;
	}
}
