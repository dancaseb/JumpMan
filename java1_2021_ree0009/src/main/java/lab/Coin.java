package lab;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class Coin implements DrawableSimulable, Touchable {
	private World world;
	private Point2D position;
	private HitListener coinCollectListener = new EmptyHitListener(); 
	private int size = 10;
	
	public Coin(World world, Point2D position) {
		this.world = world;
		this.position = position;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.save();
		gc.drawImage(Constans.COIN_IMAGE, position.getX(), position.getY(), size, size);
		
	}

	@Override
	public void simulate(double deltaT) {
		// TODO Auto-generated method stub
		
	}
	
	public void setCoinListener(HitListener coinCollectImpl) {
		coinCollectListener = coinCollectImpl;
		
	}
	

	@Override
	public BoundingBox getBoundingBox() {
		// TODO Auto-generated method stub
		return new BoundingBox(position.getX(), position.getY(), size,size);
	}

	@Override
	public Point2D getCoordinates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void isTouching(Touchable other) {
		// coinCollectListener.coinCollect();
		
	}

	@Override
	public void isTouching(Touchable a, Touchable other) {
		// TODO Auto-generated method stub
		
	}


}
