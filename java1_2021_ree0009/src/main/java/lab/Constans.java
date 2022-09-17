package lab;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Constans {
	
	public static final Image COIN_IMAGE;
	public static Point2D[] coinPos = {new Point2D(10,290), new Point2D(100,190), new Point2D(350,290), 
			new Point2D(560,290), new Point2D(100,100), new Point2D(560,30), new Point2D(500,380),
			new Point2D(200,330), new Point2D(300,250),new Point2D(400,150)};
	
	static{ 
		COIN_IMAGE = new Image(Constans.class.getResourceAsStream("coin.gif"));
	}
}
