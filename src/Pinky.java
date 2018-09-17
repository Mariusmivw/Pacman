class Pinky extends Ghost {
	public Pinky(Main mainApplet) {
		super(mainApplet);
		ghostColor = mainApplet.color(255, 200, 200);
		scatterTarget = mainApplet.tiles[2][0];
		xPos = 14 * mainApplet.tileSize;
		yPos = 17 * mainApplet.tileSize + mainApplet.tileSize / 2;
		direction = Direction.Down;
		nextDirection = Direction.Up;
	}

	public int[] getTargetTile() {
		int[] target = { mainApplet.Pacman.tileX(), mainApplet.Pacman.tileY() };
		switch (mainApplet.Pacman.direction) {
		case Up:
			target[1] -= 4;
			// break; //commented, because it's an original bug
		case Left:
			target[0] -= 4;
			break;
		case Down:
			target[1] += 4;
			break;
		case Right:
			target[0] += 4;
			break;
		}
		return target;
	}
}
