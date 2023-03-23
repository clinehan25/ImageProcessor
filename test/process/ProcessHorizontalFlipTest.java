package process;

import org.junit.Test;

import java.util.ArrayList;

import imageprocess.ProcessHorizontalFlip;
import img.Image;
import img.ImageImpl;
import img.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test to show that the horizontal flip process accurately displays a "Properly" flipped image.
 */
public class ProcessHorizontalFlipTest {

  @Test
  public void executeTest() {
    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      ArrayList<Pixel> pix = new ArrayList<>();
      for (int j = 0; j < 2; j++) {
        pix.add(new Pixel(i, j, i + j));
      }
      pixels.add(pix);
    }
    Image img = new ImageImpl(4, 2, 2, pixels);
    ProcessHorizontalFlip hFlip = new ProcessHorizontalFlip(img);
    Image img2 = hFlip.execute();
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(img.getPixels().get(i).get(j).getRed(),
                img2.getPixels().get(img2.getWidth() - i - 1).get(j).getRed());
        assertEquals(img.getPixels().get(i).get(j).getGreen(),
                img2.getPixels().get(img2.getWidth() - i - 1).get(j).getGreen());
        assertEquals(img.getPixels().get(i).get(j).getBlue(),
                img2.getPixels().get(img2.getWidth() - i - 1).get(j).getBlue());
      }
    }
  }
}
