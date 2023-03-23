package img;

import java.util.ArrayList;

/**
 * Interface representing a PPM Image.
 */
public interface Image {
  /**
   * Method that returns the maximum value of this image's pixels.
   *
   * @return an integer representing maximum value.
   */
  int getValue();

  /**
   * Method that returns the width of this image's pixels.
   *
   * @return an integer representing image width.
   */
  int getWidth();

  /**
   * Method that returns the height of this image's pixels.
   *
   * @return an integer representing image height.
   */
  int getHeight();

  /**
   * Method that returns the 2D ArrayList of Pixels of this image.
   *
   * @return a 2D ArrayList representing each pixel contained in the image.
   */
  ArrayList<ArrayList<Pixel>> getPixels();
}
