package lab;

public class FrameCounter {
	
	public double frameCounter = 0; 
	
	private void counter(double frameCounter) {
		this.frameCounter += frameCounter;
		
	}
	
	public boolean checkCounter(double d) {
		counter(d);
		if(this.frameCounter >= 0.1) {
			this.frameCounter = 0;
			return true;
		}

		return false;
		
		
	}

}
