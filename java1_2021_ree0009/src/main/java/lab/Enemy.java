package lab;

import java.util.Random;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Enemy implements DrawableSimulable, Touchable {
	private Point2D position;
	private Point2D size = new Point2D(10, 3);
	private Point2D speed;
	private World world;
	private Random rnd = new Random();
	private HitListener hitlistener = new EmptyHitListener();

	public Enemy(World world) {
		this.world = world;
		int generate_rnd = rnd.nextInt(2);
		if (generate_rnd == 0) {
			this.position = new Point2D(world.getWidth(), rnd.nextInt((int) world.getHeight()));
			this.speed = new Point2D(-1 * (rnd.nextInt(100) + 100), 0);
		} else {
			this.position = new Point2D(0, rnd.nextInt((int) world.getHeight()));
			this.speed = new Point2D((rnd.nextInt(100) + 100), 0);
		}
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.DARKGREY);
		gc.fillRect(position.getX(), position.getY(), size.getX(), size.getY());

	}

	@Override
	public void simulate(double deltaT) {
		position = position.add(speed.multiply(deltaT));

	}

	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return new BoundingBox(position.getX(), position.getY(), size.getX(), size.getY());
	}

	@Override
	public Point2D getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isTouching(Touchable other) {
		hitlistener.hit();
	}

	@Override
	public void isTouching(Touchable a, Touchable other) {
		// TODO Auto-generated method stub

	}
	
	public void setHitListener(HitListener newhitlistener) {
		hitlistener = newhitlistener;
		
	}
	public Point2D getPosition() {
		return position;
	}

}
