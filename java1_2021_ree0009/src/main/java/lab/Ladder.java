package lab;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ladder implements DrawableSimulable, Touchable {
	
	private World world; 
	private Point2D position; 
	private Point2D size;
	
	public Ladder(World world, Point2D position, Point2D size) {
		this.world = world;
		this.position = position;
		this.size = size;
	}

	@Override
	public BoundingBox getBoundingBox() {
		return new BoundingBox(position.getX(), position.getY(), size.getX(), size.getY());
	}

	@Override
	public Point2D getCoordinates() {
		return position;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.save();
		gc.setFill(Color.BLUE);
		gc.fillRect(position.getX(), position.getY(), size.getX()/5, size.getY());
		gc.fillRect(position.getX()+size.getX()*4/5, position.getY(), size.getX()/5, size.getY());
		for ( int i = (int) position.getY(); i < position.getY() + size.getY();) {
			gc.fillRect(position.getX(), i, size.getX(), 5);
			i +=10;
		}
		
	}

	@Override
	public void simulate(double deltaT) {
		// TODO Auto-generated method stub
		
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
