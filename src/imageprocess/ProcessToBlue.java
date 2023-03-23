package imageprocess;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * Class representing the operation on an image: turn the image into blue greyscale.
 */
public class ProcessToBlue implements ImageProcessor {
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessToBlue(Image img) {
    this.img = img;
  }

  @Override
  public Image execute() {
    Image img2 = new ImageImpl(img.getValue(), img.getWidth(), img.getHeight(), img.getPixels());
    for (int i = 0; i < img2.getHeight(); i++) {
      for (int j = 0; j < img2.getWidth(); j++) {
        int blueChannel = img2.getPixels().get(i).get(j).getBlue();
        img2.getPixels().get(i).set(j, new Pixel(0, 0, blueChannel));
      }
    }
    return img2;
  }
}
