package imageprocess;

import java.util.ArrayList;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * An image process that applies a sepia filter.
 */
public class ProcessSepia implements ImageProcessor {
  private final Image img;
  private final double[][] kernel;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessSepia(Image img) {
    this.img = img;
    this.kernel =
            new double[][] {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
  }

  @Override
  public Image execute() {
    Image img2 = new ImageImpl(this.img.getValue(),
            this.img.getWidth(), this.img.getHeight(), this.img.getPixels());
    for (int i = 1; i < img2.getHeight() - 1; i++) {
      for (int j = 1; j < img2.getWidth() - 1; j++) {
        img2.getPixels().get(i).set(j,
                filterHelper(this.img.getPixels(), this.kernel, i, j));
      }
    }
    return img2;
  }

  /**
   * Helper for the execute blur method that runs each pixel through the filter.
   *
   * @param img the image in pixels
   * @param kernel the kernel filter
   * @param row the row of the selected pixel
   * @param col the col of the selected pixel
   * @return the new pixel to be added to the new image
   */
  private Pixel filterHelper(ArrayList<ArrayList<Pixel>> img, double[][] kernel, int row, int col) {
    int r = 0;
    int g = 0;
    int b = 0;
    for (int i = 0; i < kernel.length; i++) {

      double sumR = kernel[i][0] * img.get(row).get(col).getRed();
      double sumG = kernel[i][1] * img.get(row).get(col).getGreen();
      double sumB = kernel[i][2] * img.get(row).get(col).getBlue();

      int newValue = Math.min((int) Math.round(sumR + sumB + sumG), 255);
      newValue = Math.max(0, newValue);

      switch (i) {
        case 0:
          r = newValue;
          break;

        case 1:
          g = newValue;
          break;

        case 2:
          b = newValue;
          break;

        default:
          break;
      }
    }
    return new Pixel(r, g, b);
  }
}
