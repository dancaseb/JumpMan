package lab;

import java.util.ArrayList;
import java.util.Iterator;


import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class World {

	private double width;
	private double height;
	private Person person;
	private boolean isTouchingTwoObject = false;
	private boolean isTouching = false;
	// private Point2D pos = new Point2D(rnd.nextInt(100),rnd.nextInt(100));
	private Point2D size = new Point2D(25, 25);
	private DrawableSimulable[] entities;
	// private ArrayList<Coin> coins = new ArrayList<Coin>();
	// private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Coin> coins_temp = new ArrayList<Coin>();
	private ArrayList<DrawableSimulable> coins_enemies = new ArrayList<DrawableSimulable>();
	private int frameGenerateEnemy = 0;
	private int points = 0;

	
	private GameListener gamelistener = new EmptyGameListener();

	public World(double width, double height) {
		super();
		this.width = width;
		this.height = height;

		entities = new DrawableSimulable[] { new Platform(this, setPoint(0, height - 10), setPoint(width, 10)),
				new Platform(this, setPoint(250, 200), setPoint(120, 10)),
				new Platform(this, setPoint(70, 110), setPoint(120, 10)), new Rope(this, setPoint(180, 120), 50),
				new Platform(this, setPoint(0, 300), setPoint(80, 10)),
				new Platform(this, setPoint(300, 300), setPoint(60, 10)),
				new Platform(this, setPoint(100, 200), setPoint(60, 10)),
				new Platform(this, setPoint(260, 110), setPoint(60, 10)),
				new Platform(this, setPoint(360,90), setPoint(60, 10)),
				new Platform(this, setPoint(440,70), setPoint(60, 10)),
				new Platform(this, setPoint(520,50), setPoint(60, 10)),
				new Platform(this, setPoint(540,300), setPoint(60, 10)),
				new Platform(this, setPoint(450,200), setPoint(120, 10)),
				new Ladder(this, setPoint(300, 110), setPoint(25, 280)),
				new Rope(this, setPoint(130, 210), 150),
				new Rope(this, setPoint(260, 120), 50),
				new Ladder(this, setPoint(50, 300), setPoint(25, 90)),
				new Ladder(this, setPoint(550, 200), setPoint(25, 90))};
		for (int i = 0; i < Constans.coinPos.length; i++) {
	
			coins_enemies.add(new Coin(this, Constans.coinPos[i]));
		
		}

		person = new Person(this, setPoint(5,height-20), size);

	}

	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);

		for (DrawableSimulable ds : entities) {
			ds.draw(gc);
		}
		for (DrawableSimulable ce : coins_enemies) {
			ce.draw(gc);
		}
		/*
		 * for(Coin i: coins) { i.draw(gc); } for(Enemy i: enemies) { i.draw(gc); }
		 */
		person.draw(gc);

	}

	public void simulate(double deltaT) {
		isTouchingTwoObject = false;
		isTouching = false;
		coins_temp.clear();
		if(person.getCoordinates().getX()<-50 || person.getCoordinates().getX()>width) {
		
			hit();
			}
		person.simulate(deltaT);
		for (DrawableSimulable ds : entities) {
			
			ds.simulate(deltaT);
			if (ds instanceof Touchable) {
				Touchable touch = (Touchable) ds;
				if (person.getBoundingBox().intersects(touch.getBoundingBox())) {
					isTouching = true;
					for (DrawableSimulable dsother : entities) {
						if (dsother instanceof Touchable && dsother != touch) {
							Touchable touch1 = (Touchable) dsother;
							if (person.getBoundingBox().intersects(touch.getBoundingBox())
									&& person.getBoundingBox().intersects(touch1.getBoundingBox())) {
								isTouchingTwoObject = true;
								person.isTouching(touch1, touch);

							}
						}
					}
					if (isTouchingTwoObject == false && isTouching == true) {
						person.isTouching(touch);
						if (touch instanceof Platform) {
							person.setGroundLevel(touch.getCoordinates());
						}else if(touch instanceof Ladder  ) {
							person.setClimbingLadderMiddle(touch.getCoordinates());
						}else if(touch instanceof Rope) {
							person.setClimbingRopeMiddle(touch.getCoordinates());
						}
								

					}
				}

			}
		}
		// System.out.println(isTouchingTwoObject +"" + isTouching);
		if (isTouchingTwoObject == false && isTouching == false) {
			person.setAir();
		}
		for (DrawableSimulable ce : coins_enemies) {
			if (ce instanceof Enemy) {
				((Enemy) ce).setHitListener(this::hit);
			}
		}
		/*
		 * for (Enemy e : enemies) { e.setHitListener(this::hit); }
		 */
		for (Iterator<DrawableSimulable> iterator = coins_enemies.iterator(); iterator.hasNext();) {
			DrawableSimulable temp = iterator.next();
			
			temp.simulate(deltaT);
			if (temp instanceof Enemy) {
				Enemy e = (Enemy) temp;
				if (person.getBoundingBox().intersects(e.getBoundingBox())) {
					e.isTouching(person);
					iterator.remove();
				}
				if (e.getPosition().getX() > width || e.getPosition().getX() < 0) {
					iterator.remove();
				}
			}else if(temp instanceof Coin) {
				
				Coin coin = (Coin) temp;
				coins_temp.add(coin);
				if(person.getBoundingBox().intersects(coin.getBoundingBox())) {
					points+=100;
					gamelistener.changeStats(points, person.lives);
					iterator.remove();
				}
				
			}
			
		}
		/*if(coins_temp.removeIf(e->(person.getBoundingBox().intersects(e.getBoundingBox()))))
		{
			points+=100;
			gamelistener.changeStats(points, person.lives);
		}
		coins_temp.clear();*/
		/*for (Iterator<Enemy> iterator = coins_enemies.iterator(); iterator.hasNext();) {

			Enemy e = iterator.next();
			e.simulate(deltaT);
			if (person.getBoundingBox().intersects(e.getBoundingBox())) {
				e.isTouching(person);
				iterator.remove();
			}
			if (e.getPosition().getX() > width || e.getPosition().getX() < 0) {
				iterator.remove();
			}
		}
		if (coins.removeIf(e -> (person.getBoundingBox().intersects(e.getBoundingBox())))) {
			points += 100;

			gamelistener.changeStats(points, person.lives);
		}*/

	}

	private Point2D setPoint(double x, double y) {
		return new Point2D(x, y);
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setSpeed(double x, double y) {

		person.setSpeed(setPoint(x, y));

	}

	public void jump(int x, int y) {
		person.setJump(setPoint(x, y));

	}

	public void setGameListener(GameListener gameListenerImpl) {
		this.gamelistener = gameListenerImpl;
	}

	// vola se co pet snimku
	public void changeFrame() {
		frameGenerateEnemy++;
		if (frameGenerateEnemy == 20) {
			coins_enemies.add(new Enemy(this));

			frameGenerateEnemy = 0;
		}
		person.changeAnimation();

	}

	private void hit() {

		person.lives--;
		if(person.lives <0) {
			
			gamelistener.gameOver();
		}
		gamelistener.changeStats(points, person.lives);
		person.setDefaultPos();

	}

}
