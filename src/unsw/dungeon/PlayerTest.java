package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {

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
		doorOpenHandler o2 = new doorOpenHandler(this.dungeon);
		player.attach(o2);
		dungeon.setPlayer(player);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.dungeon = null;
		this.player = null;
	}

	/**
	 * player can move up, down, left and right
	 */
	@Test
	void testMove() {
		player.moveDown();
		assertTrue(player.getX() == 0);
		assertTrue(player.getY() == 1);
		player.moveUp();
		assertTrue(player.getX() == 0);
		assertTrue(player.getY() == 0);
		player.moveRight();
		assertTrue(player.getX() == 1);
		assertTrue(player.getY() == 0);
		player.moveLeft();
		assertTrue(player.getX() == 0);
		assertTrue(player.getY() == 0);
	}
	
	/**
	 * player's movement should be blocked by a wall
	 */
	@Test
	void testWall() {
		Wall wall = new Wall(1, 1);
		this.dungeon.addEntity(wall);
		wall = new Wall(0, 1);
		this.dungeon.addEntity(wall);
		wall = new Wall(1, 0);
		this.dungeon.addEntity(wall);
		player.moveDown();
		assertEquals(player.getX(), 0);
		assertEquals(player.getY(), 0);
	}
	
	/**
	 * player's movement should be blocked by closed door
	 * 2. If player does not have the corresponding key, 
	 *    then the door will block the movement of the player and will not be unlocked	
	 */
	@Test
	void testClosedDoor() {
		Key key= new Key(1, 0, 1);
		this.dungeon.addEntity(key);
		Door door = new Door(2, 0, 2);
		this.dungeon.addEntity(door);
		player.moveRight();
		player.moveRight();
		assertEquals(player.getX(), 1);
		assertEquals(player.getY(), 0);
	}
	
	/**
	 * player's movement should not be blocked by a open door
	 * 1. The door should be opened automatically when player tried to move 
	 * 	  into the square with the door if player has the corresponding key
	 * 3. When the door is opened, the key that was used to open the door will be consumed
	 * 4. The door will remain open once it is unlocked
	 */
	@Test
	void testOpenedDoor() {
		Key key= new Key(1, 0, 1);
		this.dungeon.addEntity(key);
		Door door = new Door(2, 0, 1);
		this.dungeon.addEntity(door);
		player.moveRight();
		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 0);
		assertTrue(player.getKey() == null);
		
		player.moveRight();
		player.moveLeft();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 0);
		
	}
	
	/**
	 * player's movement should be blocked by a boulder and a wall
	 * and the boulder can be pushed to next square if there is no wall 
	 * and player should be in the previous square the boulder at
	 */
//	@Test
//	void testBoulder() {
//		Wall wall = new Wall(2, 0);
//		this.dungeon.addEntity(wall);
//		Boulder boulder = new Boulder(1, 0);
//		this.dungeon.addEntity(boulder);
//		boulder = new Boulder(0, 1);
//		this.dungeon.addEntity(boulder);
//		player.moveRight();
//		assertTrue(player.getX() == 0);
//		assertTrue(player.getY() == 0);
//		player.moveDown();
//		assertTrue(player.getX() == 0);
//		assertTrue(player.getY() == 1);
//		assertTrue(boulder.getX() == 0);
//		assertTrue(boulder.getY() == 2);
//	}
	
}
