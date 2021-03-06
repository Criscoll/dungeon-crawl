package unsw.dungeon;

public class InvincibilityState extends EnemyState {
	
	public InvincibilityState(Enemy enemy) {
		super(enemy); 
	}
	
	public void move(int pX, int pY, Dungeon dungeon) {
	
		
		
		int eX = this.enemy.getX(); 
		int eY = this.enemy.getY();
		
		// case 1: already next to eachother
		
		if (pX == eX && pY == eY) this.enemy.killEnemy();
		

		
		// case 2: on the same row
		if (pY == eY) {
			if (pX > eX) { 
				if (!(dungeon.isObstructed((eX - 1), eY))) {this.enemy.moveLeft(); return; }
				else if (!(dungeon.isObstructed(eX, (eY + 1)))) { this.enemy.moveDown(); return;} 
				else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 
			}
			else {
				if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveRight(); return; }
				else if (!(dungeon.isObstructed(eX, (eY + 1)))) { this.enemy.moveDown(); return;} 
				else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 
			}
		}
		
		if (pX == eX) {
			if (pY > eY) { 
				if (!(dungeon.isObstructed(eX, (eY - 1)))) {this.enemy.moveUp(); return; }
				else if (!(dungeon.isObstructed((eX - 1), eY))) { this.enemy.moveLeft(); return;} 
				else if (!(dungeon.isObstructed((eX + 1), eY))) { this.enemy.moveRight(); return;} 
			}
			else {
				if (!(dungeon.isObstructed(eX, (eY + 1)))) {this.enemy.moveDown(); return; }
				else if (!(dungeon.isObstructed((eX - 1), eY))) { this.enemy.moveLeft(); return;} 
				else if (!(dungeon.isObstructed((eX + 1), eY))) { this.enemy.moveRight(); return;} 
			}
		}		
		
		// case 3: not on the same row
		
		if (pX > eX && pY < eY) { // north east of enemy
			if (!(dungeon.isObstructed((eX - 1), eY))) {this.enemy.moveLeft(); return; }
			else if (!(dungeon.isObstructed(eX, (eY + 1)))) { this.enemy.moveDown(); return;} 
		}
		
		if (pX < eX && pY < eY) { // north west of enemy
			if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveRight(); return; }
			else if (!(dungeon.isObstructed(eX, (eY + 1)))) { this.enemy.moveDown(); return;} 
		}
		
		if (pX > eX && pY > eY) { // South east of enemy
			if (!(dungeon.isObstructed((eX - 1), eY))) {this.enemy.moveLeft(); return; }
			else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 
		}
		
		if (pX < eX && pY > eY) { // South west of enemy
			if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveRight(); return; }
			else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveUp(); return;} 
		}

		// pick unobstructed area to move to
		if (!(dungeon.isObstructed((eX + 1), eY))) {this.enemy.moveLeft(); return; }
		else if (!(dungeon.isObstructed(eX, (eY - 1)))) { this.enemy.moveDown(); return;} 		
		else if (!(dungeon.isObstructed((eX + 1), eY ))) { this.enemy.moveRight(); return;} 
		else {this.enemy.moveUp(); return;} 	
		
		
	}

}
