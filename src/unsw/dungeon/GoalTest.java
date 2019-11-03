package unsw.dungeon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GoalTest {
	
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
		boulderPushHandler o3 = new boulderPushHandler(this.dungeon);
		player.attach(o3);
		
		dungeon.setPlayer(player);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		this.dungeon = null;
		this.player = null;
	}

	/**
	 * test exit goal 
	 */
	@Test
	void testExit() {
		
		Goal exitGoal = new Goal("exit");
		this.dungeon.setGoal(exitGoal);
		GoalCheckingObserver o4 = new GoalCheckingObserver(this.dungeon);
		player.attach(o4);
		
		
		Exit exit = new Exit(1, 1);
		this.dungeon.addEntity(exit);
		player.moveDown();
		assertFalse(dungeon.isLevelCompleted());
		player.moveRight();
		assertTrue(dungeon.isLevelCompleted());
		player.moveDown();
		assertTrue(dungeon.isLevelCompleted());
	}

	/**
	 * test boulder goal 
	 */
	@Test
	void testBoulder() {
		
		LogicalOperator lo = new LogicalOperator("AND");
		Goal goal1 = new Goal("exit");
		lo.add(goal1);
		Goal goal2 = new Goal("boulders");
		lo.add(goal2);
		this.dungeon.setGoal(lo);

		GoalCheckingObserver o4 = new GoalCheckingObserver(this.dungeon);
		player.attach(o4);		
		
		Exit exit = new Exit(2, 0);
		this.dungeon.addEntity(exit);
		
		Boulder boulder = new Boulder(1, 1);
		this.dungeon.addEntity(boulder);
		FloorSwitch floorSwitch = new FloorSwitch(1, 2);
		this.dungeon.addEntity(floorSwitch);
		
		//player cannot exit unless all goal satisfy
		player.moveRight();
		assertFalse(this.dungeon.isLevelCompleted());
		player.moveRight();
		assertFalse(this.dungeon.isLevelCompleted());
		
		//boulder goal satisfy, but not reached exit
		player.moveDown();		
		
		assertTrue(this.dungeon.canPlayerExit());
		assertFalse(this.dungeon.isLevelCompleted());
		
		//boulder pushed away, the goal is not satisfy
		player.moveDown();
		assertFalse(this.dungeon.canPlayerExit());
		assertFalse(this.dungeon.isLevelCompleted());
		
		//satisfy the goal again
		player.moveRight();
		player.moveDown();
		player.moveDown();
		player.moveLeft();
		player.moveUp();
		assertTrue(dungeon.canPlayerExit());
		assertFalse(dungeon.isLevelCompleted());
		
		// go to the exit
		player.moveRight();
		player.moveUp();
		player.moveUp();
		player.moveUp();
		assertTrue(dungeon.canPlayerExit());
		assertTrue(dungeon.isLevelCompleted());
		
	}
	
	/**
	 * test OR condition
	 * 
	 */
	void testOrBoulder() {
		LogicalOperator lo = new LogicalOperator("OR");
		Goal goal1 = new Goal("exit");
		lo.add(goal1);
		Goal goal2 = new Goal("boulders");
		lo.add(goal2);
		this.dungeon.setGoal(lo);

		GoalCheckingObserver o4 = new GoalCheckingObserver(this.dungeon);
		player.attach(o4);		
		
		Exit exit = new Exit(2, 0);
		this.dungeon.addEntity(exit);
		
		//player can always just go to the exit and win
		Boulder boulder = new Boulder(1, 1);
		this.dungeon.addEntity(boulder);
		FloorSwitch floorSwitch = new FloorSwitch(1, 2);
		this.dungeon.addEntity(floorSwitch);
		
		player.moveRight();
		assertFalse(this.dungeon.isLevelCompleted());
		assertTrue(this.dungeon.canPlayerExit());
		
		player.moveRight();
		assertTrue(this.dungeon.isLevelCompleted());
			
	}

	/** 
	 * test treasure goal
	 * 
	 */
	@Test
	void testTreasure() {
		LogicalOperator lo = new LogicalOperator("AND");
		Goal goal1 = new Goal("exit");
		lo.add(goal1);
		Goal goal2 = new Goal("treasure");
		lo.add(goal2);
		this.dungeon.setGoal(lo);
		
		GoalCheckingObserver o4 = new GoalCheckingObserver(this.dungeon);
		player.attach(o4);
		
		Exit exit = new Exit(2, 0);
		this.dungeon.addEntity(exit);
		
		// player needs to pick all treasures up to exit
		Treasure treasure = new Treasure(0, 1);
		dungeon.addEntity(treasure);
		treasure = new Treasure(1, 1);
		dungeon.addEntity(treasure);
		player.moveDown();
		assertFalse(this.dungeon.canPlayerExit());
		
		player.moveRight();
		assertTrue(this.dungeon.canPlayerExit());
		
		player.moveRight();
		player.moveUp();
		assertTrue(this.dungeon.isLevelCompleted());
		
	}
	
	/** 
	 * test complicate goal condition
	 * 
	 */
	@Test
	void testAdvance() {
		LogicalOperator and = new LogicalOperator("AND");
		Goal goal1 = new Goal("exit");
		and.add(goal1);
		
		LogicalOperator or = new LogicalOperator("OR");
		Goal goal2 = new Goal("treasure");
		or.add(goal2);
		Goal goal3 = new Goal("boulders");
		or.add(goal3);
		
		and.add(or);
		
		this.dungeon.setGoal(and);
		
		//add entities
		Exit exit = new Exit(2, 0);
		this.dungeon.addEntity(exit);		
		Treasure treasure = new Treasure(0, 1);
		dungeon.addEntity(treasure);
		treasure = new Treasure(1, 1);
		dungeon.addEntity(treasure);
		Boulder boulder = new Boulder(0, 2);
		this.dungeon.addEntity(boulder);
		FloorSwitch floorSwitch = new FloorSwitch(0, 3);
		this.dungeon.addEntity(floorSwitch);

		
		GoalCheckingObserver o4 = new GoalCheckingObserver(this.dungeon);
		player.attach(o4);
		
		// player needs to pick all treasures up to exit
		player.moveDown();
		assertFalse(this.dungeon.canPlayerExit());
		
		// player can satisfy one of the goal to exit
		
		player.moveDown();
		assertTrue(this.dungeon.canPlayerExit());
		
		player.moveDown();
		assertFalse(this.dungeon.canPlayerExit());
		
		player.moveRight();
		player.moveUp();
		player.moveUp();
		assertTrue(this.dungeon.canPlayerExit());
		
		
	}
}
