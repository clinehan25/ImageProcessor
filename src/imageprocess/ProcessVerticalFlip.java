package imageprocess;

import java.util.ArrayList;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * A process class that is used to vertically flip the provided image.
 */
public class ProcessVerticalFlip implements ImageProcessor {
  private final Image img;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessVerticalFlip(Image img) {
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

    // initializes new Image with the new pixels
    Image img2 = new ImageImpl(img.getValue(), img.getWidth(), img.getHeight(), pixels2);
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int r = img.getPixels().get(i).get(j).getRed();
        int g = img.getPixels().get(i).get(j).getGreen();
        int b = img.getPixels().get(i).get(j).getBlue();

        img2.getPixels().get(img2.getHeight() - i - 1).set(j, new Pixel(r, g, b));
      }
    }
    return img2;
  }
}