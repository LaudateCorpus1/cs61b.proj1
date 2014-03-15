
public class Run {
	private int numPix;
	private int red;
	private int green;
	private int blue;
	
	public Run(){
		numPix = 0;
		red = 0;
		green = 0;
		blue = 0;
	}
	
	public Run(int numPix, int red, int green, int blue){
		this.numPix = numPix;
		this.red = red; 
		this.green = green;
		this.blue = blue;
	}
	
	public Run(int numPix, Run run){
		this.numPix = numPix;
		red = run.red;
		green = run.green;
		blue = run.blue;
	}

	public int getNumPix() {
		return numPix;
	}

	public int getRed() {
		return red;
	}

	public int getGreen() {
		return green;
	}

	public int getBlue() {
		return blue;
	}

	public void setNumPix(int numPix) {
		this.numPix = numPix;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public void setRun(int numPix, int red, int green, int blue){
		this.numPix = numPix;
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	
	public void incNumPix(){
		numPix++;
	}
	
	public void incNumPix(int i){
		numPix += i;
	}
	
	public void decNumPix(){
		numPix--;
	}
}
