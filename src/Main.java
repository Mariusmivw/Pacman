import processing.core.PApplet;

public class Main extends PApplet {
	public Tile[][] tiles = new Tile[28][36];
	public int tileSize = 16;
	public Pacman Pacman;
	public Blinky Blinky;
	public Inky Inky;
	public Pinky Pinky;
	public Clyde Clyde;

	public static void main(String[] args) {
		PApplet.main("Main");
	}

	public void settings() {
		size(28 * tileSize, 36 * tileSize);
	}

	public void setup() {
		TileType None = TileType.None;
		TileType Dot = TileType.Dot;
		TileType SDot = TileType.SDot;
		TileType Wall = TileType.Wall;
		TileType Slow = TileType.Slow;
		TileType GWall = TileType.GWall;

		TileType[][] typeField = {
			{ None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None,
			None, None, None, None, None, None, None, None, None, None, None },
			{ None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None,
			None, None, None, None, None, None, None, None, None, None, None },
			{ None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None,
			None, None, None, None, None, None, None, None, None, None, None },
			{ Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall },
			{ Wall, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot, Dot,
			Dot, Dot, Dot, Dot, Dot, Dot, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, SDot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, SDot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot,
			Dot, Dot, Dot, Dot, Dot, Dot, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, Dot, Dot, Dot, Dot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot,
			Wall, Wall, Dot, Dot, Dot, Dot, Dot, Dot, Wall },
			{ Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, None, Wall, Wall, None, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall },
			{ None, None, None, None, None, Wall, Dot, Wall, Wall, Wall, Wall, Wall, None, Wall, Wall, None, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, None, None, None, None, None },
			{ None, None, None, None, None, Wall, Dot, Wall, Wall, None, None, None, None, None, None, None, None,
			None, None, Wall, Wall, Dot, Wall, None, None, None, None, None },
			{ None, None, None, None, None, Wall, Dot, Wall, Wall, None, Wall, Wall, Wall, GWall, GWall, Wall, Wall,
			Wall, None, Wall, Wall, Dot, Wall, None, None, None, None, None },
			{ Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, None, Wall, None, None, None, None, None, None,
			Wall, None, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall },
			{ Slow, Slow, Slow, Slow, Slow, Slow, Dot, Wall, Wall, None, Wall, None, None, None, None, None, None,
			Wall, None, Wall, Wall, Dot, Slow, Slow, Slow, Slow, Slow, Slow },
			{ Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, None, Wall, None, None, None, None, None, None,
			Wall, None, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall },
			{ None, None, None, None, None, Wall, Dot, Wall, Wall, None, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, None, Wall, Wall, Dot, Wall, None, None, None, None, None },
			{ None, None, None, None, None, Wall, Dot, Wall, Wall, None, None, None, None, None, None, None, None,
			None, None, Wall, Wall, Dot, Wall, None, None, None, None, None },
			{ None, None, None, None, None, Wall, Dot, Wall, Wall, None, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, None, Wall, Wall, Dot, Wall, None, None, None, None, None },
			{ Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, None, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, None, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall },
			{ Wall, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot, Dot,
			Dot, Dot, Dot, Dot, Dot, Dot, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, SDot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot, Dot, Dot, Dot, None, None, Dot, Dot, Dot, Dot,
			Dot, Dot, Dot, Wall, Wall, Dot, Dot, SDot, Wall },
			{ Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, Dot, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall },
			{ Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, Dot, Wall, Wall, Dot, Wall, Wall, Dot, Wall, Wall, Wall },
			{ Wall, Dot, Dot, Dot, Dot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot, Wall, Wall, Dot, Dot, Dot, Dot,
			Wall, Wall, Dot, Dot, Dot, Dot, Dot, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, Dot, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall, Wall, Dot, Wall,
			Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Dot, Wall },
			{ Wall, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot, Dot,
			Dot, Dot, Dot, Dot, Dot, Dot, Dot, Wall },
			{ Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall,
			Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall, Wall },
			{ None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None,
			None, None, None, None, None, None, None, None, None, None, None },
			{ None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None, None,
			None, None, None, None, None, None, None, None, None, None, None }
		};

		for (int i = 0; i < 28; i++) {
			for (int u = 0; u < 36; u++) {
				tiles[i][u] = new Tile(this, typeField[u][i], i, u);
			}
		}

		for (int i = 0; i < 28; i++) {
			for (int u = 0; u < 36; u++) {
				tiles[i][u].initWalls();
			}
		}

		Pacman = new Pacman(this);
		Blinky = new Blinky(this);
		Pinky = new Pinky(this);
		Inky = new Inky(this);
		Clyde = new Clyde(this);

		this.stroke(0, 0, 0, 0);
		this.strokeWeight(0);
		this.rectMode(CENTER);
	}

	public void draw() {
		background(0);
		for (int i = 0; i < 28; i++) {
			for (int u = 0; u < 36; u++) {
				tiles[i][u].draw();
			}
		}
		Pacman.update();
		Blinky.update();
		Pinky.update();
		Inky.update();
		Clyde.update();
	}
}