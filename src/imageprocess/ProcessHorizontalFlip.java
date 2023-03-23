package imageprocess;

import java.util.ArrayList;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * A process class that is used to horizontally flip the provided image.
 */
public class ProcessHorizontalFlip implements ImageProcessor {
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessHorizontalFlip(Image img) {
    this.img = img;
  }

  @Override
  public Image execute() {

    // initialize new ArrayList for new Pixels
    ArrayList<ArrayList<Pixel>> pixels2 = new ArrayList<>();
    for (int i = 0; i < img.getPixels().size(); i++) {
      pixels2.add(new ArrayList<Pixel>());
      for (int j = 0; j < img.getPixels().get(0).size(); j++) {
        pixels2.get(i).add(new Pixel(0, 0, 0));
      }
    }

    // Initialize new image w/ new ArrayList
    Image img2 = new ImageImpl(img.getValue(), img.getWidth(), img.getHeight(), pixels2);

    // flip horizontally
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int r = img.getPixels().get(i).get(j).getRed();
        int g = img.getPixels().get(i).get(j).getGreen();
        int b = img.getPixels().get(i).get(j).getBlue();

        img2.getPixels().get(i).set(img2.getWidth() - j - 1, new Pixel(r, g, b));
      }
    }
    return img2;
  }
}

