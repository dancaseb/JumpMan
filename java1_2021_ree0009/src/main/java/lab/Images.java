package lab;


import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Images {
	
	public static Image[] images = new Image[9];

	private int index0; 
	private int index1;
	private int currentIndex;
	
	
	
	public Images(){
		images[0] =  new Image(Images.class.getResourceAsStream("stand.png"), 100, 100, false, false);
		images[1] =  new Image(Images.class.getResourceAsStream("Right0.png"), 100, 100, false, false);
		images[2] = new Image(Images.class.getResourceAsStream("Right1.png"), 100, 100, false, false);
		images[3] = new Image(Images.class.getResourceAsStream("JMleft.png"), 100, 100, false, false);
		images[4] = new Image(Images.class.getResourceAsStream("JMleft1.png"), 100, 100, false, false);
		images[5] = new Image(Images.class.getResourceAsStream("JMup1.png"), 100, 100, false, false);
		images[6] = new Image(Images.class.getResourceAsStream("JMup.png"), 100, 100, false, false);
		images[7] = new Image(Images.class.getResourceAsStream("JMjumpright.png"), 100, 100, false, false);
		images[8] = new Image(Images.class.getResourceAsStream("JMjumleft.png"), 100, 100, false, false);
	}
	
	public void changeDirection(Point2D speed) {
		int direction = 0;
		
		 if(speed.getX() == 1 && speed.getY() == 0) {
			direction = 1;
		}else if(speed.getX()==0 && speed.getY() ==0) {
			direction =0;
		}else if(speed.getX() == -1 && speed.getY() == 0){
			direction = 2;
		}else if((speed.getX() == 0 && speed.getY() == -1)||(speed.getX() == 0 && speed.getY() ==1)) {
			direction = 3;
		}else if(speed.getX()==1 && speed.getY() ==-1 ) {
			direction = 4;
		}else if(speed.getX()==-1 && speed.getY() ==-1 ) {
			direction = 5;
		}
		
		setAvailableImages(direction);
		
	}
	
	private void setAvailableImages(int direction) {
		
		switch(direction) {
		case 1:
			this.index0 = 1;
			this.index1 = 2;
			break;
		case 0:
			this.index0 = 0;
			this.index1 = 0;
			break;
		case 2:
			this.index0 = 3;
			this.index1 = 4;
			break;
		case 3:
			this.index0 = 5;
			this.index1 = 6;
			break;
		case 4:
			this.index0 = 7;
			this.index0 = 7;
			break;
		case 5:
			this.index0 = 8;
			this.index0 = 8;
			break;
			

		}
		
		
	}
	
	public Image changeAnimation() {
		
		if(currentIndex == index1) {
			currentIndex = index0;
			return images[index0];
			
		}
		currentIndex = index1;
		return images[index1];
		
		
		
		
	}
	
	
	
	
	//public static Image right0 = new Image(Images.class.getResourceAsStream("Right0.png"), 100, 100, false, false);
	//public static Image right1 = new Image(Images.class.getResourceAsStream("Right1.png"), 100, 100, false, false);
	/*
	public Images() {
		this.image = new Image(Images.class.getResourceAsStream("JumpManRight.png"));
		//this.image = new Image(Images.class.getResourceAsStream("JumpManRight.png"), 100, 100, false, false);
	}*/
	/*
	static {
		right0 = new Image(Images.class.getResourceAsStream("Right0.png"), 100, 100, false, false);
		right1 = new Image(Images.class.getResourceAsStream("Right1.png"), 100, 100, false, false);
		
	}*/
		
	

}
