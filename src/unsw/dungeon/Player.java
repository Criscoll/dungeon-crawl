package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public void moveUp() {
    	if (dungeon.isObstructed(getX(), getY() - 1)) return;
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
    	if (dungeon.isObstructed(getX(), getY() + 1)) return;
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	if (dungeon.isObstructed(getX() - 1, getY())) return;
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
    	if (dungeon.isObstructed(getX() + 1, getY())) return;
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
}
