package unsw.dungeon;

public class NormalState extends EnemyState {
	
	public NormalState(Enemy enemy) {
		super(enemy); 
	}
	
	public void move(int pX, int pY, Dungeon dungeon) {
	
		
		int eX = this.enemy.getX(); 
		int eY = this.enemy.getY();
		
		// case 1: already next to eachother
		
		if (pX == eX && pY == eY) dungeon.killPlayer();
		
		// case 2: on the same row
		if (pY == eY) {
			if (pX > eX) { this.enemy.moveRight(); return;} 
			else if (pX < eX){this.enemy.moveLeft(); return; } 
			else return; 
		}
		
		if (pX == eX) {
			if (pY > eY) { this.enemy.moveDown(); return;} 
			else if (pY < eY){this.enemy.moveUp(); return; } 
			else return; 
		}		
		
		// case 3: not on the same row
		
		if (pX > eX && pY < eY) { // north east of enemy
			if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveRight(); return; }
			else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 
		}
		
		if (pX < eX && pY < eY) { // north west of enemy
			if (!(dungeon.isObstructed((eX - 1), eY))) {this.enemy.moveLeft(); return; }
			else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 
		}
		
		if (pX > eX && pY > eY) { // South east of enemy
			if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveRight(); return; }
			else if (!(dungeon.isObstructed(eX, (eY + 1)))) { this.enemy.moveDown(); return;} 
		}
		
		if (pX < eX && pY > eY) { // South west of enemy
			if (!(dungeon.isObstructed((eX - 1), eY))) {this.enemy.moveLeft(); return; }
			else if (!(dungeon.isObstructed(eX, (eY + 1)))) { this.enemy.moveDown(); return;} 
		}

		// pick unobstructed area to move to
		if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveRight(); return; }
		else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 		
		else if (!(dungeon.isObstructed((eX + 1), eY ))) { this.enemy.moveLeft(); return;} 
		else {this.enemy.moveDown(); return;} 	
		
		
	}

}
