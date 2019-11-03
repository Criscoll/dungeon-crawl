package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EnemyTest {

	private Dungeon dungeon;
	private Player player;
	private int height = 10;
	private int width = 10;
	@BeforeEach
	void setUp() throws Exception {
		this.dungeon = new Dungeon(height, width);
		player = new Player(dungeon, 5, 5);
		
		EnemyHandler enemyHandler = new EnemyHandler(dungeon); 
		player.attachMovementObserver(enemyHandler);
		dungeon.setPlayer(player);
		
		
	}

	@AfterEach
	void tearDown() throws Exception {
		this.dungeon = null;
		this.player = null;
	}

	/**
	 * Test enemy movement in every direction
	 */
	@Test
	void testUpMove() {
		Enemy enemy = new Enemy(dungeon, 5, 8);
		this.dungeon.addEntity(enemy);

		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 8); 
		
		player.moveUp();
		
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 7); 

	}
	
	@Test
	void testDownMove() {
		Enemy enemy = new Enemy(dungeon, 5, 3);
		this.dungeon.addEntity(enemy);

		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 3); 
		
		player.moveDown();
		
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 4); 

	}
	
	@Test
	void testRightMove() {
		Enemy enemy = new Enemy(dungeon, 3, 5);
		this.dungeon.addEntity(enemy);

		assertEquals(enemy.getX(), 3); 
		assertEquals(enemy.getY(), 5); 
		
		player.moveRight();
		
		assertEquals(enemy.getX(), 4); 
		assertEquals(enemy.getY(), 5); 

	}
	
	@Test
	void testLeftMove() {
		Enemy enemy = new Enemy(dungeon, 8, 5);
		this.dungeon.addEntity(enemy);

		assertEquals(enemy.getX(), 8); 
		assertEquals(enemy.getY(), 5); 
		
		player.moveLeft();
		
		assertEquals(enemy.getX(), 7); 
		assertEquals(enemy.getY(), 5); 

	}
	
	/**
	 * Enemies should move away from player when invincible
	 */
	@Test
	void testInvincibilityMovement() {
		this.player.setInvinicibility(true);
		
		Enemy enemy = new Enemy(dungeon, 8, 5);
		this.dungeon.addEntity(enemy);

		assertEquals(enemy.getX(), 8); 
		assertEquals(enemy.getY(), 5); 
		
		player.moveRight();
		
		assertEquals(enemy.getX(), 9); 
		assertEquals(enemy.getY(), 5); 
		
		this.player.setInvinicibility(false);

	}
	
	@Test
	void testCollisionDetect() {
		Enemy enemy = new Enemy(dungeon, 5, 8);
		Wall wall = new Wall(6, 8); 
		this.dungeon.addEntity(wall);
		this.dungeon.addEntity(enemy);

		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 8); 
		
		assertTrue(dungeon.getEntity(6, 8) instanceof Wall); 
		assertTrue(dungeon.isObstructed(6, 8) == true); 
		
		enemy.moveRight();
		
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 8); 

	}
	
}
	

