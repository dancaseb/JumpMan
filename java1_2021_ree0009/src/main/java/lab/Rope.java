package lab;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rope implements DrawableSimulable, Touchable{
	private World world;
	private Point2D position;
	private int length;
	private int size = 5;
	
	public Rope(World world, Point2D position, int length) {
		this.world = world;
		this.position = position;
		this.length = length;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.save();
		gc.setFill(Color.BROWN);
		gc.fillRect(position.getX(), position.getY(), 2, length);
		
	}

	@Override
	public void simulate(double deltaT) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(position.getX(),position.getY(), size,length);
	
	}

	@Override
	public Point2D getCoordinates() {
		return position;
	}

	@Override
	public void isTouching(Touchable other) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void isTouching(Touchable a, Touchable other) {
		// TODO Auto-generated method stub
		
	}

}
