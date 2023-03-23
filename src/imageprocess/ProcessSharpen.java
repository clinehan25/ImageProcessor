package imageprocess;

import java.util.ArrayList;

import img.Image;
import img.ImageImpl;
import img.Pixel;

/**
 * An image processor that applies a sharpen filter.
 */
public class ProcessSharpen implements ImageProcessor {
  private final Image img;
  private final double[][] kernel;

  /**
   * Constructor: Initializes an operation to execute.
   */
  public ProcessSharpen(Image img) {
    this.img = img;
    this.kernel = new double[][]{
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }

  @Override
  public Image execute() {
    Image img2 = new ImageImpl(this.img.getValue(),
            this.img.getWidth(), this.img.getHeight(), this.img.getPixels());
    for (int i = 3; i < img2.getHeight() - 3; i++) {
      for (int j = 3; j < img2.getWidth() - 3; j++) {
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
    return new Pixel(Math.max(0, Math.min(255, r)),
            Math.max(0, Math.min(255, g)), Math.max(0, Math.min(255, b)));
  }
}
