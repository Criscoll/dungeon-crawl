package unsw.dungeon;

public class Fireball extends Entity{

	private Dungeon dungeon;
	
	public Fireball(int x, int y, Dungeon dungeon) {
		super(x, y);  
        this.dungeon = dungeon;
	}

	public boolean moveUp() {
    	if (dungeon.isObstructed(getX(), getY() - 1) || getY() <= 0)
    		return false;
        y().set(getY() - 1);
        return true;
    }

    public boolean moveDown() {
    	if (dungeon.isObstructed(getX(), getY() + 1) || getY() >= dungeon.getHeight() - 1) 
    		return false;
    	y().set(getY() + 1);
    	return true;
    }

    public boolean moveLeft() {
    	
    	if (dungeon.isObstructed(getX() - 1, getY()) || getX() <= 0) 
    		return false;
        x().set(getX() - 1);
        return true;
    }

    public boolean moveRight() {
    	if (dungeon.isObstructed(getX() + 1, getY()) || getX() >= dungeon.getWidth() - 1) 
    		return false;    
        x().set(getX() + 1);
        return true;
    }
}
