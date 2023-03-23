package process;

import org.junit.Test;

import java.util.ArrayList;

import imageprocess.ProcessVerticalFlip;
import img.Image;
import img.ImageImpl;
import img.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test to show that the vertical flip process accurately displays a "Properly" flipped image.
 */
public class ProcessVerticalFlipTest {

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
    ProcessVerticalFlip vFlip = new ProcessVerticalFlip(img);
    Image img2 = vFlip.execute();
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        assertEquals(img.getPixels().get(i).get(j).getRed(),
                img2.getPixels().get(i).get(img2.getHeight() - j - 1).getRed());
        assertEquals(img.getPixels().get(i).get(j).getGreen(),
                img2.getPixels().get(i).get(img2.getHeight() - j - 1).getGreen());
        assertEquals(img.getPixels().get(i).get(j).getBlue(),
                img2.getPixels().get(i).get(img2.getHeight() - j - 1).getBlue());
      }
    }
  }
}
