package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PortalTest {
	
	
	private Dungeon dungeon;
	private Player player;
	private int height = 10;
	private int width = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		this.dungeon = new Dungeon(height, width);
		player = new Player(dungeon, 5, 5);
		PortalHandler o1 = new PortalHandler(this.dungeon);
		player.attachMovementObserver(o1);
		dungeon.setPlayer(player);
	}

	@AfterEach
	void tearDown() throws Exception {
		this.dungeon = null;
		this.player = null;
	}

	/**
	 * test portal teleportation
	 * A player should teleport out the direction they go in
	 */
	@Test
	void testPortalRight() {
		
		Portal portal1 = new Portal(6, 5, 1);
		Portal portal2 = new Portal(8, 8, 1);
		this.dungeon.addEntity(portal1);
		this.dungeon.addEntity(portal2);

		
		assertTrue(this.dungeon.getEntity(6, 5) instanceof Portal); 
		assertTrue(this.dungeon.getEntity(8, 8) instanceof Portal); 

		player.moveRight();
		
		assertTrue(this.player.getX() == 9); 
		assertTrue(this.player.getY() == 8); 

		
		
	}
	
	@Test
	void testPortalLeft() {
		
		Portal portal1 = new Portal(4, 5, 1);
		Portal portal2 = new Portal(8, 8, 1);
		this.dungeon.addEntity(portal1);
		this.dungeon.addEntity(portal2);

		
		assertTrue(this.dungeon.getEntity(4, 5) instanceof Portal); 
		assertTrue(this.dungeon.getEntity(8, 8) instanceof Portal); 

		player.moveLeft();
		
		assertTrue(this.player.getX() == 7); 
		assertTrue(this.player.getY() == 8); 
		
		
	}
	
	@Test
	void testPortalUp() {
		
		Portal portal1 = new Portal(5, 4, 1);
		Portal portal2 = new Portal(8, 8, 1);
		this.dungeon.addEntity(portal1);
		this.dungeon.addEntity(portal2);

		
		assertTrue(this.dungeon.getEntity(5, 4) instanceof Portal); 
		assertTrue(this.dungeon.getEntity(8, 8) instanceof Portal); 

		player.moveUp();
		
		assertTrue(this.player.getX() == 8); 
		assertTrue(this.player.getY() == 7); 

		
		
	}
	
	@Test
	void testPortalDown() {
		
		Portal portal1 = new Portal(5, 6, 1);
		Portal portal2 = new Portal(8, 8, 1);
		this.dungeon.addEntity(portal1);
		this.dungeon.addEntity(portal2);

		
		assertTrue(this.dungeon.getEntity(5, 6) instanceof Portal); 
		assertTrue(this.dungeon.getEntity(8, 8) instanceof Portal); 

		player.moveDown();
		
		assertTrue(this.player.getX() == 8); 
		assertTrue(this.player.getY() == 9); 

		
		
	}
	
	
	@Test
	void testPortalObstruction() {
		
		
		
	}

}
