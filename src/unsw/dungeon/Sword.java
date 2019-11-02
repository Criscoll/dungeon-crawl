package unsw.dungeon;

public class Sword extends Entity{
	private int durability; 

	
	public Sword(int x, int y) {
		super(x, y);
		this.setDurability(5);
		setPickupable(true);
	}

	public void attack() {
		this.setDurability(this.getDurability() - 1);
	}

	public int getDurability() {
		return durability;
	}

	public void setDurability(int durability) {
		this.durability = durability;
	}
	
	
}
