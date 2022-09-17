package lab;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Platform implements DrawableSimulable, Touchable {
	private World world;
	private Point2D position;
	private Point2D size;
	
	public Platform(World world, Point2D position, Point2D size) {
		this.world = world;
		this.position = position;
		this.size = size;
		
	}
	
	public void draw(GraphicsContext gc) {
		gc.save();
		gc.setFill(Color.GREEN);
		gc.fillRect(position.getX(), position.getY(), size.getX(), size.getY());
		gc.setFill(Color.BLACK);
		
		
		
		
		
	
	}
	
	
	public BoundingBox getBoundingBox() {
		return new BoundingBox(position.getX(),position.getY(), size.getX(),size.getY());
		
	}

	@Override
	public void simulate(double deltaT) {
		// TODO Auto-generated method stub
		
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
