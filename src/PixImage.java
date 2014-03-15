/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */

	private Pixel[][] image;
	private int width;
	private int height;

	private static int[][] CONV_MAT_GX = new int[][] {{1,0,-1},
													  {2,0,-2},
													  {1,0,-1}};
	private static int[][] CONV_MAT_GY = new int[][] {{1,2,1},
		   											  {0,0,0},
		   											  {-1,-2,-1}};
  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
	if(width > 0 && height > 0){
		image = new Pixel[height][width];
	}
	else{
		image = new Pixel[1][1];
	}
	//I interpreted "blank" to mean all white
	for(int i = 0; i < height; i++){
		for(int j = 0; j < width; j++){
			image[i][j] = new Pixel();
		}
	}
	this.width = width;
	this.height = height;
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return image[y][x].getRed();
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return image[y][x].getGreen();
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return image[y][x].getBlue();
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
	  image[y][x].setPixRGB(red, green, blue);//Pixel class does the RGB validity check
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
	String pixString = "PixImage: " + width + "x" + height + "\n\t";
	int numPixPerLine = width;
	int k = 0;
	for(int i = 0; i < width; i++){
		for(int j = 0; j < height; j++){
			if(k >= numPixPerLine){
				pixString += "\n\t";
				k = 0;
			}
			pixString += "(" + i + "," + j + ")" + ": " + "(" + getRed(i,j) + 
					", " + getGreen(i,j) + ", " + getBlue(i,j) + ") ";
			k++;
		}
	}
	pixString += "\n";
    return pixString;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
	if(numIterations <= 0){
		return this;
	}
	PixImage blurImage = new PixImage(this.width, this.height);
	
	for(int i = 0; i < height; i++){
		for(int j = 0; j < width; j++){
			blurPix(j, i, blurImage);
		}
	}
	
	return blurImage.boxBlur(numIterations-1);
  }
  
  public void blurPix(int x, int y, PixImage blurImage){
	  char posType = positionType(x,y);
	  int[] loopIndicies = neighborLoopIndecies(posType);
	  
	  int widthStart = loopIndicies[0];
	  int widthEnd = loopIndicies[1];
	  int heightStart = loopIndicies[2];
	  int heightEnd = loopIndicies[3];
	  int numNeighbors = loopIndicies[4];
	  
	  int redSum = 0;
	  int greenSum = 0;
	  int blueSum = 0;
	  
	  for(int i = heightStart; i <= heightEnd; i++){
		  for(int j = widthStart; j <= widthEnd; j++){
			  redSum += image[y+i][x+j].getRed();
			  greenSum += image[y+i][x+j].getGreen();
			  blueSum += image[y+i][x+j].getBlue();
		  }
	  }
	  
	  short blurRed = (short) (redSum/numNeighbors);
	  short blurGreen = (short) (greenSum/numNeighbors);
	  short blurBlue = (short) (blueSum/numNeighbors);
	  blurImage.setPixel(x, y, blurRed, blurGreen, blurBlue);
  }
  
  
  /**
   * posType() returns an char based on whether a position is:
   * top left (origin): '0'
   * bottom left: '1'
   * bottom right: '2'
   * top right: '3'
   * top edge: 'u'
   * left edge: 'l'
   * bottom edge: 'd'
   * right edge: 'r'
   * any other (ie middle): 'm'
   * 
   * @param x is the width index
   * @param y is the height index
   * 
   */
  public char positionType(int x, int y){
	  char posType = 'z';
	  
	  if(y == 0){
		  if(x == 0){
			  posType = '0';
		  }else if(x == width-1){
			  posType = '3';
		  }else{
			  posType = 't';  
		  }
		  
	  }else if(x == 0){
		  if(y == height-1){
			  posType = '1'; 
		  }else{
			  posType = 'l';
		  }
		  
	  }else if(y == height-1){
		  if(x == width-1){
			  posType = '2';
		  }else{
			  posType = 'd';
		  }
		  
	  }else if(x == width-1){
		  posType = 'r';
	  }else{
		  posType = 'm';
	  }
	  return posType;
  }
  
  /**
   * burPix() blurs a pixel based on it's neighbors
   * 
   * @param x position of the old pixel in "this"
   * @param y position of the old pixel in "this"
   * @param blurImage the PixImage to hold blurred Pixel
   */
  
  public int[] neighborLoopIndecies(char posType){
	  
	  int[] loopIndicies;
	  int widthStart = 0;
	  int widthEnd = 0;
	  int heightStart = 0;
	  int heightEnd = 0;
	  int numNeighbors = 0;
	  
	  switch(posType){
	  	case 'm':
	  		numNeighbors = 9;
	  		widthStart = -1;
	  		widthEnd = 1;
	  		heightStart = -1;
	  		heightEnd = 1;
	  		break;
	  	case 't':
	  		numNeighbors = 6;
	  		widthStart = -1;
	  		widthEnd = 1;
	  		heightStart = 0;
	  		heightEnd = 1;
	  		break;
	  	case 'd':
	  		numNeighbors = 6;
	  		widthStart = -1;
	  		widthEnd = 1;
	  		heightStart = -1;
	  		heightEnd = 0;
	  		break;
	  	case 'l':
	  		numNeighbors = 6;
	  		widthStart = 0;
	  		widthEnd = 1;
	  		heightStart = -1;
	  		heightEnd = 1;
	  		break;
	  	case 'r':
	  		numNeighbors = 6;
	  		widthStart = -1;
	  		widthEnd = 0;
	  		heightStart = -1;
	  		heightEnd = 1;
	  		break;
	  	case '0':
	  		numNeighbors = 4;
	  		widthStart = 0;
	  		widthEnd = 1;
	  		heightStart = 0;
	  		heightEnd = 1;
	  		break;
	  	case '1':
	  		numNeighbors = 4;
	  		widthStart = 0;
	  		widthEnd = 1;
	  		heightStart = -1;
	  		heightEnd = 0;
	  		break;
	  	case '2':
	  		numNeighbors = 4;
	  		widthStart = -1;
	  		widthEnd = 0;
	  		heightStart =-1;
	  		heightEnd = 0;
	  		break;
	  	case '3':
	  		numNeighbors = 4;
	  		widthStart = -1;
	  		widthEnd = 0;
	  		heightStart = 0;
	  		heightEnd = 1;
	  		break;
	  	default:
	  		break;
	  }
	  
	  loopIndicies = new int[]{widthStart, widthEnd,
		  					  heightStart, heightEnd,
		  				      numNeighbors};
	  return loopIndicies;
  }
  
  

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
	  
	PixImage sobelImage = new PixImage(this.width, this.height);
	
	for(int i = 0; i < height; i++){
		for(int j = 0; j < width; j++){
			sobelPix(j, i, sobelImage);
		}
	}
	  
    return sobelImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }
  
  /**
   * sobelPix() takes the pixel at x,y and assigns it to the same pixel
   * in sobelImage
   * 
   * @param x width index of pixel
   * @param y height indel of pixel
   * @param sobelImage the PixImage to hold the sobelImage
   */
  
  public void sobelPix(int x, int y, PixImage sobelImage){

	  char posType = positionType(x,y);
	  int[] loopIndicies = neighborLoopIndecies(posType);
	  PixImage sobelPix = new PixImage(3,3);
	  
	  int widthStart = loopIndicies[0];
	  int widthEnd = loopIndicies[1];
	  int heightStart = loopIndicies[2];
	  int heightEnd = loopIndicies[3];
	  
	  int sobelXStart = 1;
	  int sobelYStart = 1;
	  short grayScale = 0;
	  
	  for(int i = heightStart; i <= heightEnd; i++){
		  for(int j = widthStart; j <= widthEnd; j++){
			  sobelPix.image[sobelYStart+i][sobelXStart+j] = image[y+i][x+j];
		  }
	  }
	  sobelPix.reflectMissingPix(posType);
	  grayScale = PixImage.mag2gray(sobelPix.energy());
	  sobelImage.setPixel(x, y, grayScale, grayScale, grayScale);
	  
  }
  
  public void reflectMissingPix(char posType){
	  
	  switch(posType){
	  	case 'm':
	  		break;
	  	case 't':
	  		reflectTop();
	  		break;
	  	case 'd':
	  		reflectDown();
	  		break;
	  	case 'l':
	  		reflectLeft();
	  		break;
	  	case 'r':
	  		reflectRight();
	  		break;
	  	case '0':
	  		reflectTopLeft();
	  		break;
	  	case '1':
	  		reflectDownLeft();
	  		break;
	  	case '2':
	  		reflectDownRight();
	  		break;
	  	case '3':
	  		reflectTopRight();
	  		break;
	  	default:
	  		break;
	  }
  }
  
  public void reflectTopLeft(){
	  image[0][0] = image[1][1];
	  image[0][1] = image[1][1];
	  image[0][2] = image[1][2];
	  image[1][0] = image[1][1];
	  image[2][0] = image[2][1];
  }
  public void reflectDownLeft(){
	  image[0][0] = image[0][1];
	  image[1][0] = image[1][1];
	  image[2][0] = image[1][1];
	  image[2][1] = image[1][1];
	  image[2][2] = image[1][2];
  }
  public void reflectDownRight(){
	  image[0][2] = image[0][1];
	  image[1][2] = image[1][1];
	  image[2][2] = image[1][1];
	  image[2][1] = image[1][1];
	  image[2][0] = image[1][0];
  }
  public void reflectTopRight(){
	  image[0][0] = image[1][0];
	  image[0][1] = image[1][1];
	  image[0][2] = image[1][1];
	  image[1][2] = image[1][1];
	  image[2][2] = image[2][1];
  }
  public void reflectTop(){
	  image[0][0] = image[1][0];
	  image[0][1] = image[1][1];
	  image[0][2] = image[1][2];
  }
  public void reflectLeft(){
	  image[0][0] = image[0][1];
	  image[1][0] = image[1][1];
	  image[2][0] = image[2][1];
  }
  public void reflectDown(){
	  image[2][0] = image[1][0];
	  image[2][1] = image[1][1];
	  image[2][2] = image[1][2];
  }
  public void reflectRight(){
	  image[0][2] = image[0][1];
	  image[1][2] = image[1][1];
	  image[2][2] = image[2][1];
  }
  
  public long energy(){
	  long redEnergy = 0;
	  long greenEnergy = 0;
	  long blueEnergy = 0;
	  
	  redEnergy = (long) (Math.pow(redgx(), 2) + Math.pow(redgy(), 2));
	  greenEnergy = (long) (Math.pow(greengx(), 2) + Math.pow(greengy(), 2));
	  blueEnergy = (long) (Math.pow(bluegx(), 2) + Math.pow(bluegy(), 2));
	  
	  return redEnergy + greenEnergy + blueEnergy;
  }
  
  /**
   * redGX() returns the red gradient x component of the 3x3
   * PixImage it is called on
   * 
   * @return gx the gradient value
   */
  public int redgx(){
	  int gx = 0;
	  for(int i = 0; i < width; i++){
		  for(int j = 0; j < height; j++){
			  gx += CONV_MAT_GX[j][i]*image[j][i].getRed();
		  }
	  }
	  return gx;
  }
  public int redgy(){
	  int gy = 0;
	  for(int i = 0; i < width; i++){
		  for(int j = 0; j < height; j++){
			  gy += CONV_MAT_GY[j][i]*image[j][i].getRed();
		  }
	  }
	  return gy;
  }
  public int greengx(){
	  int gx = 0;
	  for(int i = 0; i < width; i++){
		  for(int j = 0; j < height; j++){
			  gx += CONV_MAT_GX[j][i]*image[j][i].getGreen();
		  }
	  }
	  return gx;
  }
  public int greengy(){
	  int gy = 0;
	  for(int i = 0; i < width; i++){
		  for(int j = 0; j < height; j++){
			  gy += CONV_MAT_GY[j][i]*image[j][i].getGreen();
		  }
	  }
	  return gy;
  }
  public int bluegx(){
	  int gx = 0;
	  for(int i = 0; i < width; i++){
		  for(int j = 0; j < height; j++){
			  gx += CONV_MAT_GX[j][i]*image[j][i].getBlue();
		  }
	  }
	  return gx;
  }
  public int bluegy(){
	  int gy = 0;
	  for(int i = 0; i < width; i++){
		  for(int j = 0; j < height; j++){
			  gy += CONV_MAT_GY[j][i]*image[j][i].getBlue();
		  }
	  }
	  return gy;
  }
  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());

    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
