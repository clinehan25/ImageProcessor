package img;

/**
 * Class representing a single pixel in (r,g,b) format.
 */
public class Pixel {
  private final int red;
  private final int green;
  private final int blue;

  /**
   * Main Constructor: initializes a pixel with rgb parameters.
   *
   * @param red   representing the red channel for this pixel
   * @param green representing the green channel for this pixel
   * @param blue  representing the blue channel for this pixel
   * @throws IllegalArgumentException if any of the components are negative or more than 255.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if ((red < 0 || red > 255) || (green < 0 || green > 255) || (blue < 0 || blue > 255)) {
      throw new IllegalArgumentException("RGB parameters violate constraints");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Initializes a pixel with one parameter for a greyscale image.
   *
   * @param value One input value for all three channels.
   * @throws IllegalArgumentException if any of the components are negative or more than 255.
   */
  public Pixel(int value) throws IllegalArgumentException {
    this(value, value, value);
  }

  /**
   * Getter for the red channel of this pixel.
   *
   * @return integer value for red.
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Getter for the green channel of this pixel.
   *
   * @return integer value for green.
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Getter for the blue channel of this pixel.
   *
   * @return integer value for blue.
   */
  public int getBlue() {
    return this.blue;
  }
}
