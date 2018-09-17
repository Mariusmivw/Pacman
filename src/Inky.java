class Inky extends Ghost {
	public Inky(Main mainApplet) {
		super(mainApplet);
		ghostColor = mainApplet.color(0, 255, 255);
		scatterTarget = mainApplet.tiles[27][35];
		xPos = 12 * mainApplet.tileSize;
		yPos = 17 * mainApplet.tileSize + mainApplet.tileSize / 2;
		direction = Direction.Up;
		nextDirection = Direction.Down;
	}

	public int[] getTargetTile() {
		int[] target = { mainApplet.Pacman.tileX(), mainApplet.Pacman.tileY() };
		switch (mainApplet.Pacman.direction) {
		case Up:
			target[1] -= 2;
			// break; //commented, because it's an original bug
		case Left:
			target[0] -= 2;
			break;
		case Down:
			target[1] += 2;
			break;
		case Right:
			target[0] += 2;
			break;
		}
		target[0] += target[0] - mainApplet.Blinky.tileX();
		target[1] += target[1] - mainApplet.Blinky.tileY();
		return target;
	}

	public void extraDraw() {
		mainApplet.pushStyle();
		mainApplet.fill(ghostColor, (float)0.25 * 255);
		int[] target = getTargetTile();
		target[0] = (target[0] + mainApplet.Blinky.tileX()) / 2;
		target[1] = (target[1] + mainApplet.Blinky.tileY()) / 2;
		mainApplet.rect(target[0] * mainApplet.tileSize + mainApplet.tileSize / 2, target[1] * mainApplet.tileSize + mainApplet.tileSize / 2, mainApplet.tileSize / 8 * 6,
				mainApplet.tileSize / 8 * 6);
		mainApplet.popStyle();
	}
}
