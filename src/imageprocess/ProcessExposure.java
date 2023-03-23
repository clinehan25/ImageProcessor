package imageprocess;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * A process class that is used to darken/brighten the provided image.
 */
public class ProcessExposure implements ImageProcessor {

  private final int amount;
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessExposure(int amount, Image img) {
    this.amount = amount;
    this.img = img;
  }

  @Override
  public Image execute() {
    Image img2 = new ImageImpl(this.img.getValue(),
            this.img.getWidth(), this.img.getHeight(), this.img.getPixels());
    int r = 0;
    int g = 0;
    int b = 0;
    for (int i = 0; i < img2.getHeight(); i++) {
      for (int j = 0; j < img2.getWidth(); j++) {
        r = Math.max(0, Math.min(255, img2.getPixels().get(i).get(j).getRed() + this.amount));
        g = Math.max(0, Math.min(255, img2.getPixels().get(i).get(j).getGreen() + this.amount));
        b = Math.max(0, Math.min(255, img2.getPixels().get(i).get(j).getBlue() + this.amount));

        img2.getPixels().get(i).set(j, new Pixel(r, g, b));
      }
    }
    return img2;
  }
}