package img;

import java.util.ArrayList;

/**
 * Class that is an implementation of Image interface, represents an Image.
 */
public class ImageImpl implements Image {
  private final int value;
  private final int width;
  private final int height;
  private final ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Main constructor for ImageImpl representing an image object with 4 parameters.
   *
   * @param value  representing the maximum value of an images pixels.
   * @param width  representing the width of the image in pixels.
   * @param height representing the height of the image in pixels.
   * @param pixels representing a 2D ArrayList of pixels in the image.
   * @throws IllegalArgumentException if provided fields are negative/null or OOB for pixels.
   */
  public ImageImpl(int value, int width, int height, ArrayList<ArrayList<Pixel>> pixels)
          throws IllegalArgumentException {
    if (value <= 1 || pixels == null || width != pixels.get(0).size() || height != pixels.size()) {
      throw new IllegalArgumentException("Parameters (ImageImpl) are invalid");
    }
    this.value = value;
    this.width = width;
    this.height = height;
    this.pixels = pixels;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public ArrayList<ArrayList<Pixel>> getPixels() {
    return this.pixels;
  }

}
