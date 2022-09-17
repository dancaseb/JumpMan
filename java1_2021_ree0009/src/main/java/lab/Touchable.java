package lab;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

public interface Touchable {
	
	BoundingBox getBoundingBox();
	
	Point2D getCoordinates();
	
	
	void isTouching(Touchable other);
	void isTouching(Touchable a, Touchable other);
}
