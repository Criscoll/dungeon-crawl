package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AttackTest {

	private Dungeon dungeon;
	private Player player;
	private int height = 10;
	private int width = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		this.dungeon = new Dungeon(height, width);
		player = new Player(dungeon, 5, 5);
		player.setHasWeapon(true);
		
		AttackHandler attackHandler = new AttackHandler(dungeon); 
		player.attachAttackObserver(attackHandler);
		
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
	void testEnemyDeath() {
		
		// attack right
		Enemy enemy = new Enemy(dungeon, 6, 5);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(6, 5) instanceof Enemy);  
		assertTrue(dungeon.getEntity(6, 5) != null);  
		assertEquals(enemy.getX(), 6); 
		assertEquals(enemy.getY(), 5); 
		
		player.attackRight();
		
		assertTrue(dungeon.getEntity(6, 5) == null); 
		
		// attack left
		enemy = new Enemy(dungeon, 4, 5);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(4, 5) instanceof Enemy);  
		assertTrue(dungeon.getEntity(4, 5) != null);  
		assertEquals(enemy.getX(), 4); 
		assertEquals(enemy.getY(), 5); 
		
		player.attackLeft();
		
		assertTrue(dungeon.getEntity(4, 5) == null); 
		
		
		// attack up
		enemy = new Enemy(dungeon, 5, 4);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(5, 4) instanceof Enemy);  
		assertTrue(dungeon.getEntity(5, 4) != null);  
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 4); 
		
		player.attackUp();
		
		assertTrue(dungeon.getEntity(5, 4) == null); 
		
		
		// attack down
		enemy = new Enemy(dungeon, 5, 6);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(5, 6) instanceof Enemy);  
		assertTrue(dungeon.getEntity(5, 6) != null);  
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 6); 
		
		player.attackDown();
		
		assertTrue(dungeon.getEntity(5, 6) == null); 
		

	}
	
	/**
	 * The player should not be able to attack if no sword is in inventory
	 */
	@Test
	public void testNoSwordAttack() {
		
		this.player.setHasWeapon(false);
		
		// attack right
		Enemy enemy = new Enemy(dungeon, 6, 5);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(6, 5) instanceof Enemy);  
		assertTrue(dungeon.getEntity(6, 5) != null);  
		assertEquals(enemy.getX(), 6); 
		assertEquals(enemy.getY(), 5); 
		
		player.attackRight();
		
		assertTrue(dungeon.getEntity(6, 5) != null); 
		
		// attack left
		enemy = new Enemy(dungeon, 4, 5);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(4, 5) instanceof Enemy);  
		assertTrue(dungeon.getEntity(4, 5) != null);  
		assertEquals(enemy.getX(), 4); 
		assertEquals(enemy.getY(), 5); 
		
		player.attackLeft();
		
		assertTrue(dungeon.getEntity(4, 5) != null); 
		
		
		// attack up
		enemy = new Enemy(dungeon, 5, 4);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(5, 4) instanceof Enemy);  
		assertTrue(dungeon.getEntity(5, 4) != null);  
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 4); 
		
		player.attackUp();
		
		assertTrue(dungeon.getEntity(5, 4) != null); 
		
		
		// attack down
		enemy = new Enemy(dungeon, 5, 6);
		this.dungeon.addEntity(enemy);
		
		assertTrue(dungeon.getEntity(5, 6) instanceof Enemy);  
		assertTrue(dungeon.getEntity(5, 6) != null);  
		assertEquals(enemy.getX(), 5); 
		assertEquals(enemy.getY(), 6); 
		
		player.attackDown();
		
		assertTrue(dungeon.getEntity(5, 6) != null); 
		
		
		this.player.setHasWeapon(true);

		
	}
	
}
	
