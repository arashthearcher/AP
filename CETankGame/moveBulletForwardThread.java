package CETankGame;

public class moveBulletForwardThread extends Thread {

	Game game;
	Bullet bullet;
	public moveBulletForwardThread(Bullet bullet) {

		this.bullet = bullet;
		this.game = bullet.game;
		
	}
	
	
	@Override
	public void run() {
		while(bullet.isAlive())
		{
			
			bullet.moveForward();
			game.BulletIsotopicCheck();
			game.checkBulletsOutofBound();
			game.bulletVSBoxandTrap();
			game.bulletVSwall();
			game.tankVSbullet();
			bullet.updateBulletLocation();
			
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
		
	}

}
