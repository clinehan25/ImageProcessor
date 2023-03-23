package imageprocess;

import java.util.ArrayList;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * Implements a image process that blurs the image using a filter.
 */
public class ProcessBlur implements ImageProcessor {
  private final Image img;
  private final double[][] kernel;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessBlur(Image img) {
    this.img = img;
    this.kernel =
            new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
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
      for (int j = 0; j < kernel[0].length; j++) {
        r += kernel[i][j] * img.get(row + i - 1).get(col + j - 1).getRed();
        g += kernel[i][j] * img.get(row + i - 1).get(col + j - 1).getGreen();
        b += kernel[i][j] * img.get(row + i - 1).get(col + j - 1).getBlue();
      }
    }
    return new Pixel(Math.min(255, r), Math.min(255, g), Math.min(255, b));
  }
}


