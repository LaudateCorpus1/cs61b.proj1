/**
 *  This class represents a pixel, which has a red, green, and a blue intensity in the range
 *  0...255
 */
public class Pixel {
	private short red;
	private short green;
	private short blue;
	
	private static short MAX_VALUE = 255;
	private static short MIN_VALUE = 0; 
	
	public Pixel(){
		red = MAX_VALUE;
		green = MAX_VALUE;
		blue = MAX_VALUE;
	}
	
	public Pixel(short red, short green, short blue){
		if(validPixel(red, green, blue)){
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
	}
	
	/**
	 * validPixel() checks that the values of a pixel are in
	 * the range of MAX_VALUE and MIN_VALUE
	 */
	public boolean validPixel(short red, short green, short blue){
		return(red >= MIN_VALUE && red <= MAX_VALUE) &&
			  (green >= MIN_VALUE && green <= MAX_VALUE) &&
			  (blue >= MIN_VALUE && blue <= MAX_VALUE);
	}
	
	/**
	 * setPixRGB() sets a pixel's red, green, and blue values to params.
	 * 
	 * If any of the three color intensities is NOT in the range 0...255, then
	 * this method does nothing.
	 * 
	 */
	public void setPixRGB(short red, short green, short blue){
		if(validPixel(red, green, blue)){
			this.red = red;
			this.green = green;
			this.blue = blue;
		}
	}
	
	public short getRed(){
		return red;
	}

	public short getGreen() {
		return green;
	}

	public short getBlue() {
		return blue;
	}
}