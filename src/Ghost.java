class Ghost {
	public int ghostColor;
	public int xPos = 10, yPos = 20;
	public boolean scatter = true;
	public boolean frightened = true;
	public byte elroy = 0;
	public Tile scatterTarget;
	public Direction direction;
	public Direction nextDirection;
	public int released = 0;
	private int ticker = 0;
	public Main mainApplet;

	public Ghost(Main mainApplet) {
		this.mainApplet = mainApplet;
	}

	private void move() {
		switch (direction) {
		case Up:
			yPos--;
			break;
		case Down:
			yPos++;
			break;
		case Left:
			xPos--;
			break;
		case Right:
			xPos++;
			break;
		}

		if (yPos % mainApplet.tileSize == mainApplet.tileSize / 2) {
			// in new tile
			if (xPos % mainApplet.tileSize == mainApplet.tileSize / 2 && released == 2) {

				direction = nextDirection;
				determineNextDirection();

			} else if (released == 0) {

				direction = nextDirection;
				if (tileY() == 17) {
					nextDirection = direction != Direction.Up ? Direction.Up : Direction.Down;
				}

			} else if (released == 1) {

				direction = nextDirection;
				if (tileY() == 17 && direction == Direction.Down) {
					nextDirection = Direction.Up;
				} else if (tileY() == 15) {
					released = 2;
					determineNextDirection();
				}

			}
		}
	}

	private void draw() {
		mainApplet.pushStyle();
			mainApplet.fill(ghostColor, (float)0.9 * 255);
			mainApplet.ellipse(xPos, yPos, mainApplet.tileSize, mainApplet.tileSize);
			mainApplet.fill(ghostColor, (float)0.5 * 255);
			int[] target = getTargetTile();
			mainApplet.rect(target[0] * mainApplet.tileSize + mainApplet.tileSize / 2, target[1] * mainApplet.tileSize + mainApplet.tileSize / 2, 
				mainApplet.tileSize / 8 * 6, mainApplet.tileSize / 8 * 6);
		mainApplet.popStyle();
		extraDraw();
	}

  public void update() {
	if (ticker < 84*60+1) {
	  switch (ticker++) {
		case 7*60:
		  scatter = !scatter;
		  break;
		case 27*60:
		  scatter = !scatter;
		  break;
		case 34*60:
		  scatter = !scatter;
		  break;
		case 54*60:
		  scatter = !scatter;
		  break;
		case 59*60:
		  scatter = !scatter;
		  break;
		case 79*60:
		  scatter = !scatter;
		  break;
		case 84*60:
		  scatter = !scatter;
		  break;
	  }
	}
	move();
	draw();
  }

	public void determineNextDirection() {
		int[] target = getTargetTile();
		int xDist = target[0] - tileX();
		int yDist = target[1] - tileY();
		switch (direction) {
		case Up:
			yDist++;
			break;
		case Down:
			yDist--;
			break;
		case Left:
			xDist++;
			break;
		case Right:
			xDist--;
			break;
		}

		if (Math.abs(xDist) < Math.abs(yDist)) {
			if (yDist < 0) {
				if (xDist < 0) {
					setNextDirection(Direction.Up, Direction.Left, Direction.Right, Direction.Down);
				} else {
					setNextDirection(Direction.Up, Direction.Right, Direction.Left, Direction.Down);
				}
			} else {
				if (xDist < 0) {
					setNextDirection(Direction.Down, Direction.Left, Direction.Right, Direction.Up);
				} else {
					setNextDirection(Direction.Down, Direction.Right, Direction.Left, Direction.Up);
				}
			}
		} else if (Math.abs(xDist) > Math.abs(yDist)) {
			if (xDist < 0) {
				if (yDist < 0) {
					setNextDirection(Direction.Left, Direction.Up, Direction.Down, Direction.Right);
				} else {
					setNextDirection(Direction.Left, Direction.Down, Direction.Up, Direction.Right);
				}
			} else {
				if (yDist < 0) {
					setNextDirection(Direction.Right, Direction.Up, Direction.Down, Direction.Left);
				} else {
					setNextDirection(Direction.Right, Direction.Down, Direction.Up, Direction.Left);
				}
			}
		} else {
			if (yDist < 0) {
				if (xDist < 0) {
					setNextDirection(Direction.Up, Direction.Left, Direction.Down, Direction.Right);
				} else {
					setNextDirection(Direction.Up, Direction.Right, Direction.Left, Direction.Down);
				}
			} else {
				if (xDist < 0) {
					setNextDirection(Direction.Left, Direction.Down, Direction.Up, Direction.Right);
				} else {
					setNextDirection(Direction.Down, Direction.Right, Direction.Up, Direction.Left);
				}
			}
		}
	}

	private void setNextDirection(Direction high, Direction midHigh, Direction midLow, Direction low) {
		Direction[] directions = { high, midHigh, midLow, low };
		boolean foundDirection = false;

		int xOff = 0, yOff = 0;

		switch (direction) {
		case Up:
			yOff = -1;
			break;
		case Down:
			yOff = 1;
			break;
		case Left:
			xOff = -1;
			break;
		case Right:
			xOff = 1;
			break;
		}

		for (Direction direct : directions) {
			switch (direct) {
			case Up:
				if (direction != Direction.Down && mainApplet.tiles[tileX() + xOff][tileY() + yOff - 1].type() != TileType.Wall
						&& !((tileY() + yOff == 14 || tileY() + yOff == 26)
								&& (tileX() + xOff == 12 || tileX() + xOff == 15))) {
					foundDirection = true;
				}
				break;
			case Down:
				if (direction != Direction.Up && mainApplet.tiles[tileX() + xOff][tileY() + yOff + 1].type() != TileType.Wall
						&& mainApplet.tiles[tileX() + xOff][tileY() + yOff + 1].type() != TileType.GWall) {
					foundDirection = true;
				}
				break;
			case Left:
				if (direction != Direction.Right && ((tileY() == 17 && tileX() < 2)
						|| mainApplet.tiles[tileX() + xOff - 1][tileY() + yOff].type() != TileType.Wall)) {
					foundDirection = true;
				}
				break;
			case Right:
				if (direction != Direction.Left && ((tileY() == 17 && tileX() > 25)
						|| mainApplet.tiles[tileX() + xOff + 1][tileY() + yOff].type() != TileType.Wall)) {
					foundDirection = true;
				}
				break;
			}
			if (foundDirection) {
				nextDirection = direct;
				break;
			}
		}
	}

	public void extraDraw() {
	}

	public int[] getTargetTile() {
		return null;
	}

	public int tileX() {
		return (int) ((float) xPos / mainApplet.width * 28);
	}

	public int tileY() {
		return (int) ((float) yPos / mainApplet.height * 36);
	}
}
