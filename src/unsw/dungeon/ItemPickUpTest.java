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

	/**
	 * test pick up a key
	 * and if the player already has a key then the key can not be picked up
	 */
	@Test
	void testKey() {
		Key key = new Key(1, 0, 1);
		this.dungeon.addEntity(key);
		player.moveRight();
		assertTrue(this.dungeon.getEntity(1, 0) == null);
		key = new Key(2, 0, 2);
		this.dungeon.addEntity(key);
		player.moveRight();
		assertTrue(this.dungeon.getEntity(2, 0) instanceof Key);
	}
	
	/**
	 * test pick up treasure
	 * The treasure disappear from the map after player picked it up
	 * Player should pick up treasure automatically by moving into the same square as the item
	 * A player should not have a limit to the amount of treasure they can pick up
	 */
	@Test 
	void testTreasure() {
		Treasure treasure = new Treasure(1, 0);
		this.dungeon.addEntity(treasure);
		treasure = new Treasure(2, 0);
		this.dungeon.addEntity(treasure);
		treasure = new Treasure(3, 0);
		this.dungeon.addEntity(treasure);
		player.moveRight();
		assertTrue(this.dungeon.getEntity(1, 0) == null);
		player.moveRight();
		assertTrue(this.dungeon.getEntity(2, 0) == null);
		player.moveRight();
		assertTrue(this.dungeon.getEntity(3, 0) == null);
		
	}
	
}
