/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes
 *  a PixImage object.  Descriptions of the methods you must implement appear
 *  below.  They include constructors of the form
 *
 *      public RunLengthEncoding(int width, int height);
 *      public RunLengthEncoding(int width, int height, int[] red, int[] green,
 *                               int[] blue, int[] runLengths) {
 *      public RunLengthEncoding(PixImage image) {
 *
 *  that create a run-length encoding of a PixImage having the specified width
 *  and height.
 *
 *  The first constructor creates a run-length encoding of a PixImage in which
 *  every pixel is black.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts a PixImage object into a run-length encoding of that image.
 *
 *  See the README file accompanying this project for additional details.
 */

import java.util.Iterator;

public class RunLengthEncoding implements Iterable {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
	
	private DList runs;
	private int width;
	private int height;

  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with two parameters) constructs a run-length
   *  encoding of a black PixImage of the specified width and height, in which
   *  every pixel has red, green, and blue intensities of zero.
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   */

  public RunLengthEncoding(int width, int height) {
    // Your solution here.
	  if(width > 0 && height > 0){
		  this.width = width;
		  this.height = height;  
	  }else{
		  this.width = 1;
		  this.height = 1;
	  }
	  runs = new DList();
	  runs.insertFront(new Run(width*height, 0, 0, 0));
  }

  /**
   *  RunLengthEncoding() (with six parameters) constructs a run-length
   *  encoding of a PixImage of the specified width and height.  The runs of
   *  the run-length encoding are taken from four input arrays of equal length.
   *  Run i has length runLengths[i] and RGB intensities red[i], green[i], and
   *  blue[i].
   *
   *  @param width the width of the image.
   *  @param height the height of the image.
   *  @param red is an array that specifies the red intensity of each run.
   *  @param green is an array that specifies the green intensity of each run.
   *  @param blue is an array that specifies the blue intensity of each run.
   *  @param runLengths is an array that specifies the length of each run.
   *
   *  NOTE:  All four input arrays should have the same length (not zero).
   *  All pixel intensities in the first three arrays should be in the range
   *  0...255.  The sum of all the elements of the runLengths array should be
   *  width * height.  (Feel free to quit with an error message if any of these
   *  conditions are not met--though we won't be testing that.)
   */

  public RunLengthEncoding(int width, int height, int[] red, int[] green,
                           int[] blue, int[] runLengths) {
    // Your solution here.
	boolean validParameters = false;
	if(validParameters(width, height, red, green, blue, runLengths)){
		this.width = width;
		this.height = height;
		runs = new DList();
		
		for(int i = 0; i < runLengths.length; i++){
			runs.insertBack(new Run(runLengths[i], red[i], green[i], blue[i]));
		}
	}else{
		System.out.println("invalid parameters for 6 parameter RunLengthEncoding");
	}
  }
  /**
   * check that the parameters are valid for RunLenghEncoding constructor
   * @param width
   * @param height
   * @param red
   * @param green
   * @param blue
   * @param runLengths
   * @return
   */
  public boolean validParameters(int width, int height, int[] red, int[] green,
          						 int[] blue, int[] runLengths){
	  boolean validParameters = true;
	  if(!(runLengths.length == red.length && 
		   runLengths.length == green.length &&
		   runLengths.length == blue.length)){
		  validParameters = false;
	  }else if(!(runLengths.length > 0)){
		  validParameters = false;
	  }else if(!(width > 0 && height >0)){
		  validParameters = false;
	  }
	  if(validParameters){
		  int numPix = 0;
		  for(int i  = 0; i < runLengths.length; i++){
			 numPix += runLengths[i];
		  }
		  if(numPix != width*height){
			  validParameters = false;
		  }
	  }
	  if(validParameters){
		  int[][] colors = new int[][] {red, green, blue};
		  for(int i = 0; i < 3; i++){
			  for(int j = 0; j < red.length; j++){
				  if(!(colors[i][j] >= 0)){
					  validParameters = false;
				  }
			  }
		  }
	  }
	  return validParameters;
  }

  /**
   *  getWidth() returns the width of the image that this run-length encoding
   *  represents.
   *
   *  @return the width of the image that this run-length encoding represents.
   */

  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   *  getHeight() returns the height of the image that this run-length encoding
   *  represents.
   *
   *  @return the height of the image that this run-length encoding represents.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   *  iterator() returns a newly created RunIterator that can iterate through
   *  the runs of this RunLengthEncoding.
   *
   *  @return a newly created RunIterator object set to the first run of this
   *  RunLengthEncoding.
   */
  public RunIterator iterator() {
    // Replace the following line with your solution.
    return new RunIterator(runs);
    // You'll want to construct a new RunIterator, but first you'll need to
    // write a constructor in the RunIterator class.
  }

  /**
   *  toPixImage() converts a run-length encoding of an image into a PixImage
   *  object.
   *
   *  @return the PixImage that this RunLengthEncoding encodes.
   */
  public PixImage toPixImage() {
    // Replace the following line with your solution.
	PixImage image = new PixImage(width, height);
	RunIterator itr = iterator();
	int[] currRun;
	int wIndex = 0;
	int hIndex = 0;
	
	while(itr.hasNext()){
		currRun = itr.next();
		for(int i = 0; i < currRun[0]; i++){
			if(wIndex >= width){
				wIndex = 0;
				hIndex++;
			}
			image.setPixel(wIndex, hIndex, 
						  (short)currRun[1], (short)currRun[2], 
						  (short)currRun[3]);
			wIndex++;
		}
	}
    return image;
  }

  /**
   *  toString() returns a String representation of this RunLengthEncoding.
   *
   *  This method isn't required, but it should be very useful to you when
   *  you're debugging your code.  It's up to you how you represent
   *  a RunLengthEncoding as a String.
   *
   *  @return a String representation of this RunLengthEncoding.
   */
  public String toString() {
    // Replace the following line with your solution.
	String rleString = new String();
	rleString += "RunLengthEncoding\n\t";
	RunIterator itr = iterator();
	int[] currRun;
	int i = 0;
	int numRunsPerLine = 3;
	while(itr.hasNext()){
		if(i >= numRunsPerLine){
			rleString += "\n\t";
			i = 0;
		}
		currRun = itr.next();
		rleString += "(" + currRun[0] + ", " + currRun[1] +
					 ", " + currRun[2] + ", " + currRun[3] + "), "; 
		i++;
	}
    return rleString;
  }


  /**
   *  The following methods are required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of a specified PixImage.
   * 
   *  Note that you must encode the image in row-major format, i.e., the second
   *  pixel should be (1, 0) and not (0, 1).
   *
   *  @param image is the PixImage to run-length encode.
   */
  public RunLengthEncoding(PixImage image) {
    // Your solution here, but you should probably leave the following line
    // at the end.
	//check();
	width = image.getWidth();
	height = image.getHeight();
	runs = new DList();
	int[] currPix;
	int[] prevPix = getPixRGB(image, 0, 0);
	Run currRun = new Run(1, prevPix[0], prevPix[1], prevPix[2]);
	int wIndex = 1;
	int hIndex = 0;
	
	for(int i = 1; i < image.getWidth()*image.getHeight(); i++){
		if(wIndex >= image.getWidth()){
			wIndex = 0;
			hIndex++;
		}
		currPix = getPixRGB(image, wIndex, hIndex);
		if(comparePix(prevPix, currPix)){
			currRun.incNumPix();
		}else{
			runs.insertBack(currRun);
			prevPix = currPix;
			currRun = new Run(1, prevPix[0], prevPix[1], prevPix[2]);
		}
		wIndex++;
	}
	
	runs.insertBack(currRun);
	check();
  }
  
  public int[] getPixRGB(PixImage image, int x, int y){
	  return new int[] {image.getRed(x,y),image.getGreen(x,y), image.getBlue(x,y)};
  }
  
  public boolean comparePix(int[] prevPix, int[] currPix){
	  boolean equal = true;
	  for(int i = 0; i < prevPix.length; i++){
		  if(prevPix[i] != currPix[i]){
			  equal = false;
		  }
	  }
	  return equal;
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same RGB intensities, or if the sum of
   *  all run lengths does not equal the number of pixels in the image.
   */
  public void check() {
    // Your solution here.
	  String initerrmsg = "\n***Bad RunLengthEncoding detected:";
	  String errmsg = initerrmsg;
	  int numPix = 0;
	  RunIterator itr = iterator();
	  int[] currRun = null;
	  while(itr.hasNext()){
		  currRun = itr.next();
		  numPix += currRun[0];
	  }
	  if(numPix > width*height){
		  errmsg += "\n\ttoo many pixels to fil PixImage";
	  }else if(numPix < width*height){
		  errmsg += "\n\ttoo few pixels to fil PixImage";
	  }
	  
	  int[] nextRun;
	  itr = iterator();
	  if(itr.hasNext()){
		  currRun = itr.next();
	  }
	  while(itr.hasNext()){
		  nextRun = itr.next();
		  if(currRun[1] == nextRun[1] &&
			 currRun[2] == nextRun[2] &&
			 currRun[3] == nextRun[3]){
			 errmsg += "\n\ttwo consecutive runs have the same color values";
		  }else{
			  currRun = nextRun;
		  }
	
	  }
	  itr = iterator();
	  while(itr.hasNext()){
		  currRun = itr.next();
		  if(currRun[0] <= 0){
			  errmsg += "\n\ta run has a length less then 1";
		  }
	  }
	  
	  if(!errmsg.equals(initerrmsg)){
		  System.out.println(errmsg + "***\n");
	  }
  }


  /**
   *  The following method is required for Part IV.
   */

  /**
   *  setPixel() modifies this run-length encoding so that the specified color
   *  is stored at the given (x, y) coordinates.  The old pixel value at that
   *  coordinate should be overwritten and all others should remain the same.
   *  The updated run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs with exactly the same RGB color.
   *
   *  @param x the x-coordinate of the pixel to modify.
   *  @param y the y-coordinate of the pixel to modify.
   *  @param red the new red intensity to store at coordinate (x, y).
   *  @param green the new green intensity to store at coordinate (x, y).
   *  @param blue the new blue intensity to store at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
	  DListNode currRunNode = runs.front();
	  DListNode prevRunNode = runs.front();//x,y will be in prevRunNode 
	  Run theRun;
	  int rlePos = y*width + x + 1;//Pixel position in the RunLengthEncoding
	  int runPos = 0;//Pixel position within the Run
	  int rleSum = 0;
	  if(x > width-1 || y > height-1){
		  System.out.println("Invalid corridinates to setPixel()");
	  }else{
		  while(rleSum < rlePos){
			  rleSum += ((Run)currRunNode.item).getNumPix();
			  prevRunNode = currRunNode;
			  currRunNode = runs.next(currRunNode);
		  }
		  
		  theRun = (Run)prevRunNode.item;
		  runPos = theRun.getNumPix() + rlePos - rleSum;
		  
		  if(!rgbEqual(theRun, red, green, blue)){
			  if(theRun.getNumPix() == 1){//special case if run is length 1
				  if(runs.isFront(prevRunNode)){
					  if(rgbEqual((Run)prevRunNode.next.item, red, green, blue)){
						  ((Run)prevRunNode.next.item).incNumPix();
						  runs.remove(prevRunNode);
					  }else{
						  theRun.setRun(1, red, green, blue);
					  }
				  }else if(runs.isBack(prevRunNode)){
					  if(rgbEqual((Run)prevRunNode.prev.item, red, green, blue)){
						  ((Run)prevRunNode.prev.item).incNumPix();
						  runs.remove(prevRunNode);
					  }else{
						  theRun.setRun(1, red, green, blue);
					  }
				  }else{
					  if(rgbEqual((Run)prevRunNode.prev.item, red, green, blue)){
						  if(rgbEqual((Run)prevRunNode.next.item, red, green, blue)){
							  ((Run)prevRunNode.prev.item).incNumPix();
							  ((Run)prevRunNode.prev.item).incNumPix(
									   ((Run)runs.next(prevRunNode).item).getNumPix());
							  runs.remove(runs.next(prevRunNode));
							  runs.remove(prevRunNode);
						  }else{
							  ((Run)prevRunNode.prev.item).incNumPix();
							  runs.remove(prevRunNode);
						  }
					  }else if(rgbEqual((Run)prevRunNode.next.item, red, green, blue)){
						  ((Run)prevRunNode.next.item).incNumPix();
						  runs.remove(prevRunNode);
					  }else{
						  theRun.setRun(1, red, green, blue);
					  }
				  }
			  }
			  //3 possibilities: pixel is at the start, middle, or end of Run
			  
			  else if(runPos > 1 && runPos < theRun.getNumPix()){//middle
				  runs.insertBefore(new Run(runPos-1, theRun), prevRunNode);
				  runs.insertAfter(new Run(theRun.getNumPix() - runPos, theRun), 
						  prevRunNode);
				  theRun.setRun(1, red, green, blue);
			  }else if(runPos == 1){//start of run
					  if(runs.isFront(prevRunNode)){
						  runs.insertBefore(new Run(1, red, green, blue), prevRunNode);
						  ((Run)prevRunNode.item).decNumPix();
					  }else{
						  if(rgbEqual((Run)prevRunNode.prev.item, red, green, blue)){
							  ((Run)prevRunNode.prev.item).incNumPix();
							  ((Run)prevRunNode.item).decNumPix();
						  }else{
							  runs.insertBefore(new Run(1, red, green, blue), prevRunNode);
							  ((Run)prevRunNode.item).decNumPix();
						  }
					  }
			  }else if(runPos == theRun.getNumPix()){//end of run
					  if(runs.isBack(prevRunNode)){
						  runs.insertAfter(new Run(1, red, green, blue), prevRunNode);
						  ((Run)prevRunNode.item).decNumPix();
					  }else{
						  if(rgbEqual((Run)prevRunNode.next.item, red, green, blue)){
							  ((Run)prevRunNode.next.item).incNumPix();
							  ((Run)prevRunNode.item).decNumPix();
						  }else{
							  runs.insertAfter(new Run(1, red, green, blue), prevRunNode);
							  ((Run)prevRunNode.item).decNumPix();
						  }
					  }
			  }else{
				  System.out.println("***Error in setPixel()***");
			  }
		  }//else do nothing
	  }
    check();
  }
  
  public boolean rgbEqual(Run run, short red, short green, short blue){
	  return run.getRed() == red && run.getGreen() == green && 
			 run.getBlue() == blue;
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
   * setAndCheckRLE() sets the given coordinate in the given run-length
   * encoding to the given value and then checks whether the resulting
   * run-length encoding is correct.
   *
   * @param rle the run-length encoding to modify.
   * @param x the x-coordinate to set.
   * @param y the y-coordinate to set.
   * @param intensity the grayscale intensity to assign to pixel (x, y).
   */
  private static void setAndCheckRLE(RunLengthEncoding rle,
                                     int x, int y, int intensity) {
    rle.setPixel(x, y,
                 (short) intensity, (short) intensity, (short) intensity);
    rle.check();
  }

  /**
   * main() runs a series of tests of the run-length encoding code.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
	  
	  //added
	  RunLengthEncoding rlex;
	  PixImage imagex = new PixImage(10,10);
	  imagex.setPixel(4,4, (short)0,(short)0,(short)0);
	  rlex = new RunLengthEncoding(imagex);
	  System.out.println(rlex);
	  //rlex.check();
	  System.out.println("end added tests\n");
	  //end added
	  
    PixImage image1 = array2PixImage(new int[][] { { 0, 3, 6 },
                                                   { 1, 4, 7 },
                                                   { 2, 5, 8 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x3 image.  Input image:");
    System.out.print(image1);
    RunLengthEncoding rle1 = new RunLengthEncoding(image1);
    rle1.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle1.getWidth() == 3 && rle1.getHeight() == 3,
           "RLE1 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(image1.equals(rle1.toPixImage()),
           "image1 -> RLE1 -> image does not reconstruct the original image");
    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 42);
    image1.setPixel(0, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           /*
                       array2PixImage(new int[][] { { 42, 3, 6 },
                                                    { 1, 4, 7 },
                                                    { 2, 5, 8 } })),
           */
           "Setting RLE1[0][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 0, 42);
    image1.setPixel(1, 0, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][0] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 1, 2);
    image1.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 0, 0, 0);
    image1.setPixel(0, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[0][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 7);
    image1.setPixel(2, 2, (short) 7, (short) 7, (short) 7);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 7 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 2, 2, 42);
    image1.setPixel(2, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[2][2] = 42 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle1, 1, 2, 42);
    image1.setPixel(1, 2, (short) 42, (short) 42, (short) 42);
    doTest(rle1.toPixImage().equals(image1),
           "Setting RLE1[1][2] = 42 fails.");


    PixImage image2 = array2PixImage(new int[][] { { 2, 3, 5 },
                                                   { 2, 4, 5 },
                                                   { 3, 4, 6 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on another 3x3 image.  Input image:");
    System.out.print(image2);
    RunLengthEncoding rle2 = new RunLengthEncoding(image2);
    rle2.check();
    System.out.println("Testing getWidth/getHeight on a 3x3 encoding.");
    doTest(rle2.getWidth() == 3 && rle2.getHeight() == 3,
           "RLE2 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x3 encoding.");
    doTest(rle2.toPixImage().equals(image2),
           "image2 -> RLE2 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 0, 1, 2);
    image2.setPixel(0, 1, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[0][1] = 2 fails.");

    System.out.println("Testing setPixel() on a 3x3 encoding.");
    setAndCheckRLE(rle2, 2, 0, 2);
    image2.setPixel(2, 0, (short) 2, (short) 2, (short) 2);
    doTest(rle2.toPixImage().equals(image2),
           "Setting RLE2[2][0] = 2 fails.");


    PixImage image3 = array2PixImage(new int[][] { { 0, 5 },
                                                   { 1, 6 },
                                                   { 2, 7 },
                                                   { 3, 8 },
                                                   { 4, 9 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 5x2 image.  Input image:");
    System.out.print(image3);
    RunLengthEncoding rle3 = new RunLengthEncoding(image3);
    rle3.check();
    System.out.println("Testing getWidth/getHeight on a 5x2 encoding.");
    doTest(rle3.getWidth() == 5 && rle3.getHeight() == 2,
           "RLE3 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 5x2 encoding.");
    doTest(rle3.toPixImage().equals(image3),
           "image3 -> RLE3 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 4, 0, 6);
    image3.setPixel(4, 0, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[4][0] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 1, 6);
    image3.setPixel(0, 1, (short) 6, (short) 6, (short) 6);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][1] = 6 fails.");

    System.out.println("Testing setPixel() on a 5x2 encoding.");
    setAndCheckRLE(rle3, 0, 0, 1);
    image3.setPixel(0, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle3.toPixImage().equals(image3),
           "Setting RLE3[0][0] = 1 fails.");


    PixImage image4 = array2PixImage(new int[][] { { 0, 3 },
                                                   { 1, 4 },
                                                   { 2, 5 } });

    System.out.println("Testing one-parameter RunLengthEncoding constuctor " +
                       "on a 3x2 image.  Input image:");
    System.out.print(image4);
    RunLengthEncoding rle4 = new RunLengthEncoding(image4);
    rle4.check();
    System.out.println("Testing getWidth/getHeight on a 3x2 encoding.");
    doTest(rle4.getWidth() == 3 && rle4.getHeight() == 2,
           "RLE4 has wrong dimensions");

    System.out.println("Testing toPixImage() on a 3x2 encoding.");
    doTest(rle4.toPixImage().equals(image4),
           "image4 -> RLE4 -> image does not reconstruct the original image");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 2, 0, 0);
    image4.setPixel(2, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[2][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 0);
    image4.setPixel(1, 0, (short) 0, (short) 0, (short) 0);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 0 fails.");

    System.out.println("Testing setPixel() on a 3x2 encoding.");
    setAndCheckRLE(rle4, 1, 0, 1);
    image4.setPixel(1, 0, (short) 1, (short) 1, (short) 1);
    doTest(rle4.toPixImage().equals(image4),
           "Setting RLE4[1][0] = 1 fails.");
    
    int[] rr = {0, 1, 2, 3, 4, 5};
    int[] rg = {20, 18, 16, 14, 12, 10};
    int[] rb = {42, 42, 42, 137, 137, 137};
    int[] rl = {3, 2, 5, 1, 1, 4};
    System.out.println("\nSetting various pixels in a runLengthEncoding");
    System.out.println("Orginal:");
    RunLengthEncoding rley = new RunLengthEncoding(4, 4, rr, rg, rb, rl);
    System.out.println(rley);
    rley.setPixel(3, 2, (short) 5, (short) 10, (short) 136);
    System.out.println("Setting:");
    System.out.println(rley);
    rley.setPixel(0, 3, (short) 5, (short) 10, (short) 136);
    System.out.println("Setting:");
    System.out.println(rley);
    rley.setPixel(1, 0, (short) 1, (short) 18, (short) 42);
    System.out.println("Setting:");
    System.out.println(rley);
    rley.setPixel(2, 0, (short) 1, (short) 18, (short) 42);
    System.out.println("Setting:");
    System.out.println(rley);
  }
}
