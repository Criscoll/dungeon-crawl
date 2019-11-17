package unsw.dungeon;

import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class AttackHandler implements AttackObserver{
	
	private Dungeon dungeon; 
//	private Player player;
	
	public AttackHandler(Dungeon dungeon) {
		this.dungeon = dungeon; 
//		this.player = dungeon.getPlayer();
	}
	
	
	public void update(int x, int y, Player player) {

	
		List<Enemy> enemies = dungeon.getEnemies();

		if(player.getWeapon() instanceof Sword) {			
			for (Enemy enemy : enemies) {
				if (enemy.getX() == x && enemy.getY() == y) {
					this.dungeon.removeEntity(enemy); 
				}	
			}
		}
		else if(player.getWeapon() instanceof Wand && !dungeon.isObstructed(x, y)) {
			((Wand)player.getWeapon()).attack();
			Image fireballImage = new Image("/fireball.png");
			ImageView view = new ImageView(fireballImage);
			Fireball fireball = new Fireball(x, y, dungeon);
			DungeonControllerLoader.trackPosition(fireball, view);
			fireball.setView(view);
			dungeon.addEntity(fireball);
			view.setFitHeight(32);
			view.setFitWidth(32);
			Timeline timeline = new Timeline();
			timeline.setCycleCount(20);
			KeyFrame keyFrame = null;
			
			// create different keyframe for different attack
			//attck above
			if(x == player.getX() && y == player.getY()-1) {
				
				keyFrame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(dungeon.getEntities().contains(fireball) && !fireball.moveUp()) {
							for(Enemy enemy: enemies) {
								if(fireball.getX() == enemy.getX() && fireball.getY()-1 == enemy.getY()) {
									dungeon.removeEntity(enemy);
									break;
								}
							}
							dungeon.removeEntity(fireball);
						}											
					}
				});				
			}
			//attck blow
			else if(x == player.getX() && y == player.getY()+1) {
				keyFrame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(dungeon.getEntities().contains(fireball) && !fireball.moveDown()) {
							for(Enemy enemy: enemies) {
								if(fireball.getX() == enemy.getX() && fireball.getY()+1 == enemy.getY()) {
									dungeon.removeEntity(enemy);
									break;
								}
							}
							dungeon.removeEntity(fireball);
						}											
					}
				});	
			}
			//attck left
			else if(x == player.getX() - 1 && y == player.getY()) {
				keyFrame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(dungeon.getEntities().contains(fireball) && !fireball.moveLeft()) {
							for(Enemy enemy: enemies) {
								if(fireball.getX()-1 == enemy.getX() && fireball.getY() == enemy.getY()) {
									dungeon.removeEntity(enemy);
									break;
								}
							}
							dungeon.removeEntity(fireball);
						}											
					}
				});	
			}
			//attck right
			else if(x == player.getX() + 1 && y == player.getY()){
				keyFrame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						if(dungeon.getEntities().contains(fireball) && !fireball.moveRight()) {
							for(Enemy enemy: enemies) {
								if(fireball.getX()+1 == enemy.getX() && fireball.getY() == enemy.getY()) {
									dungeon.removeEntity(enemy);
									break;
								}
							}
							dungeon.removeEntity(fireball);
						}											
					}
				});	
			}
				
			timeline.getKeyFrames().clear();
			timeline.getKeyFrames().add(keyFrame);
			timeline.play();
		}
		
	}
}
