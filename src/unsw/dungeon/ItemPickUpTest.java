package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemPickUpTest {

	private Dungeon dungeon;
	private Player player;
	private int height = 10;
	private int width = 10;
	@BeforeEach
	void setUp() throws Exception {
		this.dungeon = new Dungeon(height, width);
		player = new Player(dungeon, 0, 0);
		ItemPickUpHandler o1 = new ItemPickUpHandler(this.dungeon);
		player.attach(o1);
		dungeon.setPlayer(player);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.dungeon = null;
		this.player = null;
	}

	@Test
	void testKey() {
		Key key = new Key(1, 0, 1);
		this.dungeon.addEntity(key);
		player.moveRight();
		assertTrue(this.dungeon.getEntity(1, 0) == null);
	}

}
