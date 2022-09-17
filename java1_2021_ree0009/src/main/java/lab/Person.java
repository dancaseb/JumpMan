package lab;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Person implements DrawableSimulable, Touchable {

	private Point2D position;
	private Point2D size;
	private World world;
	private Image image;
	public Point2D speed = new Point2D(0, 0);
	private Point2D acceleration = new Point2D(0, 9.81);
	public boolean isOnGround = false;
	public boolean isJumping = true;
	public boolean isHoldingRope = false;
	public boolean isOnLadder = false;
	public int lives = 5;
	private ImageView imageView = new ImageView();
	private int velocity = 150;
	private Point2D defaultPosition;
	
	//private boolean change = false;
	private Images images = new Images();

	// private String imagePath = "JumpManRight.png" ;

	public Person(World world, Point2D position, Point2D size) {
		this.world = world;
		this.position = position;
		this.size = size;
		this.defaultPosition =  new Point2D(10,world.getHeight()-20);
		 imageView.setImage(Images.images[0]);
		 

	}

	public void draw(GraphicsContext gc) {
		
		
		gc.drawImage(imageView.getImage(), position.getX(), position.getY(), size.getX(), size.getY());
	}

	public void simulate(double deltaT) {
		
		
		 if(isJumping == true && isOnLadder == true) {
			//stop();
		 }
		
		
		 if (isHoldingRope == true) {
			
			position = position.add(speed.multiply(deltaT));

		} else if (isOnGround == true && isJumping == false) {
		
			position = position.add(speed.multiply(deltaT));
		}

		else if (isJumping == true && isHoldingRope == false && isOnGround == false && isOnLadder == false) {

			speed = speed.add(acceleration);

			position = position.add(speed.multiply(deltaT));

		} else if (isOnLadder == true) {
			position = position.add(speed.multiply(deltaT));
		}

	}

	public BoundingBox getBoundingBox() {
		return new BoundingBox(position.getX(), position.getY(), size.getX(), size.getY());

	}

	public BoundingBox getBottomBoundingBox() {
		return new BoundingBox(position.getX(), position.getY() + size.getY(), size.getX(), 1);
	}

	// je postava na zemi
	public boolean isOnGround() {
		if (isOnGround) {
			return true;
		}
		return false;
	}

	public void setSpeed(Point2D speed) {
		
		images.changeDirection(speed);
	
		
		 if (isHoldingRope == true && isOnGround == false) {
			 
			
			this.speed = setPoint(speed.getX() * 0, velocity* speed.getY());

		} else if (isOnGround == true && speed.getY() == 0) {
			
			this.speed = speed.multiply(velocity);
		} else if (speed.getX() == 0 && speed.getY() == 0 && isJumping == false) {

			this.speed = speed.multiply(velocity);
		} else if (isOnLadder == true && isOnGround == true && speed.getX() != 0) {

			this.speed = setPoint(speed.getX(), 0);
		} else if (isOnLadder == true && isOnGround == false) {

			this.speed = setPoint(speed.getX() * 0, velocity*speed.getY());
		} else if (isOnGround == true && isOnLadder == true && speed.getY() != 0) {

			this.speed = setPoint(0, velocity*speed.getY());
		}
		

	}

	public void setJump(Point2D speed) {
		images.changeDirection(speed);
		if ((isOnGround == true && isJumping == false)|| (isHoldingRope == true) || (isOnLadder == true)) {
		
			this.speed = setPoint(1.5*velocity * speed.getX() * Math.cos(0.78), 1.5*velocity * speed.getY() * Math.sin(0.9));
			isJumping = true;
			isOnGround = false;
		}

	}

	// funkce ke stabilizaci panacka na platforme, ziska, jak moc panacek zapadl do
	// platformy a vrati ho na platformu
	public void setGroundLevel(Point2D pos) {

		position = position.add(0, pos.getY() - position.getY() - 25);


	}
	public void setClimbingLadderMiddle(Point2D pos) {
		position = position.add(-position.getX() + pos.getX(),0);
	}
	public void setClimbingRopeMiddle(Point2D pos) {
		position = position.add(-position.getX() + pos.getX()-size.getX()/2,0);
	}

	@Override
	public Point2D getCoordinates() {
		
		return position;
	}


	private void stop() {
		if (isJumping == true) {
			speed = speed.multiply(0);
			isJumping = false;
		} else if (isHoldingRope == true) {

			speed = speed.multiply(0);
		}
	}

	public void setAir() {
		isJumping = true;
		isOnGround = false;
		isHoldingRope = false;
		isOnLadder = false;
	}

	@Override
	public void isTouching(Touchable other) {
		if (other instanceof Platform) {
			isOnGround = true;

			stop();
			isJumping = false;
			isHoldingRope = false;
			isOnLadder = false;

		} else if (other instanceof Rope) {
			isHoldingRope = true;
			isJumping = false;
			isOnGround = false;
			isOnLadder = false;

		} else if (other instanceof Ladder) {
			isOnLadder = true;
			isHoldingRope = false;
			isOnGround = false;

		}

	}

	private Point2D setPoint(double x, double y) {
		return new Point2D(x, y);
	}

	@Override
	public void isTouching(Touchable a, Touchable other) {
		if (other instanceof Ladder) {
			isOnGround = true;
			isOnLadder = true;

		}

	}
	
	public void changeAnimation() {
		
		imageView.setImage(images.changeAnimation());
		
		
	}
	public void setDefaultPos() {
		
		this.position = defaultPosition;
	}

}
