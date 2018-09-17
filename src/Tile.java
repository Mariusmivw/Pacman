public class Tile {
	private int tileX, tileY;
	private TileType type;
	private byte wall = 0;
	private Main mainApplet;

	public Tile(Main mainApplet, TileType type, int x, int y) {
		this.mainApplet = mainApplet;
		this.type = type;
		tileX = x;
		tileY = y;
	}

	public void initWalls() {
		if (type == TileType.Wall) {
			if (tileY != 3 && next(Direction.Up).type() == TileType.Wall
			&& ((tileX != 0 && next(Direction.Up).next(Direction.Left).type() != TileType.Wall)
			|| (tileX != 27 && next(Direction.Up).next(Direction.Right).type() != TileType.Wall))) {
				wall += 1;
			}
			if (tileY != 33 && next(Direction.Down).type() == TileType.Wall
			&& ((tileX != 0 && next(Direction.Down).next(Direction.Left).type() != TileType.Wall)
			|| (tileX != 27 && next(Direction.Down).next(Direction.Right).type() != TileType.Wall))) {
				wall += 2;
			}
			if (tileX != 0 && next(Direction.Left).type() == TileType.Wall
			&& ((tileY != 3 && next(Direction.Left).next(Direction.Up).type() != TileType.Wall)
			|| (tileY != 33 && next(Direction.Left).next(Direction.Down).type() != TileType.Wall))) {
				wall += 4;
			}
			if (tileX != 27 && next(Direction.Right).type() == TileType.Wall
			&& ((tileY != 3 && next(Direction.Right).next(Direction.Up).type() != TileType.Wall)
			|| (tileY != 33 && next(Direction.Right).next(Direction.Down).type() != TileType.Wall))) {
				wall += 8;
			}
		}
	}

	public int tileX() {
		return tileX;
	}

	public int tileY() {
		return tileY;
	}

	public TileType type() {
		return type;
	}

	public void draw() {
		int tileSize = mainApplet.tileSize;
		switch (type) {
			case Dot:
				mainApplet.pushStyle();
					mainApplet.fill(255, 255, 255);
					mainApplet.ellipse(tileX * tileSize + tileSize / 2, tileY * tileSize + tileSize / 2, tileSize / 3, tileSize / 3);
				mainApplet.popStyle();
				break;
			case SDot:
				mainApplet.pushStyle();
					mainApplet.fill(255, 255, 255);
					mainApplet.ellipse(tileX * tileSize + tileSize / 2, tileY * tileSize + tileSize / 2, tileSize, tileSize);
				mainApplet.popStyle();
				break;
			case Wall:
				String binary = Integer.toBinaryString(wall);
				mainApplet.pushStyle();
					mainApplet.fill(0, 0, 255);

					if (binary.length() > 0 && binary.charAt(binary.length() - 1) == '1') {
						// line up
						mainApplet.rect(tileX * tileSize + tileSize / 2, tileY * tileSize, tileSize / 8, tileSize);
					}
					if (binary.length() > 1 && binary.charAt(binary.length() - 2) == '1') {
						// line down
						mainApplet.rect(tileX * tileSize + tileSize / 2, tileY * tileSize + tileSize, tileSize / 8, tileSize);
					}
					if (binary.length() > 2 && binary.charAt(binary.length() - 3) == '1') {
						// line left
						mainApplet.rect(tileX * tileSize, tileY * tileSize + tileSize / 2, tileSize, tileSize / 8);
					}
					if (binary.length() > 3 && binary.charAt(binary.length() - 4) == '1') {
						// line left
						mainApplet.rect(tileX * tileSize + tileSize, tileY * tileSize + tileSize / 2, tileSize, tileSize / 8);
					}
				mainApplet.popStyle();
				break;
			case GWall:
				mainApplet.pushStyle();
					mainApplet.fill(0, 0, 255);
					if (next(Direction.Left).type() == TileType.Wall) {
						mainApplet.rect(tileX * tileSize - tileSize / 2, tileY * tileSize + tileSize / 2, tileSize, tileSize / 8);
					}
					if (next(Direction.Right).type() == TileType.Wall) {
						mainApplet.rect((tileX + 1) * tileSize + tileSize / 2, tileY * tileSize + tileSize / 2, tileSize, tileSize / 8);
					}
					mainApplet.fill(255, 180, 200);
					mainApplet.rect(tileX * tileSize + tileSize / 2, tileY * tileSize + tileSize / 2, tileSize, tileSize / 8);
				mainApplet.popStyle();
				break;
			default:
				break;
		}
	}

	Tile next(Direction direction) {
		switch (direction) {
			case Up:
				return tileY > 0 ? mainApplet.tiles[tileX][tileY - 1] : null;

			case Down:
				return tileY < 35 ? mainApplet.tiles[tileX][tileY + 1] : null;

			case Left:
				return tileX > 0 ? mainApplet.tiles[tileX - 1][tileY] : null;

			case Right:
				return tileX < 27 ? mainApplet.tiles[tileX + 1][tileY] : null;

			default:
				return null;
		}
	}
}
