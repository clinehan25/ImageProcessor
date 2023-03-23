package imageprocess;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * Image process that greyscales based on highest rgb value.
 */
public class ProcessValue implements ImageProcessor {
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessValue(Image img) {
    this.img = img;
  }

  @Override
  public Image execute() {
    Image img2 = new ImageImpl(img.getValue(), img.getWidth(), img.getHeight(), img.getPixels());
    for (int i = 0; i < img2.getHeight(); i++) {
      for (int j = 0; j < img2.getWidth(); j++) {
        int r = img2.getPixels().get(i).get(j).getRed();
        int g = img2.getPixels().get(i).get(j).getGreen();
        int b = img2.getPixels().get(i).get(j).getBlue();

        int greyChannel = Math.max(r, Math.max(g, b));
        img2.getPixels().get(i).set(j, new Pixel(greyChannel, greyChannel, greyChannel));
      }
    }
    return img2;
  }
}
