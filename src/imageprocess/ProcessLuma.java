package imageprocess;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * Image processor that greyscales based on the rgb value luma.
 */
public class ProcessLuma implements ImageProcessor {
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessLuma(Image img) {
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

        int greyChannel = (int)((0.2126 * r) + (0.7152 * g) + (0.0722 * b));
        img2.getPixels().get(i).set(j, new Pixel(greyChannel, greyChannel, greyChannel));
      }
    }
    return img2;
  }
}
