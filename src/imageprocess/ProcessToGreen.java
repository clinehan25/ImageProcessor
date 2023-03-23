package imageprocess;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * Class representing the operation on an image: turn the image into green greyscale.
 */
public class ProcessToGreen implements ImageProcessor {
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessToGreen(Image img) {
    this.img = img;
  }

  @Override
  public Image execute() {
    Image img2 = new ImageImpl(img.getValue(), img.getWidth(), img.getHeight(), img.getPixels());
    for (int i = 0; i < img2.getHeight(); i++) {
      for (int j = 0; j < img2.getWidth(); j++) {
        int greenChannel = img2.getPixels().get(i).get(j).getGreen();
        img2.getPixels().get(i).set(j, new Pixel(0, greenChannel, 0));
      }
    }
    return img2;
  }
}
